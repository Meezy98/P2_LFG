import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { SocialViewComponent } from './social-view.component';

describe('SocialViewComponent', () => {
  let component: SocialViewComponent;
  let fixture: ComponentFixture<SocialViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialViewComponent ],
      imports: [HttpClientTestingModule,
      RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(SocialViewComponent);
    let component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
