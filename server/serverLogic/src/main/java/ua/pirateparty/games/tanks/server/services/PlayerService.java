package ua.pirateparty.games.tanks.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pirateparty.games.tanks.server.persistence.domain.Player;
import ua.pirateparty.games.tanks.server.persistence.repository.PlayerRepository;

@Service("playerService")
public class PlayerService {

   @Autowired
   private PlayerRepository repository;

   private PlayerService(){}

   public Player getPlayerByCredentials(String login, String password){
      return repository.findByCredentials(login, password);
   }

   public Player getNewPlayer(String login, String password) {
      Player player = null;

      if (repository.isLoginUnique(login) == 0){
         player = new Player(login, password);
         repository.save(player);
      }

      return player;
   }
}
