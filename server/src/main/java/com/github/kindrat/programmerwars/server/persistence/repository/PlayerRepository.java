package com.github.kindrat.programmerwars.server.persistence.repository;

import com.github.kindrat.programmerwars.server.persistence.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select player from Player player where player.login = :login and player.password = :password")
    Player findByCredentials(@Param("login") String login, @Param("password") String password);

    @Query("select count(ALL player) from Player player where player.login = :login")
    int isLoginUnique(@Param("login") String login);

    Player findByNicknameAndPassword(String login, String password);
}
