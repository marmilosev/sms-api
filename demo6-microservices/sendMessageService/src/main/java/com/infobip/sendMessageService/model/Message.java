package com.infobip.sendMessageService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
//@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn
    private User user;
//    private int userId;
    private String number;
    private Date dateTime;
    private String messageText;

    public Message() {

    }
}
