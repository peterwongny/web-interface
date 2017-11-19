package com.cs7s.webpage.ui.unansweredEnquiriesTable;

import com.cs7s.webpage.database.UnansweredEnquiries;
import com.cs7s.webpage.database.UnansweredEnquiriesRepository;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import org.vaadin.gridutil.cell.GridCellFilter;

/**
 * The page that shows the Unanswered Enquiries Table. The records of the table can be edited and searched here.
 * @author Wong Ngo Yin
 */
@SuppressWarnings("serial")
public class UnansweredEnquiriesView extends Panel implements View {
	/**
	 * The unanswered enquiries repository.
	 */
	public UnansweredEnquiriesRepository unansweredEnquiriesRepo;
	private UnansweredEnquiriesForm form = new UnansweredEnquiriesForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();

	/**
	 * The name of this view.
	 */
	public final static String VIEW_NAME = "UnansweredEnquiries";


	final Grid<UnansweredEnquiries> grid;
	private ListDataProvider<UnansweredEnquiries> provider;
	private List<UnansweredEnquiries> unansweredEnquiriesTable;

	/**
	 * The constructor of UnansweredEnquiriesView.
	 * @param unansweredEnquiriesRepo the unanswered enquiries repository.
	 */
	public UnansweredEnquiriesView(UnansweredEnquiriesRepository unansweredEnquiriesRepo) {
		this.unansweredEnquiriesRepo = unansweredEnquiriesRepo;
		this.grid = new Grid<>(UnansweredEnquiries.class);
		grid.setSizeUndefined();
		unansweredEnquiriesTable = unansweredEnquiriesRepo.findAll();
		provider = new ListDataProvider<>(unansweredEnquiriesRepo.findAll());
		grid.setDataProvider(provider);
		grid.setColumnOrder("line_id", "enquiry", "answered");
		grid.removeColumn("id");
		updateList();

		// Filter
		final GridCellFilter<UnansweredEnquiries> filter = new GridCellFilter<>(this.grid, UnansweredEnquiries.class);
		filter.setTextFilter("line_id", true, false);
		filter.setTextFilter("enquiry", true, false);

		HorizontalLayout main = new HorizontalLayout(grid, form);
		main.setSizeFull();
		grid.setSizeFull();
		grid.setHeightByRows(10);

		verticalLayout.addComponents(main);

		form.setVisible(false);

		grid.asSingleSelect().addValueChangeListener(e -> {
			if (e.getValue() == null) {
				form.setVisible(false);
			}
			else {
				form.setUnansweredEnquiries(e.getValue());
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
	 * Updates Unanswered Enquiries Table using the data from the database.
	 */
	public void updateList() {
		unansweredEnquiriesTable.clear();
		unansweredEnquiriesTable.addAll(unansweredEnquiriesRepo.findAll());
		provider.refreshAll();
	}

	/**
	 * Save (insert or update) an entity in Unanswered Enquiries Table.
	 * @param unansweredEnquiries the unanswered enquiries entity.
	 */
	public void save(UnansweredEnquiries unansweredEnquiries) {
		unansweredEnquiriesRepo.saveAndFlush(unansweredEnquiries);
		updateList();
	}
}