import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from "@angular/router";

import { UserService } from "./shared/user.service";
import { AuthenticationGuardService } from "./shared/authentication-guard.service";

import { AppComponent } from './app.component';
import { ToolBarComponent } from "./toolbar/toolbar.component";
import { NavBarComponent } from "./navbar/navbar.component";
import { AuthenticationComponent } from "./authentication/authentication.component";
import { HomeComponent } from "./home/home.component";
import { MaterialDesignModule } from "./shared/material.module";
import { ApplicationListComponent } from "./applications/application-list.component";


@NgModule({
  declarations: [
    AppComponent,
    ToolBarComponent,
    NavBarComponent,
    AuthenticationComponent,
    HomeComponent,
    ApplicationListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MaterialDesignModule,
    RouterModule.forRoot([
      { path: "home", component: HomeComponent, canActivate: [ AuthenticationGuardService ] },
      { path: "applications", component: ApplicationListComponent, canActivate: [ AuthenticationGuardService ] },
      { path: "signin", component: AuthenticationComponent },
      { path: "", redirectTo: "home", pathMatch: "full" },
      { path: "**", redirectTo: "home", pathMatch: "full" }
    ])
  ],
  providers: [
    UserService,
    AuthenticationGuardService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
