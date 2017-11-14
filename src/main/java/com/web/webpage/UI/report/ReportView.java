package com.web.webpage.UI.report;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ReportView extends VerticalLayout implements View{
	
	public final static String VIEW_NAME = "Regular Report";
	

	
	@Override
    public void enter(ViewChangeEvent event) {
		addComponent(new Label("Coming Soon!"));
    }
	

}
