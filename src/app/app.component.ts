import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { UserService } from "./shared/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  navigationLinks:any[];

  constructor(private _userService: UserService, private _router: Router) {
    this.navigationLinks = [
      { label: 'Home', link: 'home' },
      { label: 'Applications', link: 'applications' },
    ];
  }

  isAuthenticated(): boolean {
    return this._userService.isAuthenticated();
  }
}
