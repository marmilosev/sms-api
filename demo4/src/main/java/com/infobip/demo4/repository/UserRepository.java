package com.infobip.demo4.repository;

import com.infobip.demo4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username=:username")
    User findByUsername(@Param("username") String username);

    @Query ("select u.idUser from User u where u.number=:number")
    User findByNumber(@Param("number") String number);

//    @Query("select u from User u where u.username=:username")
//    User findByUsernameAuth(@Param("username") String username);
}
