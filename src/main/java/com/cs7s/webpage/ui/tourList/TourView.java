package com.cs7s.webpage.ui.tourList;

import java.util.List;

import org.vaadin.gridutil.cell.GridCellFilter;

import com.cs7s.webpage.database.Tour;
import com.cs7s.webpage.database.TourRepository;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The page that displays Tour List.
 * The tours can be searched, edited, sorted, and filtered on this page.
 * @author Wong Ngo Yin
 */
@SpringComponent
@SpringView(name = TourView.VIEW_NAME)
@SuppressWarnings("serial")
@UIScope
public class TourView extends Panel implements View {
	public final static String VIEW_NAME = "Tour List";

	public TourRepository tourRepo;
	private TourForm form = new TourForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();
		
	private final Grid<Tour> grid;
	final ListDataProvider<Tour> provider;
	
	private List<Tour> tourlist;
	
	/**
	 * The constructor of TourView.
	 * @param tourRepo the Tour List repository.
	 */
	public TourView(TourRepository tourRepo) {
		this.tourRepo = tourRepo;
		this.grid = new Grid<>(Tour.class);
		grid.setSizeUndefined();
	    grid.setColumnOrder("id", "name", "description", "duration", "day", "weekday_price", "weekend_price", "hits");
	    
	    tourlist = tourRepo.findAll();
	    provider = new ListDataProvider<>(tourlist);	
		grid.setDataProvider(provider);
		updateList();
		
		//filter
		final GridCellFilter<Tour> filter = new GridCellFilter<>(this.grid, Tour.class);
		filter.setTextFilter("id",true,false);
		filter.setTextFilter("name", true, false);
		filter.setTextFilter("description", true, false);
		filter.setTextFilter("duration", true, true);
		filter.setTextFilter("day", true, false);
		filter.setTextFilter("weekday_price", true, true);
		filter.setTextFilter("weekend_price", true, true);
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
	
	/**
	 * Entry point when user selects this page which updates the table.
	 * @param ViewChangeEvent
	 */
	@Override
    public void enter(ViewChangeEvent event) {
		updateList();
		verticalLayout.addComponent(grid);
		setContent(verticalLayout);
    }
	
	/**
	 * Updates the table based on the Tour List in the database.
	 */
	public void updateList() {
		tourlist.clear();
		tourlist.addAll(tourRepo.findAll());
		provider.refreshAll();
	}
	
	/**
	 * Deletes a tour entity from the Tour List in the database.
	 * @param tour
	 */
	public void delete(Tour tour) {
		tourRepo.delete(tour);
		updateList();
	}
	
	/**
	 * Updates or inserts a tour entity from the Tour List in the database.
	 * @param tour the tour to be saved.
	 */
	public void save(Tour tour) {
		tourRepo.saveAndFlush(tour);
		updateList();
	}

}
