import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoginComponent } from './login.component';
import { AuthService } from 'src/app/_services/user_data/auth.service';
import { Observable } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

export class MockLoginService extends AuthService {
  //override login(username: string, password: string): Observable<any> {
    
  //}
}

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [HttpClientTestingModule,
      RouterTestingModule,
      
      FormsModule,
      ReactiveFormsModule,
      BrowserModule],
      providers: [{provide: AuthService, MockLoginService}]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    component.ngOnInit();
    fixture.detectChanges();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(LoginComponent);
    let component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });

  it('should submit user login info when onSubmit is called', () => {
    //expect(component.submitted).toBeTruthy();
  })

});
