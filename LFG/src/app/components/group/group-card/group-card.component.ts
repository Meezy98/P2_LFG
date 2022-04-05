import { Component, Input, OnInit } from '@angular/core';
import { GroupDetails } from 'src/app/models/group-details.model';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-group-card',
  templateUrl: './group-card.component.html',
  styleUrls: ['./group-card.component.css']
})
export class GroupCardComponent implements OnInit {

  @Input()
  groupSessions!: GroupDetails[];

  @Input()
  gameId!:number;

  game: any;

  constructor(
    private sessionStorage: SessionStorageService, 
    private userService: UserService) { }

  ngOnInit(): void {
    this.getGameInfo();
    this.game = this.sessionStorage.getGame();
  }

  getGameInfo(){
  }

  groupRequest(group:any) {
    this.sessionStorage.checkIfDuplicate(group);
    this.userService.requestToJoinGroup(group).subscribe(
      (data) => {
        this.joinGroup(data)
        console.log(data);
      }
    )
  }

  joinGroup(data:any):void{
    if(!this.checkMaxJoin()) this.sessionStorage.addToGroups(data);
    else alert("MAX GROUPS JOINED");
  }

  checkMaxJoin():boolean{
    return this.sessionStorage.getWaitingGroups().length == 3;
  }
}
