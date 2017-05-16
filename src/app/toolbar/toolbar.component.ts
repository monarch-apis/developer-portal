import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { UserService } from "../shared/user.service";

@Component({
  selector: 'mdp-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: [ './toolbar.component.css' ],
})
export class ToolBarComponent {
  private sub: any;
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

  onSignOutClicked(event) {
    event.preventDefault();

    this.sub = this._userService.signOut()
      .subscribe(
        () => {
          this._userService.setUnauthenticated();
          this._router.navigate(['/signin']);
        },
        () => {
          this._userService.setUnauthenticated();
          this._router.navigate(['/signin']);
        }
      );
  }
}
