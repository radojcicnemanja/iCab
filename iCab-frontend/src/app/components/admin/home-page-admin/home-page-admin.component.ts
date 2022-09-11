import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home-page-admin',
  templateUrl: './home-page-admin.component.html',
  styleUrls: ['./home-page-admin.component.css']
})
export class HomePageAdminComponent implements OnInit {

  constructor(private router: Router, private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  redirectToUsers(){
    this.router.navigate(['users'], {relativeTo: this.activeRoute})
  }

  redirectToRequests(){
    this.router.navigate(['requests'], {relativeTo: this.activeRoute})
  }

}
