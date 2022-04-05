import { Component, OnInit } from '@angular/core';
import { SocialsBuilder } from 'src/app/utils/socials-builder';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-lfgmain',
  templateUrl: './lfgmain.component.html',
  styleUrls: ['./lfgmain.component.css']
})
export class LFGMainComponent implements OnInit {

  gameId: number = 0;
  currentUser: any;
  stats: any;
  hostGroupPanel:boolean = false;
  joinGroupPanel: boolean = false;

  constructor(
    private sessionStorage: SessionStorageService,
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private routingAllocator: RoutingAllocatorService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.getSocial();
  }

  viewGroups():void{
    if(this.sessionStorage.getCreatedGroup()) this.hostGroupPanel = true;
    else this.viewJoinedGroups();
  }

  getSocial():void{      
    this.userService.getSocialsAsUser(1).subscribe({
      next: res => {
        console.log(res);
        if(res.success) {
          let builtSocial = SocialsBuilder.buildSocials(res)
          this.tokenStorage.saveSocials(builtSocial);      
          this.viewGroups();
        }else{
          this.routingAllocator.modifySocials();
        }
      },
      error: err => {
        console.log(err);
      }
    });
  }

  viewJoinedGroups():void{if(this.sessionStorage.getWaitingGroups()) this.joinGroupPanel = true;}

  send(data:any){this.gameId = data.gId;}

  goToProfile(): void {this.routingAllocator.profile();}

  goToSession():void{this.routingAllocator.hostSession();}

  goToUserGroup():void{this.routingAllocator.userSession();}

  logOut(): void {
    if(this.sessionStorage.getCreatedGroup()) this.userService.endSession();
    this.tokenStorage.signOut();
    this.routingAllocator.login();
  }
}
