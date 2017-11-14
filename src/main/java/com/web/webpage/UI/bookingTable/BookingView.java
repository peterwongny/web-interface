package com.web.webpage.UI.bookingTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gridutil.cell.CellFilterComponent;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.AbstractRenderer;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.Renderer;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.database.Booking;
import com.web.webpage.database.BookingRepository;
import com.web.webpage.database.BookingStatus;
import com.web.webpage.database.Booking;



public class BookingView extends Panel implements View{
	
	

	public BookingRepository bookingRepo;
	private TextField filterText = new TextField();
	private BookingForm form = new BookingForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();

	public final static String VIEW_NAME = "Booking Table";
	
	
	final Grid<Booking> grid;
	private ListDataProvider<Booking> provider;
	
	public BookingView(BookingRepository bookingRepo) {
		this.bookingRepo = bookingRepo;
		this.grid = new Grid<>(Booking.class);
		grid.setSizeUndefined();
		updateList();
		
		grid.getColumn("date").setWidth(140);
//		grid.getColumn("date").setRenderer(new DateRenderer(new SimpleDateFormat("yyyy-MM-dd")));
		
		
	    grid.setColumnOrder("booking_id", "tour_id", "date", "total_cap", "remaining_cap", "min_req", "status", "hotel", "tour_guide", "tour_guide_line_acc", "hits");

		//filter
		final GridCellFilter<Booking> filter = new GridCellFilter<>(this.grid, Booking.class);
		filter.setTextFilter("booking_id",true,false);
		filter.setTextFilter("tour_id", true, false);
		filter.setDateFilter("date");
//		filter.setTextFilter("date", true, true);
		filter.setTextFilter("total_cap", true, true);
		filter.setTextFilter("remaining_cap", true, true);
		filter.setTextFilter("min_req", true, true);
		filter.setTextFilter("hotel", true, false);
		filter.setTextFilter("tour_guide", true, false);
		filter.setTextFilter("tour_guide_line_acc", true, false);
		filter.setNumberFilter("hits", Integer.class);
		
		CellFilterComponent<ComboBox<BookingStatus>> statusFilter = filter.setComboBoxFilter("status", BookingStatus.class, Arrays.asList(BookingStatus.Confirmed, BookingStatus.Not_Confirmed, BookingStatus.Cancelled));


        Button addBookingBtn = new Button("Add new booking");
        addBookingBtn.addClickListener(e -> {
        	grid.asSingleSelect().clear();
        	form.setBooking(new Booking());
        	setSizeFull();
        });

        HorizontalLayout toolbar = new HorizontalLayout(addBookingBtn);

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
        		form.setBooking(e.getValue());
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
		provider = new ListDataProvider<>(bookingRepo.findAll());
		grid.setDataProvider(provider);
	}
	
	
	public void delete(Booking booking) {
		bookingRepo.delete(booking);
		updateList();
	}
	
	public void save(Booking booking) {
		bookingRepo.saveAndFlush(booking);
		updateList();
	}
}
