package com.web.webpage.UI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.web.webpage.database.Tour;
import com.web.webpage.database.TourRepository;

public class CustomerView extends VerticalLayout implements View{
	private final TourRepository tourRepo;
	
	public final static String VIEW_NAME = "Customer Table";
	
	
	final Grid<Tour> grid;
	
	public CustomerView(TourRepository tourRepo) {
		this.tourRepo = tourRepo;
		this.grid = new Grid<>(Tour.class);
	}
	
	@Override
    public void enter(ViewChangeEvent event) {
		listTours();
		addComponent(grid);
    }
	
	private void listTours() {
	    grid.setItems(tourRepo.findAll());
	    grid.setColumnOrder("id", "name", "description", "duration", "day", "weekday_price", "weekend_price", "hits");
	}
}
