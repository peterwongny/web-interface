package com.cs7s.webpage.ui.report;

import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.TourRepository;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;

/**
 * The page that shows the Weekly Reports.
 */
@SuppressWarnings("serial")
public class ReportView extends Panel implements View {
	/**
	 * The name of this view.
	 */
	public final static String VIEW_NAME = "Weekly Report";
	
	private BookingReport bookingReport;

	public ReportView(BookingRepository bookingRepo, TourRepository tourRepo) {		
		this.bookingReport = new BookingReport(bookingRepo, tourRepo);
		this.setContent(bookingReport);
		this.setSizeFull();
	}

	/**
	 * Indicates that the user has entered this page.
	 * @param event the event that fires when the user has entered this page.
	 */
	@Override
	public void enter(ViewChangeEvent event) {

	}
}
