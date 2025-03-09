package com.eventmanagement.eventmanagement.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.eventmanagement.eventmanagement.models.Event;
import com.eventmanagement.eventmanagement.repositories.EventRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventImplServices implements IEventServices {

    @Autowired
    private EventRepository eventRepositoryObj;
    
    @Override
    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return (List<Event>) this.eventRepositoryObj.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Event findById(int id) {
        return this.eventRepositoryObj.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Event create(Event event) {
        return this.eventRepositoryObj.save(event);
    }

    @Override
    public void deleteById(int id) {
        this.eventRepositoryObj.deleteById(id);
    }

    @Override
    public Event update(Event event, int id) {
        Event eventObj = this.eventRepositoryObj.findById(id).orElse(null);
        if (eventObj != null) {
            eventObj.setTitleEvent(event.getTitleEvent());
            eventObj.setDateEvent(event.getDateEvent());
            eventObj.setDescriptionEvent(event.getDescriptionEvent());
            eventObj.setLocationEvent(event.getLocationEvent());
            return this.eventRepositoryObj.save(eventObj);
        }
        return null;
    }    
}
