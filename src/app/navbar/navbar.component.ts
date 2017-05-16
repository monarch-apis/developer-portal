import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { UserService } from "../shared/user.service";

@Component({
  selector: 'mdp-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: [ './navbar.component.css' ],
})
export class NavBarComponent {
  private sub: any;
  navigationLinks:any[];

  constructor(private _userService: UserService, private _router: Router) {
    this.navigationLinks = [
      { label: 'Home', link: 'home', icon: 'home' },
      { label: 'Applications', link: 'applications', icon: 'apps' },
      { label: 'Services', link: 'home', icon: 'settings' }
    ];
  }

  isAuthenticated(): boolean {
    return this._userService.isAuthenticated();
  }

  onClickNavItem(routerLink:string) {
    event.preventDefault();

    this._router.navigate([routerLink]);
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
