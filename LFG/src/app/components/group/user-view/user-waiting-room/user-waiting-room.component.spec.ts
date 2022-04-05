import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { UserWaitingRoomComponent } from './user-waiting-room.component';

describe('UserWaitingRoomComponent', () => {
  let component: UserWaitingRoomComponent;
  let fixture: ComponentFixture<UserWaitingRoomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserWaitingRoomComponent ],
      imports: [ RouterTestingModule, HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserWaitingRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
