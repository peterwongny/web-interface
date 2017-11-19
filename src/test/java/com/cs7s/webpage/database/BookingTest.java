package com.cs7s.webpage.database;

import java.text.SimpleDateFormat;

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
@SpringBootTest(classes = { BookingTest.class, Booking.class })
@SuppressWarnings("deprecation")
public class BookingTest {
	@Autowired
	private BookingRepository bookingRepo;

	@Test
	public void checkBookingStatus() {
		assertThat(BookingStatus.valueOf("CONFIRMED")).isEqualTo(BookingStatus.values()[0]);
		assertThat(BookingStatus.valueOf("NOT_CONFIRMED")).isEqualTo(BookingStatus.values()[1]);
		assertThat(BookingStatus.valueOf("CANCELLED")).isEqualTo(BookingStatus.values()[2]);
	}

	@Test
	public void setAndGetTourOfferingWithStringStatus() throws Exception {
		boolean found = false;
		boolean thrown = false;
		Booking testBooking = null;
		try {
			testBooking = new Booking();
			testBooking.setBooking_id("9D99920171212");
			testBooking.setTour_id("9D999");
			testBooking.setDate(new SimpleDateFormat("YYYY-MM-dd").parse("2017-12-12"));
			testBooking.setTour_guide("Vickyram");
			testBooking.setTour_guide_line_acc("tourguide.vickyram");
			testBooking.setHotel("Hotel de Gobinmurthy");
			testBooking.setTotal_cap(30);
			testBooking.setRemaining_cap(30);
			testBooking.setMin_req(4);
			testBooking.setStatus("not confirmed");
			testBooking.setHits(0);
			bookingRepo.save(testBooking);

			testBooking.setTotal_cap("30");
			testBooking.setRemaining_cap("30");
			testBooking.setMin_req("4");
			bookingRepo.save(testBooking);

			Iterable<Booking> bookings = this.bookingRepo.findAll();
			for (Booking booking : bookings) {
				if (booking.getBooking_id().equals(testBooking.getBooking_id())) {
					testBooking = booking;
					found = true;
				}
			}
		} catch (Exception e) {
			System.out.println("Error occured: " + e.toString());
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(found).isEqualTo(true);
		assertThat(testBooking).isNotNull();
		assertThat(testBooking.getBooking_id()).isEqualTo("9D99920171212");
		assertThat(testBooking.getTour_id()).isEqualTo("9D999");
		assertThat(testBooking.getDate()).isEqualTo(new SimpleDateFormat("YYYY-MM-dd").parse("2017-12-12"));
		assertThat(testBooking.getTour_guide()).isEqualTo("Vickyram");
		assertThat(testBooking.getTour_guide_line_acc()).isEqualTo("tourguide.vickyram");
		assertThat(testBooking.getHotel()).isEqualTo("Hotel de Gobinmurthy");
		assertThat(testBooking.getTotal_cap()).isEqualTo("30");
		assertThat(testBooking.getRemaining_cap()).isEqualTo("30");
		assertThat(testBooking.getMin_req()).isEqualTo("4");
		assertThat(testBooking.getStatus()).isEqualTo(BookingStatus.NOT_CONFIRMED);
		assertThat(testBooking.getHits()).isGreaterThanOrEqualTo(0);
	}

	@Test
	public void setAndGetTourOfferingWithConfirmedStatus() throws Exception {
		boolean found = false;
		boolean thrown = false;
		Booking testBooking = null;
		try {
			testBooking = new Booking();
			testBooking.setBooking_id("9D99920171212");
			testBooking.setTour_id("9D999");
			testBooking.setDate(new SimpleDateFormat("YYYY-MM-dd").parse("2017-12-12"));
			testBooking.setTour_guide("Vickyram");
			testBooking.setTour_guide_line_acc("tourguide.vickyram");
			testBooking.setHotel("Hotel de Gobinmurthy");
			testBooking.setTotal_cap(30);
			testBooking.setRemaining_cap(30);
			testBooking.setMin_req(4);
			testBooking.setStatus(BookingStatus.CONFIRMED);
			testBooking.setHits(0);
			bookingRepo.save(testBooking);

			Iterable<Booking> bookings = this.bookingRepo.findAll();
			for (Booking booking : bookings) {
				if (booking.getBooking_id().equals(testBooking.getBooking_id())) {
					testBooking = booking;
					found = true;
				}
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(found).isEqualTo(true);
		assertThat(testBooking).isNotNull();
		assertThat(testBooking.getBooking_id()).isEqualTo("9D99920171212");
		assertThat(testBooking.getTour_id()).isEqualTo("9D999");
		assertThat(testBooking.getDate()).isEqualTo(new SimpleDateFormat("YYYY-MM-dd").parse("2017-12-12"));
		assertThat(testBooking.getTour_guide()).isEqualTo("Vickyram");
		assertThat(testBooking.getTour_guide_line_acc()).isEqualTo("tourguide.vickyram");
		assertThat(testBooking.getHotel()).isEqualTo("Hotel de Gobinmurthy");
		assertThat(testBooking.getTotal_cap()).isEqualTo("30");
		assertThat(testBooking.getRemaining_cap()).isEqualTo("30");
		assertThat(testBooking.getMin_req()).isEqualTo("4");
		assertThat(testBooking.getStatus()).isEqualTo(BookingStatus.CONFIRMED);
		assertThat(testBooking.getHits()).isGreaterThanOrEqualTo(0);
	}

	@Test
	public void setAndGetTourOfferingWithNotConfirmedStatus() throws Exception {
		boolean found = false;
		boolean thrown = false;
		Booking testBooking = null;
		try {
			testBooking = new Booking();
			testBooking.setBooking_id("9D99920171212");
			testBooking.setTour_id("9D999");
			testBooking.setDate(new SimpleDateFormat("YYYY-MM-dd").parse("2017-12-12"));
			testBooking.setTour_guide("Vickyram");
			testBooking.setTour_guide_line_acc("tourguide.vickyram");
			testBooking.setHotel("Hotel de Gobinmurthy");
			testBooking.setTotal_cap(30);
			testBooking.setRemaining_cap(30);
			testBooking.setMin_req(4);
			testBooking.setStatus(BookingStatus.NOT_CONFIRMED);
			testBooking.setHits(0);
			bookingRepo.save(testBooking);

			Iterable<Booking> bookings = this.bookingRepo.findAll();
			for (Booking booking : bookings) {
				if (booking.getBooking_id().equals(testBooking.getBooking_id())) {
					testBooking = booking;
					found = true;
				}
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(found).isEqualTo(true);
		assertThat(testBooking).isNotNull();
		assertThat(testBooking.getBooking_id()).isEqualTo("9D99920171212");
		assertThat(testBooking.getTour_id()).isEqualTo("9D999");
		assertThat(testBooking.getDate()).isEqualTo(new SimpleDateFormat("YYYY-MM-dd").parse("2017-12-12"));
		assertThat(testBooking.getTour_guide()).isEqualTo("Vickyram");
		assertThat(testBooking.getTour_guide_line_acc()).isEqualTo("tourguide.vickyram");
		assertThat(testBooking.getHotel()).isEqualTo("Hotel de Gobinmurthy");
		assertThat(testBooking.getTotal_cap()).isEqualTo("30");
		assertThat(testBooking.getRemaining_cap()).isEqualTo("30");
		assertThat(testBooking.getMin_req()).isEqualTo("4");
		assertThat(testBooking.getStatus()).isEqualTo(BookingStatus.NOT_CONFIRMED);
		assertThat(testBooking.getHits()).isGreaterThanOrEqualTo(0);
	}

	@Test
	public void setAndGetTourOfferingWithCancelledStatus() throws Exception {
		boolean found = false;
		boolean thrown = false;
		Booking testBooking = null;
		try {
			testBooking = new Booking();
			testBooking.setBooking_id("9D99920171212");
			testBooking.setTour_id("9D999");
			testBooking.setDate(new SimpleDateFormat("YYYY-MM-dd").parse("2017-12-12"));
			testBooking.setTour_guide("Vickyram");
			testBooking.setTour_guide_line_acc("tourguide.vickyram");
			testBooking.setHotel("Hotel de Gobinmurthy");
			testBooking.setTotal_cap(30);
			testBooking.setRemaining_cap(30);
			testBooking.setMin_req(4);
			testBooking.setStatus(BookingStatus.CANCELLED);
			testBooking.setHits(0);
			bookingRepo.save(testBooking);

			Iterable<Booking> bookings = this.bookingRepo.findAll();
			for (Booking booking : bookings) {
				if (booking.getBooking_id().equals(testBooking.getBooking_id())) {
					testBooking = booking;
					found = true;
				}
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(found).isEqualTo(true);
		assertThat(testBooking).isNotNull();
		assertThat(testBooking.getBooking_id()).isEqualTo("9D99920171212");
		assertThat(testBooking.getTour_id()).isEqualTo("9D999");
		assertThat(testBooking.getDate()).isEqualTo(new SimpleDateFormat("YYYY-MM-dd").parse("2017-12-12"));
		assertThat(testBooking.getTour_guide()).isEqualTo("Vickyram");
		assertThat(testBooking.getTour_guide_line_acc()).isEqualTo("tourguide.vickyram");
		assertThat(testBooking.getHotel()).isEqualTo("Hotel de Gobinmurthy");
		assertThat(testBooking.getTotal_cap()).isEqualTo("30");
		assertThat(testBooking.getRemaining_cap()).isEqualTo("30");
		assertThat(testBooking.getMin_req()).isEqualTo("4");
		assertThat(testBooking.getStatus()).isEqualTo(BookingStatus.CANCELLED);
		assertThat(testBooking.getHits()).isGreaterThanOrEqualTo(0);
	}
}