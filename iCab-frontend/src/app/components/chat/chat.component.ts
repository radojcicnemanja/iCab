import { HttpClient } from '@angular/common/http';
import { AfterViewChecked, Component, ElementRef, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable, of } from 'rxjs';
import { UserService } from 'src/app/service/user/user.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { serverPort } from 'src/app/app.consts';
import { ChatService } from 'src/app/service/chat/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit,AfterViewChecked {

  url = serverPort;
  otherUser?: any;
  thisUser: any = JSON.parse(sessionStorage.getItem('user')!);
  channelName?: string;
  socket?: WebSocket;
  stompClient?: Stomp.Client;
  newMessage: any;
  messages?: Observable<Array<any>>;
  chats: any = [];
  cone2: string = 'tzone&cone2'
  @Input() username: string | undefined;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private http:HttpClient,
    private el: ElementRef,
    private chatService: ChatService) {}


  ngOnInit(): void {
    let currentUser = localStorage.getItem('currentUser')
    if(currentUser != null){
      this.thisUser = JSON.parse(currentUser)
    }
    let username = 'tzone'
    if (this.thisUser.username == 'tzone'){
      username = 'cone'
    }
    this.getChats()
    this.userService
      .getByUsername(username)
      .subscribe((data: any) => {
        this.otherUser = data;
        const nick1 = this.thisUser.username;
        const nick2 = this.otherUser.username;
        if (this.thisUser.roles[0].name == 'ROLE_CUSTOMER') {
          this.channelName = nick1 + '&' + nick2;
        } else {
          this.channelName = nick2 + '&' + nick1;
        }
        //this.otherUser.propic = "data:image/jpeg;base64,"+ this.otherUser.propic;
        this.connectToChat(this.channelName);
        console.log(this.el)
        this.el.nativeElement.querySelector("#chat").scrollIntoView();
      });
  }

  ngAfterViewChecked(): void {
    this.scrollDown();
  }

  scrollDown(){
    var container = this.el.nativeElement.querySelector("#chat");
    container.scrollTop = container.scrollHeight;
  }

  connectToChat(channel: string) {
    this.loadChat(channel);
    console.log('connecting to chat...');
    this.socket = new SockJS('http://localhost:8088/chat');
    this.stompClient = Stomp.over(this.socket);

    this.stompClient.connect({}, (frame) => {
      console.log('connected to: ' + frame);
      this.stompClient!.subscribe(
        '/topic/messages/' + channel,
        (response) => {
          this.messages?.subscribe(data => {
            let mgs:Array<any> = data;
            mgs.push(JSON.parse(response.body))
            mgs.sort((a, b) => (a.id > b.id) ? 1 : -1)
            this.messages = of(mgs);
          })
        }
      );
    });
  }

  sendMsg() {
    if (this.newMessage !== '') {
      this.stompClient!.send(
        '/app/chat/' + this.channelName,
        {},
        JSON.stringify({
          sender: this.thisUser.username,
          t_stamp: 'to be defined in server',
          content: this.newMessage,
        })
      );
      this.newMessage = ''
    }
  }

  loadChat(channel: string){
    this.messages = this.http.post<Array<any>>(this.url+'messages/getMessages' ,  channel);
    this.messages.subscribe(data => {
      let mgs:Array<any> = data;
      mgs.sort((a, b) => (a.id > b.id) ? 1 : -1)
      this.messages = of(mgs);
    })
    this.channelName = channel
    console.log(this.messages);
  }

  whenWasItPublished(myTimeStamp: string) {
    const endDate = myTimeStamp.indexOf('-');
    return (
      myTimeStamp.substring(0, endDate) +
      ' at ' +
      myTimeStamp.substring(endDate + 1)
    );
  }

  getChats(){
    this.chatService.getAllChats(this.thisUser.username).subscribe(data => {
      console.log(data)
      this.chats = data
    })
      console.log(this.chats + 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
  }

  connect(channel: string){
    this.stompClient?.disconnect(()=>{})
    this.connectToChat(channel)
  }

  transformChatName(chatName: string){
    const t = chatName.indexOf('-');
    let username = ''
    if(chatName.substring(0, t) != this.thisUser.username){
      username = chatName.substring(0, t)
    }else{
      username = chatName.substring(t)
    }
    this.userService
    .getByUsername(username)
    .subscribe((data: any) => {
      this.otherUser = data;
    });
    return this.otherUser.name + ' ' + this.otherUser.lastName
  }

}

