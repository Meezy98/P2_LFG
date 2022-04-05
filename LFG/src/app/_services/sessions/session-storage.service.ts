import { Injectable } from '@angular/core';

const KEYS = {
  TOKEN_KEY: 'auth-token',
  USER_KEY:'auth-user',
  SELECTED_GAME: 'sel-game',
  CREATED_GROUP: 'host-group',
  JOINED_GROUPS: 'joined-groups',
}

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  constructor() { }

  joinedGroups:any[] = [];

  createdGroup:any;

  stats:any[] = [];

  addToStats(stats:any[]):void{
    this.stats = stats;
  }

  getStats():any[]{
    return this.stats;
  }

  updateGroup(group:any):void{
    let storing = {
      _groupId: group.groupId,
      _gameId: group.gameId,
      _groupLead : group.hostUser,
      _groupDetails: group.sessionDetails,
      _groupMembers: group.groupMembers,
      _waitingUsers: group.waitingMembers
    }
    this.joinedGroups.push(storing);
  }

  addToWaitingRoom(group:any):void{
    if(this.joinedGroups){
      this.joinedGroups.push(group);
     }
  }

   getWaitingGroups(): any{
    if(this.joinedGroups) return this.joinedGroups;
  }

  public addToGroups(group:any):void{
    if(this.checkIfMax() && this.checkIfDuplicate(group)){
      this.addToWaitingRoom(group);
      console.log("added");
    }else{
      console.log("fail");
    };
  }

  public leaveAllGroups():void{
    if(this.joinedGroups){
      for(let i:number = 0; i < this.joinedGroups.length; i++){
        console.log(this.joinedGroups[i]);
        this.leaveGroup(this.joinedGroups[i].groupId);
      }
    }
  }

  public leaveGroup(groupId:number):void{
    console.log(`${groupId}`)      

    for(let i:number = 0; i < this.joinedGroups.length; i++){
      console.log(this.joinedGroups[i])
      if(groupId == this.joinedGroups[i].groupId) {
        console.log("found it");
        this.joinedGroups.splice(i, 1);
        window.sessionStorage.setItem(KEYS.JOINED_GROUPS, JSON.stringify(this.joinedGroups));
        return;
      }
    } 
  }

  checkIfMax():boolean{
    if(this.joinedGroups) return !(this.joinedGroups.length >= 3)
    return true;
  }

  checkIfDuplicate(group:any):boolean{
    for(let i:number = 0; i < this.joinedGroups.length; i++){
      if(group.groupId == this.joinedGroups[i].groupId || group.groupid == this.joinedGroups[i].groupId) {
        return false;
      }
    } 
    return true;
  }

  public saveCreatedGroup(group: any): void{
    this.createdGroup = group;
    // window.sessionStorage.removeItem(KEYS.CREATED_GROUP);
    // window.sessionStorage.setItem(KEYS.CREATED_GROUP, JSON.stringify(group))
  }

  public getCreatedGroup():any{
    if(this.createdGroup) return this.createdGroup;
  }

  public removeCreatedGroup():any{
    this.createdGroup = undefined;
  }

  public saveGame(game: any): void {
    window.sessionStorage.removeItem(KEYS.SELECTED_GAME);
    window.sessionStorage.setItem(KEYS.SELECTED_GAME, JSON.stringify(game));
  }

  public getGame(): any {
    const game = window.sessionStorage.getItem(KEYS.SELECTED_GAME);
    if (game) {
      return JSON.parse(game);
    }
    return {};
  }
}
