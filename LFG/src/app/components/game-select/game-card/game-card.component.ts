import { Component, Input, OnInit } from '@angular/core';
import { AvailableGames } from 'src/app/models/available-games.model';
import { Game } from 'src/app/models/game.model';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';

@Component({
  selector: 'app-game-card',
  templateUrl: './game-card.component.html',
  styleUrls: ['./game-card.component.css']
})
export class GameCardComponent implements OnInit {

  constructor(
    private sessionStorage: SessionStorageService,
    private routingAllocator: RoutingAllocatorService) { }

  ngOnInit(): void {
  }

  @Input()
  game!: Game;

  goToGroups():void{
    this.sessionStorage.saveGame(
      new AvailableGames(
        this.game.gameId, 
        this.game.name, 
        this.game.imgLink, 
        this.game.sessions)
    );
    this.goToGameGroups();
  }

  goToGameGroups(): void{
    this.routingAllocator.gameGroups();
  }

}
