package com.chatop.datalayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.chatop.datalayer.entity.users;

@Repository
public interface usersRepository extends CrudRepository<users, Long> {
    Optional<users> findByEmail(String email);
    Optional<users> findByName(String name);
}
