package com.web.webpage.UI.enquiriesTable;
//package com.web.webpage.UI;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.vaadin.icons.VaadinIcons;
//import com.vaadin.navigator.View;
//import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
//import com.vaadin.shared.ui.ValueChangeMode;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.CssLayout;
//import com.vaadin.ui.Grid;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.Panel;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.VerticalLayout;
//import com.vaadin.ui.themes.ValoTheme;
//import com.web.webpage.database.UnansweredEnquiries;
//import com.web.webpage.database.UnansweredEnquiriesRepository;
//
//
//public class UnansweredEnquiriesView extends Panel implements View{
//	
//	public UnansweredEnquiriesRepository unRepo;
//	private TextField filterText = new TextField();
//	private UnansweredEnquiriesForm form = new UnansweredEnquiriesForm(this);
//	private VerticalLayout verticalLayout = new VerticalLayout();
//
//	public final static String VIEW_NAME = "Unanswered Enquiries";
//	
//	
//	final Grid<UnansweredEnquiries> grid;
//	
//	public UnansweredEnquiriesView(UnansweredEnquiriesRepository unRepo) {
//		this.unRepo = unRepo;
//		this.grid = new Grid<>(UnansweredEnquiries.class);
//		grid.setSizeUndefined();
//		
//
//		filterText.setPlaceholder("filter by name...");
//        filterText.addValueChangeListener(e -> updateList());
//        filterText.setValueChangeMode(ValueChangeMode.LAZY);
//
//        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
//        clearFilterTextBtn.setDescription("Clear the current filter");
//        clearFilterTextBtn.addClickListener(e -> filterText.clear());
//        
//        CssLayout filtering = new CssLayout();
//        filtering.addComponents(filterText, clearFilterTextBtn);
//        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
//
//        Button addTourBtn = new Button("Add new tour");
//        addTourBtn.addClickListener(e -> {
//        	grid.asSingleSelect().clear();
//        	form.setUnansweredEnquiries(new UnansweredEnquiries());
//        });
//
//        HorizontalLayout toolbar = new HorizontalLayout(filtering, addTourBtn);
//
//        HorizontalLayout main = new HorizontalLayout(grid, form);
//        main.setSizeFull();
//        grid.setSizeFull();
//        main.setExpandRatio(grid, 1);
//        
//        verticalLayout.addComponents(toolbar, main);
//        
//        updateList();
//        
//        form.setVisible(false);
//        
//        grid.asSingleSelect().addValueChangeListener(e -> {
//        	if (e.getValue() == null) {
//        		form.setVisible(false);
//        	}
//        	else {
//        		form.setUnansweredEnquiries(e.getValue());
//        	}
//        });
//
//
//	}
//	
//	@Override
//    public void enter(ViewChangeEvent event) {
//		updateList();
//		verticalLayout.addComponent(grid);
//		setContent(verticalLayout);
//    }
//	
//	public void updateList() {
//	    grid.setItems(unRepo.findAll());
//	    grid.setColumnOrder("id", "name", "description", "duration", "day", "weekday_price", "weekend_price", "hits");
//	}
//	
//	public void delete(UnansweredEnquiries un) {
//		unRepo.delete(un);
//		updateList();
//	}
//	
//	public void save(UnansweredEnquiries un) {
//		unRepo.saveAndFlush(un);
//		updateList();
//	}
//}
