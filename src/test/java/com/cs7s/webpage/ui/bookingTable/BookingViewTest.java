package com.cs7s.webpage.ui.bookingTable;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;
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
	
	@Mock
	private Booking booking;
	

	@Before
	public void setup() {
		bookingList = new ArrayList<Booking>();
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking);
		bookingList.add(booking);
	}
	
	@InjectMocks 
	private BookingView bookingView;

	
	@Test
	public void saveTest() throws Exception {

		when(bookingRepo.findAll()).thenReturn(bookingList);
		
		bookingView = new BookingView(bookingRepo,customerRepo);
		
		bookingView.save(booking);
	}
	

}