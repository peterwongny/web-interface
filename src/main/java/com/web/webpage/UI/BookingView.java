//package com.web.webpage.UI;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.vaadin.icons.VaadinIcons;
//import com.vaadin.navigator.View;
//import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
//import com.vaadin.shared.ui.ValueChangeMode;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.CssLayout;
//import com.vaadin.ui.Grid;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.Panel;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.VerticalLayout;
//import com.vaadin.ui.themes.ValoTheme;
//import com.web.webpage.database.Booking;
//import com.web.webpage.database.BookingRepository;
//
//
//public class BookingView extends Panel implements View{
//	
//	public BookingRepository bookingRepo;
//	private TextField filterText = new TextField();
//	private BookingForm form = new BookingForm(this);
//	private VerticalLayout verticalLayout = new VerticalLayout();
//
//	public final static String VIEW_NAME = "Booking Table";
//	
//	
//	final Grid<Booking> grid;
//	
//	public BookingView(BookingRepository bookingRepo) {
//		this.bookingRepo = bookingRepo;
//		this.grid = new Grid<>(Booking.class);
//		grid.setSizeUndefined();
//		
//
//		filterText.setPlaceholder("filter by name...");
//        filterText.addValueChangeListener(e -> updateList());
//        filterText.setValueChangeMode(ValueChangeMode.LAZY);
//
//        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
//        clearFilterTextBtn.setDescription("Clear the current filter");
//        clearFilterTextBtn.addClickListener(e -> filterText.clear());
//        
//        CssLayout filtering = new CssLayout();
//        filtering.addComponents(filterText, clearFilterTextBtn);
//        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
//
//        Button addBookingBtn = new Button("Add new booking");
//        addBookingBtn.addClickListener(e -> {
//        	grid.asSingleSelect().clear();
//        	form.setBooking(new Booking());
//        });
//
//        HorizontalLayout toolbar = new HorizontalLayout(filtering, addBookingBtn);
//
//        HorizontalLayout main = new HorizontalLayout(grid, form);
//        main.setSizeFull();
//        grid.setSizeFull();
//        main.setExpandRatio(grid, 1);
//        
//        verticalLayout.addComponents(toolbar, main);
//        
//        updateList();
//        
//        form.setVisible(false);
//        
//        grid.asSingleSelect().addValueChangeListener(e -> {
//        	if (e.getValue() == null) {
//        		form.setVisible(false);
//        	}
//        	else {
//        		form.setBooking(e.getValue());
//        	}
//        });
//
//
//	}
//	
//	@Override
//    public void enter(ViewChangeEvent event) {
//		updateList();
//		verticalLayout.addComponent(grid);
//		setContent(verticalLayout);
//    }
//	
//	public void updateList() {
//	    grid.setItems(bookingRepo.findAll());
//	    grid.setColumnOrder("id", "name", "description", "duration", "day", "weekday_price", "weekend_price", "hits");
//	}
//	
//	public void delete(Booking booking) {
//		bookingRepo.delete(booking);
//		updateList();
//	}
//	
//	public void save(Booking booking) {
//		bookingRepo.saveAndFlush(booking);
//		updateList();
//	}
//}
