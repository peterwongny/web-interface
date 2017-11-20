package com.cs7s.webpage.ui.customerTable;

import com.cs7s.webpage.database.Customer;
import com.cs7s.webpage.database.CustomerRepository;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import org.vaadin.gridutil.cell.GridCellFilter;

/**
 * The page that shows the Customer Table. The records of the table can be edited and searched here.
 * @author Wong Ngo Yin
 *
 */
@SuppressWarnings("serial")
public class CustomerView extends Panel implements View {
	/**
	 * The customer repository.
	 */
	public CustomerRepository customerRepo;
	private CustomerForm form = new CustomerForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();

	/**
	 * The name of this view.
	 */
	public final static String VIEW_NAME = "Customer Table";

	final Grid<Customer> grid;
	private ListDataProvider<Customer> provider;
	private List<Customer> customerTable;

	/**
	 * The constructor of CustomerView.
	 * @param customerRepo the customer repository to be displayed.
	 */
	public CustomerView(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
		this.grid = new Grid<>(Customer.class);
		grid.setSizeUndefined();
		customerTable = customerRepo.findAll();
		provider = new ListDataProvider<>(customerRepo.findAll());
		grid.setDataProvider(provider);

		grid.setColumnOrder("name", "cust_id", "line_id", "phone",
				"age", "tour_joined", "adult_num", "children_num",
				"toddler_num", "fee", "amt_paid", "special_req");
		updateList();

		// Filter
		final GridCellFilter<Customer> filter = new GridCellFilter<>(this.grid, Customer.class);
		filter.setTextFilter("name", true, false);

		filter.setTextFilter("cust_id",true,false);
		filter.setTextFilter("line_id", true, false);
		filter.setTextFilter("phone", true, false);
		filter.setTextFilter("age", true, true);
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
	 * Indicates that the user has entered this page.
	 * @param event the event that fires when the user has entered this page.
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		updateList();
		verticalLayout.addComponent(grid);
		setContent(verticalLayout);
	}

	/**
	 * Update Customer Table based on the data in the database.
	 */
	public void updateList() {
		customerTable.clear();
		customerTable.addAll(customerRepo.findAll());
		provider.refreshAll();
	}

	/**
	 * Save (update or insert) a customer entity in Customer Table.
	 * @param customer the customer entity to be saved.
	 */
	public void save(Customer customer) {
		customerRepo.saveAndFlush(customer);
		updateList();
	}
}
