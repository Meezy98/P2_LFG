import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Group } from 'src/app/models/group.model';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl('')
  });
  submitted = false;
  posts : any;
  currentUser: any;

  Input()
  game: Game

  constructor(private router: Router, private formBuilder: FormBuilder, private userService: UserService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.form = this.formBuilder.group(
      {   
        maxGroupSize: [
          '',
          [
            Validators.required,
            Validators.maxLength(2)
          ]
        ],
        description: [
          '', 
          [
            Validators.required, 
            Validators.maxLength(100)
          ]
        ]
      }
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

    console.log(JSON.stringify(this.form.value, null, 2));

    let gSize = this.form.get('maxGroupSize')?.value
    let desc = this.form.get('description')?.value
    console.log(gSize, desc)
    if(gSize != null && desc != null) {
      let group = new Group(1, )
      this.userService.createGroup(userN, email).subscribe(
        (data) => {
          console.log(data.username);
          console.log(data.email)
          console.log(data.password)
        }) 
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}


