import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddSocialsComponent } from './components/add-socials/add-socials.component';
import { ApiViewComponent } from './components/api-view/api-view.component';
import { GameDisplayComponent } from './components/game-select/game-display/game-display.component';
import { UserViewComponent } from './components/group/user-view/user-view.component';
import { ViewGameGroupsComponent } from './components/group/view-game-groups/view-game-groups.component';
import { CreateGroupComponent } from './components/host-group/create-group/create-group.component';
import { HostViewComponent } from './components/host-group/host-view/host-view.component';
import { LFGMainComponent } from './components/lfgmain/lfgmain.component';
import { LoginComponent } from './components/login/login.component';
import { ModifyCredentialComponent } from './components/modify-credential/modify-credential.component';
import { ModifyProfileComponent } from './components/modify-profile/modify-profile.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { PollingComponent } from './components/polling/polling.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SocialViewComponent } from './components/social/social-view/social-view.component';

const routes: Routes = [
  {
    component: LoginComponent,
    path: ''
  },
  {
    component: NewUserComponent,
    path: 'new'
  },
  {
    component: LFGMainComponent,
    path: 'main'
  },
  {
    component: ProfileComponent,
    path: 'main/profile'
  },
  {
    component: ModifyProfileComponent,
    path: 'main/profile/modify'
  },
  {
    component: ModifyCredentialComponent,
    path: 'main/credential/modify'
  },
  {
    component: GameDisplayComponent,
    path: 'main/games'
  },
  {
    component: ViewGameGroupsComponent,
    path: 'game/group'
  },
  {
    component: UserViewComponent,
    path: 'game/group/view'
  },
  {
    component: HostViewComponent,
    path: 'game/group/host'
  },
  {
    component: CreateGroupComponent,
    path: 'game/group/create'
  },
  {
    component: ApiViewComponent,
    path: 'test/api'
  },
  {
    component: PollingComponent,
    path: 'test/polling'
  }, 
  {
    component: SocialViewComponent,
    path: 'main/socials'
  },
  {
    component: AddSocialsComponent,
    path: 'main/socials/modify'
  },
  {
    component: SocialViewComponent,
    path: 'test/social'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
    ],
  exports: [RouterModule]
  
})
export class AppRoutingModule { }

