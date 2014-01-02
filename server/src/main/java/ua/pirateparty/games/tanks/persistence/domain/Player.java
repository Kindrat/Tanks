package ua.pirateparty.games.tanks.persistence.domain;

import ua.pirateparty.games.tanks.server.entities.player.State;

import javax.persistence.*;

@Entity(name = "player.profile")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Transient
    private State state;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
}
