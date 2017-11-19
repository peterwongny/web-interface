package com.cs7s.webpage.ui.report;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.TourRepository;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates weekly reports.
 */
@SuppressWarnings("serial")
public class BookingReport extends VerticalLayout {
	private BookingRepository bookingRepo;
	private TourRepository tourRepo;
	private int maxShown = 5;

	/**
	 * The constructor for BookingReport;
	 * @param bookingRepo the booking repository.
	 * @param tourRepo the tour repository.
	 */
	public BookingReport(BookingRepository bookingRepo, TourRepository tourRepo) {
		this.bookingRepo = bookingRepo;
		this.tourRepo = tourRepo;

		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DAY_OF_WEEK, -(cal.get(Calendar.DAY_OF_WEEK)-1)); 

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formattedStartDate = "1971-01-01";
		String formattedEndDate = format1.format(cal.getTime());

		Label overallLabel = new Label("<h1><b>OVERALL</b></h1>"
				+ "<h3><b>Retrieved on: " + formattedEndDate + " Sunday</b></h3><hr>", ContentMode.HTML);
		overallLabel.setSizeFull();
		this.addComponent(overallLabel);
		this.setComponentAlignment(overallLabel, Alignment.TOP_CENTER);
		generateReport(formattedStartDate, formattedEndDate);
		
		cal.add( Calendar.DAY_OF_WEEK, -7);
		formattedStartDate = format1.format(cal.getTime());
		
		Label weeklyLabel = new Label("<hr size='40' color='black' /><h1><b>" + formattedStartDate + " to " + formattedEndDate + "</b></h1>"
				+ "<h3><b>Retrieved on: " + formattedEndDate + " Sunday</b></h3><hr>", ContentMode.HTML);
		weeklyLabel.setSizeFull();
		this.addComponent(weeklyLabel);
		this.setComponentAlignment(overallLabel, Alignment.TOP_CENTER);
		generateReport(formattedStartDate, formattedEndDate);
	}
	
	private void generateReport(String formattedStartDate, String formattedEndDate) {
		// Most searched tours
		String maxHitString = "<h2><b>" + maxShown + " most searched tours:</b></h2>\n";
		Map<String, Integer> maxHits = sortByValueDesc(getHits(formattedStartDate, formattedEndDate));
		List<String> maxHitsList = new ArrayList<String>(maxHits.keySet());

		int count = 0;
		for(String maxHit : maxHitsList) {
			String tourName = tourRepo.findById(maxHit).get(0).getName();
			maxHitString += "\t<p><b>" + tourName + " (" + Integer.toString(maxHits.get(maxHit)) + " hits)</b></p>\n";

			if (++count == maxShown) {
				break;
			}
		}
		Label maxHitLabel = new Label();
		maxHitLabel.setContentMode(ContentMode.HTML);
		maxHitLabel.setValue(maxHitString);
		maxHitLabel.setSizeFull();
		this.addComponent(maxHitLabel);

		// Most joined tours
		String maxJoinString = "<h2><b>" + maxShown + " most joined tours:</b></h2>\n";
		Map<String, Integer> maxJoins = sortByValueDesc(getJoined(formattedStartDate, formattedEndDate));
		List<String> maxJoinsList = new ArrayList<String>(maxJoins.keySet());

		count = 0;
		for(String maxJoin : maxJoinsList) {
			String tourName = tourRepo.findById(maxJoin).get(0).getName();
			maxJoinString += "\t<p><b>" + tourName + " (" + Integer.toString(maxJoins.get(maxJoin)) + " people)</b></p>\n";

			if (++count == maxShown) {
				break;
			}
		}
		Label maxJoinLabel = new Label();
		maxJoinLabel.setContentMode(ContentMode.HTML);
		maxJoinLabel.setValue(maxJoinString);
		maxJoinLabel.setSizeFull();
		this.addComponent(maxJoinLabel);

		// Most joined tours by percentage
		String maxJoinPercentString = "<h2><b>" + maxShown + "  most joined tours (in percentage):</b></h2>\n";
		Map<String, Double> maxJoinsPercents = sortByValueDesc(getJoinPercentage(formattedStartDate, formattedEndDate));
		ArrayList<String> maxJoinsPercentsList = new ArrayList<String>(maxJoinsPercents.keySet());

		count = 0;
		for(String maxJoinsPercent : maxJoinsPercentsList) {
			String tourName = tourRepo.findById(maxJoinsPercent).get(0).getName();

			maxJoinPercentString += "\t<p><b>" + tourName + " (" + Double.toString(maxJoinsPercents.get(maxJoinsPercent)) + "%)</b></p>\n";
			if (++count == maxShown) {
				break;
			}
		}
		Label maxJoinPercentLabel = new Label();
		maxJoinPercentLabel.setContentMode(ContentMode.HTML);
		maxJoinPercentLabel.setValue(maxJoinPercentString);
		maxJoinPercentLabel.setSizeFull();
		this.addComponent(maxJoinPercentLabel);

		// Least searched tours
		String minHitString = "<h2><b>" + maxShown + " least searched tours:</b></h2>\n";
		Map<String, Integer> minHits = sortByValueAsc(getHits(formattedStartDate, formattedEndDate));
		List<String> minHitsList = new ArrayList<String>(maxHits.keySet());

		count = 0;
		for(String minHit : minHitsList) {
			String tourName = tourRepo.findById(minHit).get(0).getName();
			minHitString += "\t<p><b>" + tourName + " (" + Integer.toString(minHits.get(minHit)) + " hits)</b></p>\n";

			if (++count == maxShown) {
				break;
			}
		}
		Label minHitLabel = new Label();
		minHitLabel.setContentMode(ContentMode.HTML);
		minHitLabel.setValue(minHitString);
		minHitLabel.setSizeFull();
		this.addComponent(minHitLabel);		

		// Least joined tours by percentage
		String minJoinPercentString = "<h2><b>" + maxShown + " least joined tours (in percentage):</b></h2>\n";
		Map<String, Double> minJoinsPercents = sortByValueAsc(getJoinPercentage(formattedStartDate, formattedEndDate));
		ArrayList<String> minJoinsPercentsList = new ArrayList<String>(minJoinsPercents.keySet());

		count = 0;
		for(String minJoinsPercent : minJoinsPercentsList) {
			String tourName = tourRepo.findById(minJoinsPercent).get(0).getName();
			minJoinPercentString += "\t<p><b>" + tourName + " (" + Double.toString(minJoinsPercents.get(minJoinsPercent)) + "%)</b></p>\n";

			if (++count == maxShown) {
				break;
			}
		}
		Label minJoinPercentLabel = new Label();
		minJoinPercentLabel.setContentMode(ContentMode.HTML);
		minJoinPercentLabel.setValue(minJoinPercentString);
		minJoinPercentLabel.setSizeFull();
		this.addComponent(minJoinPercentLabel);
	}

	private <K, V extends Comparable<? super V>> Map<K, V> sortByValueAsc(Map<K, V> map) {
		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(
						Map.Entry::getKey, 
						Map.Entry::getValue, 
						(e1, e2) -> e1, 
						LinkedHashMap::new
						));
	}

	private <K, V extends Comparable<? super V>> Map<K, V> sortByValueDesc(Map<K, V> map) {
		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey, 
						Map.Entry::getValue, 
						(e1, e2) -> e1, 
						LinkedHashMap::new
						));
	}

	private boolean dateInRange(String date1, String date2, Date targetDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = format.parse(date1);
			endDate = format.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return !(targetDate.compareTo(startDate) < 0 || targetDate.compareTo(endDate) > 0);
	}

	private Map<String, Integer> getHits(String date1, String date2) {
		List<Booking> data = bookingRepo.findAll();
		Map<String, Integer> result = new HashMap<String, Integer>();

		for(Booking booking : data) {
			Date tourDate = booking.getDate();

			if (!dateInRange(date1, date2, tourDate)) {
				continue;
			} else if(result.get(booking.getTour_id()) == null) {
				result.put(booking.getTour_id(), booking.getHits());
			} else {
				result.put(booking.getTour_id(), result.get(booking.getTour_id()) + booking.getHits());
			}
		}

		return result;
	}

	private Map<String, Integer> getJoined(String date1, String date2) {
		List<Booking> data = bookingRepo.findAll();
		Map<String, Integer> result = new HashMap<String, Integer>();

		for(Booking booking : data) {
			Date tourDate = booking.getDate();

			if (!dateInRange(date1, date2, tourDate)) {
				continue;
			}

			int numJoined = Integer.parseInt(booking.getTotal_cap()) - Integer.parseInt(booking.getRemaining_cap());
			if(result.get(booking.getTour_id()) == null) {
				result.put(booking.getTour_id(), numJoined);
			} else {
				result.put(booking.getTour_id(), result.get(booking.getTour_id()) + numJoined);
			}
		}

		return result;
	}

	private Map<String, Integer> getCapacity(String date1, String date2) {
		List<Booking> data = bookingRepo.findAll();

		Map<String, Integer> result = new HashMap<String, Integer>();

		for(Booking booking : data) {
			Date tourDate = booking.getDate();
			if (!dateInRange(date1, date2, tourDate)) {
				continue;
			}

			int capacity = Integer.parseInt(booking.getTotal_cap());
			if(result.get(booking.getTour_id()) == null) {
				result.put(booking.getTour_id(), capacity);
			}
			else {
				result.put(booking.getTour_id(), result.get(booking.getTour_id()) + capacity);
			}
		}

		return result;
	}

	private Map<String, Double> getJoinPercentage(String date1, String date2) {
		Map<String, Integer> capacity = getCapacity(date1, date2);
		Map<String, Integer> join = getJoined(date1, date2);
		Map<String, Double> result = new HashMap<String, Double>();

		for (Map.Entry<String, Integer> entry : capacity.entrySet()) {
			double size = entry.getValue();
			double percentage = join.get(entry.getKey()) * 100.0 / size ;
			result.put(entry.getKey(), percentage);
		}

		return result;
	}
}
