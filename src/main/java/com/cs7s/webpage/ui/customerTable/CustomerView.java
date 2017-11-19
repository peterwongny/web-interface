package com.cs7s.webpage.ui.customerTable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.cs7s.webpage.database.Customer;
import com.cs7s.webpage.database.CustomerRepository;
import com.vaadin.data.provider.CallbackDataProvider;
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

/**
 * the page that show customer table, you can edit and search the table in this page
 * @author Wong Ngo Yin
 *
 */
public class CustomerView extends Panel implements View{
	
	public CustomerRepository customerRepo;
	private CustomerForm form = new CustomerForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();

	public final static String VIEW_NAME = "Customer List";
	
	
	final Grid<Customer> grid;
	private ListDataProvider<Customer> provider;
	private List<Customer> customerTable;
	
	/**
	 * constructor of CustomerView
	 * @param customerRepo
	 */
	public CustomerView(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
		this.grid = new Grid<>(Customer.class);
		grid.setSizeUndefined();
		customerTable = customerRepo.findAll();
		provider = new ListDataProvider<>(customerRepo.findAll());
		grid.setDataProvider(provider);
		grid.setColumnOrder("cust_id", "name", "age", "phone", "line_id", "tour_joined", "adult_num", "children_num", "toddler_num", "fee", "amt_paid", "special_req");
		updateList();
		
		
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
	
	/**
	 * indicate and update the view when customer enter this page
	 * entry point when users choose this page to be shown
	 * 
	 * @param ViewChangeEvent
	 */
	@Override
    public void enter(ViewChangeEvent event) {
		updateList();
		verticalLayout.addComponent(grid);
		setContent(verticalLayout);
    }
	
	/**
	 * update the table base on the data in the database
	 */
	public void updateList() {
		customerTable.clear();
		customerTable.addAll(customerRepo.findAll());
		provider.refreshAll();
	}
	
	/**
	 * delete a customer entity from the customer table in the database
	 * @param customer
	 */
	public void delete(Customer customer) {
		customerRepo.delete(customer);
		updateList();
	}
	
	/**
	 * update or insert and custoemr entity to the custoemr table in database
	 * @param customer
	 */
	public void save(Customer customer) {
		customerRepo.saveAndFlush(customer);
		updateList();
	}
}
