package com.cs7s.webpage.ui.enquiriesTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gridutil.cell.CellFilterComponent;
import org.vaadin.gridutil.cell.GridCellFilter;

import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.cs7s.webpage.database.BookingStatus;
import com.cs7s.webpage.database.EnquiriesStatus;
import com.cs7s.webpage.database.UnansweredEnquiries;
import com.cs7s.webpage.database.UnansweredEnquiriesRepository;
import com.cs7s.webpage.database.UnansweredEnquiries;

/**
* the page that show unansweredEnquiries table, you can edit and search the table in this page
* @author Wong Ngo Yin
*
*/
public class UnansweredEnquiriesView extends Panel implements View{
	
	public UnansweredEnquiriesRepository unansweredEnquiriesRepo;
	private UnansweredEnquiriesForm form = new UnansweredEnquiriesForm(this);
	private VerticalLayout verticalLayout = new VerticalLayout();

	public final static String VIEW_NAME = "UnansweredEnquiries";
	
	
	final Grid<UnansweredEnquiries> grid;
	private ListDataProvider<UnansweredEnquiries> provider;
	private List<UnansweredEnquiries> unansweredEnquiriesTable;
	
	/**
	 * constructor of UnansweredEnquiriesView
	 * @param unansweredEnquiriesRepo
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
		
		
		//filter
		final GridCellFilter<UnansweredEnquiries> filter = new GridCellFilter<>(this.grid, UnansweredEnquiries.class);
		filter.setTextFilter("line_id", true, false);
		filter.setTextFilter("enquiry", true, false);

		CellFilterComponent<ComboBox<EnquiriesStatus>> statusFilter = filter.setComboBoxFilter("answered", EnquiriesStatus.class, Arrays.asList(EnquiriesStatus.True, EnquiriesStatus.False));

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
	 * indicate and update the view when unansweredEnquiries enter this page
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
		unansweredEnquiriesTable.clear();
		unansweredEnquiriesTable.addAll(unansweredEnquiriesRepo.findAll());
		provider.refreshAll();
	}
	
	/**
	 * delete a unansweredEnquiries entity from the unansweredEnquiries table in the database
	 * @param unansweredEnquiries
	 */
	public void delete(UnansweredEnquiries unansweredEnquiries) {
		unansweredEnquiriesRepo.delete(unansweredEnquiries);
		updateList();
	}
	
	/**
	 * update or insert and unansweredEnquiries entity to the unansweredEnquiries table in database
	 * @param unansweredEnquiries
	 */
	public void save(UnansweredEnquiries unansweredEnquiries) {
		unansweredEnquiriesRepo.saveAndFlush(unansweredEnquiries);
		updateList();
	}
}