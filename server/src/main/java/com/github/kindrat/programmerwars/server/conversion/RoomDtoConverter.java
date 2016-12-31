package com.github.kindrat.programmerwars.server.conversion;

import com.github.kindrat.programmerwars.server.dto.RoomDto;
import com.github.kindrat.programmerwars.server.persistence.entity.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoConverter implements Converter<Room, RoomDto> {
    @Override
    public RoomDto convert(Room source) {
        return RoomDto.builder()
                .name(source.getName())
                .participants(source.getParticipants())
                .build();
    }
}
