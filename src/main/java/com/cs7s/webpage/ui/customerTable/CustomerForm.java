package com.cs7s.webpage.ui.customerTable;

import com.cs7s.webpage.database.Customer;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The form to edit a customer entity in Customer Table.
 * @author Wong Ngo Yin
 */
@SuppressWarnings("serial")
public class CustomerForm extends FormLayout {
	private TextField name = new TextField("Name");
	private TextField cust_id = new TextField("Customer ID");
	private TextField line_id = new TextField("LINE ID");
	private TextField phone = new TextField("Phone");
	private TextField age = new TextField("Age");
	private TextField tour_joined = new TextField("Tour Joined");
	private TextField adult_num = new TextField("Number of Adults");
	private TextField children_num = new TextField("Number of Children");
	private TextField toddler_num = new TextField("Number of Toddlers");
	private TextField fee = new TextField("Fee");
	private TextField amt_paid = new TextField("Amount Paid");
	private TextField special_req = new TextField("Special Requests");
	
	private Button save = new Button("Save");
	private Button close = new Button("Close Form");

	private CustomerView customerView;

	private Binder<Customer> binder = new Binder<>(Customer.class);
	private Customer customer;

	/**
	 * The constructor of CustomerForm.
	 * @param customerView the representation of Customer Table.
	 */
	public CustomerForm(CustomerView customerView) {
		this.customerView = customerView;

		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, close);
		HorizontalLayout row1 = new HorizontalLayout(name, cust_id, line_id, phone, age);
		HorizontalLayout row2 = new HorizontalLayout(tour_joined, adult_num, children_num, toddler_num);
		HorizontalLayout row3 = new HorizontalLayout(fee, amt_paid, special_req);
		addComponents(row1, row2, row3, buttons);

		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);

		binder.bindInstanceFields(this);

		save.addClickListener(e -> save());
		close.addClickListener(e -> setVisible(false));
	}

	/**
	 * Bind the form to the provided customer entity.
	 * @param customer the customer entity.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
		binder.setBean(customer);

		setVisible(true);
		name.selectAll();
	}

	private void save() {
		customerView.save(customer);
		setVisible(false);
	}
}