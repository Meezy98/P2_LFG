import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { interval, Observable, of, startWith, Subscription, switchMap } from 'rxjs';
import { TokenStorageService } from '../user_data/token-storage.service';
import { UserService } from '../user_data/user.service';
import { SessionStorageService } from './session-storage.service';


const API_URL = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PollingRequestsService implements OnDestroy{

  timeInterval!: Subscription;

  getResult(): any {
    return of(["hey", "this", "worked"]);
  }

  constructor(
    private http: HttpClient,
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private sessionStorage: SessionStorageService) { }

  ngOnDestroy(): void {
    this.timeInterval.unsubscribe();
  }

  pollGroupsForGame(gameId: number): Observable<any>{
    return this.http.post(API_URL, JSON.stringify(gameId));
  }

  updateMembers(group:any){
    this.timeInterval = interval(3000)
      .pipe(
        startWith(0),
        switchMap(() => this.userService.refreshGroupMemberList(group))
      ).subscribe(
        res => {
          if(res.groupMembers.length == group._groupMembers.length) this.sessionStorage.updateGroup(res);
        },
        err => console.log("lol"))
  }
  
}