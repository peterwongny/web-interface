package com.cs7s.webpage.ui.bookingTable;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.StaffLoginRepository;
import com.cs7s.webpage.database.UnansweredEnquiries;
import com.cs7s.webpage.database.UnansweredEnquiriesRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = { BookingFormTest.class, BookingForm.class })
public class BookingFormTest {
	@Mock
	private BookingView bookingView;
	
	@Mock
	private Booking booking;
	
	@InjectMocks 
	private BookingForm bookingForm = new BookingForm(bookingView);


	@Test
	public void bindTest() throws Exception {
		bookingForm.setBooking(booking);
	}
	
}