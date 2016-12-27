package com.github.kindrat.programmerwars.server.controller;

import com.github.kindrat.programmerwars.server.dto.RoomDto;
import com.github.kindrat.programmerwars.server.persistence.entity.Player;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LoginAPI {
    @RequestMapping(method = POST, params = "/login")
    public List<RoomDto> login(Player player) {
        return Collections.emptyList();
    }

    @RequestMapping(method = GET, params = "/test")
    public String login() {
        return "Success";
    }
}
