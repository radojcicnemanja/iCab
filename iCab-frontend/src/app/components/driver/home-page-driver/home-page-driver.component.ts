import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page-driver',
  templateUrl: './home-page-driver.component.html',
  styleUrls: ['./home-page-driver.component.css']
})
export class HomePageDriverComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  logout(){
    localStorage.removeItem('currentUser');
    this.router.navigate(['login'])
  }

}
