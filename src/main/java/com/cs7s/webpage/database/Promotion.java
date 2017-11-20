package com.cs7s.webpage.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.Integer;
import java.lang.Float;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.UUID;


@Entity //referring to the type of record in a table
@Table(name = "promotion_table")
public class Promotion {
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Id
    private UUID id;
    
    @Column(name="description")
    private String description;
    
    @Column(name="tour_offering")
    private String tourOffering;

	@Column(name="type")
    private String type;
    
    @Column(name="deadline")
    private Timestamp deadline;
    
    @Column(name="discount", nullable=true)
    private Float discount;
    
    @Column(name="customer_quota")
    private Integer customer_quota;
    
    @Column(name="overall_quota")
    private Integer overall_quota;
    
	public String getTourOffering() {
		return tourOffering;
	}

	public void setTourOffering(String tourOffering) {
		this.tourOffering = tourOffering;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDiscount() {
		return String.valueOf(discount);
	}

	public void setDiscount(String discount) {
		this.discount = Float.parseFloat(discount);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeadline() {
		return String.valueOf(deadline);
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public String getCustomer_quota() {
		return String.valueOf(customer_quota);
	}

	public void setCustomer_quota(String customer_quota) {
		this.customer_quota = Integer.parseInt(customer_quota);
	}

	public String getOverall_quota() {
		return String.valueOf(overall_quota);
	}

	public void setOverall_quota(String overall_quota) {
		this.overall_quota = Integer.parseInt(overall_quota);
	}
}
