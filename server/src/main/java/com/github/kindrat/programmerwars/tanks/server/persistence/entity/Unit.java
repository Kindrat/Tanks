package com.github.kindrat.programmerwars.tanks.server.persistence.entity;

import com.github.kindrat.programmerwars.tanks.common.dto.UnitDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Unit")
@Table(name = "units", schema = "player")
public class Unit implements DtoConvertible<UnitDto>{

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public UnitDto getAsApiDto() {
        return null;
    }

}
