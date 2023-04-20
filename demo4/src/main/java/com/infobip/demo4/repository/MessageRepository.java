package com.infobip.demo4.repository;

import com.infobip.demo4.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query("select m from Message m where m.user=:idUser")
    Message findByUser(@Param("idUser") int idUser);
}