import { Component, OnInit } from '@angular/core';
import { RequestService } from 'src/app/service/request/request.service';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-requests-page-admin',
  templateUrl: './requests-page-admin.component.html',
  styleUrls: ['./requests-page-admin.component.css']
})
export class RequestsPageAdminComponent implements OnInit {

  requests: any = []

  constructor(private _requestService: RequestService,
    private _userService: UserService) { }

  ngOnInit(): void {
    this._requestService.getAllRequests()
      .subscribe(response => this.requests = response)
  }

  refreshRequests(){
    this._requestService.getAllRequests()
      .subscribe(response => this.requests = response)
  }

  approve(username: any){
    this._requestService.approve(username).subscribe(() => {
      this._requestService.getAllRequests()
      .subscribe(response => this.requests = response)
    })
  }

  disapprove(username: any){
    this._requestService.disapprove(username).subscribe(() => {
      this._requestService.getAllRequests()
      .subscribe(response => this.requests = response)
    })
  }

}
