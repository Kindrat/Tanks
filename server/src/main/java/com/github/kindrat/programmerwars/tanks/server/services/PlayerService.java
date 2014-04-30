package com.github.kindrat.programmerwars.tanks.server.services;

import com.github.kindrat.programmerwars.tanks.server.persistence.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playerService")
public class PlayerService {

   @Autowired
   private PlayerRepository repository;

}
