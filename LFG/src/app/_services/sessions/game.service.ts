import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { AvailableGames } from 'src/app/models/available-games.model';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private httpClient:HttpClient) { }

  game1: AvailableGames = new AvailableGames(
    1, 
    "Apex Legends", 
    "https://cdn.cloudflare.steamstatic.com/steam/apps/1172470/header.jpg?t=1638900075", 
    4);
  
  game2: AvailableGames = new AvailableGames(
    2, 
    "League of Legends", 
    "https://cdn1.epicgames.com/salesEvent/salesEvent/EGS_LeagueofLegends_RiotGames_S1_2560x1440-ee500721c06da3ec1e5535a88588c77f?h=270&resize=1&w=480", 
    3);

  public findAllGames(): Observable<AvailableGames[]>{
    return of(
      [
        this.game1,
        this.game2
      ]
    )
  }

  public findGame(id:number): Observable<AvailableGames>{
    if(id == 1) return of(this.game1);
    if(id == 2) return of(this.game2);
    return of(this.game2);  
  }
}
