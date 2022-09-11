import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpParams} from '@angular/common/http';
import { catchError, filter, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { serverPort } from 'src/app/app.consts';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUser: any;
  private _url = serverPort
  private access_token = null;
  private loginHeaders = new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  });

  constructor(private _http: HttpClient, private router: Router) { }
  ngOnInit(): void {
    this.whoami()
  }

  login(user: any): Observable<any> {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    // const body = `username=${user.username}&password=${user.password}`;
    const body = {
      'username': user.username,
      'password': user.password
    };
    return this.post(this._url + 'login/', JSON.stringify(body), loginHeaders)
  }

  post(path: string, body: any, customHeaders?: HttpHeaders): Observable<any> {
    return this.request(path, body, 'POST', customHeaders);
  }

  private request(path: string, body: any, method: string, custemHeaders?: HttpHeaders): Observable<any> {
    const req = new HttpRequest(method, path, body, {
      headers: custemHeaders || this.loginHeaders,
    });

    return this._http.request(req).pipe(filter(response => response instanceof HttpResponse))
      .pipe(catchError(error => this.checkError(error)));
  }

  private checkError(error: any): any {
    throw error;
  }

  getMyInfo(username: string) {
    return this._http.get<any>(this._url + 'login/whoami/' + username)
    .pipe(map(user => {
      this.currentUser = user;
      localStorage.setItem('currentUser', JSON.stringify(user));
      return user;
    }));
  }

  whoami(){
    let jwt = localStorage.getItem('jwt')
    let token = this.getDecodedAccessToken(jwt? jwt : '')
    this.getMyInfo(token.sub).subscribe(user => {
      this.currentUser = user
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
function jwt_decode(token: string): any {
  throw new Error('Function not implemented.');
}

