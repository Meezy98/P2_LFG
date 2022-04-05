import { AvailableGames } from './available-games.model';

describe('AvailableGames', () => {
  it('should create an instance', () => {
    let availableGames: AvailableGames = new AvailableGames(1, "game", "game_url", 1);
    expect(availableGames).toBeTruthy();
  });
});
