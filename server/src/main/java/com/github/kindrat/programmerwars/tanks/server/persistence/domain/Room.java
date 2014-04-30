package com.github.kindrat.programmerwars.tanks.server.persistence.domain;

import javax.persistence.*;

@Entity(name = "room")
@Table(name = "static.rooms")
public class Room {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private long id;

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }
}
