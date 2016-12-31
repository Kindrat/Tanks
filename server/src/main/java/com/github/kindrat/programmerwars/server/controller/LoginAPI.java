package com.github.kindrat.programmerwars.server.controller;

import com.github.kindrat.programmerwars.server.conversion.ConverterFactory;
import com.github.kindrat.programmerwars.server.dto.RoomDto;
import com.github.kindrat.programmerwars.server.persistence.entity.Room;
import com.github.kindrat.programmerwars.server.persistence.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequiredArgsConstructor
public class LoginAPI {

    private final RoomRepository repository;
    private final ConverterFactory converterFactory;

    @RequestMapping(method = GET, name = "/login")
    public List<RoomDto> login() {
        List<Room> rooms = repository.findAll();
        return converterFactory.convert(rooms, RoomDto.class);
    }
}
