package com.cs7s.webpage.ui.unansweredEnquiriesTable;


import com.cs7s.webpage.database.EnquiriesStatus;
import com.cs7s.webpage.database.UnansweredEnquiries;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The form to edit the information of an unanswered enquiries entity in Unanswered Enquiries Table.
 */
@SuppressWarnings("serial")
public class UnansweredEnquiriesForm extends FormLayout {
	private TextField line_id = new TextField("line_id");
	private TextField enquiry = new TextField("enquiry");
	private NativeSelect<EnquiriesStatus> answered = new NativeSelect<>("Answered");

	private Button save = new Button("Save");
	private Button close = new Button("Close Form");

	private UnansweredEnquiriesView unView;

	private Binder<UnansweredEnquiries> binder = new Binder<>(UnansweredEnquiries.class);
	private UnansweredEnquiries un;

	/**
	 * The constructor of UnansweredEnquiriesForm.
	 * @param unView the representation of Unanswered Enquiries Table.
	 */
	public UnansweredEnquiriesForm(UnansweredEnquiriesView unView) {
		this.unView = unView;

		answered.setItems(EnquiriesStatus.values());
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, close);
		HorizontalLayout row1 = new HorizontalLayout(line_id, enquiry, answered);
		addComponents(row1, buttons);

		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);

		binder.bindInstanceFields(this);

		save.addClickListener(e -> save());
		close.addClickListener(e -> setVisible(false));

	}

	/**
	 * Bind the form to the provided unanswered enquiries entity.
	 * @param un the unanswered enquiries entity.
	 */
	public void setUnansweredEnquiries(UnansweredEnquiries un) {
		this.un = un;
		binder.setBean(un);

		setVisible(true);
		line_id.selectAll();
	}

	private void save() {
		unView.save(un);
		setVisible(false);
	}
}