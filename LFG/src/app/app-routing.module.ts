import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApiViewComponent } from './components/api-view/api-view.component';
import { GameDisplayComponent } from './components/game-select/game-display/game-display.component';
import { ViewGameGroupsComponent } from './components/group/view-game-groups/view-game-groups.component';
import { LFGMainComponent } from './components/lfgmain/lfgmain.component';
import { LoginComponent } from './components/login/login.component';
import { ModifyCredentialComponent } from './components/modify-credential/modify-credential.component';
import { ModifyProfileComponent } from './components/modify-profile/modify-profile.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { ProfileComponent } from './components/profile/profile.component';

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
    path: 'game/:id'
  },
  {
    component: ApiViewComponent,
    path: 'test/api'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
    ],
  exports: [RouterModule]
  
})
export class AppRoutingModule { }

