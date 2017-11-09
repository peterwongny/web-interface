package com.web.webpage.UI;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.database.Tour;
import com.web.webpage.database.TourRepository;


@SpringView(name = TourView.VIEW_NAME)
@SpringComponent
@UIScope
public class TourView extends Panel implements View{

	
	public TourRepository tourRepo;
	private TextField filterText = new TextField();
	private TourForm form = new TourForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();


	public final static String VIEW_NAME = "Tour List";
	
	
	final Grid<Tour> grid;

	public TourView(TourRepository tourRepo) {
		this.tourRepo = tourRepo;
		this.grid = new Grid<>(Tour.class);
		grid.setSizeUndefined();
		

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
        	form.setTour(new Tour());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addTourBtn);

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);
        
        verticalLayout.addComponents(toolbar, main);
        
        updateList();
        
        form.setVisible(false);
        
        grid.asSingleSelect().addValueChangeListener(e -> {
        	if (e.getValue() == null) {
        		form.setVisible(false);
        	}
        	else {
        		form.setTour(e.getValue());

        	}
        });


	}
	
	@Override
    public void enter(ViewChangeEvent event) {
		updateList();
		verticalLayout.addComponent(grid);
		setContent(verticalLayout);

    }
	
	public void updateList() {
	    grid.setItems(tourRepo.findByName(filterText.getValue()));
	    grid.setColumnOrder("id", "name", "duration", "day", "weekday_price", "weekend_price", "description", "hits");
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
