import { Component, OnInit } from '@angular/core';
import { RoutingAllocatorService } from 'src/app/_services/routing/routing-allocator.service';
import { TokenStorageService } from 'src/app/_services/user_data/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(
    private routingAllocater: RoutingAllocatorService,
    private tokenStorage: TokenStorageService) { }

  currentUser: any;
  stats: any;

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.stats = this.tokenStorage.getSocials();
  }

  modifyProfile(): void { this.routingAllocater.modifyProfile(); }
  modifyCredential(): void { this.routingAllocater.modifyCredentials(); }
  viewSocials(): void { this.routingAllocater.socialPage(); }
  goToMain(): void { this.routingAllocater.main(); }
}
