import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SocialsBuilder } from 'src/app/utils/socials-builder';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-add-socials',
  templateUrl: './add-socials.component.html',
  styleUrls: ['./add-socials.component.css']
})
export class AddSocialsComponent implements OnInit {

  form: FormGroup = new FormGroup({
    steamId: new FormControl(''),   
  });
  submitted = false;
  errorMessage = '';
  isLinkFailed = false;
  posts : any;

  constructor(private formBuilder: FormBuilder, private userService: UserService, 
    private tokenStorage: TokenStorageService, private routingAllocator: RoutingAllocatorService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {   
        steamId: [
          '',
          [
            Validators.required,
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

    let sID = this.form.get('steamId')?.value
    if(sID != null) {
      console.log(sID);
      this.userService.createSocials({gameId: 3, social: sID}).subscribe({
        next: data => {     
          console.log(data);    
          this.isLinkFailed = false;
          let builtSocial = SocialsBuilder.buildSocials(data)
          this.tokenStorage.saveSocials(builtSocial);
          this.routingAllocator.socialPage();  
        },
        error: err => {
          this.errorMessage = err.error.message;
          console.log("Link unsuccessful.")
          this.isLinkFailed = true;
        }
      });        
    }
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  goToProfile(): void {
    this.routingAllocator.profile();
  }
}


