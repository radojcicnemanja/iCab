import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page-customer',
  templateUrl: './home-page-customer.component.html',
  styleUrls: ['./home-page-customer.component.css']
})
export class HomePageCustomerComponent implements OnInit {

  activeIndex: number = 0
  user: any

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  do(data: any){
    this.activeIndex = data.activeIndex
    this.user = data.user
  }

  logout(){
    localStorage.removeItem('currentUser');
    this.router.navigate(['login'])
  }

}
