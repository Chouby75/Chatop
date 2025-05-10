package com.chatop.datalayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.chatop.datalayer.entity.rentals;
import com.chatop.datalayer.entity.users;

@Repository
public interface rentalsRepository extends CrudRepository<rentals, Long> {
    Optional<rentals> findByName(String name);
    Optional<rentals> findByOwnerId(users owner_id);
}
