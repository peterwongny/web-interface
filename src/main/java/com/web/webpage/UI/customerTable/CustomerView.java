package com.web.webpage.UI.customerTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.database.Customer;
import com.web.webpage.database.CustomerRepository;
import com.web.webpage.database.Customer;


public class CustomerView extends Panel implements View{
	
	public CustomerRepository customerRepo;
	private CustomerForm form = new CustomerForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();

	public final static String VIEW_NAME = "Customer List";
	
	
	final Grid<Customer> grid;
	private ListDataProvider<Customer> provider;
	
	public CustomerView(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
		this.grid = new Grid<>(Customer.class);
		grid.setSizeUndefined();
		updateList();
		grid.setColumnOrder("cust_id", "name", "age", "phone", "line_id", "tour_joined", "adult_num", "children_num", "toddler_num", "fee", "amt_paid", "special_req");
		
		//filter
		final GridCellFilter<Customer> filter = new GridCellFilter<>(this.grid, Customer.class);
		filter.setTextFilter("cust_id",true,false);
		filter.setTextFilter("name", true, false);
		filter.setTextFilter("age", true, true);
		filter.setTextFilter("phone", true, false);
		filter.setTextFilter("line_id", true, false);
		filter.setTextFilter("tour_joined", true, false);
		filter.setTextFilter("adult_num", true, false);
		filter.setTextFilter("children_num", true, false);
		filter.setTextFilter("toddler_num", true, false);
		filter.setTextFilter("fee", true, false);
		filter.setTextFilter("amt_paid", true, false);
		filter.setTextFilter("special_req", true, false);
		

        Button addCustomerBtn = new Button("Add new customer");
        addCustomerBtn.addClickListener(e -> {
        	grid.asSingleSelect().clear();
        	form.setCustomer(new Customer());
        	setSizeFull();
        });

        HorizontalLayout toolbar = new HorizontalLayout(addCustomerBtn);

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
        		form.setCustomer(e.getValue());
        		setSizeFull();
        	}
        });


	}
	
	@Override
    public void enter(ViewChangeEvent event) {
		updateList();
		verticalLayout.addComponent(grid);
		setContent(verticalLayout);
    }
	
	public void updateList() {
		provider = new ListDataProvider<>(customerRepo.findAll());
		grid.setDataProvider(provider);
	}
	
	public void delete(Customer customer) {
		customerRepo.delete(customer);
		updateList();
	}
	
	public void save(Customer customer) {
		customerRepo.saveAndFlush(customer);
		updateList();
	}
}
