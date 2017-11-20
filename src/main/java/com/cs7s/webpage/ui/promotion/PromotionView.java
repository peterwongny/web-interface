package com.cs7s.webpage.ui.promotion;

import java.util.List;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import org.vaadin.gridutil.cell.GridCellFilter;

import com.cs7s.webpage.database.Promotion;
import com.cs7s.webpage.database.Customer;
import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.PromotionRepository;
import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.CustomerRepository;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;


import com.vaadin.ui.Panel;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

@SpringComponent
@SpringView(name = PromotionView.VIEW_NAME)
@SuppressWarnings("serial")
@UIScope
public class PromotionView extends Panel implements View{
	
	public final static String VIEW_NAME = "Promotion";
	
	public PromotionRepository promoRepo;
	public BookingRepository bookingRepo;
	public CustomerRepository customerRepo;

	private PromotionForm form; 
	private VerticalLayout verticalLayout = new VerticalLayout();
		
	private final Grid<Promotion> grid;
		
	final ListDataProvider<Promotion> provider;
	
	private List<Promotion> promolist;
	
	/**
	 * The constructor of TourView.
	 * @param tourRepo the Tour List repository.
	 */
	public PromotionView(PromotionRepository promoRepo,
			BookingRepository bookingRepo, 
			CustomerRepository customerRepo) {
		this.promoRepo = promoRepo;
		this.bookingRepo = bookingRepo;
		this.customerRepo = customerRepo;
		this.form = new PromotionForm(this);
		this.grid = new Grid<>(Promotion.class);

		grid.setSizeUndefined();
	    grid.setColumnOrder("description","tourOffering", "type",
	    		"discount", "deadline", "customer_quota",
	    		"overall_quota");
	    grid.getColumn("description").setMaximumWidth(300);
	    promolist = promoRepo.findAll();
	    provider = new ListDataProvider<>(promolist);	
		grid.setDataProvider(provider);
		updateList();
		
        Button addPromoBtn = new Button("Add new promotion");
        addPromoBtn.addClickListener(e -> {
        	grid.asSingleSelect().clear();
        	form.setPromotion(new Promotion());
        	setSizeFull();
        });

        HorizontalLayout toolbar = new HorizontalLayout(addPromoBtn);

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        grid.setHeightByRows(10);
        
        verticalLayout.addComponents(toolbar, main);
                
        form.setVisible(false);
        
        grid.asSingleSelect().addValueChangeListener(e -> { 
        	if (e.getValue() == null) {
        		form.setVisible(false);
        	}
        	else {
        		form.setPromotion(e.getValue());
        		setSizeFull();
        	}
        });
	}
	
	/**
	 * Entry point when user selects this page which updates the table.
	 * @param ViewChangeEvent
	 */
	@Override
    public void enter(ViewChangeEvent event) {
		updateList();
		verticalLayout.addComponent(grid); 
		setContent(verticalLayout);
    }
	
	/**
	 * Updates the table based on the Tour List in the database.
	 */
	public void updateList() {
		promolist.clear();
		promolist.addAll(promoRepo.findAll());
		provider.refreshAll();
	}
	
	/**
	 * Updates or inserts a tour entity from the Tour List in the database.
	 * @param tour the tour to be saved.
	 */
	public void save(Promotion promo) {
		promoRepo.saveAndFlush(promo);
		updateList();
		Booking booking = bookingRepo.findById(promo.getTourOffering());
		booking.setHas_promotion(true);
		bookingRepo.saveAndFlush(booking);
		System.out.println("VAL OF PROMOTION "+ String.valueOf(booking.getHas_promotion()));
		try {
			String response = null;
	    	String stringUrl = "https://api.line.me/v2/bot/message/multicast";
	    	stringUrl = stringUrl.replaceAll(" ", "%20");
	    	URL url = new URL(stringUrl);
	    	JsonObject params = new JsonObject();
	    	
	    	List<Customer> customers = customerRepo.findAll();
	    	String[] stringLineIds = new String[customers.size()];
	    	for(int i = 0; i < customers.size(); i++) {
	    		stringLineIds[i]=customers.get(i).getLine_id();
	    		System.out.println(stringLineIds[i]);
	    	}
	    	JsonArray lineIds = new JsonArray();
	        for (String id : stringLineIds) {
	            lineIds.add(new JsonPrimitive(id));
	        }
	    	JsonObject message = new JsonObject();
	    	message.addProperty("type","text");
	    	message.addProperty("text",promo.getDescription());
	    	JsonArray messages = new JsonArray();
	    	messages.add(message);
	    	params.add("to", lineIds);
	    	params.add("messages", messages);
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", 
	        		"Bearer 0KyopSNQN0FQIb4cQ8wnk84exJHoeR4MPhQB1TU8kTz89vZFjH"
	        		+ "P5x5r33TDfMpyu9iFfBv3yH3l5NoWJREQvSvaxAGXRG8rW0uGvF"
	        		+ "Bn2IXPTts0zK+CnQmwDK5n2Qw4576y6/kNKU16DNuz7cXai2AdB0"
	        		+ "4t89/1O/w1cDnyilFU=");
	        conn.setRequestProperty("Content-Type", 
	        		"application/json");
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
	        os.writeBytes(params.toString()); 
	        os.flush();
	        os.close();
	        BufferedReader in = new BufferedReader(
	        		new InputStreamReader(conn.getInputStream()));
	        String inputLine;
	        StringBuffer sb = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	            sb.append(inputLine);
	        }
	        in.close();
	        response = sb.toString();
	        System.out.println("POST RESPONSE "+response);
		} catch (Exception e) {
			System.out.println("EXCEPTION FOR POST "+ e.toString());
		}
	}

}
