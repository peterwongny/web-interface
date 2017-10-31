package com.web.webpage.UI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.web.webpage.database.Tour;
import com.web.webpage.database.TourRepository;

public class ReportView extends VerticalLayout implements View{
	
	public final static String VIEW_NAME = "Regular Report";
	

	
	@Override
    public void enter(ViewChangeEvent event) {
		generateInfo();
    }
	
	private void generateInfo() {
		addComponent(new Label("Coming Soon!"));
	}
	
}
