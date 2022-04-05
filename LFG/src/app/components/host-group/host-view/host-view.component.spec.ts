import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

import { HostViewComponent } from './host-view.component';

describe('HostViewComponent', () => {
  let component: HostViewComponent;
  let fixture: ComponentFixture<HostViewComponent>;
  let mockSessionStorage : jasmine.SpyObj<SessionStorageService>;
  let mockTokenStorage : jasmine.SpyObj<TokenStorageService>;
  let mockUserService : jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HostViewComponent ],
      imports: [RouterTestingModule,
      HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HostViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    
   expect(component).toBeTruthy();
  });
 
});
