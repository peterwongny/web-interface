package com.web.webpage.UI;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.database.Tour;
import com.web.webpage.database.TourRepository;


public class TourView extends VerticalLayout implements View{
	
	public TourRepository tourRepo;
	private TextField filterText = new TextField();
	private TourForm form = new TourForm(this);

	public final static String VIEW_NAME = "Tour List";
	
	
	final Grid<Tour> grid;
	
	public TourView(TourRepository tourRepo) {
		this.tourRepo = tourRepo;
		this.grid = new Grid<>(Tour.class);
		grid.setSizeFull();
		
		filterText.setPlaceholder("filter by name...");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());
        
        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addTourBtn = new Button("Add new tour");
        addTourBtn.addClickListener(e -> {
        	grid.asSingleSelect().clear();
        	form.setCustomer(new Tour());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addTourBtn);

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);
        
        addComponents(toolbar, main);
        
        updateList();

        form.setVisible(false);
        
        grid.asSingleSelect().addValueChangeListener(e -> {
        	if (e.getValue() == null) {
        		form.setVisible(false);
        	}
        	else {
        		form.setCustomer(e.getValue());
        	}
        });


	}
	
	@Override
    public void enter(ViewChangeEvent event) {
		updateList();
		addComponent(grid);
    }
	
	public void updateList() {
	    grid.setItems(tourRepo.findByName(filterText.getValue()));
	    grid.setColumnOrder("id", "name", "description", "duration", "day", "weekday_price", "weekend_price", "hits");
	}
	
	public void delete(Tour tour) {
		tourRepo.delete(tour);
		updateList();
	}
	
	public void save(Tour tour) {
		tourRepo.saveAndFlush(tour);
		updateList();
	}
}
