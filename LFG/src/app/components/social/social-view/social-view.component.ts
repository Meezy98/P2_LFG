import { Component, OnInit } from '@angular/core';
import { SocialsBuilder } from 'src/app/utils/socials-builder';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-social-view',
  templateUrl: './social-view.component.html',
  styleUrls: ['./social-view.component.css']
})
export class SocialViewComponent implements OnInit {

  validSocial:boolean = false;

  steamSocial!:any;

  constructor(
    private userService:UserService,
    private sessionService: SessionStorageService,
    private routingAllocator: RoutingAllocatorService,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit(): void {
    this.getSocial();
    // this.getAchievements();
  }

  getSocial():void{      
    this.userService.getSocialsAsUser(1).subscribe({
      next: res => {
        console.log(res);
        if(res.success) {
          let builtSocial = SocialsBuilder.buildSocials(res)
          this.tokenStorage.saveSocials(builtSocial);
          this.validSocial = true;
        }else{
          this.steamSocial = res;
        }
      },
      error: err => {
        console.log(err);
      }
    });
  }


  linkSteamProfile():void{
    this.routingAllocator.modifySocials();
  }


}
