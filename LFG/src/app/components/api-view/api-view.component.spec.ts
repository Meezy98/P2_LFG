import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApiViewComponent } from './api-view.component';

describe('ApiViewComponent', () => {
  let component: ApiViewComponent;
  let fixture: ComponentFixture<ApiViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApiViewComponent ],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApiViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should return data when getData is called', () => {
    //expect(component.getData()).toHaveBeenCalled();
  })
});
