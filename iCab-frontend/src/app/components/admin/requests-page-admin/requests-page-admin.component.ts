import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-requests-page-admin',
  templateUrl: './requests-page-admin.component.html',
  styleUrls: ['./requests-page-admin.component.css']
})
export class RequestsPageAdminComponent implements OnInit {

  requests: any

  constructor() { }

  ngOnInit(): void {
  }

}
