import { UserViewGroup } from './user-view-group.model';
import { Game } from './game.model'

describe('UserViewGroup', () => {
  it('should create an instance', () => {
    let game : Game = {gameId: 1, platformKey: 1, name: 'name', imgLink: 'image', sessions: 3}
    
    expect(new UserViewGroup(1, game, 3, 2, 'description')).toBeTruthy();
  });
});
