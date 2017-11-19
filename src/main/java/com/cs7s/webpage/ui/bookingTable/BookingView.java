package com.cs7s.webpage.ui.bookingTable;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import org.vaadin.gridutil.cell.GridCellFilter;

/**
 * The page that shows the Booking Table. The records of the table can be edited and searched here.
 * @author Wong Ngo Yin
 */
@SuppressWarnings("serial")
public class BookingView extends Panel implements View {
	/**
	 * The booking repository.
	 */
	public BookingRepository bookingRepo;
	private BookingForm form = new BookingForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();

	/**
	 * The name of this view.
	 */
	public final static String VIEW_NAME = "Booking Table";

	private final Grid<Booking> grid;
	private ListDataProvider<Booking> provider;
	private List<Booking> bookingTable;

	/**
	 * The constructor of BookingView.
	 * @param bookingRepo the booking repository to be displayed.
	 */
	public BookingView(BookingRepository bookingRepo) {
		this.bookingRepo = bookingRepo;
		this.grid = new Grid<>(Booking.class);
		grid.setSizeUndefined();

		bookingTable = bookingRepo.findAll();
		provider = new ListDataProvider<>(bookingTable);
		grid.setDataProvider(provider);
		updateList();

		grid.getColumn("date").setWidth(140);		

		grid.setColumnOrder("booking_id", "tour_id", "date", "total_cap", "remaining_cap", "min_req", "status", "hotel", "tour_guide", "tour_guide_line_acc", "hits");

		// Filter
		final GridCellFilter<Booking> filter = new GridCellFilter<>(this.grid, Booking.class);
		filter.setTextFilter("booking_id",true,false);
		filter.setTextFilter("tour_id", true, false);
		filter.setDateFilter("date");
		filter.setTextFilter("total_cap", true, true);
		filter.setTextFilter("remaining_cap", true, true);
		filter.setTextFilter("min_req", true, true);
		filter.setTextFilter("hotel", true, false);
		filter.setTextFilter("tour_guide", true, false);
		filter.setTextFilter("tour_guide_line_acc", true, false);
		filter.setNumberFilter("hits", Integer.class);

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
	 * Updates Booking Table using the data from the database.
	 */
	public void updateList() {
		bookingTable.clear();
		bookingTable.addAll(bookingRepo.findAll());
		provider.refreshAll();
	}

	/**
	 * Save (insert or update) an entity in Booking Table.
	 * @param booking the booking entity to be saved.
	 */
	public void save(Booking booking) {
		bookingRepo.saveAndFlush(booking);
		updateList();
	}
}