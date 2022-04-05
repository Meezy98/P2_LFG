import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

import { UserStatsComponent } from './user-stats.component';

class MockedUserService extends UserService{
  gameId = 1
}
describe('UserStatsComponent', () => {
  let component: UserStatsComponent;
  let fixture: ComponentFixture<UserStatsComponent>;
  let service: MockedUserService;
  let mockUserService : jasmine.SpyObj<UserService>
  let mockSession : jasmine.SpyObj<SessionStorageService>
  let mockToken: jasmine.SpyObj<TokenStorageService>
  let http: HttpClient;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserStatsComponent ],
      imports: [HttpClientTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    service = new MockedUserService(http, mockToken, mockSession);
    fixture = TestBed.createComponent(UserStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    service.gameId
    expect(component).toBeTruthy();
  });
});


