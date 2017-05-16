import { Injectable } from "@angular/core";
import { Headers, RequestOptions, Response, Http } from "@angular/http";
import { Observable } from "rxjs";
import { User } from "./user";

@Injectable()
export class UserService {

  private _isAuthenticated: boolean = false;

  constructor(private _http: Http) {
  }

  setAuthenticated() {
    this._isAuthenticated = true;
  }

  setUnauthenticated() {
    this._isAuthenticated = false;
  }

  isAuthenticated(): boolean {
    return this._isAuthenticated;
  }

  getUser(): Observable<User> {
    let headers = new Headers({ "Content-Type": "application/json" });
    let options = new RequestOptions({ headers: headers });

    return this._http.get("/api/user", options)
      .map(this.handleSuccess)
      .do(data => console.log('All: ' + JSON.stringify(data)))
      .catch(this.handleError);
  }

  signOut() {
    return this._http.post("/api/user/signout", null)
      .catch(this.handleError);
  }

  private handleSuccess(response: Response) {
    let body = response.json();
    return body || {};
  }

  private handleError(error: Response) {
    return Observable.throw(error.json().error || 'Server error');
  }
}
