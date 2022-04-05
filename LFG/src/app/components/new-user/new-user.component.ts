import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import Validation from 'src/app/utils/validation';
import { AuthService } from 'src/app/_services/user_data/auth.service';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl('')
  });
  submitted = false;
  posts: any;

  constructor(
    private routingAllocator: RoutingAllocatorService,
    private formBuilder: FormBuilder,
    private authService: AuthService,
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
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ],
        email: [
          '',
          [
            Validators.required,
            Validators.email,
            Validators.maxLength(50)
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ],
        confirmPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ],
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );

  }

  goToLogin(): void {
    this.routingAllocator.login();
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
    let email = this.form.get('email')?.value
    let passW = this.form.get('password')?.value
    console.log(userN, email, passW)
    if (userN != null && email != null && passW != null) {
      this.authService.register(userN, email, passW).subscribe(
        (data) => {
          console.log("Profile successfully created!");
          this.goToLogin();
        })
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}


