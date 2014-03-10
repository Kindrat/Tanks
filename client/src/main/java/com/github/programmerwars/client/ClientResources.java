package com.github.programmerwars.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ClientResources extends ClientBundle {

   public interface MyCss extends CssResource {
      String blackText();
   }

   @Source("ClientResources.css")
   MyCss style();
}
