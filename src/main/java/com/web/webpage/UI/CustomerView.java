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
//import com.web.webpage.database.Customer;
//import com.web.webpage.database.CustomerRepository;
//
//
//public class CustomerView extends Panel implements View{
//	
//	public CustomerRepository customerRepo;
//	private TextField filterText = new TextField();
//	private CustomerForm form = new CustomerForm(this);
//	private VerticalLayout verticalLayout = new VerticalLayout();
//
//	public final static String VIEW_NAME = "Customer List";
//	
//	
//	final Grid<Customer> grid;
//	
//	public CustomerView(CustomerRepository customerRepo) {
//		this.customerRepo = customerRepo;
//		this.grid = new Grid<>(Customer.class);
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
//        Button addCustomerBtn = new Button("Add new customer");
//        addCustomerBtn.addClickListener(e -> {
//        	grid.asSingleSelect().clear();
//        	form.setCustomer(new Customer());
//        });
//
//        HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn);
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
//        		form.setCustomer(e.getValue());
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
//	    grid.setItems(customerRepo.findAll());
//	    grid.setColumnOrder("id", "name", "description", "duration", "day", "weekday_price", "weekend_price", "hits");
//	}
//	
//	public void delete(Customer customer) {
//		customerRepo.delete(customer);
//		updateList();
//	}
//	
//	public void save(Customer customer) {
//		customerRepo.saveAndFlush(customer);
//		updateList();
//	}
//}
