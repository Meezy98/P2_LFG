import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { GroupCardComponent } from './group-card.component';
import { HttpClientModule } from '@angular/common/http';
import * as e from 'cors';

describe('GroupCardComponent', () => {
  let component: GroupCardComponent;
  let fixture: ComponentFixture<GroupCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupCardComponent ],
      imports: [HttpClientModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show game info when getGameInfo is called', () => {
    //expect(component.getGameInfo()).toHaveBeenCalled();
  })
});
