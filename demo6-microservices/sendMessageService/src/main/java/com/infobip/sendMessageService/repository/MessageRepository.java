package com.infobip.sendMessageService.repository;

import com.infobip.sendMessageService.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends CrudRepository<Message, Integer> {

//    @Query("select m from Message m where m.user=:idUser")
//    Message findByUser(@Param("idUser") int idUser);
}
