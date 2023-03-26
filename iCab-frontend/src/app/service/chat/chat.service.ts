import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { serverPort } from 'src/app/app.consts';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private _url = serverPort

  constructor(private http: HttpClient) { }

  public getAllChats(username: string){
    return this.http.get(this._url + 'messages/getChats/' + username)
  }
}
