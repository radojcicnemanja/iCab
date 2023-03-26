import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageAdminComponent } from './components/admin/home-page-admin/home-page-admin.component';
import { RequestsPageAdminComponent } from './components/admin/requests-page-admin/requests-page-admin.component';
import { UsersPageAdminComponent } from './components/admin/users-page-admin/users-page-admin.component';
import { ChatComponent } from './components/chat/chat.component';
import { HomePageCustomerComponent } from './components/customer/home-page-customer/home-page-customer.component';
import { HomePageDriverComponent } from './components/driver/home-page-driver/home-page-driver.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'landing-page',
    component: LandingPageComponent,

    children: [
      {
        path: 'admin',
        component: HomePageAdminComponent,
      },
      {
        path: 'customer',
        component: HomePageCustomerComponent,
      },
      {
        path: 'driver',
        component: HomePageDriverComponent,
      }
    ]
  },
  {
    path: 'chat',
    component: ChatComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
