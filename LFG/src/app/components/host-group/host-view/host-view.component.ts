import { Component, HostListener, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { interval, Observable, of, startWith, Subscription, switchMap } from 'rxjs';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { PollingRequestsService } from 'src/app/_services/sessions/polling-requests.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-host-view',
  templateUrl: './host-view.component.html',
  styleUrls: ['./host-view.component.css']
})
export class HostViewComponent implements OnInit, OnDestroy {

  currentUser: any;
  stats: any;
  group!: any;

  timeInterval!: Subscription;


  // groupMembers!: any[];

  waitingRoomCheck:boolean = false;

  currentUsers:number = 1;

  constructor(
    private sessionStorage: SessionStorageService,
    private tokenStorage: TokenStorageService,
    private routingAllocator: RoutingAllocatorService,
    private userService: UserService) { }

  @HostListener('unloaded')
  ngOnDestroy(): void {
    this.timeInterval.unsubscribe();
  }

  ngOnInit(): void {
    this.initializeView();
  }

  initializeView():void{
    this.group = this.sessionStorage.getCreatedGroup();
    this.updateMembers(this.group);
    this.stats = this.tokenStorage.getSocials();
    this.group = this.sessionStorage.getCreatedGroup();
  }

  addMember(newMember:any):void{
    this.userService.respondToApplicant(newMember, true).subscribe(
      (data) => {
        this.convertToGroupFromWaitingListResponse(data);
        this.currentUsers++;
        this.ngOnInit();
      }
    );

  }

  removeMember(member:any):void{
    this.userService.respondToApplicant(member, false).subscribe(
      (data) => {
        this.convertToGroupFromWaitingListResponse(data);
        this.currentUsers--;
        this.ngOnInit();
      }
    );
  }

  checkMaxGroup():void{
    if(this.group._groupDetails.maxusers == this.group._groupMembers.length){
      this.getMemberSocials();
    }
  }

  getMemberSocials():void{
    this.userService.getSocialAsGroup(this.group._gameId, this.group._groupId).subscribe({
      next: res =>{
        console.log(res)
        this.openSocialLinks(res);
      },
      error: err =>{
        console.log(err)
      }
    }
    );
  }

  openSocialLinks(data:any):void{
    console.log(data);
    let userGroups: any = data;
    if(data){
      for(let i = 0 ; i < userGroups.length; i++){
        window.open(userGroups[i].steamProfile.response.players.player[0].profileurl, userGroups[i].steamProfile.response.players.player[0].personaname);
      }
      this.disbandGroup();
      // window.close();
    }
  }

  checkUsername(value: string) {
    return value !== this.group._groupLead.username;
  }

  removing():void{
    console.log("removing member");
  }


  goMainPage():void{
    this.routingAllocator.main();
  }

  goToWaitingRoom(): void{
    this.waitingRoomCheck = !this.waitingRoomCheck;
  }

  removingUserFromWaitingRoom(applicant:any): void{
    this.userService.respondToApplicant(applicant, false).subscribe(
      (data) => {
        this.convertToGroupFromWaitingListResponse(data);
        this.ngOnInit();
      }
    );
  }


  convertToGroupFromWaitingListResponse(data:any):void{
    console.log(data);
    this.group._groupDetails = data.sessionDetails;
    this.group._groupMembers = data.groupMembers;
    this.group._waitingUsers = data.waitingMembers;
    this.sessionStorage.saveCreatedGroup(this.group);
    this.checkMaxGroup()
  }

  disbandGroup():void{
    this.userService.endSession();
    this.sessionStorage.removeCreatedGroup();
    this.timeInterval.unsubscribe();
    this.timeInterval.closed;
    this.goMainPage();
    this.ngOnDestroy();
  }

  updateMembers(group:any){
    this.timeInterval = interval(3000)
      .pipe(
        startWith(0),
        switchMap(() => this.userService.refreshGroupMemberList(group))
      ).subscribe(
        res => {
          if(this.sessionStorage.getCreatedGroup())
          if(res.groupMembers.length != group._groupMembers.length ||
            res.waitingMembers.length != group._waitingUsers.length) this.convertToGroupFromWaitingListResponse(res);
        },
        err => console.log("lol"))
  }


}
