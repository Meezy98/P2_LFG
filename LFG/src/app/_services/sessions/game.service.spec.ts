import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { GameService } from './game.service';

describe('GameService', () => {
  let service: GameService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule,
      RouterTestingModule],

    });
    service = TestBed.inject(GameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return game 1 when 1 is passed into findGame', () => {
   // expect(service.findGame(1)).toEqual(game1);
  })

  it('should return game 2 when 2 is passed into findGame', () => {
  //  expect(service.findGame(2)).toEqual(game2);
  })

  
});
