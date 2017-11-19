package com.cs7s.webpage.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * The tour entity that exists in the database.
 * @author Wong Ngo Yin
 */
@Entity // Refers to the type of record in a table.
@Table(name = "tour_list")
public class Tour {
	@Id
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "duration")
	private int duration;

	@Column(name = "day")
	private String day;

	@Column(name = "weekday_price")
	private Integer weekday_price;

	@Column(name = "weekend_price")
	private Integer weekend_price;

	@Column(name = "tag")
	private String tag;

	@Column(name = "hits")
	private int hits;

	/**
	 * @return the tour ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the tour ID to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the tour name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the tour name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tour description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the tour description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the tour duration.
	 */
	public String getDuration() {
		return Integer.toString(duration);
	}

	/**
	 * @param duration the tour duration to set.
	 */
	public void setDuration(String duration) {
		setDuration(Integer.parseInt(duration));
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the tour's departure days.
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the tour's departure days to set.
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the tour's weekday price.
	 */
	public String getWeekday_price() {
		return Integer.toString(weekday_price);
	}

	/**
	 * @param weekday_price the tour's weekday price to set.
	 */
	public void setWeekday_price(String weekday_price) {
		setWeekday_price(Integer.parseInt(weekday_price));
	}

	public void setWeekday_price(int weekday_price) {
		this.weekday_price = weekday_price;
	}

	/**
	 * @return the tour's weekend price.
	 */
	public String getWeekend_price() {
		return Integer.toString(weekend_price);
	}

	/**
	 * @param weekend_price the tour's weekend price to set.
	 */
	public void setWeekend_price(String weekend_price) {
		setWeekend_price(Integer.parseInt(weekend_price));

	}

	public void setWeekend_price(int weekend_price) {
		this.weekend_price = weekend_price;
	}

	/**
	 * @return the tour's tag.
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tour's tag to set.
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the tour's number of search hits.
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * @param hits the tour's number of search hits to set.
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}
}