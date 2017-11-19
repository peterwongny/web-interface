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
 * The page that shows the Tour List. The records of the table can be edited and searched here.
 * @author Wong Ngo Yin
 */
@SpringComponent
@SpringView(name = TourView.VIEW_NAME)
@SuppressWarnings("serial")
@UIScope
public class TourView extends Panel implements View {
	/**
	 * The tour repository.
	 */
	public TourRepository tourRepo;
	private TourForm form = new TourForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();

	/**
	 * The name of this view.
	 */
	public final static String VIEW_NAME = "Tour List";

	private final Grid<Tour> grid;
	final ListDataProvider<Tour> provider;

	private List<Tour> tourlist;

	/**
	 * The constructor of TourView.
	 * @param tourRepo the tour repository.
	 */
	public TourView(TourRepository tourRepo) {
		this.tourRepo = tourRepo;
		this.grid = new Grid<>(Tour.class);
		grid.setSizeUndefined();
		grid.setColumnOrder("id", "name", "description", "duration", "day", "weekday_price", "weekend_price", "tag", "hits");

		tourlist = tourRepo.findAll();
		provider = new ListDataProvider<>(tourlist);	
		grid.setDataProvider(provider);
		updateList();

		// Filter
		final GridCellFilter<Tour> filter = new GridCellFilter<>(this.grid, Tour.class);
		filter.setTextFilter("id",true,false);
		filter.setTextFilter("name", true, false);
		filter.setTextFilter("description", true, false);
		filter.setTextFilter("duration", true, true);
		filter.setTextFilter("day", true, false);
		filter.setTextFilter("weekday_price", true, true);
		filter.setTextFilter("weekend_price", true, true);
		filter.setTextFilter("tag", true, false);
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
	 * Indicates that the user has entered this page.
	 * @param event the event that fires when the user has entered this page.
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		updateList();
		verticalLayout.addComponent(grid);
		setContent(verticalLayout);
	}

	/**
	 * Updates Tour List using the data from the database.
	 */
	public void updateList() {
		tourlist.clear();
		tourlist.addAll(tourRepo.findAll());
		provider.refreshAll();
	}

	/**
	 * Save (insert or update) an entity in Tour List.
	 * @param tour the tour entity.
	 */
	public void save(Tour tour) {
		tourRepo.saveAndFlush(tour);
		updateList();
	}
}