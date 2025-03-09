package com.eventmanagement.eventmanagement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
