package com.web.webpage.UI.bookingTable;


import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.database.Booking;
import com.web.webpage.database.BookingStatus;

public class BookingForm extends FormLayout {

	private TextField booking_id = new TextField("booking_id");
	private TextField tour_id = new TextField("tour_id");
	private DateField date = new DateField("date");
	private TextField tour_guide = new TextField("tour_guide");
	private TextField tour_guide_line_acc = new TextField("tour_guide_line_acc");
	private TextField hotel = new TextField("hotel");
	private TextField total_cap = new TextField("total_capacity");
	private TextField remaining_cap = new TextField("remaining_capacity");
	private TextField min_req = new TextField("min_req");
	private NativeSelect<BookingStatus> status = new NativeSelect<>("status");

	
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	private Button close = new Button("Close Form");
	
	private BookingView bookingView;
	
	private Binder<Booking> binder = new Binder<>(Booking.class);
//	binder.forField(date).withConverter(new LocalDateToDateConverter()).bind(Booking::getDate, Booking::setDate);
//	binder.forField(duration).withConverter(new StringToIntegerConverter("Must enter a number")).bind(Booking::getDuration, Booking::setDuration);
	private Booking booking;
	
	public BookingForm(BookingView bookingView) {
		this.bookingView = bookingView;
		
		binder.forField(date).withConverter(new LocalDateToDateConverter()).bind(Booking::getDate, Booking::setDate);
		
		
		status.setItems(BookingStatus.values());
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete, close);
		HorizontalLayout row1 = new HorizontalLayout(booking_id, tour_id, date, tour_guide, tour_guide_line_acc);
		HorizontalLayout row2 = new HorizontalLayout(hotel, total_cap, remaining_cap, min_req, status);
		addComponents(row1, row2, buttons);
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		
		binder.bindInstanceFields(this);
		
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		close.addClickListener(e -> setVisible(false));
	
	}
	
	public void setBooking(Booking booking) {
		this.booking = booking;
		binder.setBean(booking);
		
		setVisible(true);
		booking_id.selectAll();
	}
	
	public void delete() {
		bookingView.delete(booking);
		setVisible(false);
	}
	
	public void save() {
		bookingView.save(booking);
		setVisible(false);
	}
}
