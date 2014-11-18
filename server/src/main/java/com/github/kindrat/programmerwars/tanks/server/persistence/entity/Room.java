package com.github.kindrat.programmerwars.tanks.server.persistence.entity;

import com.github.kindrat.programmerwars.tanks.common.dto.RoomDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Room")
@Table(name = "rooms", schema = "static")
public class Room implements DtoConvertible<RoomDto>{

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public RoomDto getAsApiDto() {
        return null;
    }
}
