package com.web.webpage.UI.customerTable;


import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.database.Customer;

public class CustomerForm extends FormLayout {

	private TextField name = new TextField("name");
	private TextField cust_id = new TextField("customer id");
	private TextField line_id = new TextField("line id");
	private TextField phone = new TextField("phone");
	private TextField age = new TextField("age");
	private TextField tour_joined = new TextField("tour joined");
	private TextField adult_num = new TextField("adult num");
	private TextField children_num = new TextField("children num");
	private TextField toddler_num = new TextField("toddler num");
	private TextField fee = new TextField("fee");
	private TextField amt_paid = new TextField("amt paid");
	private TextField special_req = new TextField("special req");
	
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	private Button close = new Button("Close Form");
	
	private CustomerView customerView;
	
	private Binder<Customer> binder = new Binder<>(Customer.class);
//	binder.forField(duration).withConverter(new StringToIntegerConverter("Must enter a number")).bind(Customer::getDuration, Customer::setDuration);
	private Customer customer;
	
	public CustomerForm(CustomerView customerView) {
		this.customerView = customerView;
		
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete, close);
		HorizontalLayout row1 = new HorizontalLayout(name, cust_id, line_id, phone, age);
		HorizontalLayout row2 = new HorizontalLayout(tour_joined, adult_num, children_num, toddler_num);
		HorizontalLayout row3 = new HorizontalLayout(fee, amt_paid, special_req);
		addComponents(row1, row2, row3, buttons);
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
		
		binder.bindInstanceFields(this);
		
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		close.addClickListener(e -> setVisible(false));
	
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		binder.setBean(customer);
		
		setVisible(true);
		name.selectAll();
	}
	
	public void delete() {
		customerView.delete(customer);
		setVisible(false);
	}
	
	public void save() {
		customerView.save(customer);
		setVisible(false);
	}
}
