import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { UserViewComponent } from './user-view.component';

describe('UserViewComponent', () => {
  let component: UserViewComponent;
  let fixture: ComponentFixture<UserViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserViewComponent ],
      imports: [HttpClientTestingModule,
      RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(UserViewComponent);
    let component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
