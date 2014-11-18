package com.github.kindrat.programmerwars.tanks.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.github.kindrat.programmerwars.tanks.server.persistence.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

   @Query("select player from Player player where player.login = :login and player.password = :password")
   public Player findByCredentials(@Param("login") String login, @Param("password") String password);

   @Query("select count(*) from Player player where player.login = :login")
   public int isLoginUnique(@Param("login") String login);

   public Player findByNicknameAndPassword(String login, String password);
}
