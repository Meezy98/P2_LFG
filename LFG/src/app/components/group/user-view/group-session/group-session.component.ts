import { Component, EventEmitter, HostListener, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { interval, startWith, Subscription, switchMap } from 'rxjs';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-group-session',
  templateUrl: './group-session.component.html',
  styleUrls: ['./group-session.component.css']
})
export class GroupSessionComponent implements OnInit, OnDestroy {

  timeInterval!: Subscription;

  @Input()
  insideGroup!:any;

  // @Output()
  // leaveSessionEvent = new EventEmitter<any>();

  @HostListener('unloaded')
  ngOnDestroy(): void {
      try{
        this.timeInterval.unsubscribe();
      }catch(e){
        console.log(e)
      }
  }

  constructor(
    private routingAllocation:RoutingAllocatorService,
    private userService: UserService,
    private sessionStorage: SessionStorageService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    console.log(this.insideGroup);
    this.updateGroup();
  }

  goMainPage(): void {
    this.routingAllocation.main();
  }

  // checkMaxGroup():void{
  //   if(this.insideGroup._groupDetails.maxusers == this.insideGroup._groupMembers.length){
  //     this.getMemberSocials();
  //   }
  // }

  // getMemberSocials():void{
  //   this.userService.getSocialAsGroup(this.insideGroup._gameId, this.insideGroup._groupId).subscribe({
  //     next: res =>{
  //       console.log(res)
  //       this.openSocialLinks(res);
  //     },
  //     error: err =>{
  //       console.log(err)
  //     }
  //   }
  //   );
  // }

  // openSocialLinks(data:any):void{
  //   if(data){
  //     for(let i =0 ; i < data.length; i++){
  //       window.open(data[i].steamProfile.response.players.player[0].profileurl, "_blank");
  //     }
  //     window.close();
  //   }
  // }

  leaveSession(): void {
    this.userService.leaveAllWaitingList().subscribe(
      (data) => {
        console.log(data)
        this.sessionStorage.leaveAllGroups();
      }
    );
    this.goMainPage();
  }

  updateGroup():void{
    // this.insideGroup = this.sessionStorage.joinedGroups[0];
    // console.log(this.insideGroup);
    console.log("updating Session");
    if(this.insideGroup){
    this.timeInterval = interval(3000)
      .pipe(
        startWith(0),
        switchMap(() => this.userService.refreshOtherMembers(this.insideGroup._groupId)) 
      ).subscribe(
        res => {
          if(res.success){
            console.log()
            this.updateSession(this.convertToGroupFromWaitingListResponse(res))
          }else{
            if(res.groupId == 0) this.leaveSession();
          }
        },
        err => console.log(err)
      )

    }
  }

  updateSession(groupUpdate:any):void{
    console.log(groupUpdate);
    let groupMembers:any[] = groupUpdate._groupMembers;
    let inSession:boolean = false;
    this.insideGroup = groupUpdate;
    for(let i = 0; i < groupMembers.length; i++){

      if(groupMembers[i].username == this.tokenStorage.getUser().username){
        console.log("found")
        inSession = true;
      }
    }
    if(inSession) return;
    else this.leaveSession();
  }

  convertToGroupFromWaitingListResponse(data:any):any{
    let group = {
      _groupId: data.groupId,
      _gameId: data.gameId,
      _hostUser: data.hostUser,
      _groupDetails : data.sessionDetails,
      _groupMembers : data.groupMembers,
      _waitingUsers : data.waitingMembers
    }
    return group;
  }

}
