package com.web.webpage.UI.tourList;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.vaadin.data.provider.ListDataProvider;
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
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.database.Tour;
import com.web.webpage.database.TourRepository;


@SpringView(name = TourView.VIEW_NAME)
@SpringComponent
@UIScope
public class TourView extends Panel implements View{

	
	public TourRepository tourRepo;
	private TourForm form = new TourForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();


	public final static String VIEW_NAME = "Tour List";
	
	
	private final Grid<Tour> grid;
	private ListDataProvider<Tour> provider;

	public TourView(TourRepository tourRepo) {
		this.tourRepo = tourRepo;
		this.grid = new Grid<>(Tour.class);
		grid.setSizeUndefined();
		updateList();
	    grid.setColumnOrder("id", "name", "duration", "day", "weekday_price", "weekend_price", "description", "hits");

		
		
		//filter
		final GridCellFilter<Tour> filter = new GridCellFilter<>(this.grid, Tour.class);
		filter.setTextFilter("id",true,false);
		filter.setTextFilter("name", true, false);
		filter.setTextFilter("duration", true, true);
		filter.setTextFilter("day", true, false);
		filter.setTextFilter("weekday_price", true, true);
		filter.setTextFilter("weekend_price", true, true);
		filter.setTextFilter("description", true, false);
		filter.setNumberFilter("hits", Integer.class);
		
		        

        Button addTourBtn = new Button("Add new tour");
        addTourBtn.addClickListener(e -> {
        	grid.asSingleSelect().clear();
        	form.setTour(new Tour());
        	setSizeFull();
        });

        HorizontalLayout toolbar = new HorizontalLayout(addTourBtn);

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        grid.setHeightByRows(10);
        
        verticalLayout.addComponents(toolbar, main);
                
        form.setVisible(false);
        
        grid.asSingleSelect().addValueChangeListener(e -> {
        	if (e.getValue() == null) {
        		form.setVisible(false);
        	}
        	else {
        		form.setTour(e.getValue());
        		setSizeFull();
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
		provider = new ListDataProvider<>(tourRepo.findAll());	
		grid.setDataProvider(provider);
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
