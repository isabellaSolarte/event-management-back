package com.eventmanagement.eventmanagement.services;
import java.util.List;

import com.eventmanagement.eventmanagement.AccesoDatos.models.Event;
public interface IEventServices {
    public List<Event> findAll();
    public Event findById(int id);
    public Event create(Event event);
    public void deleteById(int id);
    public Event update(Event event, int id);
}
