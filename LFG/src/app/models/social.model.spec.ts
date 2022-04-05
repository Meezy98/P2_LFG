import { Social } from './social.model';

describe('Social', () => {
  it('should create an instance', () => {
    let social: Social = new Social(1, 2, 3, 'profileURL', 'avatar', 'avatarMedium', 'avatarFull', 'avatarHash')
    expect(social).toBeTruthy();
  });
});
