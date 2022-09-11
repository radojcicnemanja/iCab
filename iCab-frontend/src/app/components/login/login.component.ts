import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogService } from 'primeng/dynamicdialog';
import { LoginUser } from 'src/app/model/loginUser';
import { RegistrationDialog } from '../dialogs/registration/registrationDialog';
import { LoginService } from './login.service';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [DialogService]
})
export class LoginComponent implements OnInit {

  loginUser: LoginUser = new LoginUser()
  username: string = ""
  password: string = ""
  user: any

  constructor(private router: Router, 
    private _loginService: LoginService,
    private activeRoute: ActivatedRoute, 
    private dialogService: DialogService) { }

  ngOnInit(): void {
  }

  show() {
    const ref = this.dialogService.open(RegistrationDialog, {
      header: 'Registration',
      width: '70%'
    });
  }

  redirectToLandingPage() {

  }

  login() {
    this._loginService.login({ username: this.username, password: this.password })
      .subscribe(data => {
        localStorage.setItem('jwt', data.body.accessToken)
        let token = this.getDecodedAccessToken(data.body.accessToken)
        this._loginService.getMyInfo(token.sub).subscribe(response => { this.user = response 
        if (this.user.roles[0].name == "ROLE_ADMIN") {
          this.router.navigate(['landing-page/admin'])
        } else if (this.user.roles[0].name == "ROLE_CUSTOMER") {
          this.router.navigate(['landing-page/customer'])
        } else {
          this.router.navigate(['landing-page/driver'])
        }
      })
      })
  }

  public getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch(Error) {
      return null;
    }
  }

}
