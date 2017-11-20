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

	@Mock
	private Booking booking;

	@Mock
	private Booking booking2;

	
	@Mock
	private Tour tour;
	
	@InjectMocks
	BookingReport bookingReport;
	
//	@InjectMocks
//	BookingReport bookingReport = new BookingReport(bookingRepo, tourRepo);



	@Before
	public void setup() {
		bookingList = new ArrayList<Booking>();
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking);
//		bookingList.add(booking2);
//		bookingList.add(booking2);

		tourList = new ArrayList<Tour>();
		tourList.add(tour);
		
		
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
//		when(tourRepo.findById("")).thenReturn(tourList);
		when(booking.getDate()).thenReturn(date);
//		when(booking.getHits()).thenReturn(1);
//		when(booking.getTour_id()).thenReturn("");
//		when(booking.getTotal_cap()).thenReturn("1");
//		when(booking.getRemaining_cap()).thenReturn("1");
//		when(booking2.getDate()).thenReturn(new Date(System.currentTimeMillis()-24*60*60*1000));
//		when(booking2.getHits()).thenReturn(1);
//		when(booking2.getTour_id()).thenReturn("");
//		when(booking2.getTotal_cap()).thenReturn("1");
//		when(booking2.getRemaining_cap()).thenReturn("1");
//		when(tour.getName()).thenReturn("");
		
		bookingReport = new BookingReport(bookingRepo, tourRepo);
	}
	
//	@Test
//	public void correctDateTest() throws Exception {
//		
//		bookingList.add(booking2);
//		bookingList.add(booking2);
//		
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1);
//		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		String nowDate = format1.format(cal.getTime());
//		Date date1 = new Date();
//		Date date2 = new Date();
//		date1 = format1.parse(nowDate);
//		cal.add(Calendar.DATE, +2);
//		String nextDate = format1.format(cal.getTime());
//		date2 = format1.parse(nextDate);
//		
//		when(bookingRepo.findAll()).thenReturn(bookingList);
//		when(tourRepo.findById("")).thenReturn(tourList);
//		when(tourRepo.findById(null)).thenReturn(tourList);
//		when(booking.getDate()).thenReturn(date1);
////		when(booking.getHits()).thenReturn(1);
////		when(booking.getTour_id()).thenReturn("");
////		when(booking.getTotal_cap()).thenReturn("1");
////		when(booking.getRemaining_cap()).thenReturn("1");
//		when(booking2.getDate()).thenReturn(date2);
////		when(booking2.getHits()).thenReturn(1);
//		when(booking2.getTour_id()).thenReturn("");
////		when(booking2.getTotal_cap()).thenReturn("1");
////		when(booking2.getRemaining_cap()).thenReturn("1");
////		when(tour.getName()).thenReturn("");
//		
//		bookingReport.getReport();
//	}

}