package com.web.webpage.UI;


import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.database.Tour;


@ViewScope
public class TourForm extends FormLayout {

	private TextField id = new TextField("Id");
	private TextField name = new TextField("name");
	private TextField description = new TextField("description");
	private TextField duration = new TextField("duration");
	private TextField day = new TextField("day");
	private TextField weekday_price = new TextField("weekday_price");
	private TextField weekend_price = new TextField("weekend_price");
	
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	private Button close = new Button("Close Form");
	
	private TourView tourView;
	
	private Binder<Tour> binder = new Binder<>(Tour.class);
//	binder.forField(duration).withConverter(new StringToIntegerConverter("Must enter a number")).bind(Tour::getDuration, Tour::setDuration);
	private Tour tour;
	
	public TourForm(TourView tourView) {
		this.tourView = tourView;
		
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete, close);
		HorizontalLayout row1 = new HorizontalLayout(id, name,duration, day);
		description.setSizeFull();
		HorizontalLayout row2 = new HorizontalLayout(weekday_price, weekend_price, description);
		addComponents(row1, row2, buttons);
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		
		binder.bindInstanceFields(this);
		
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		close.addClickListener(e -> setVisible(false));
	
	}
	
	public void setTour(Tour tour) {
		this.tour = tour;
		binder.setBean(tour);
		
		setVisible(true);
		id.selectAll();
	}
	
	public void delete() {
		tourView.delete(tour);
		setVisible(false);
	}
	
	public void save() {
		tourView.save(tour);
		setVisible(false);
	}
}
