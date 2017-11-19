package com.cs7s.webpage.ui.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.TourRepository;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class BookingReport extends VerticalLayout {
	
	private BookingRepository bookingRepo;
	private TourRepository tourRepo;
	
	public BookingReport(BookingRepository bookingRepo, TourRepository tourRepo) {
		this.bookingRepo = bookingRepo;
		this.tourRepo = tourRepo;
		
		this.addComponent(new Label("OVERALL", ContentMode.PREFORMATTED));
		
		//most search
		String maxHitString = "Most search tour: \n";
		List<Map.Entry<String, Integer>> maxHits = getMaxFromMap(getHits());
		
		for(Map.Entry<String, Integer> maxHit : maxHits) {
			
			String tourName = tourRepo.findById(maxHit.getKey()).get(0).getName();
			
			maxHitString += "\t" + tourName + " (" + Integer.toString(maxHit.getValue()) + " hits)\n";
		}
		Label maxHitLabel = new Label();
		maxHitLabel.setContentMode(ContentMode.PREFORMATTED);
		maxHitLabel.setValue(maxHitString);
		this.addComponent(maxHitLabel);
		
		
		// most joined
		String maxJoinString = "Tour with most people joined: \n";
		List<Map.Entry<String, Integer>> maxJoins = getMaxFromMap(getJoined());
		
		for(Map.Entry<String, Integer> maxJoin : maxJoins) {
			
			String tourName = tourRepo.findById(maxJoin.getKey()).get(0).getName();
			
			maxJoinString += "\t" + tourName + " (" + Integer.toString(maxJoin.getValue()) + " people)\n";
		}
		Label maxJoinLabel = new Label();
		maxJoinLabel.setContentMode(ContentMode.PREFORMATTED);
		maxJoinLabel.setValue(maxJoinString);
		this.addComponent(maxJoinLabel);
		
		
		//most joined percentage
		String maxJoinPercentString = "Tour with most people joined in percentage: \n";
		List<Map.Entry<String, Double>> maxJoinsPercents = getMaxFromMapDouble(getJoinPercentage());
		
		for(Map.Entry<String, Double> maxJoinsPercent : maxJoinsPercents) {
			
			String tourName = tourRepo.findById(maxJoinsPercent.getKey()).get(0).getName();
			
			maxJoinPercentString += "\t" + tourName + " (" + Double.toString(maxJoinsPercent.getValue()) + "%)\n";
		}
		Label maxJoinPercentLabel = new Label();
		maxJoinPercentLabel.setContentMode(ContentMode.PREFORMATTED);
		maxJoinPercentLabel.setValue(maxJoinPercentString);
		this.addComponent(maxJoinPercentLabel);
		
		
		//least joined percentage
		String minJoinPercentString = "Tour with least people joined in percentage: \n";
		List<Map.Entry<String, Double>> minJoinsPercents = getMinFromMapDouble(getJoinPercentage());
		
		for(Map.Entry<String, Double> minJoinsPercent : minJoinsPercents) {
			
		String tourName = tourRepo.findById(minJoinsPercent.getKey()).get(0).getName();
				
		minJoinPercentString += "\t" + tourName + " (" + Double.toString(minJoinsPercent.getValue()) + "%)\n";
		}
		Label minJoinPercentLabel = new Label();
		minJoinPercentLabel.setContentMode(ContentMode.PREFORMATTED);
		minJoinPercentLabel.setValue(minJoinPercentString);
		this.addComponent(minJoinPercentLabel);
		
		
	}
	
	private Map<String, Integer> getHits(){
		List<Booking> data = bookingRepo.findAll();
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for(Booking booking : data) {
			if(result.get(booking.getTour_id()) == null) {
				result.put(booking.getTour_id(), booking.getHits());
			}
			else {
				result.put(booking.getTour_id(), result.get(booking.getTour_id()) + booking.getHits());
			}
		}
		
		return result;
	}
	
	private Map<String, Integer> getJoined(){
		List<Booking> data = bookingRepo.findAll();
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for(Booking booking : data) {
			int numJoined = Integer.parseInt(booking.getTotal_cap()) - Integer.parseInt(booking.getRemaining_cap());
			if(result.get(booking.getTour_id()) == null) {
				result.put(booking.getTour_id(), numJoined);
			}
			else {
				result.put(booking.getTour_id(), result.get(booking.getTour_id()) + numJoined);
			}
		}
		
		return result;
	}
	
	private Map<String, Integer> getCapacity(){
		List<Booking> data = bookingRepo.findAll();
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for(Booking booking : data) {
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
	
	private Map<String, Double> getJoinPercentage(){
		Map<String, Integer> capacity = getCapacity();
		Map<String, Integer> join = getJoined();
		Map<String, Double> result = new HashMap<String, Double>();
		
		for (Map.Entry<String, Integer> entry : capacity.entrySet())
		{
			double size = entry.getValue();
			double percentage = join.get(entry.getKey()) * 100.0 / size ;
		    result.put(entry.getKey(), percentage);
		}
		
		return result;
	}
	
	private List<Map.Entry<String, Integer>> getMaxFromMap(Map<String, Integer> map) {
		
		List<Map.Entry<String, Integer>> maxEntry = new ArrayList<Map.Entry<String, Integer>>();
		
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
		    if (maxEntry.isEmpty() || entry.getValue() > maxEntry.get(0).getValue())
		    {
		        maxEntry.clear();
		        maxEntry.add(entry);
		    }
		    else if (entry.getValue() == maxEntry.get(0).getValue())
		    {
		        maxEntry.add(entry);
		    }
		}
		
		return maxEntry;
	}
	
	private List<Map.Entry<String, Double>> getMaxFromMapDouble(Map<String, Double> map) {
		
		List<Map.Entry<String, Double>> maxEntry = new ArrayList<Map.Entry<String, Double>>();
		
		for (Map.Entry<String, Double> entry : map.entrySet())
		{
		    if (maxEntry.isEmpty() || entry.getValue() > maxEntry.get(0).getValue())
		    {
		        maxEntry.clear();
		        maxEntry.add(entry);
		    }
		    else if (entry.getValue() == maxEntry.get(0).getValue())
		    {
		        maxEntry.add(entry);
		    }
		}
		
		return maxEntry;
	}
	
//	private List<Map.Entry<String, Integer>> getMinFromMap(Map<String, Integer> map) {
//		
//		List<Map.Entry<String, Integer>> minEntry = new ArrayList<Map.Entry<String, Integer>>();
//		
//		for (Map.Entry<String, Integer> entry : map.entrySet())
//		{
//		    if (minEntry.isEmpty() || entry.getValue() < minEntry.get(0).getValue())
//		    {
//		    	minEntry.clear();
//		        minEntry.add(entry);
//		    }
//		    else if (entry.getValue() == minEntry.get(0).getValue())
//		    {
//		    	minEntry.add(entry);
//		    }
//		}
//		
//		return minEntry;
//	}
	
	private List<Map.Entry<String, Double>> getMinFromMapDouble(Map<String, Double> map) {
		
		List<Map.Entry<String, Double>> minEntry = new ArrayList<Map.Entry<String, Double>>();
		
		for (Map.Entry<String, Double> entry : map.entrySet())
		{
		    if (minEntry.isEmpty() || entry.getValue() < minEntry.get(0).getValue())
		    {
		    	minEntry.clear();
		        minEntry.add(entry);
		    }
		    else if (entry.getValue() == minEntry.get(0).getValue())
		    {
		    	minEntry.add(entry);
		    }
		}
		
		return minEntry;
	}
	
}


