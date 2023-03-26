package com.example.demo.domain;

import javax.persistence.*;

@Entity
public class Chat {

    @Id
    @SequenceGenerator(name = "chatIdSeqGen", sequenceName = "chatIdSeq", initialValue = 14, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatIdSeqGen")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Chat(){}

    public Chat(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
