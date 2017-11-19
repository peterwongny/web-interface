package com.cs7s.webpage.ui.tourList;

import com.cs7s.webpage.database.Tour;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A form that shows the information of a tour entity so that users can edit the tour.
 * @author Wong Ngo Yin
 */
@SuppressWarnings("serial")
@ViewScope
public class TourForm extends FormLayout {
	private TextField id = new TextField("ID");
	private TextField name = new TextField("Name");
	private TextField description = new TextField("Description");
	private TextField duration = new TextField("Duration");
	private TextField day = new TextField("Day");
	private TextField weekday_price = new TextField("Weekday Price");
	private TextField weekend_price = new TextField("Weekend Price");
	private TextField tag = new TextField("Tag");
	
	private Button save = new Button("Save");
	private Button close = new Button("Close Form");
	
	private TourView tourView;
	
	private Binder<Tour> binder = new Binder<>(Tour.class);
//	binder.forField(duration).withConverter(new StringToIntegerConverter("Must enter a number")).bind(Tour::getDuration, Tour::setDuration);
	private Tour tour;
	
	public TourForm(TourView tourView) {
		this.tourView = tourView;
		
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, close);
		HorizontalLayout row1 = new HorizontalLayout(id, name,description, duration);
		description.setSizeFull();
		HorizontalLayout row2 = new HorizontalLayout(day, weekday_price, weekend_price, tag);
		addComponents(row1, row2, buttons);
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		
		binder.bindInstanceFields(this);
		
		save.addClickListener(e -> save());
		close.addClickListener(e -> setVisible(false));
	}
	
	/**
	 * bind the form to the tour entity provided
	 * @param tour
	 */
	public void setTour(Tour tour) {
		this.tour = tour;
		binder.setBean(tour);
		
		setVisible(true);
		id.selectAll();
	}
	
	private void save() {
		tourView.save(tour);
		setVisible(false);
	}
}
