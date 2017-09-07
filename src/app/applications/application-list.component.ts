import { Component, OnInit } from "@angular/core";
import { ApplicationService } from './application.service';
import { Application } from './application';

@Component({
  templateUrl: './application-list.component.html',
  styleUrls: [ './application-list.component.css' ],
})
export class ApplicationListComponent implements OnInit {

  constructor(private _applicationService: ApplicationService) {

  }

  applications:Application[] = []

  ngOnInit() {
    this._applicationService.getApplication().subscribe(
      (body) => {
        this.applications = body
        console.log('sucess')
      },
      error => {
        console.log('fail')
      }
    )
  }
}
