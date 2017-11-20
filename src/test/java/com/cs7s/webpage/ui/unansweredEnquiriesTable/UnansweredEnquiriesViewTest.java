package com.cs7s.webpage.ui.unansweredEnquiriesTable;

import com.cs7s.webpage.database.StaffLoginRepository;
import com.cs7s.webpage.database.UnansweredEnquiries;
import com.cs7s.webpage.database.UnansweredEnquiriesRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
@SpringBootTest(classes = { UnansweredEnquiriesViewTest.class, UnansweredEnquiriesView.class })
public class UnansweredEnquiriesViewTest {
	@Mock
	private UnansweredEnquiriesRepository unRepo;
	
	
	private List<UnansweredEnquiries> enquiryList;
	
	@Mock
	private UnansweredEnquiries enquiry;
	

	@Before
	public void setup() {
		enquiryList = new ArrayList<UnansweredEnquiries>();
		enquiryList.add(enquiry);
		enquiryList.add(enquiry);
		enquiryList.add(enquiry);
		enquiryList.add(enquiry);
	}
	
	@InjectMocks 
	private UnansweredEnquiriesView unView;

	
	@Test
	public void saveTest() throws Exception {

		when(unRepo.findAll()).thenReturn(enquiryList);
		
		unView = new UnansweredEnquiriesView(unRepo);
		
		unView.save(enquiry);
	}
	

}