package com.eventmanagement.eventmanagement.AccesoDatos.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvent;

    @Column(nullable = false)
    private String titleEvent;

    @Column(nullable = false)
    private Date dateEvent;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descriptionEvent;

    @Column(nullable = false)
    private String locationEvent;
}
