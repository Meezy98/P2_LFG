import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { ProfileComponent } from './profile.component';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let location: Location;
  let router: RoutingAllocatorService;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileComponent ],
      imports: [RouterTestingModule]
    })
    

    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to Modify Profile when modifyProfile is called', () => {
    // location = TestBed.get(Location);
    // router.modifyProfile().then(() => {
    //   expect(location.path()).toBe("/main/profile/modify")
    });
    
  

  it('should navigate to main when goToMain is called', () => {
    //expect(component.goToMain()).toHaveBeenCalled();
  })
});
