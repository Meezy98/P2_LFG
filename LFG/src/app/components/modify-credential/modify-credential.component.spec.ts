import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';

import { ModifyCredentialComponent } from './modify-credential.component';

describe('ModifyCredentialComponent', () => {
  let component: ModifyCredentialComponent;
  let fixture: ComponentFixture<ModifyCredentialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifyCredentialComponent ],
      imports: [RouterTestingModule,
      HttpClientTestingModule,
    
    ReactiveFormsModule,
    BrowserModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyCredentialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    let fixture = TestBed.createComponent(ModifyCredentialComponent);
    let component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });
});
