package com.cs7s.webpage.ui.promotion;

import com.cs7s.webpage.database.Promotion;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateTimeField;
import com.vaadin.data.converter.LocalDateTimeToDateConverter;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import com.vaadin.data.ValueContext;
import java.util.ArrayList;
import com.vaadin.ui.TwinColSelect;
import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;

import java.util.Set;
import java.util.Date;
import com.vaadin.data.Result;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.time.OffsetDateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A form that shows the information of a promotion entity so that users can edit the promotions.
 * @author Wong Ngo Yin
 */
@SuppressWarnings("serial")
@ViewScope
public class PromotionForm extends FormLayout {
	private TextField description = new TextField("Description");
	private TextField type = new TextField("Type");
	private DateTimeField deadline = new DateTimeField("Deadline");
	private TextField discount = new TextField("Discount");
	private TextField customer_quota = new TextField("Customer Quota");
	private TextField overall_quota = new TextField("Overall Quota");


	private Button save = new Button("Save");
	private Button close = new Button("Close Form");

	private PromotionView promotionView;	
	private Binder<Promotion> binder = new Binder<>(Promotion.class);

	TwinColSelect<String> select = new TwinColSelect<>();

	private Promotion promo;

	/**
	 * The constructor of PromotionForm.
	 * @param promotionView the representation of Promotion Table.
	 * @param bookingRepo the booking repository.
	 */
	public PromotionForm(PromotionView promotionView, BookingRepository bookingRepo) {
		this.promotionView = promotionView;

		select.setLeftColumnCaption("Available options");
		select.setRightColumnCaption("Selected options");

		binder.bind(description, Promotion::getDescription, Promotion::setDescription);
		binder.bind(type, Promotion::getType, Promotion::setType);
		binder.bind(discount, Promotion::getDiscount, Promotion::setDiscount);
		binder.bind(customer_quota, Promotion::getCustomer_quota, Promotion::setCustomer_quota);
		binder.bind(overall_quota, Promotion::getOverall_quota, Promotion::setOverall_quota);

		//Get relevant tour offering ids
		List<Booking> bookings = bookingRepo.findAll();
		List<String> bookingIds = new ArrayList<>();
		for(Booking booking: bookings) {
			bookingIds.add(booking.getBooking_id());
		}
		select.setItems(bookingIds);

		// Few items, so we can set rows to match item count
		select.setRows(5);

		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, close);
		HorizontalLayout row1 = new HorizontalLayout(description, type, discount);
		description.setSizeFull();
		HorizontalLayout row2 = new HorizontalLayout(deadline, customer_quota,
				overall_quota);

		addComponents(row1, row2, select,buttons);

		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);

		save.addClickListener(e -> save());
		close.addClickListener(e -> setVisible(false));

	}

	/**
	 * Binds the form to the provided promotion entity.
	 * @param promo the promotion entity.
	 */
	public void setPromotion(Promotion promo) {

		this.promo = promo;
		binder.setBean(promo);

		setVisible(true);
		description.selectAll();
	}

	private void save() {
		OffsetDateTime odt = OffsetDateTime.now ( ZoneId.systemDefault () );
		ZoneOffset zoneOffset = odt.getOffset();
		LocalDateTimeToDateConverter conv = new LocalDateTimeToDateConverter(zoneOffset);
		Result<Date> res = conv.convertToModel(deadline.getValue(),new ValueContext());
		Date thedate;
		Timestamp timestamp;
		String dateStr =  res.toString();
		if (dateStr.length() > 4) {
			dateStr = dateStr.substring(3, dateStr.length() - 1);
			System.out.println("RESULT " + dateStr);
			try {
				thedate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(dateStr);
				System.out.println("DATE "+thedate.toString());
				timestamp = new Timestamp(thedate.getTime());
				System.out.println("TIMESTAMP "+timestamp);
				Set<String> tourOfferings  = select.getValue();
				for(String s: tourOfferings) {
					promo = new Promotion();
					promo.setType(type.getValue());
					promo.setCustomer_quota(customer_quota.getValue());
					promo.setDeadline(timestamp);
					promo.setDescription(description.getValue());
					promo.setDiscount(discount.getValue());
					promo.setOverall_quota(overall_quota.getValue());
					promo.setTourOffering(s);
					promotionView.save(promo);
				}
				setVisible(false);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
	}

}