package com.eventmanagement.eventmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.eventmanagement.eventmanagement.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    
}
