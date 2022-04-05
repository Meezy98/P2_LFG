import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-group-members',
  templateUrl: './group-members.component.html',
  styleUrls: ['./group-members.component.css']
})
export class GroupMembersComponent implements OnInit {

  @Input()
  hostUsername!:string;

  @Input()
  groupMembers: any[] = []

  @Output()
  removeUser = new EventEmitter<any>(); 

  viewStats:boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  checkUsername(username:string):boolean{return username != this.hostUsername;}

  removingUser(applicant:any){
    this.removeUser.emit(applicant);
  }

  viewAchievements(groupUser: any) {
    this.viewStats=!this.viewStats;
  }

}
