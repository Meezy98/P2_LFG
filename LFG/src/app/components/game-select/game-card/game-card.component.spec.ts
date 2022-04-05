import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { Game } from 'src/app/models/game.model';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { GameCardComponent } from './game-card.component';

describe('GameCardComponent', () => {
  let component: GameCardComponent;
  let fixture: ComponentFixture<GameCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GameCardComponent ],
      imports: [RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GameCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let game : Game = {gameId: 1, platformKey: 1, name: 'name', imgLink: 'image', sessions: 3}   
    let fixture = TestBed.createComponent(GameCardComponent);
    let component = fixture.componentInstance
    expect(component).toBeTruthy();
  });

  // it('should return a game when an Id is passed in', () => {
    
  // })
});
