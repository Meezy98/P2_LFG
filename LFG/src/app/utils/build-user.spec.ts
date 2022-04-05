import { User } from '../models/user.model';
import BuildUser from './build-user';


describe('BuildUser', () => {

  it('should create an instance', () => {
    let user:User = new User(1, "name", "email")
    expect(BuildUser.userBuilder(user)).toBeTruthy();
  });
});
