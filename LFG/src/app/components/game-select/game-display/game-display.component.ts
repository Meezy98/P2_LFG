import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { UserService } from 'src/app/_services/user_data/user.service';
import { Game } from 'src/app/models/game.model';
import { interval, startWith, Subscription, switchMap } from 'rxjs';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';


@Component({
  selector: 'app-game-display',
  templateUrl: './game-display.component.html',
  styleUrls: ['./game-display.component.css']
})
export class GameDisplayComponent implements OnInit , OnDestroy{
  games!: Game[];

  gId!: number;

  timeInterval!: Subscription;

  stats:any[] = [];

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private sessionStorage: SessionStorageService  ) { }

  @HostListener('unloaded')
  ngOnDestroy(): void {
    if(this.timeInterval) this.timeInterval.unsubscribe();
  }

  ngOnInit(): void {
    this.findAllGames();
  }

  public findAllGames() {
    this.userService.generateGames().subscribe(
      (data) => {
        this.games = data.gameSessionList;
        this.getPollingGames();
      }
    )
  }

  // public findAllGamesPlayable() {
  //   this.userService.generateGames().subscribe(
  //     (data) => {
  //       this.games = data.gameSessionList;
  //       this.findAllStats();
  //     }
  //   )
  // }

  public findAllStats():void{
    if(this.games && this.sessionStorage.getStats().length == 0){
      for(let i = 0; this.games.length; i++){
        if(this.userService.getAchievements(this.games[i].gameId)){
        this.userService.getAchievements(this.games[i].gameId).subscribe(
          (data) => {
            try{
              if(data.playerstats.achievements){
              let validGame = {
                game: this.games[i],
                achievements: data.playerstats.achievements.length,
                stats: data.playerstats.stats
              };
              this.stats.push(validGame);
            }
            }catch(e){
              console.log("lol");
              console.log(e);
            }
          },
            (error) => {
              console.log("lol")
              console.log(error);
            }
          )
        } 
      this.sessionStorage.addToStats(this.stats);
      console.log(this.sessionStorage.getStats());
    }
    }
  }

  public getPollingGames(){
    this.timeInterval = interval(3000)
      .pipe(
        startWith(0),
        switchMap(() => this.userService.generateGames())
      ).subscribe(
        res => {
            this.games = res.gameSessionList;
            console.log(res);
            if(this.sessionStorage.getStats().length == 0) this.findAllStats();
            if(this.checkUpdatedGames(res)) {
              this.games = res;
            }
          },
        err => console.log("lol"))
  }

  checkUpdatedGames(data:any): boolean {
    let updatedGame: boolean = false;
    for(let i = 0; i < data.gameSessionList.length; i++){
      if(data.gameSessionList[i].sessions != this.games[i].sessions) {
        updatedGame = true;
        console.log(this.stats[i].game.sessions)
      }
    }
    return updatedGame;
  }


}
