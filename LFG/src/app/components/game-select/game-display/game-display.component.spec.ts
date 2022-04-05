import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule} from'@angular/router/testing';
import { GameDisplayComponent } from './game-display.component';

describe('GameDisplayComponent', () => {
  let component: GameDisplayComponent;
  let fixture: ComponentFixture<GameDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GameDisplayComponent ],
      imports: [HttpClientModule,
      RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GameDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // it('should return all games when findAllGames is called', () => {
  //   expect(component.games.length).toBe(0);
  //   component.findAllGames();
  //   expect(component.games.length).toBeGreaterThan(0);
  // });
});
