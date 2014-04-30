package com.github.kindrat.programmerwars.tanks.server.services;

import com.github.kindrat.programmerwars.tanks.server.persistence.domain.Player;
import com.github.kindrat.programmerwars.tanks.server.persistence.domain.Room;
import com.github.kindrat.programmerwars.tanks.server.persistence.repository.PlayerRepository;
import com.github.kindrat.programmerwars.tanks.server.services.exceptions.DaoException;
import com.github.kindrat.programmerwars.tanks.server.services.exceptions.ErrorCode;
import com.github.kindrat.programmerwars.tanks.server.services.exceptions.ServerLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;
import java.util.List;

@Service("lobbyService")
public class LobbyService
{
   private static final Logger LOGGER = LoggerFactory.getLogger(LobbyService.class);

   private PlayerRepository playerRepository;

   @Autowired
   public LobbyService(PlayerRepository playerRepository)
   {
      this.playerRepository = playerRepository;
   }

   public List<Room> getActiveRooms()
   {
      throw new NotImplementedException();
   }

   public Player getPlayerByCredentials(String login, String password) throws DaoException, ServerLogicException
   {
      try
      {
         Player player = playerRepository.findByCredentials(login, password);
         if (isPlayerLoaded(player))
         {
            return player;
         }
         else
         {
            throw new ServerLogicException(ErrorCode.PLAYER_NOT_LOADED);
         }
      }
      catch (SQLException e)
      {
         LOGGER.error("Database error while loading player : {}", e);
         throw new DaoException();
      }


   }

   public Player getNewPlayer(String login, String password) throws DaoException, ServerLogicException
   {
      try
      {
         if (playerRepository.isLoginUnique(login) == 0)
         {
            Player player = playerRepository.save(new Player(login, password));
            if (isPlayerLoaded(player))
            {
               return player;
            }
            else
            {
               throw new ServerLogicException(ErrorCode.PLAYER_NOT_LOADED);
            }
         }
         else
         {
            throw new ServerLogicException(ErrorCode.DUPLICATE_PLAYER_LOGIN);
         }

      }
      catch (SQLException e)
      {
         LOGGER.error("Database error while loading player : {}", e);
         throw new DaoException();
      }
   }

   private boolean isPlayerLoaded(Player player)
   {
      return player != null && player.getId() > 0;
   }
}
