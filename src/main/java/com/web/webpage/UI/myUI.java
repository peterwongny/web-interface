package com.web.webpage.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.web.webpage.database.Tour;
import com.web.webpage.database.TourRepository;


@SpringUI
@Theme("valo")
public class myUI extends UI{
	
	private final TourRepository tourRepo;
	
	final Grid<Tour> grid;
	
	public myUI(TourRepository tourRepo) {
		this.tourRepo = tourRepo;
		this.grid = new Grid<>(Tour.class);
	}
	
	@Override
    protected void init(VaadinRequest request) {
		setContent(grid);
		listTours();
    }
	
	private void listTours() {
	    grid.setItems(tourRepo.findAll());
	    grid.setColumnOrder("id", "name", "description", "duration", "day", "weekday_price", "weekend_price", "hits");
	}
}