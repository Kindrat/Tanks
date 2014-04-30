package com.github.kindrat.programmerwars.tanks.common.network;

public class Header
{
   private final String name;
   private final Object value;

   public Header(String name, Object value)
   {
      this.name = name;
      this.value = value;
   }

   public String getName()
   {
      return name;
   }

   public Object getValue()
   {
      return value;
   }
}
