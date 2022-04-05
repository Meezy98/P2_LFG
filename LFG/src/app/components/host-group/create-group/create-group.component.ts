import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Group } from 'src/app/models/group.model';
import BuildGroup from 'src/app/utils/build-group';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {

  form: FormGroup = new FormGroup({
    maxGroupSize: new FormControl(''),
    description: new FormControl('')
  });
  submitted = false;
  posts : any;
  currentUser: any;
  panelNumber!: number;
  group!: Group;

  @Input()
  gameId!: number;

  game: any;

  @Output()
  newGroupEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor(
    private routingAllocation: RoutingAllocatorService,
    private formBuilder: FormBuilder, 
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private sessionStorage: SessionStorageService) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm():void{
    this.currentUser = this.tokenStorage.getUser();
    this.game = this.sessionStorage.getGame();
    this.form = this.formBuilder.group(
      {   
        maxGroupSize: [
          '',
          [
            Validators.required,
            Validators.max(5)
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
    
    let gSize = this.form.get('maxGroupSize')?.value
    let description = this.form.get('description')?.value
    
    if(gSize != 1 && description != null) {
      let g = JSON.stringify({
      gameId: this.game.gameId,
      maxUsers: gSize,
      description: description
        })
           this.createGroup(g);
          }else{
            this.submitted = false;
            this.form.reset();
          }
  }

  createGroup(g:any){
    console.log(this.userService.leaveAllWaitingList());
      this.userService.createGroup(g).subscribe(
        (data) => {
          let group:Group = BuildGroup.groupBuilder(data);
          this.sessionStorage.removeCreatedGroup();
          this.sessionStorage.saveCreatedGroup(group);
          this.goToHostView()
        })
  }
  
  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  goToHostView() {
    this.routingAllocation.hostSession();
  }

  goBack():void{
    this.routingAllocation.main();
  }

}


