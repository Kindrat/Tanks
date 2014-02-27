package ua.pirateparty.games.tanks.server.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import ua.pirateparty.games.tanks.server.persistence.domain.Player;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findByPassword(String password);
}
