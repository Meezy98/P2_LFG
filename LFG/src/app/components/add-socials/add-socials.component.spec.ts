import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder,ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';

import { AddSocialsComponent } from './add-socials.component';

describe('AddSocialsComponent', () => {
  let component: AddSocialsComponent;
  let fixture: ComponentFixture<AddSocialsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSocialsComponent ],
      imports: [ HttpClientTestingModule,
        ReactiveFormsModule,
      BrowserModule,
      RouterTestingModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSocialsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(AddSocialsComponent);
    let component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
