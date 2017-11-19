package com.cs7s.webpage.ui.tourList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.cs7s.webpage.database.Tour;
import com.cs7s.webpage.database.TourRepository;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.DataProvider;
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


@SpringView(name = TourView.VIEW_NAME)
@SpringComponent
@UIScope

/**
 * the page that show tour table, you can edit and search the table in this page
 * @author Wong Ngo Yin
 *
 */
public class TourView extends Panel implements View{

	
	public TourRepository tourRepo;
	private TourForm form = new TourForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();


	public final static String VIEW_NAME = "Tour List";
	
	
	private final Grid<Tour> grid;
	final ListDataProvider<Tour> provider;
	
	private List<Tour> tourlist;
	
	/**
	 * constructor of TourView 
	 * @param tourRepo
	 */
	public TourView(TourRepository tourRepo) {
		this.tourRepo = tourRepo;
		this.grid = new Grid<>(Tour.class);
		grid.setSizeUndefined();
	    grid.setColumnOrder("id", "name", "duration", "day", "weekday_price", "weekend_price", "description", "hits");

	    tourlist = tourRepo.findAll();
	    provider = new ListDataProvider<>(tourlist);	
		grid.setDataProvider(provider);
		updateList();
		
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
	
	/**
	 * entry point when user select this page, update the table
	 * 
	 * @param ViewChangeEvent
	 */
	@Override
    public void enter(ViewChangeEvent event) {
		updateList();
		verticalLayout.addComponent(grid);
		setContent(verticalLayout);

    }
	/**
	 * update the table base on the tour table in the database
	 */
	public void updateList() {
		tourlist.clear();
		tourlist.addAll(tourRepo.findAll());
		provider.refreshAll();
	}
	
	/**
	 * delete a tour entity from the tour table in the database
	 * @param tour
	 */
	public void delete(Tour tour) {
		tourRepo.delete(tour);
		updateList();
	}
	
	/**
	 * update or insert a tour entity from the tour table in the database
	 * @param tour
	 */
	public void save(Tour tour) {
		tourRepo.saveAndFlush(tour);
		updateList();
	}

}
