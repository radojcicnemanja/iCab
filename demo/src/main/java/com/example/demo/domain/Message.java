package com.example.demo.domain;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @SequenceGenerator(name = "messageIdSeqGen", sequenceName = "messageIdSeq", initialValue = 14, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messageIdSeqGen")
    private Integer id;
    @Column(name = "chat_id")
    private Integer chat_id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "time_stamp")
    private String timeStamp;

    @Column(name = "content")
    private String content;

    public Message(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Integer chat_id) {
        this.chat_id = chat_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
