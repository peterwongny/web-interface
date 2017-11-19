package com.cs7s.webpage.database;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //refering to the type of record in a table
@Table(name = "unanswered_enquiries_table")
public class UnansweredEnquiries {
	@Id
	@Column(name="id")
	UUID id;
	
	@Column(name="line_id")
	String line_id;
	
	@Column(name="enquiry")
	String enquiry;
	
	@Column(name="answered")
	Boolean answered;
	
	
	
	

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	
	
	/**
	 * @return the line_id
	 */
	public String getLine_id() {
		return line_id;
	}
	/**
	 * @param line_id the line_id to set
	 */
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	/**
	 * @return the enquiry
	 */
	public String getEnquiry() {
		return enquiry;
	}
	/**
	 * @param enquiry the enquiry to set
	 */
	public void setEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}
	/**
	 * @return the answered
	 */
	public EnquiriesStatus getAnswered() {
		if (answered == null) {
			answered = false;
		}
		int number = (answered ? 1 : 0);
		return EnquiriesStatus.values()[number];
	}
	/**
	 * @param answered the answered to set
	 */
	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}
	
	public void setAnswered(EnquiriesStatus status) {
		setAnswered(status.ordinal()==1);
	}
}