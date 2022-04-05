import { Component, OnInit } from '@angular/core';
import { Game } from 'src/app/models/game.model';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-social-profile',
  templateUrl: './social-profile.component.html',
  styleUrls: ['./social-profile.component.css']
})
export class SocialProfileComponent implements OnInit {

  games!: any[];
  stats: any[] = [];

  gId!: number;

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private routingAllocator: RoutingAllocatorService,
    private sessionStorage: SessionStorageService
  ) { }

  getGames():void{

  }  

  ngOnInit(): void {
    this.findAllGames();
  }

  public findAllGames() {
    this.userService.generateGames().subscribe(
      (data) => {
        this.games = data.gameSessionList;
        this.findAllStats();
      }
    )
  }

  public findAllStats():void{
    
    for(let i = 0; this.games.length; i++){
    this.userService.getAchievements(this.games[i].gameId).subscribe(
      (data) => {
        console.log(data.playerstats);
        if(data.playerstats.achievements){
          let validGame = {
            game: this.games[i],
            achievements: data.playerstats.achievements.length,
            stats: data.playerstats.stats
          };
          this.stats.push(validGame);
        }}
      )
    } this.sessionStorage.addToStats(this.stats);
  }


  goMainPage():void{
    this.routingAllocator.main();    
  }

}
