import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { GroupSessionComponent } from './group-session.component';

describe('GroupSessionComponent', () => {
  let component: GroupSessionComponent;
  let fixture: ComponentFixture<GroupSessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupSessionComponent ],
      imports: [RouterTestingModule,
      HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(GroupSessionComponent);
    let component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
