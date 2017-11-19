package com.cs7s.webpage.database;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;

/**
 * The customer entity that exists in the database.
 * @author Wong Ngo Yin
 */
@Entity // Refers to the type of record in a table.
@Table(name = "customer_table")
public class Customer {
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
   	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    private UUID id;
	
    @Column(name = "name")
    private String name;
    
    @Column(name = "cust_id")
    private String cust_id;
    
    @Column(name = "line_id")
    private String line_id;

    @Column(name = "phone")
    private String phone;
    
    @Column(name = "age")
    private int age;
    
    @Column(name = "tour_joined")
    private String tour_joined;
    
    @Column(name = "adult_num")
    private int adult_num;
    
    @Column(name = "children_num")
    private int children_num;
    
    @Column(name = "toddler_num")
    private int toddler_num;
    
	@Column(name = "fee")
    private int fee;
    
    @Column(name = "amt_paid")
    private int amt_paid;
    
    @Column(name = "special_req")
    private String special_req;

	/**
	 * @return the customer's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the customer's name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the customer ID.
	 */
	public String getCust_id() {
		return cust_id;
	}

	/**
	 * @param cust_id the customer ID to set.
	 */
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
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
	 * @return the customer's phone number.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the customer's phone number to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the customer's age.
	 */
	public String getAge() {
		return Integer.toString(age);
	}

	/**
	 * @param age the customer's age to set.
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAge(String age) {
		setAge(Integer.parseInt(age));
	}

	/**
	 * @return the tour that the customer joined.
	 */
	public String getTour_joined() {
		return tour_joined;
	}

	/**
	 * @param tour_joined the the tour that the customer joined to set.
	 */
	public void setTour_joined(String tour_joined) {
		this.tour_joined = tour_joined;
	}

	/**
	 * @return the number of adults joining.
	 */
	public String getAdult_num() {
		return Integer.toString(adult_num);
	}

	/**
	 * @param adult_num the number of adults joining to set.
	 */
	public void setAdult_num(int adult_num) {
		this.adult_num = adult_num;
	}
	
	public void setAdult_num(String adult_num) {
		setAdult_num(Integer.parseInt(adult_num));
	}

	/**
	 * @return the number of children joining.
	 */
	public String getChildren_num() {
		return Integer.toString(children_num);
	}

	/**
	 * @param children_num the number of children joining to set.
	 */
	public void setChildren_num(int children_num) {
		this.children_num = children_num;
	}
	
	public void setChildren_num(String children_num) {
		setChildren_num(Integer.parseInt(children_num));
	}

	/**
	 * @return the number of toddlers joining.
	 */
	public String getToddler_num() {
		return Integer.toString(toddler_num);
	}

	/**
	 * @param toddler_num the number of toddlers joining to set.
	 */
	public void setToddler_num(int toddler_num) {
		this.toddler_num = toddler_num;
	}
	
	public void setToddler_num(String toddler_num) {
		setToddler_num(Integer.parseInt(toddler_num));
	}

	/**
	 * @return the booking fee.
	 */
	public String getFee() {
		return Integer.toString(fee);
	}

	/**
	 * @param fee the booking fee to set.
	 */
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	public void setFee(String fee) {
		setFee(Integer.parseInt(fee));
	}

	/**
	 * @return the amount the customer has paid so far.
	 */
	public String getAmt_paid() {
		return Integer.toString(amt_paid);
	}

	/**
	 * @param amt_paid the amount the customer has paid so far to set.
	 */
	public void setAmt_paid(int amt_paid) {
		this.amt_paid = amt_paid;
	}
	
	public void setAmt_paid(String amt_paid) {
		setAmt_paid(Integer.parseInt(amt_paid));
	}

	/**
	 * @return the special requests made by the customer.
	 */
	public String getSpecial_req() {
		return special_req;
	}

	/**
	 * @param special_req the special requests made by the customer to set.
	 */
	public void setSpecial_req(String special_req) {
		this.special_req = special_req;
	}
}

