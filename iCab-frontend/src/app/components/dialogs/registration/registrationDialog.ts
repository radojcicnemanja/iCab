import {Component} from '@angular/core';
import {DynamicDialogRef} from 'primeng/dynamicdialog';
import {DynamicDialogConfig} from 'primeng/dynamicdialog';

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

    constructor(public ref: DynamicDialogRef, public config: DynamicDialogConfig) { }

    ngOnInit() { }
}