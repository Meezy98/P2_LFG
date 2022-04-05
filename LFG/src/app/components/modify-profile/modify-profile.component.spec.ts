import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ModifyProfileComponent } from './modify-profile.component';
import {FormBuilder, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser'
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ModifyProfileComponent', () => {
  let component: ModifyProfileComponent;
  let fixture: ComponentFixture<ModifyProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifyProfileComponent ],
      imports: [RouterTestingModule,
      FormsModule,
      ReactiveFormsModule,
      BrowserModule,
      HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should reset form when onReset is called', () => {
    //expect(component.onReset()).toHaveBeenCalled();
  })

  it('should navigate to user profile when goToProfile is called', () => {
    //expect(component.goToProfile()).toHaveBeenCalled();
  })

  it('should change user data when submitted', () => {
    //expect(component.onSubmit()).toHaveBeenCalled();
  })
});
