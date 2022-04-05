import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-waiting-room',
  templateUrl: './waiting-room.component.html',
  styleUrls: ['./waiting-room.component.css']
})
export class WaitingRoomComponent implements OnInit {

  @Input()
  waitingUsers: any[] = [];

  @Output()
  newMember = new EventEmitter<any>();

  @Output()
  removeMember = new EventEmitter<any>();

  viewStats:boolean = false;

  constructor(
    private userService: UserService,
    private sessionStorage: SessionStorageService
  ) { }

  ngOnInit(): void {
  }

  addingUserFromWaitingRoom(applicant:any) {
    this.newMember.emit(applicant);
  }

  removingUserFromWaitingRoom(applicant:any){
    this.removeMember.emit(applicant);
  }


  viewAchievements(groupUser: any) {
    this.viewStats = !this.viewStats;
  }
}
