package com.cs7s.webpage.database;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The booking (tour offering) entity that is in the database.
 * @author Wong Ngo Yin
 */
@Entity // Refers to the type of record in a table.
@Table(name = "booking_table")
public class Booking {
	@Id
	@Column(name="booking_id")
	private String booking_id;

	@Column(name="tour_id")
	private String tour_id;

	@Column(name="date")
	private Date date;

	@Column(name="tour_guide")
	private String tour_guide;

	@Column(name="tour_guide_line_acc")
	private String tour_guide_line_acc;

	@Column(name="hotel")
	private String hotel;

	@Column(name="total_cap")
	private int total_cap;

	@Column(name="remaining_cap")
	private int remaining_cap;

	@Column(name="min_req")
	private int min_req;

	@Column(name="status")
	private String status;

	@Column(name="hits")
	private int hits;

	/**
	 * @return the booking ID.
	 */
	public String getBooking_id() {
		return booking_id;
	}

	/**
	 * @param booking_id the booking ID to set.
	 */
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}

	/**
	 * @return the tour ID.
	 */
	public String getTour_id() {
		return tour_id;
	}

	/**
	 * @param tour_id the tour ID to set.
	 */
	public void setTour_id(String tour_id) {
		this.tour_id = tour_id;
	}

	/**
	 * @return the date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the tour guide.
	 */
	public String getTour_guide() {
		return tour_guide;
	}

	/**
	 * @param tour_guide the tour guide to set.
	 */
	public void setTour_guide(String tour_guide) {
		this.tour_guide = tour_guide;
	}

	/**
	 * @return the tour guide's LINE account.
	 */
	public String getTour_guide_line_acc() {
		return tour_guide_line_acc;
	}

	/**
	 * @param tour_guide_line_acc the tour guide's LINE account to set.
	 */
	public void setTour_guide_line_acc(String tour_guide_line_acc) {
		this.tour_guide_line_acc = tour_guide_line_acc;
	}

	/**
	 * @return the hotel.
	 */
	public String getHotel() {
		return hotel;
	}

	/**
	 * @param hotel the hotel to set.
	 */
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	/**
	 * @return the total tour offering capacity.
	 */
	public String getTotal_cap() {
		return Integer.toString(total_cap);
	}

	/**
	 * @param total_cap the total tour offering capacity to set.
	 */
	public void setTotal_cap(int total_cap) {
		this.total_cap = total_cap;
	}

	public void setTotal_cap(String total_cap) {
		setTotal_cap(Integer.parseInt(total_cap));
	}

	/**
	 * @return the minimum required confirmed bookings.
	 */
	public String getMin_req() {
		return Integer.toString(min_req);
	}

	/**
	 * @param min_req the minimum required confirmed bookings to set.
	 */
	public void setMin_req(int min_req) {
		this.min_req = min_req;
	}

	public void setMin_req(String min_req) {
		setMin_req(Integer.parseInt(min_req));
	}


	/**
	 * @return the remaining tour offering capacity.
	 */
	public String getRemaining_cap() {
		return Integer.toString(remaining_cap);
	}

	/**
	 * @param remaining_cap the remaining tour offering capacity to set.
	 */
	public void setRemaining_cap(int remaining_cap) {
		this.remaining_cap = remaining_cap;
	}

	public void setRemaining_cap(String remaining_cap) {
		this.remaining_cap = Integer.parseInt(remaining_cap);
	}


	/**
	 * @return the tour offering's status.
	 */
	public BookingStatus getStatus() {
		if (status.equals("confirmed")) {
			return BookingStatus.CONFIRMED;
		} else if (status.equals("not confirmed")) {
			return BookingStatus.NOT_CONFIRMED;
		} else {
			return BookingStatus.CANCELLED;
		}
	}

	/**
	 * @param status the tour offering's status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatus(BookingStatus status) {
		if(status.ordinal() == BookingStatus.CONFIRMED.ordinal()) {
			setStatus("confirmed");
		} else if (status.ordinal() == BookingStatus.NOT_CONFIRMED.ordinal()) {
			setStatus("not confirmed");
		} else {
			setStatus("cancelled");
		}
	}

	/**
	 * @return the number of search hits.
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * @param hits the number of search hits to set.
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}
}