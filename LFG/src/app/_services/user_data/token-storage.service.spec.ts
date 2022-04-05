import { TestBed } from '@angular/core/testing';
import { User } from 'src/app/models/user.model';

import { TokenStorageService } from './token-storage.service';

describe('TokenStorageService', () => {
  let service: TokenStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TokenStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should save a new user login', () => {
    let user:any = new User(0, 'testUser', 'test@email.com')
    let testUser= {
      id: 0,
      username: 'testUser',
      email: 'test@email.com'
    }
    service.saveUser(testUser)
    expect(service.getUser()).toEqual(testUser)
  })
});
