import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

  joinedSession!: any;

  insideSession: boolean = false;

  leftSession: boolean = false;

  insideGroup!: any;

  constructor(
    private userService: UserService,
    private sessionStorage: SessionStorageService,
    private routingAllocation: RoutingAllocatorService
  ) { }

  ngOnInit(): void {
    console.log(this.joinedSession);
    console.log(this.sessionStorage.joinedGroups);
    this.joinedSession = this.sessionStorage.joinedGroups;
    this.insideSession = false;
  }

  insideSessionCheck(check:boolean):void{
    this.insideSession = check;
  }

  joinedGroup(group:any):void{
    this.sessionStorage.addToWaitingRoom(group);
    this.insideGroup = group;
  }

  // isInsideSession(): void {
  //   this.joinedSession = this.sessionStorage.getWaitingGroups();
  //   console.log(this.joinedSession);
  //   for (let i: number = 0; i < this.joinedSession.length; i++) {
  //     this.userService.refreshWaitingList(this.joinedSession[i].groupId).subscribe(
  //       (data) => {
  //         if (data.success) {
  //           this.insideSession = true;
  //           this.insideGroup = data;
  //         }
  //       }
  //     );
  //   }
  // }

  goMainPage(): void {
    this.routingAllocation.main();
  }

}
