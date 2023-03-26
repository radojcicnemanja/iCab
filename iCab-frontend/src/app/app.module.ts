import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {ImageModule} from 'primeng/image';
import {DynamicDialogModule} from 'primeng/dynamicdialog';
import {StepsModule} from 'primeng/steps';
import {RadioButtonModule} from 'primeng/radiobutton';
import {PasswordModule} from 'primeng/password';
import {DividerModule} from 'primeng/divider';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {TabViewModule} from 'primeng/tabview';
import {TableModule} from 'primeng/table';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {CardModule} from 'primeng/card';
import {PanelModule} from 'primeng/panel';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import {RatingModule} from 'primeng/rating';
import {ScrollerModule} from 'primeng/scroller';

import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationDialog } from './components/dialogs/registration/registrationDialog';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { HomePageAdminComponent } from './components/admin/home-page-admin/home-page-admin.component';
import { UsersPageAdminComponent } from './components/admin/users-page-admin/users-page-admin.component';
import { RequestsPageAdminComponent } from './components/admin/requests-page-admin/requests-page-admin.component';
import { HttpClientModule } from '@angular/common/http';
import { ChatComponent } from './components/chat/chat.component';
import { HomePageCustomerComponent } from './components/customer/home-page-customer/home-page-customer.component';
import { UsersPageCustomerComponent } from './components/customer/users-page-customer/users-page-customer.component';
import { HomePageDriverComponent } from './components/driver/home-page-driver/home-page-driver.component';
import { ReviewsPageDriverComponent } from './components/driver/reviews-page-driver/reviews-page-driver.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationDialog,
    LandingPageComponent,
    HomePageAdminComponent,
    UsersPageAdminComponent,
    RequestsPageAdminComponent,
    ChatComponent,
    HomePageCustomerComponent,
    UsersPageCustomerComponent,
    HomePageDriverComponent,
    ReviewsPageDriverComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    InputTextModule,
    FormsModule,
    ImageModule,
    DynamicDialogModule,
    BrowserAnimationsModule,
    StepsModule,
    RadioButtonModule,
    PasswordModule,
    DividerModule,
    InputTextareaModule,
    TabViewModule,
    TableModule,
    HttpClientModule ,
    MessagesModule,
    MessageModule,
    CardModule,
    PanelModule,
    ScrollPanelModule,
    RatingModule,
    ScrollerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
