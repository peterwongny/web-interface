package com.web.webpage.UI.enquiriesTable;
//package com.web.webpage.UI;
//
//
//import com.vaadin.data.Binder;
//import com.vaadin.event.ShortcutAction.KeyCode;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.FormLayout;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.NativeSelect;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.themes.ValoTheme;
//import com.web.webpage.database.UnansweredEnquiries;
//
//import com.web.webpage.database.UnansweredEnquiries;
//import com.web.webpage.database.EnquiriesStatus;
//
//public class UnansweredEnquiriesForm extends FormLayout {
//	
//	
//	private TextField line_id = new TextField("line_id");
//	private TextField enquiry = new TextField("enquiry");
//	private NativeSelect<EnquiriesStatus> status = new NativeSelect<>("Status");
//
//	
//	private Button save = new Button("Save");
//	private Button delete = new Button("Delete");
//	private Button close = new Button("Close Form");
//	
//	private UnansweredEnquiriesView unView;
//	
//	private Binder<UnansweredEnquiries> binder = new Binder<>(UnansweredEnquiries.class);
////	binder.forField(duration).withConverter(new StringToIntegerConverter("Must enter a number")).bind(UnansweredEnquiries::getDuration, UnansweredEnquiries::setDuration);
//	private UnansweredEnquiries un;
//	
//	public UnansweredEnquiriesForm(UnansweredEnquiriesView unView) {
//		this.unView = unView;
//		
//		setSizeUndefined();
//		HorizontalLayout buttons = new HorizontalLayout(save, delete, close);
//		HorizontalLayout row1 = new HorizontalLayout(line_id, enquiry, status);
//		addComponents(row1, buttons);
//		
//		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
//		save.setClickShortcut(KeyCode.ENTER);
//		
//		binder.bindInstanceFields(this);
//		
//		save.addClickListener(e -> save());
//		delete.addClickListener(e -> delete());
//		close.addClickListener(e -> setVisible(false));
//	
//	}
//	
//	public void setUnansweredEnquiries(UnansweredEnquiries un) {
//		this.un = un;
//		binder.setBean(un);
//		
//		setVisible(true);
//		line_id.selectAll();
//	}
//	
//	public void delete() {
//		unView.delete(un);
//		setVisible(false);
//	}
//	
//	public void save() {
//		unView.save(un);
//		setVisible(false);
//	}
//}
