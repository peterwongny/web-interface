package com.cs7s.webpage.database;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;

/**
 * booking (tour offering) entity that is in the database
 * @author Wong Ngo Yin
 *
 */
@Entity //refering to the type of record in a table
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
	 * @return the booking_id
	 */
	public String getBooking_id() {
		return booking_id;
	}

	/**
	 * @param booking_id the booking_id to set
	 */
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}

	/**
	 * @return the tour_id
	 */
	public String getTour_id() {
		return tour_id;
	}

	/**
	 * @param tour_id the tour_id to set
	 */
	public void setTour_id(String tour_id) {
		this.tour_id = tour_id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the tour_guide
	 */
	public String getTour_guide() {
		return tour_guide;
	}

	/**
	 * @param tour_guide the tour_guide to set
	 */
	public void setTour_guide(String tour_guide) {
		this.tour_guide = tour_guide;
	}

	/**
	 * @return the tour_guide_line_acc
	 */
	public String getTour_guide_line_acc() {
		return tour_guide_line_acc;
	}

	/**
	 * @param tour_guide_line_acc the tour_guide_line_acc to set
	 */
	public void setTour_guide_line_acc(String tour_guide_line_acc) {
		this.tour_guide_line_acc = tour_guide_line_acc;
	}

	/**
	 * @return the hotel
	 */
	public String getHotel() {
		return hotel;
	}

	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	/**
	 * @return the total_cap
	 */
	public String getTotal_cap() {
		return Integer.toString(total_cap);
	}

	/**
	 * @param total_cap the total_cap to set
	 */
	public void setTotal_cap(int total_cap) {
		this.total_cap = total_cap;
	}
	
	public void setTotal_cap(String total_cap) {
		setTotal_cap(Integer.parseInt(total_cap));
	}

	/**
	 * @return the min_req
	 */
	public String getMin_req() {
		return Integer.toString(min_req);
	}

	/**
	 * @param min_req the min_req to set
	 */
	public void setMin_req(int min_req) {
		this.min_req = min_req;
	}
	
	public void setMin_req(String min_req) {
		setMin_req(Integer.parseInt(min_req));
	}

	
	/**
	 * @return the remaining_cap
	 */
	public String getRemaining_cap() {
		return Integer.toString(remaining_cap);
	}

	/**
	 * @param remaining_cap the remaining_cap to set
	 */
	public void setRemaining_cap(int remaining_cap) {
		this.remaining_cap = remaining_cap;
	}
	
	public void setRemaining_cap(String remaining_cap) {
		this.remaining_cap = Integer.parseInt(remaining_cap);
	}

	
	/**
	 * @return the status
	 */
	public BookingStatus getStatus() {
		if (status == null) {
			status = "not confirmed";
		}
		if (status.equals("confirmed"))
			return BookingStatus.values()[0];
		if (status.equals("not confirmed"))
			return BookingStatus.values()[1];
		else
			return BookingStatus.values()[2];
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public void setStatus(BookingStatus status) {
		if(status.ordinal()==0)
			setStatus("confirmed");
		if(status.ordinal()==1)
			setStatus("not confirmed");
		if(status.ordinal()==2)
			setStatus("cancelled");
	}

	/**
	 * @return the hits
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * @param hits the hits to set
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}

}