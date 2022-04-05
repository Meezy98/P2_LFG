import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { SocialProfileComponent } from './social-profile.component';

describe('SocialProfileComponent', () => {
  let component: SocialProfileComponent;
  let fixture: ComponentFixture<SocialProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialProfileComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
