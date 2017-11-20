package com.cs7s.webpage.ui.promotion;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.Customer;
import com.cs7s.webpage.database.CustomerRepository;
import com.cs7s.webpage.database.Promotion;
import com.cs7s.webpage.database.PromotionRepository;
import com.cs7s.webpage.database.Tour;
import com.cs7s.webpage.database.TourRepository;

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
@SpringBootTest(classes = { PromotionViewTest.class, PromotionView.class })
public class PromotionViewTest {
	
	@Mock
	private PromotionRepository promoRepo;
	@Mock
	private BookingRepository bookingRepo; 
	@Mock
	private CustomerRepository customerRepo;
	
	
	private List<Promotion> promotionList;
	private List<Booking> bookingList;
	private List<Customer> customerList;
	
	
	private Promotion promotion;
	private Booking booking;
	private Customer customer;
	
	@InjectMocks 
	private PromotionView promotionView;

	
	@Before
	public void setup() {
		
		promotion = new Promotion();
		booking = new Booking();
		customer = new Customer();
		
		promotion.setTourOffering("1");
		customer.setLine_id("line_id");
		
		promotionList = new ArrayList<Promotion>();
		bookingList = new ArrayList<Booking>();
		customerList = new ArrayList<Customer>();
		
		promotionList.add(promotion);
		bookingList.add(booking);
		customerList.add(customer);
		
		when(promoRepo.findAll()).thenReturn(promotionList);
		
		promotionView = new PromotionView(promoRepo, bookingRepo, customerRepo);
	}
	
	
	
	@Test
	public void save1Test() throws Exception {

		when(bookingRepo.findById("1")).thenReturn(booking);
	
		
		promotionView.save(promotion);
	}

	@Test
	public void save2Test() throws Exception {

		when(bookingRepo.findById("1")).thenReturn(booking);
	
		when(customerRepo.findAll()).thenReturn(customerList);
		
		promotionView.save(promotion);
	}

}