import { Component, Input, OnInit } from '@angular/core';
import { SessionStorageService } from 'src/app/_services/sessions/session-storage.service';
import { UserService } from 'src/app/_services/user_data/user.service';

@Component({
  selector: 'app-user-stats',
  templateUrl: './user-stats.component.html',
  styleUrls: ['./user-stats.component.css']
})
export class UserStatsComponent implements OnInit {

  @Input()
  applicant!:any;

  userStats!:any;

  achievements!:any;

  constructor(
    private sessionStorage: SessionStorageService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getUserStats();
  }

  getUserStats():void{
    console.log(this.sessionStorage.getCreatedGroup().gameId);
    console.log(this.applicant);
    this.findAllStats();
  }

  public findAllStats():void{
    this.userService.getAchievementsWithUsername(this.applicant.username,this.sessionStorage.getCreatedGroup().gameId).subscribe(
      (data) => {
        console.log(data.playerstats);
        if(data.playerstats.achievements){
            this.achievements = data.playerstats.achievements.length;
            this.userStats = data.playerstats.stats;
          };          
        }
      )
    } 
  }


