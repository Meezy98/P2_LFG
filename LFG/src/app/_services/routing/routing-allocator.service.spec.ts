import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { RoutingAllocatorService } from './routing-allocator.service';

describe('RoutingAllocatorService', () => {
  let service: RoutingAllocatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule]
    });
    
  });

  it('should be created', () => {
    service = TestBed.inject(RoutingAllocatorService);
    expect(service).toBeTruthy();
  });
});
