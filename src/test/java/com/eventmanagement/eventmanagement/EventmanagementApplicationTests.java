package com.eventmanagement.eventmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.eventmanagement.eventmanagement.AccesoDatos.models.Event;
import com.eventmanagement.eventmanagement.controllers.EventRestController;
import com.eventmanagement.eventmanagement.services.IEventServices;

@SpringBootTest
class EventmanagementApplicationTests {

	@Mock
	private IEventServices eventServices;

	@InjectMocks
    private EventRestController eventRestController;
	private Event event;

	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        event = new Event();
        event.setIdEvent(1);
        event.setTitleEvent("Evento de Prueba");
        event.setDateEvent(new Date());
        event.setDescriptionEvent("Descripción del evento de prueba");
        event.setLocationEvent("Ubicación de prueba");
    }
	@Test
    void testGetEvents() {
        List<Event> events = Arrays.asList(event);
        when(eventServices.findAll()).thenReturn(events);
        List<Event> response = eventRestController.getEvents();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Evento de Prueba", response.get(0).getTitleEvent());
        verify(eventServices, times(1)).findAll();
    }

    @Test
    void testCreateEvent() {
        when(eventServices.create(any(Event.class))).thenReturn(event);
        ResponseEntity<?> response = eventRestController.createEvent(event);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        verify(eventServices, times(1)).create(any(Event.class));
    }

    @Test
    void testFindById_EventExists() {
        when(eventServices.findById(1)).thenReturn(event);
        ResponseEntity<?> response = eventRestController.findById(1);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(eventServices, times(1)).findById(1);
    }

    @Test
    void testFindById_EventNotFound() {
        when(eventServices.findById(2)).thenReturn(null);
        ResponseEntity<?> response = eventRestController.findById(2);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(eventServices, times(1)).findById(2);
    }

    @Test
    void testUpdateEvent_Success() {
        when(eventServices.findById(1)).thenReturn(event);
        when(eventServices.update(any(Event.class), eq(1))).thenReturn(event);
        ResponseEntity<?> response = eventRestController.update(event, 1);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(eventServices, times(1)).update(any(Event.class), eq(1));
    }

    @Test
    void testUpdateEvent_NotFound() {
        when(eventServices.findById(2)).thenReturn(null);
        ResponseEntity<?> response = eventRestController.update(event, 2);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(eventServices, times(1)).findById(2);
        verify(eventServices, never()).update(any(Event.class), eq(2));
    }

    @Test
    void testDeleteEvent() {
        doNothing().when(eventServices).deleteById(1);
        ResponseEntity<?> response = eventRestController.delete(1);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(eventServices, times(1)).deleteById(1);
    }

}
