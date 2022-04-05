import { Component, Input, OnInit } from '@angular/core';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  @Input()
  stats!:any;

  @Input()
  achievements!:any;

  constructor(
    private routingAllocator: RoutingAllocatorService
  ) { }

  ngOnInit(): void {
    console.log(this.stats);
  }


}
