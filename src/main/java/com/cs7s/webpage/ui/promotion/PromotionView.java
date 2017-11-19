package com.cs7s.webpage.ui.promotion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PromotionView extends VerticalLayout implements View{
	
	public final static String VIEW_NAME = "Promotion";
	

	
	@Override
    public void enter(ViewChangeEvent event) {
		addComponent(new Label("Promotion coming Soon!"));
    }
	

}
