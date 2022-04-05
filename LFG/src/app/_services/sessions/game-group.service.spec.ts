import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { GameGroupService } from './game-group.service';

describe('GameGroupService', () => {
  let service: GameGroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule,
        RouterTestingModule],
    });
    service = TestBed.inject(GameGroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return group 1 when 1 is passed into getGroups', () => {
    //expect(service.getGroups(1)).toEqual(service.group1);
  })

  it('should return group 2 when 2 is passed into getGroups', () => {
    //expect(service.getGroups(2)).toEqual(service.group2);
  })
});
