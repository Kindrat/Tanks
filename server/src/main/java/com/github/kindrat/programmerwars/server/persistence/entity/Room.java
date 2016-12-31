package com.github.kindrat.programmerwars.server.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "Room")
@Table(name = "rooms")
public class Room {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "participants")
    private Integer participants;
}
