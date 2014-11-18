package com.github.kindrat.programmerwars.tanks.server.persistence.entity;

import com.github.kindrat.programmerwars.tanks.common.PlayerState;
import com.github.kindrat.programmerwars.tanks.common.dto.PlayerDto;

import javax.persistence.*;
import java.security.Principal;

@Entity(name = "Player")
@Table(name = "profile", schema = "player")
public class Player implements Principal, DtoConvertible<PlayerDto> {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private long id;
   @Transient
   private PlayerState state;
   @Column(name = "first_name")
   private String firstName;
   @Column(name = "last_name")
   private String lastName;
   @Column(name = "nickname")
   private String nickname;
   @Column(name= "login")
   private String login;
   @Column(name = "password")
   private String password;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "role")
   private UserGroup userGroup;

   public Player(){}

   public Player(String login, String password) {
      this.login = login;
      this.password = password;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public PlayerState getState() {
      return state;
   }

   public void setState(PlayerState state) {
      this.state = state;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public UserGroup getUserGroup() {
      return userGroup;
   }

   public void setUserGroup(UserGroup userGroup) {
      this.userGroup = userGroup;
   }

   @Override
   public String getName() {
      return nickname;
   }

   @Override
   public PlayerDto getAsApiDto() {
      return null;
   }
}