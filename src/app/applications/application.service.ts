import { Injectable } from "@angular/core";
import { Headers, RequestOptions, Response, Http } from "@angular/http";
import { Observable } from "rxjs";
import { Application } from "./application";

@Injectable()
export class ApplicationService {


  constructor(private _http: Http) {
  }

  

  getApplication(): Observable<Application[]> {
    return this._http.get("/api/application")
      .map(this.handleSuccess)
      .do(data => console.log('All: ' + JSON.stringify(data)))
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
