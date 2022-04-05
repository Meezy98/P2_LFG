import { GroupUser } from './group-user.model';

describe('GroupUser', () => {
  it('should create an instance', () => {
    expect(new GroupUser("user")).toBeTruthy();
  });
});
