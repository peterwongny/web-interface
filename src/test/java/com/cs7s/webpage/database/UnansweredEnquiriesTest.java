package com.cs7s.webpage.database;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureTestDatabase(replace=Replace.NONE) //uses our psql db
@EnableAutoConfiguration
@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UnansweredEnquiriesTest.class, UnansweredEnquiries.class })
@SuppressWarnings("deprecation")
public class UnansweredEnquiriesTest {
	@Autowired
	UnansweredEnquiriesRepository enquiryRepo;
	
	@Test
	public void checkEnquiriesStatus() throws Exception {
		assertThat(EnquiriesStatus.FALSE.ordinal()).isEqualTo(0);
		assertThat(EnquiriesStatus.TRUE.ordinal()).isEqualTo(1);
		assertThat(EnquiriesStatus.valueOf("FALSE").ordinal()).isEqualTo(0);
		assertThat(EnquiriesStatus.valueOf("TRUE").ordinal()).isEqualTo(1);
	}
	
	@Test
	public void setAndGetAnsweredEnquiry() throws Exception {
		boolean found = false;
		boolean thrown = false;
		UnansweredEnquiries testEnquiry = null;
		try {
			testEnquiry = new UnansweredEnquiries();
			testEnquiry.setLine_id("t.avish.a");
			testEnquiry.setEnquiry("Test enquiry");
			testEnquiry.setAnswered(true);
			enquiryRepo.save(testEnquiry);

			testEnquiry.setAnswered(EnquiriesStatus.TRUE);
			enquiryRepo.save(testEnquiry);
			
			Iterable<UnansweredEnquiries> enquiries = this.enquiryRepo.findAll();

			for (UnansweredEnquiries enquiry : enquiries) {
				if (enquiry.getLine_id().equals(testEnquiry.getLine_id()) && enquiry.getEnquiry().equals(testEnquiry.getEnquiry())) {
					testEnquiry = enquiry;
					found = true;
				}
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(found).isEqualTo(true);
		assertThat(testEnquiry).isNotNull();
		assertThat(testEnquiry.getLine_id()).isEqualTo("t.avish.a");
		assertThat(testEnquiry.getEnquiry()).isEqualTo("Test enquiry");
		assertThat(testEnquiry.getAnswered()).isEqualTo(EnquiriesStatus.TRUE);
	}
	
	@Test
	public void setAndGetUnansweredEnquiry() throws Exception {
		boolean found = false;
		boolean thrown = false;
		UnansweredEnquiries testEnquiry = null;
		try {
			testEnquiry = new UnansweredEnquiries();
			testEnquiry.setLine_id("t.avish.a");
			testEnquiry.setEnquiry("Test enquiry");
			testEnquiry.setAnswered(false);
			enquiryRepo.save(testEnquiry);

			testEnquiry.setAnswered(EnquiriesStatus.FALSE);
			enquiryRepo.save(testEnquiry);
			
			Iterable<UnansweredEnquiries> enquiries = this.enquiryRepo.findAll();

			for (UnansweredEnquiries enquiry : enquiries) {
				if (enquiry.getLine_id().equals(testEnquiry.getLine_id()) && enquiry.getEnquiry().equals(testEnquiry.getEnquiry())) {
					testEnquiry = enquiry;
					found = true;
				}
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(found).isEqualTo(true);
		assertThat(testEnquiry).isNotNull();
		assertThat(testEnquiry.getLine_id()).isEqualTo("t.avish.a");
		assertThat(testEnquiry.getEnquiry()).isEqualTo("Test enquiry");
		assertThat(testEnquiry.getAnswered()).isEqualTo(EnquiriesStatus.FALSE);
	}
}
