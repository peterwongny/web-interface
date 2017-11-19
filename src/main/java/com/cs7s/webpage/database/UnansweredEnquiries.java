package com.cs7s.webpage.database;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The unanswered enquiries entity that is in the database.
 */
@Entity // Refers to the type of record in a table
@Table(name = "unanswered_enquiries_table")
public class UnansweredEnquiries {
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Id
	private UUID id;

	@Column(name = "line_id")
	private String line_id;

	@Column(name = "enquiry")
	private String enquiry;

	@Column(name = "answered")
	private Boolean answered;

	/**
	 * @return the customer's LINE ID.
	 */
	public String getLine_id() {
		return line_id;
	}

	/**
	 * @param line_id the customer's LINE ID to set.
	 */
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	/**
	 * @return the customer's enquiry.
	 */
	public String getEnquiry() {
		return enquiry;
	}

	/**
	 * @param enquiry the customer's enquiry to set.
	 */
	public void setEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}

	/**
	 * @return 1 if the enquiry has been answered, otherwise 0.
	 */
	public EnquiriesStatus getAnswered() {
		int number = (answered ? 1 : 0);
		return EnquiriesStatus.values()[number];
	}

	/**
	 * @param answered the enquiry's status to set.
	 */
	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	public void setAnswered(EnquiriesStatus status) {
		setAnswered(status.ordinal() == 1);
	}
}