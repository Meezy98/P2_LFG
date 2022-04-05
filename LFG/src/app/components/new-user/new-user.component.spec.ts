import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { NewUserComponent } from './new-user.component';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser'
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('NewUserComponent', () => {
  let component: NewUserComponent;
  let fixture: ComponentFixture<NewUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewUserComponent ],
      imports: [RouterTestingModule,
      
      FormsModule,
      ReactiveFormsModule,
      HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(NewUserComponent);
    let component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });

  it('should submit new user data when form is submitted', () => {
    //expect(component.onSubmit()).toHaveBeenCalled();
  })

  
});
