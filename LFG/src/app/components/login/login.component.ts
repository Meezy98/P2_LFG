import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/_services/user_data/auth.service'; 
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service'; 
import BuildUser from 'src/app/utils/build-user';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service'; 
import { SocialsBuilder } from 'src/app/utils/socials-builder';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  submitted = false;
  errorMessage = '';
  isLoginFailed = false;
  posts : any;
  
  form: FormGroup = new FormGroup({
    username: new FormControl(''),   
    password: new FormControl(''),
  });

  constructor(
    private routingAllocator: RoutingAllocatorService, 
    private formBuilder: FormBuilder, 
    private authService: AuthService,
    private userService: UserService, 
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm():void{
    this.form = this.formBuilder.group(
      {   
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(20)
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(30)
          ]
        ],      
      },
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    let userN = this.form.get('username')?.value
    let passW = this.form.get('password')?.value
    if(userN != null && passW != null) {
      this.authService.login(userN, passW).subscribe({
        next: data => {         
          this.isLoginFailed = false;
          if(data.success){
            this.tokenStorage.saveToken(data.jwt);
            let builtUser = BuildUser.userBuilder(data);
            this.tokenStorage.saveUser(builtUser);
            this.routingAllocator.main();
          }else{
            this.form.reset();
            return;
          }
        },
        error: err => {
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        }
      });        
    }
  }


  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}
