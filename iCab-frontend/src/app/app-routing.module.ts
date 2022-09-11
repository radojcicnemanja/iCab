import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageAdminComponent } from './components/admin/home-page-admin/home-page-admin.component';
import { RequestsPageAdminComponent } from './components/admin/requests-page-admin/requests-page-admin.component';
import { UsersPageAdminComponent } from './components/admin/users-page-admin/users-page-admin.component';
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
        
        children: [
          {
            path: 'users',
            component: UsersPageAdminComponent
          },
          {
            path: 'requests',
            component: RequestsPageAdminComponent
          }
        ]
      }
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
