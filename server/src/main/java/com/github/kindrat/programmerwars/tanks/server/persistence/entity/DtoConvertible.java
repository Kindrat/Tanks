package com.github.kindrat.programmerwars.tanks.server.persistence.entity;

import com.github.kindrat.programmerwars.tanks.common.dto.BaseDto;

public interface DtoConvertible<DTO extends BaseDto> {
    DTO getAsApiDto();
}
