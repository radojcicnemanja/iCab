import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { serverPort } from 'src/app/app.consts';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private _url = serverPort

  constructor(private http: HttpClient) { }

  public getAllRequests(): Observable<any>{
    return this.http.get<any>(this._url + 'registration/')
  }

  public approve(username: any){
    return this.http.post<any>(this._url + 'registration/approveRegistration', username)
  }

  public disapprove(username: any){
    return this.http.delete<any>(this._url + 'registration/' + username)
  }
}
