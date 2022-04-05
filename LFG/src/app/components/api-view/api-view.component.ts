import { Component, OnInit } from '@angular/core';
import { SteamApiService } from 'src/app/_services/sessions/steam-api.service';

@Component({
  selector: 'app-api-view',
  templateUrl: './api-view.component.html',
  styleUrls: ['./api-view.component.css']
})
export class ApiViewComponent implements OnInit {

  constructor(
    private steamApiService:SteamApiService
  ) { }

  ngOnInit(): void {
  }

  getData(){
    this.steamApiService.printThing();
  }

}
