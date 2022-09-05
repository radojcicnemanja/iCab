import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogService } from 'primeng/dynamicdialog';
import { RegistrationDialog } from '../dialogs/registration/registrationDialog';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [DialogService]
})
export class LoginComponent implements OnInit {

  username: any;

  constructor(private router: Router, private activeRoute: ActivatedRoute, private dialogService: DialogService) { }

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

}
