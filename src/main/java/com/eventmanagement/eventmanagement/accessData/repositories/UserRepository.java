package com.eventmanagement.eventmanagement.accessData.repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventmanagement.eventmanagement.accessData.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findByName(String name);
    boolean existsByName(String name);
}
