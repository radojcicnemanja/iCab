import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-users-page-customer',
  templateUrl: './users-page-customer.component.html',
  styleUrls: ['./users-page-customer.component.css']
})
export class UsersPageCustomerComponent implements OnInit {
  @Output() activeIndex = new EventEmitter<any>();
  users: any = []

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllDrivers().subscribe(data => {
      this.users = data
      console.log(data)
    })
  }

  goToChat(user: any){
    const param = {
      activeIndex: 1,
      user: user
    }
    this.activeIndex.emit(param)
  }

}
