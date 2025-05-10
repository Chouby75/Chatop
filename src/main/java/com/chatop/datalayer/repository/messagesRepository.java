package com.chatop.datalayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.chatop.datalayer.entity.messages;


@Repository
public interface messagesRepository extends CrudRepository<messages, Long> {
    Optional<messages> findByName(String name);
    Optional<messages> findById(Long id);
}   
