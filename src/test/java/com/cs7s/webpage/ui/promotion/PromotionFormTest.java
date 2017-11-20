//package com.cs7s.webpage.ui.promotion;
//
//import com.cs7s.webpage.database.Booking;
//import com.cs7s.webpage.database.BookingRepository;
//import com.cs7s.webpage.database.Promotion;
//import com.cs7s.webpage.database.StaffLoginRepository;
//import com.cs7s.webpage.database.Tour;
//import com.cs7s.webpage.database.UnansweredEnquiries;
//import com.cs7s.webpage.database.UnansweredEnquiriesRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = { PromotionFormTest.class, PromotionForm.class })
//public class PromotionFormTest {
//	@Mock
//	private PromotionView promotionView;
//	
//	@Mock
//	private Promotion promotion;
//	
//	@Mock
//	private BookingRepository bookingRepo;
//	
//	@Mock
//	private Booking booking;
//	
//	private List<Booking> bookingList;
//	
//	@InjectMocks 
//	private PromotionForm promotionForm;
//	
//	
//	private Promotion promo;
//
//	@Before
//	public void setup() {
//		bookingList = new ArrayList<Booking>();
//		bookingList.add(booking);
//		bookingList.add(booking);
//		
//		promo.setTourOffering("1");
//		
//		when(bookingRepo.findAll()).thenReturn(bookingList);
//
//		promotionForm = new PromotionForm(promotionView, bookingRepo);
//	}
//	
//	
//	@Test
//	public void savePromotionTest() throws Exception {
//		
//		when(bookingRepo.findAll()).thenReturn(bookingList);
//		when(booking.getBooking_id()).thenReturn(" ");
//		
//		promotionForm.save();
//	}
//	
//}