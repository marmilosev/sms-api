package com.ffos.sendmessageservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMessage;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn
    private User user;
    private String number;
    private Date dateTime;
    private String messageText;

    public Message() {

    }
}
