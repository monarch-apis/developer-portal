import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, ActivatedRoute } from '@angular/router';
import { UserService } from "./user.service";
import { Observable } from "rxjs";

@Injectable()
export class AuthenticationGuardService implements CanActivate {

  constructor(private _userService: UserService, private _router: Router, private _route: ActivatedRoute) {
  }

  canActivate(route: ActivatedRouteSnapshot) {
    if (this._userService.isAuthenticated()) {
      return Observable.of(true);
    }
    else {
      return this._userService.getUser().map(user => {
        if (user) {
          this._userService.setAuthenticated();
          return true;
        }
      }).catch(() => {
        this._router.navigate(['/signin']);
        return Observable.of(false);
      });
    }
  }
}



