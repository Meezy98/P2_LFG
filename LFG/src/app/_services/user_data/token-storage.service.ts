import { prepareEventListenerParameters } from '@angular/compiler/src/render3/view/template';
import { Injectable, ɵɵsetComponentScope } from '@angular/core';
import { Game } from 'src/app/models/game.model';
import { User } from 'src/app/models/user.model';
import { SessionStorageService } from '../sessions/session-storage.service';

const KEYS = {
  TOKEN_KEY: 'auth-token',
  USER_KEY:'auth-user',
  SELECTED_GAME: 'sel-game',
  CREATED_GROUP: 'host-group',
  JOINED_GROUPS: 'joined-groups',
  USER_SOCIALS: 'user-socials'
}

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor(
    private joinSessionManagement: SessionStorageService
  ) { }

  joinedGroups: any[] = [];

  socials: any;


  signOut(): void {
    window.sessionStorage.clear();    
    this.joinedGroups = [];
    this.socials = [];
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(KEYS.TOKEN_KEY);
    window.sessionStorage.setItem(KEYS.TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(KEYS.TOKEN_KEY);
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(KEYS.USER_KEY);
    window.sessionStorage.setItem(KEYS.USER_KEY, JSON.stringify(user));
    window.sessionStorage.setItem(KEYS.JOINED_GROUPS, JSON.stringify(this.joinedGroups));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(KEYS.USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }

  public saveSocials(socials: any): void {
    this.socials = socials;
  }

  public getSocials(): any {
    if (this.socials) {
      return this.socials;
    }
    return {};
  }
}
