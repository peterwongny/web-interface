package com.cs7s.webpage.ui.report;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.Tour;
import com.cs7s.webpage.database.TourRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = { BookingReportTest.class, BookingReport.class })
public class BookingReportTest {
	@Mock
	private BookingRepository bookingRepo;
	@Mock
	private TourRepository tourRepo;

	private List<Booking> bookingList;
	private List<Tour> tourList;
	private List<Tour> tourList2;
	private List<Tour> tourList3;
	private List<Tour> tourList4;
	private List<Tour> tourList5;
	private List<Tour> tourList6;
	private List<Tour> tourList7;

	@Mock
	private Booking booking;

	
	private Booking booking2;
	private Booking booking3;
	private Booking booking4;
	private Booking booking5;
	private Booking booking6;
	private Booking booking7;

	
	@Mock
	private Tour tour;
	
	
	
	private Tour tour2;
	private Tour tour3;
	private Tour tour4;
	private Tour tour5;
	private Tour tour6;
	private Tour tour7;
	
	@InjectMocks
	BookingReport bookingReport;
	



	@Before
	public void setup() {
		bookingList = new ArrayList<Booking>();
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking);

		tourList = new ArrayList<Tour>();
		tourList.add(tour);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format1.format(cal.getTime());
		Date date1 = new Date();
		try {
			date1 = format1.parse(nowDate);
		}catch(Exception e) {
			
		}
		
		booking2 = new Booking();
		booking2.setBooking_id("2");
		booking2.setDate(date1);
		booking2.setHits(1);
		booking2.setRemaining_cap(5);
		booking2.setTotal_cap(10);
		booking2.setTour_id("2");
		
		booking3 = new Booking();
		booking3.setBooking_id("3");
		booking3.setDate(date1);
		booking3.setHits(1);
		booking3.setRemaining_cap(5);
		booking3.setTotal_cap(10);
		booking3.setTour_id("3");
		
		booking4 = new Booking();
		booking4.setBooking_id("4");
		booking4.setDate(date1);
		booking4.setHits(1);
		booking4.setRemaining_cap(5);
		booking4.setTotal_cap(10);
		booking4.setTour_id("4");
		
		booking5 = new Booking();
		booking5.setBooking_id("5");
		booking5.setDate(date1);
		booking5.setHits(1);
		booking5.setRemaining_cap(5);
		booking5.setTotal_cap(10);
		booking5.setTour_id("5");
		
		booking6 = new Booking();
		booking6.setBooking_id("6");
		booking6.setDate(date1);
		booking6.setHits(1);
		booking6.setRemaining_cap(5);
		booking6.setTotal_cap(10);
		booking6.setTour_id("6");
		
		booking7 = new Booking();
		booking7.setBooking_id("7");
		booking7.setDate(date1);
		booking7.setHits(1);
		booking7.setRemaining_cap(5);
		booking7.setTotal_cap(10);
		booking7.setTour_id("7");
		
		tour2 = new Tour();
		tour2.setId("2");
		tour2.setName("2");
		
		tour3 = new Tour();
		tour3.setId("3");
		tour3.setName("3");

		tour4 = new Tour();
		tour4.setId("4");
		tour4.setName("4");
		
		tour5 = new Tour();
		tour5.setId("5");
		tour5.setName("5");
		
		tour6 = new Tour();
		tour6.setId("6");
		tour6.setName("6");
		
		tour7 = new Tour();
		tour7.setId("7");
		tour7.setName("7");

	}

	
	@Test
	public void incorrectDateTest() throws Exception {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format1.format(cal.getTime());
		Date date = new Date();
		date = format1.parse(nowDate);
		
		
		when(bookingRepo.findAll()).thenReturn(bookingList);
		when(booking.getDate()).thenReturn(date);
		
		bookingReport = new BookingReport(bookingRepo, tourRepo);
	}
	
	@Test
	public void correctDateTest() throws Exception {
		
		bookingList.add(booking2);
		bookingList.add(booking2);
		bookingList.add(booking2);
		bookingList.add(booking2);
		bookingList.add(booking2);
		bookingList.add(booking2);
		
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format1.format(cal.getTime());
		Date date1 = new Date();
		Date date2 = new Date();
		date1 = format1.parse(nowDate);
		cal.add(Calendar.DATE, +2);
		String nextDate = format1.format(cal.getTime());
		date2 = format1.parse(nextDate);
		
		when(bookingRepo.findAll()).thenReturn(bookingList);
		when(tourRepo.findById("2")).thenReturn(tourList);
		when(booking.getDate()).thenReturn(date2);
		
		bookingReport = new BookingReport(bookingRepo, tourRepo);
	}
	
	@Test
	public void MaxcorrectDateTest() throws Exception {
		
		bookingList.add(booking3);
		bookingList.add(booking4);
		bookingList.add(booking5);
		bookingList.add(booking6);
		bookingList.add(booking7);
		
		
		tourList2 = new ArrayList<Tour>();
		tourList3 = new ArrayList<Tour>();
		tourList4 = new ArrayList<Tour>();
		tourList5 = new ArrayList<Tour>();
		tourList6 = new ArrayList<Tour>();
		tourList7 = new ArrayList<Tour>();
		
		tourList2.add(tour2);
		tourList3.add(tour3);
		tourList4.add(tour4);
		tourList5.add(tour5);
		tourList6.add(tour6);
		tourList7.add(tour7);

		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format1.format(cal.getTime());
		Date date1 = new Date();
		Date date2 = new Date();
		date1 = format1.parse(nowDate);
		cal.add(Calendar.DATE, +2);
		String nextDate = format1.format(cal.getTime());
		date2 = format1.parse(nextDate);
		
		when(bookingRepo.findAll()).thenReturn(bookingList);
//		when(tourRepo.findById(" ")).thenReturn(tourList);
//		when(tourRepo.findById("2")).thenReturn(tourList2);
		when(tourRepo.findById("3")).thenReturn(tourList3);
		when(tourRepo.findById("4")).thenReturn(tourList4);
		when(tourRepo.findById("5")).thenReturn(tourList5);
		when(tourRepo.findById("6")).thenReturn(tourList6);
		when(tourRepo.findById("7")).thenReturn(tourList7);
		when(booking.getDate()).thenReturn(date2);
		
		bookingReport = new BookingReport(bookingRepo, tourRepo);
	}

}