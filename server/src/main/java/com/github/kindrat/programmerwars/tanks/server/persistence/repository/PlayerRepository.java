package com.github.kindrat.programmerwars.tanks.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.github.kindrat.programmerwars.tanks.server.persistence.domain.Player;

import java.sql.SQLException;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

   @Query("select p from Player p where p.login = :login and p.password = :password")
   public Player findByCredentials(@Param("login") String login, @Param("password") String password) throws SQLException;

   @Query("select count(*) from Player p where p.login = :login")
   public int isLoginUnique(@Param("login") String login) throws SQLException;
}
