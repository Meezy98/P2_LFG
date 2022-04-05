import { JsonPipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

 

export class SteamApiService {

 

  baseUrl:string = "https://api.steampowered.com";
  steamKey:string = "0D56D9D1735927D1E0B5009EAA5AD3B8";
  keyProvider:string = `/?key=${this.steamKey}`;
  steamId:string = "76561198051281395";

  steamData!: JsonPipe;

  testingUrl:string = `https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=440&count=3`;

  
  httpOptions = {
    headers: new HttpHeaders()
    .set('Access-Control-Allow-Credentials', 'true')
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')
    .set('Access-Control-Allow-Methods', 'POST,GET,PUT,DELETE')
    .set('Access-Control-Allow-Headers', 'Accept, Origin, Content-Type, X-Auth-Token')
  };

  constructor(private http:HttpClient) { }

  ngOnInit(){
    
  }

  printThing():void{
    this.http
      .get<JsonPipe>(this.testingUrl, this.httpOptions)
      .subscribe(
        (data) => console.log(data),
        (error) => console.log(error));
  }



}
