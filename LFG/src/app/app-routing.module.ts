import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LFGMainComponent } from './lfgmain/lfgmain.component';
import { LoginComponent } from './login/login.component';
import { ModifyProfileComponent } from './modify-profile/modify-profile.component';
import { NewUserComponent } from './new-user/new-user.component';
import { ProfileComponent } from './profile/profile.component';

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
    path: 'profile'
  },
  {
    component: ModifyProfileComponent,
    path: 'profile/modify'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }