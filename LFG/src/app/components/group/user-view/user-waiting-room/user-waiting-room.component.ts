import { Component, EventEmitter, HostListener, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { interval, startWith, Subscription, switchMap } from 'rxjs';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-user-waiting-room',
  templateUrl: './user-waiting-room.component.html',
  styleUrls: ['./user-waiting-room.component.css']
})
export class UserWaitingRoomComponent implements OnInit, OnDestroy {

  timeInterval!: Subscription;

  @Input()
  joinedSession:any[] = [];

  @Output()
  insideSessionEvent = new EventEmitter<any>();

  @Output()
  insideGroupEvent = new EventEmitter<any>();

  @HostListener('unloaded')
  ngOnDestroy():void{
    try{this.timeInterval.unsubscribe();
    }catch(e){
      console.log(e)
    }
  }

  constructor(
    private routingAllocator:RoutingAllocatorService,
    private sessionStorage: SessionStorageService,
    private userService: UserService
    ) { }

  ngOnInit(): void {
    console.log(this.joinedSession);
    this.isInsideSession();
  }

  goMainPage():void{this.routingAllocator.main();}

  isInsideSession(): void {
    this.joinedSession = this.sessionStorage.joinedGroups;
    console.log(this.joinedSession);
    for (let i: number = 0; i < this.joinedSession.length; i++) {
      console.log(this.joinedSession[i])
      this.checkInsideSession(this.joinedSession[i].groupId);
    }
  }

  leaveSession(groupId:any, gameId: any): void {
    this.userService.leaveGroup(groupId, gameId).subscribe(
      (data) => {
        console.log(data)
      }
    );
    this.ngOnInit();
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

  checkInsideSession(groupId:number): void{
    this.timeInterval = interval(3000)
      .pipe(
        startWith(0),
        switchMap(() => this.userService.refreshWaitingList(groupId)) 
      ).subscribe(
        res => {
          if(res.success) {
            this.sessionStorage.leaveAllGroups();
            this.insideSessionEvent.emit(true);
            this.insideGroupEvent.emit(this.convertToGroupFromWaitingListResponse(res))
            console.log();
          }else if(res.groupId == 0) {
            this.sessionStorage.leaveAllGroups();
            this.insideSessionEvent.emit(false);
            this.goMainPage();
          }
        },
        err => {
          console.log(err);
          this.sessionStorage.leaveGroup(groupId);
        }
      )
  }


}
