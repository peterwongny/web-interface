package com.cs7s.webpage.ui.unansweredEnquiriesTable;

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
@SpringBootTest(classes = { UnansweredEnquiriesFormTest.class, UnansweredEnquiriesForm.class })
public class UnansweredEnquiriesFormTest {
	@Mock
	private UnansweredEnquiriesView unView;
	
	@Mock
	private UnansweredEnquiries enquiry;
	
	@InjectMocks 
	private UnansweredEnquiriesForm unForm = new UnansweredEnquiriesForm(unView);

	
	@Test
	public void bindTest() throws Exception {
		unForm.setUnansweredEnquiries(enquiry);
	}
	
}