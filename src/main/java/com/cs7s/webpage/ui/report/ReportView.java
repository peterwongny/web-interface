package com.cs7s.webpage.ui.report;

import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.TourRepository;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ReportView extends Panel implements View{
	
	public final static String VIEW_NAME = "Regular Report";
		
	private BookingRepository bookingRepo;
	private TourRepository tourRepo;
	
	private BookingReport bookingReport;
	
	public ReportView(BookingRepository bookingRepo, TourRepository tourRepo) {
		this.bookingRepo = bookingRepo;
		this.tourRepo = tourRepo;
		
		this.bookingReport = new BookingReport(bookingRepo, tourRepo);
		
		this.setContent(bookingReport);
		this.setSizeFull();
	}
	
	@Override
    public void enter(ViewChangeEvent event) {

	}
	

}
