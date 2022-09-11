import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { serverPort } from 'src/app/app.consts';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private _url = serverPort;

  constructor(private http: HttpClient) { }

  public registerUser(user: any){
    return this.http.post<any>(this._url + 'registration/', user);
  }
}
