import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateGroupComponent } from './create-group.component';
import { BrowserModule } from '@angular/platform-browser'
import { HttpClientTestingModule } from '@angular/common/http/testing'

describe('CreateGroupComponent', () => {
  let component: CreateGroupComponent;
  let fixture: ComponentFixture<CreateGroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateGroupComponent ],
      imports: [RouterTestingModule,
      
      FormsModule,
      ReactiveFormsModule,
      BrowserModule,
      HttpClientTestingModule
    ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(CreateGroupComponent);
    let component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });

    
});
