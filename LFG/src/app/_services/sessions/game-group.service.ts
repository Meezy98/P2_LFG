import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameGroupService {


  constructor(private http: HttpClient) { }

  // group1:Observable<UserViewGroup[]> = of([
  //   new UserViewGroup(1, 1, 4, 2, "This is a test Group"),
  //   new UserViewGroup(2, 1, 4, 2, "This is a test Group"),
  //   new UserViewGroup(3, 1, 4, 2, "This is a test Group")
  // ]);

  // group2:Observable<UserViewGroup[]> = of([
  //   new UserViewGroup(4, 2, 4, 2, "This is a test Group"),
  //   new UserViewGroup(5, 2, 4, 2, "This is a test Group"),
  //   new UserViewGroup(6, 2, 4, 2, "This is a test Group")
  // ]);

  // getGroups(id: number): Observable<UserViewGroup[]>{
  //     if(id == 1) return this.group1;
  //     if(id == 2) return this.group2;
  //     return this.group2;
  //   }
  }

