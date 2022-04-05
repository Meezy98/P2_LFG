import { Component, OnInit } from '@angular/core';
import { interval, startWith, Subscription, switchMap } from 'rxjs';
import { PollingRequestsService } from 'src/app/_services/sessions/polling-requests.service';

@Component({
  selector: 'app-polling',
  templateUrl: './polling.component.html',
  styleUrls: ['./polling.component.css']
})
export class PollingComponent implements OnInit {

  timeInterval!: Subscription;

  status:any;
  constructor(private pollingRequests: PollingRequestsService) { }

  ngOnInit(): void {
    console.log("wow");
    // this.timeInterval = interval(500)
    //   .pipe(
    //     startWith(0),
    //     switchMap(() => this.pollingRequests.getResult())
    //   ).subscribe(res => console.log(res), err => console.log("lol"))
  };

  

  ngOnDestroy():void{
    console.log("bye")
    this.timeInterval.unsubscribe();
  }

}
