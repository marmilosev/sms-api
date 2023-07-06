package com.ffos.sendmessageservice.repository;

import com.ffos.sendmessageservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
