import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule} from'@angular/router/testing';
import { ViewGameGroupsComponent } from './view-game-groups.component';

describe('ViewGameGroupsComponent', () => {
  let component: ViewGameGroupsComponent;
  let fixture: ComponentFixture<ViewGameGroupsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewGameGroupsComponent ],
      imports: [HttpClientModule,
        RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewGameGroupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  
});
