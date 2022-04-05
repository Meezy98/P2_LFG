import { GameGroupHolders } from './game-group-holders.model';
import { Game } from './game.model';
import { GroupDetails } from './group-details.model';

describe('GameGroupHolders', () => {
  it('should create an instance', () => {
    let game : Game = {gameId: 1, platformKey: 1, name: 'name', imgLink: 'image', sessions: 3}
    let groupDeets : GroupDetails = new GroupDetails(
      1,
      game, 
      3, 
      2, 
      'description'
      )
    let gameGroupHolders : GameGroupHolders = new GameGroupHolders(1, [groupDeets] )
    expect(gameGroupHolders).toBeTruthy();
  });
});
