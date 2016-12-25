package com.github.kindrat.programmerwars.server.services;

import com.github.kindrat.programmerwars.server.persistence.entity.Player;
import com.github.kindrat.programmerwars.server.persistence.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playerService")
public class PlayerService {

    @Autowired
    private PlayerRepository repository;

    private PlayerService() {
    }

    public Player getPlayerByCredentials(String login, String password) {
        return repository.findByCredentials(login, password);
    }

    public Player getNewPlayer(String login, String password) {
        Player player = null;

        if (repository.isLoginUnique(login) == 0) {
            player = new Player();
            player.setLogin(login);
            player.setPassword(password);
            repository.save(player);
        }

        return player;
    }
}
