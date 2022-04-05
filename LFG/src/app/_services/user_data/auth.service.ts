import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://13.59.199.4:8080/login/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + "check",  JSON.stringify({username: username, password: password}), httpOptions);  
  }

  register(username: string, email: string, password: string): Observable<any> {
    //return this.http.post(AUTH_API, JSON.stringify({
    return this.http.post(AUTH_API + 'new', JSON.stringify({
      username: username,
      email: email,
      password: password
    }), httpOptions);
  }

}
