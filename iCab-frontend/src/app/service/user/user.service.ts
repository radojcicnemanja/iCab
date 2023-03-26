import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { serverPort } from 'src/app/app.consts';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _url = serverPort

  constructor(private http: HttpClient) { }

  public getAllUsers(){
    return this.http.get(this._url + 'users/')
  }

  public getAllDrivers(){
    return this.http.get(this._url + 'users/getDrivers')
  }

  public getByUsername(username: string){
    return this.http.get(this._url + 'users/getByUsername/' + username)
  }
}
