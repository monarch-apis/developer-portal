import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { UserService } from "../shared/user.service";
import { AuthenticationService } from "./authentication.service";

@Component({
  templateUrl: './authentication.component.html',
  styleUrls: [ './authentication.component.css' ],
  providers: [ AuthenticationService ]
})
export class AuthenticationComponent implements OnInit, OnDestroy {
  public username: string;
  public password: string;
  public isFormDisabled: boolean;

  private sub: any;

  constructor(private _authenticationService: AuthenticationService,
              private _userService: UserService,
              private _router: Router) {
  }

  ngOnInit() {
    this._userService.setUnauthenticated();
    this.enableForm();
  }

  ngOnDestroy() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }

  onSignInClicked() {
    this.disableForm();

    this.sub = this._authenticationService.authenticate(this.username, this.password)
      .subscribe(
        () => {
          this._userService.setAuthenticated();
          this._router.navigate(['/home']);
        },
        error => {
          this.enableForm();
          this.handleError(error);
        }
      );
  }

  private enableForm() {
    this.isFormDisabled = false;
  }

  private disableForm() {
    this.isFormDisabled = true;
  }

  private handleError(error: any) {
    let errMsg =
      (error.message) ? error.message : error.status ? `${error.status} - ${error.statusText}` :
          'There was an issue processing your request';
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
