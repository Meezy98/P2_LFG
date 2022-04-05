import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import Validation from 'src/app/utils/validation';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';
import BuildUser from 'src/app/utils/build-user';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';

@Component({
  selector: 'app-modify-profile',
  templateUrl: './modify-profile.component.html',
  styleUrls: ['./modify-profile.component.css']
})
export class ModifyProfileComponent implements OnInit {

  submitted = false;
  currentUser: any;

  form: FormGroup = new FormGroup({
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl('')
  });

  constructor(
    private routingAllocator: RoutingAllocatorService,
    private formBuilder: FormBuilder,
    private tokenStorage: TokenStorageService,
    private userService: UserService) { }

  ngOnInit(): void {
    this.setUpForm();
  }

  setUpForm() {
    this.currentUser = this.tokenStorage.getUser();
    this.form = this.formBuilder.group(
      {
        firstname: [
          '',
          [
            Validators.maxLength(30)
          ]
        ],
        lastname: [
          '',
          [
            Validators.maxLength(30)
          ]
        ],
        email: [
          '',
          [
            Validators.email,
            Validators.maxLength(50)
          ]
        ]
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
    if (this.currentUser.firstName) {
      this.form.controls["firstname"].patchValue(this.currentUser.firstName);
    }
    if (this.currentUser.lastName) {
      this.form.controls["lastname"].patchValue(this.currentUser.lastName);
    }
    if (this.currentUser.email) {
      this.form.controls["email"].patchValue(this.currentUser.email);
    }
  }

  goToProfile(): void {
    this.routingAllocator.profile();
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    let change: boolean = false;

    let tempUser = BuildUser.userBuilder(this.currentUser)

    let firstN = this.form.get('firstname')?.value
    if (firstN) {
      tempUser._firstName = firstN;
      if (this.currentUser._firstName != tempUser._firstName) change = true;
    }

    let lastN = this.form.get('lastname')?.value
    if (lastN) {
      tempUser._lastName = lastN;
      if (this.currentUser._lastName != tempUser._lastName) change = true;
    }

    let eMail = this.form.get('email')?.value
    if (eMail) {
      tempUser._email = eMail;
      if (this.currentUser._email != tempUser._email) change = true;
    }

    console.log(tempUser)
    if (change) {
      this.userService.updateUser(tempUser).subscribe(
        (data) => {
          this.tokenStorage.saveToken(data.jwt);
          let builtUser = BuildUser.userBuilder(data);
          this.tokenStorage.saveUser(builtUser);
          this.goToProfile();
        })
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}

