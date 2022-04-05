import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LFGMainComponent } from './components/lfgmain/lfgmain.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ModifyProfileComponent } from './components/modify-profile/modify-profile.component';

import { authInterceptorProviders } from '../_helpers/auth.interceptor';
import { GameDisplayComponent } from './components/game-select/game-display/game-display.component';
import { GameCardComponent } from './components/game-select/game-card/game-card.component';
import { ViewGameGroupsComponent } from './components/group/view-game-groups/view-game-groups.component';
import { GroupCardComponent } from './components/group/group-card/group-card.component';
import { HostViewComponent } from './components/host-group/host-view/host-view.component';
import { CreateGroupComponent } from './components/host-group/create-group/create-group.component';
import { ModifyCredentialComponent } from './components/modify-credential/modify-credential.component';
import { UserViewComponent } from './components/group/user-view/user-view.component';
import { PollingComponent } from './components/polling/polling.component';
import { WaitingRoomComponent } from './components/host-group/waiting-room/waiting-room.component';
import { GroupMembersComponent } from './components/host-group/group-members/group-members.component';
import { GroupSessionComponent } from './components/group/user-view/group-session/group-session.component';
import { UserWaitingRoomComponent } from './components/group/user-view/user-waiting-room/user-waiting-room.component';
import { AddSocialsComponent } from './components/add-socials/add-socials.component';
import { SocialViewComponent } from './components/social/social-view/social-view.component';
import { SocialProfileComponent } from './components/social/social-profile/social-profile.component';
import { StatsComponent } from './components/social/stats/stats.component';
import { UserStatsComponent } from './components/host-group/user-stats/user-stats.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LFGMainComponent,
    NewUserComponent,
    ProfileComponent,
    ModifyProfileComponent,
    GameDisplayComponent,
    GameCardComponent,
    ViewGameGroupsComponent,
    GroupCardComponent,
    HostViewComponent,
    CreateGroupComponent,
    ModifyCredentialComponent,
    UserViewComponent,
    WaitingRoomComponent,
    GroupMembersComponent,
    GroupSessionComponent,
    UserWaitingRoomComponent,
    PollingComponent,
    AddSocialsComponent,
    SocialViewComponent,
    SocialProfileComponent,
    StatsComponent,
    UserStatsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
platformBrowserDynamic().bootstrapModule(AppModule)
