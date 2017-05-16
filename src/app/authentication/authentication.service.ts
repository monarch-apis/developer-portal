import { Injectable } from "@angular/core";
import { Http, Response, RequestOptions, Headers } from "@angular/http";
import { Observable } from "rxjs";

@Injectable()
export class AuthenticationService {

  constructor(private _http: Http) {
  }

  authenticate(username: string, password: string) {
    let headers = new Headers({ "Content-Type": "application/json" });
    let options = new RequestOptions({ headers: headers });
    let body = { username: username, password: password }

    return this._http.post("/api/authentication/authenticate", body, options)
      .catch(this.handleError);
  }

  private handleError(error: Response) {
    return Observable.throw(error.json().error || 'Server error');
  }
}
