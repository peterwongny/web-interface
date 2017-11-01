package com.web.webpage.database;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity //refering to the type of record in a table
@Table(name = "tour_list")
public class Tour {
    @Id
    private String id;
    
    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;
    
    @Column(name="duration")
    private String duration;
    
    @Column(name="day")
    private String day;
    
    @Column(name="weekday_price")
    private String weekday_price;
    
    @Column(name="weekend_price")
    private String weekend_price;
    
    @Column(name="hits")
    private String hits;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the weekday_price
	 */
	public String getWeekday_price() {
		return weekday_price;
	}

	/**
	 * @param weekday_price the weekday_price to set
	 */
	public void setWeekday_price(String weekday_price) {
		this.weekday_price = weekday_price;
	}

	/**
	 * @return the weekend_price
	 */
	public String getWeekend_price() {
		return weekend_price;
	}

	/**
	 * @param weekend_price the weekend_price to set
	 */
	public void setWeekend_price(String weekend_price) {
		this.weekend_price = weekend_price;
	}

	/**
	 * @return the hits
	 */
	public String getHits() {
		return hits;
	}

	/**
	 * @param hits the hits to set
	 */
	public void setHits(String hits) {
		this.hits = hits;
	}
    
    
}


