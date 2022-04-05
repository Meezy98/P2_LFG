import { Game } from './game.model';
import { GroupDetails } from './group-details.model';

describe('GroupDetails', () => {
  it('should create an instance', () => {
    let game : Game = {gameId: 1, platformKey: 1, name: 'name', imgLink: 'image', sessions: 3}
    let groupDeets : GroupDetails = new GroupDetails(
      1,
      game, 
      3, 
      2, 
      'description'
      )
    expect(groupDeets).toBeTruthy();
  });
});
