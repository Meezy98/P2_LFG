import { Injectable } from '@angular/core';
import { Router } from '@angular/router';


const  ROUTES = {
    profile: ['/main/profile'],
    modifyProfile: ['/main/profile/modify'],
    modifyCredentials: ['/main/credential/modify'],
    modifySocials: ['/main/socials/modify'],
    sessionHost: ['/game/group/host'],
    sessionUser: ['/game/group/view'],
    createGroup: ['/game/group/create'],
    gameGroups: ['/game/group'],
    mainPage: ['/main'],
    socialPage: ['/main/socials'],
    login: ['']
  }

@Injectable({
  providedIn: 'root'
})

export class RoutingAllocatorService {

  constructor(private router:Router) { }

  profile():void{this.router.navigate(ROUTES.profile);}

  hostSession():void{this.router.navigate(ROUTES.sessionHost);}

  userSession():void{this.router.navigate(ROUTES.sessionUser);}

  login():void{this.router.navigate(ROUTES.login);}

  gameGroups():void{this.router.navigate(ROUTES.gameGroups);}
  
  main():void{this.router.navigate(ROUTES.mainPage);}
 
  createGroup():void{this.router.navigate(ROUTES.createGroup);}
 
  modifyProfile():void{this.router.navigate(ROUTES.modifyProfile);}

  modifyCredentials():void{this.router.navigate(ROUTES.modifyCredentials);}

  modifySocials():void{this.router.navigate(ROUTES.modifySocials)};

  socialPage():void{this.router.navigate(ROUTES.socialPage)};
  
  
}
