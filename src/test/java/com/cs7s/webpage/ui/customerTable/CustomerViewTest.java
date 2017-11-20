package com.cs7s.webpage.ui.customerTable;

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
@SpringBootTest(classes = { CustomerViewTest.class, CustomerView.class })
public class CustomerViewTest {
	@Mock
	private CustomerRepository customerRepo;
	
	
	private List<Customer> customerList;
	
	@Mock
	private Customer customer;
	

	@Before
	public void setup() {
		customerList = new ArrayList<Customer>();
		customerList.add(customer);
		customerList.add(customer);
		customerList.add(customer);
		customerList.add(customer);
	}
	
	@InjectMocks 
	private CustomerView customerView;

	
	@Test
	public void saveTest() throws Exception {

		when(customerRepo.findAll()).thenReturn(customerList);
		
		customerView = new CustomerView(customerRepo);
		
		customerView.save(customer);
	}
	

}