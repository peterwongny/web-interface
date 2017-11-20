package com.cs7s.webpage.ui.bookingTable;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.BookingStatus;
import com.cs7s.webpage.database.Customer;
import com.cs7s.webpage.database.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = { BookingViewTest.class, BookingView.class })
public class BookingViewTest {
	@Mock
	private BookingRepository bookingRepo;
	
	@Mock
	private CustomerRepository customerRepo;
	
	private List<Booking> bookingList;
	
	
	private Booking booking;
	
	private Booking booking2;
	
	private Booking booking3;
	
	private Customer customer;
	
	private List<Customer> customerList;

	@InjectMocks 
	BookingView bookingView;
	
	@Before
	public void setup() {

		
		booking = new Booking();
		booking.setStatus(BookingStatus.NOT_CONFIRMED);
		booking.setBooking_id("1");
		
		booking2 = new Booking();
		booking2.setStatus(BookingStatus.CONFIRMED);
		booking2.setBooking_id("2");
	
		booking3 = new Booking();
		booking3.setStatus(BookingStatus.CANCELLED);
		booking3.setBooking_id("3");

		bookingList = new ArrayList<Booking>();
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking2);
		bookingList.add(booking2);
		bookingList.add(booking3);
		bookingList.add(booking3);
		
		
		customer = new Customer();
		customer.setLine_id("line_id");
		
		customerList = new ArrayList<Customer>();
		customerList.add(customer);
		
		when(bookingRepo.findAll()).thenReturn(bookingList);
		
		bookingView = new BookingView(bookingRepo,customerRepo);
	}
	



	
	@Test
	public void saveTest() throws Exception {

		when(bookingRepo.findAll()).thenReturn(bookingList);
		
		bookingView = new BookingView(bookingRepo,customerRepo);

		bookingView.save(booking);
	}
	
	@Test
	public void saveConfirmed1Test() throws Exception {

		when(bookingRepo.findById("2")).thenReturn(booking2);
				
		bookingView.save(booking2);
	}
	
	@Test
	public void saveConfirmed2Test() throws Exception {

		when(bookingRepo.findById("2")).thenReturn(booking3);
				
		bookingView.save(booking2);
	}
	
	@Test
	public void saveConfirmed3Test() throws Exception {

		when(bookingRepo.findById("2")).thenReturn(booking3);
		
		when(customerRepo.findByTourJoined("3")).thenReturn(customerList);

				
		bookingView.save(booking2);
	}
	
	
	@Test
	public void saveNotConfirmed1Test() throws Exception {

		when(bookingRepo.findById("3")).thenReturn(booking3);
				
		bookingView.save(booking3);
	}
	
	@Test
	public void saveNotConfirmed2Test() throws Exception {

		when(bookingRepo.findById("3")).thenReturn(booking2);
				
		bookingView.save(booking3);
	}
	
	@Test
	public void saveNotConfirmed3Test() throws Exception {

		when(bookingRepo.findById("3")).thenReturn(null);
				
		bookingView.save(booking3);
	}
	
	@Test
	public void saveNotConfirmed4Test() throws Exception {

		when(bookingRepo.findById("3")).thenReturn(booking2);
		
		when(customerRepo.findByTourJoined("2")).thenReturn(customerList);
		
		bookingView.save(booking3);
	}

}