package com.github.programmerwars.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ClientLayout extends Composite {

   private static ClientUiBinder uiBinder = GWT.create(ClientUiBinder.class);

   @UiTemplate("ClientLayout.ui.xml")
   interface ClientUiBinder extends UiBinder<Widget, ClientLayout> {
   }

   @UiField
   Label completionLabel1;

   @UiField
   Label completionLabel2;

   @UiField(provided = true)
   final ClientResources res;

   public ClientLayout() {
      this.res = GWT.create(ClientResources.class);
      res.style().ensureInjected();
      initWidget(uiBinder.createAndBindUi(this));
   }

}
