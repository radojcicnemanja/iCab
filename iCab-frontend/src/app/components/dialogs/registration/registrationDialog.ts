import {Component} from '@angular/core';
import {DynamicDialogRef} from 'primeng/dynamicdialog';
import {DynamicDialogConfig} from 'primeng/dynamicdialog';
import { RegistrationService } from '../../login/registration.service';

@Component({
    templateUrl: './registrationDialog.html',
})
export class RegistrationDialog {

    user: string = 'Customer'
    username: string = ''
    password: string = ''
    confirmPassword: string = ''
    name: string = ''
    lastName: string = ''
    email: string = ''
    phoneNumber: string = ''
    carDescription: string = ''

    constructor(public ref: DynamicDialogRef, 
        public config: DynamicDialogConfig,
        private _registrationService: RegistrationService) { }

    ngOnInit() { }

    registerUser(){
        let user = {
            "name": this.name,
            "lastName": this.lastName,
            "email": this.email,
            "phoneNumber": this.phoneNumber,
            "password": this.password,
            "username": this.username,
            "verificationCode": ""
        }
        this._registrationService.registerUser(user).subscribe(
            data => console.log('success', data),
            error => console.log('error', error))
    }
}