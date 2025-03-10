package com.eventmanagement.eventmanagement.AccesoDatos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventmanagement.eventmanagement.AccesoDatos.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    
}
