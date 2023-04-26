package com.infobip.demo4.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Table(name = "messages")
//@Builder
public class Message{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;
    @ManyToOne
    @JoinColumn
    private User user;
    private String number;
    private Date dateTime;
    private String messageText;

    public Message() {

    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
