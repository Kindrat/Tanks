package com.github.kindrat.programmerwars.tanks.server.persistence.domain;

import javax.persistence.*;

@Entity(name = "unit")
@Table(name = "player.units")
public class Unit {
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
