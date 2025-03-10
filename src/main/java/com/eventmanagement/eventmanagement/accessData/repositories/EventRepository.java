package com.eventmanagement.eventmanagement.accessData.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventmanagement.eventmanagement.accessData.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    
}
