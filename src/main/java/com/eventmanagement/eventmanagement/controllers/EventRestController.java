package com.eventmanagement.eventmanagement.controllers;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.eventmanagement.AccesoDatos.models.Event;
import com.eventmanagement.eventmanagement.services.IEventServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class EventRestController {
    @Autowired 
    private IEventServices eventServicesObj;

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventServicesObj.findAll();
    }

    @PostMapping("/events")
    public ResponseEntity<?> createEvent(@RequestBody Event eventParam) {
        Event eventObj = null;
        HashMap<String, Object> responses = new HashMap();
        ResponseEntity<?> response = null;
        try {
            eventObj = eventServicesObj.create(eventParam);
            response = new ResponseEntity<Event>(eventObj, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            responses.put("mensaje", "Error al crear el evento");
            responses.put("error", e.getMessage());
            response = ResponseEntity.badRequest().body(responses);
        }
        return response;
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Event eventObj = null;
        HashMap<String, Object> responses = new HashMap();
        ResponseEntity<?> response = null;
        try {
            eventObj = eventServicesObj.findById(id);
            if (eventObj == null) {
                responses.put("mensaje", "El evento con ID: ".concat(id.toString().concat(" no existe")));
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responses);
            } else {
                response = new ResponseEntity<Event>(eventObj, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            responses.put("mensaje", "Error al realizar la consulta");
            responses.put("error", e.getMessage());
            response = ResponseEntity.badRequest().body(responses);
        }
        return response;
    }

    @PutMapping("events/{id}")
    public ResponseEntity<?> update(@RequestBody Event product, @PathVariable Integer id) {
        Event eventObj = null;
        HashMap<String, Object> respuestas = new HashMap();
        ResponseEntity<?> response;
        try {
            eventObj = eventServicesObj.findById(id);
            if (eventObj == null) {
                respuestas.put("mensaje", "No se pudo actualizar el evento con ID: ".concat(id.toString()));
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestas);
            } else {
                eventObj = eventServicesObj.update(product, id);
                response = new ResponseEntity<Event>(eventObj, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            respuestas.put("mensaje", "Error al actualizar el evento");
            respuestas.put("error", e.getMessage());
            response = ResponseEntity.badRequest().body(respuestas);
        }
        return response;
    }
    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        HashMap<String, Object> responses = new HashMap();
        ResponseEntity<?> response = null;
        try {
            eventServicesObj.deleteById(id);
            responses.put("mensaje", "Evento eliminado con éxito");
            response = new ResponseEntity<HashMap<String, Object>>(responses, HttpStatus.OK);
        } catch (DataAccessException e) {
            responses.put("mensaje", "Error al eliminar el evento");
            responses.put("error", e.getMessage());
            response = ResponseEntity.badRequest().body(responses);
        }
        return response;
    }
    
    
    
}
