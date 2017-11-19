package com.cs7s.webpage.ui;

import com.cs7s.webpage.ui.bookingTable.BookingView;
import com.cs7s.webpage.ui.customerTable.CustomerView;
import com.cs7s.webpage.ui.promotion.PromotionView;
import com.cs7s.webpage.ui.report.ReportView;
import com.cs7s.webpage.ui.tourList.TourView;
import com.cs7s.webpage.ui.unansweredEnquiriesTable.UnansweredEnquiriesView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * The screen that is shown after login. It consists of a menu and the views.
 * @author Wong Ngo Yin
 */
@SpringComponent
@SuppressWarnings({"deprecation", "serial"})
@UIScope
public class MainScreen extends HorizontalLayout {
	private Menu menu;

	/**
	 * The main UI class.
	 */
    public MyUI ui;

    /**
     * The constructor of MainScreen.
     * @param ui the main UI class.
     */
    public MainScreen(MyUI ui) {
        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new Menu(navigator);
        
        menu.addView(new TourView(ui.tourRepo), TourView.VIEW_NAME,
                TourView.VIEW_NAME, FontAwesome.EDIT);
        

        menu.addView(new BookingView(ui.bookingRepo), BookingView.VIEW_NAME,
                BookingView.VIEW_NAME, FontAwesome.EDIT);
        
        menu.addView(new CustomerView(ui.customerRepo), CustomerView.VIEW_NAME,
                CustomerView.VIEW_NAME, FontAwesome.EDIT);
        
        menu.addView(new UnansweredEnquiriesView(ui.unRepo), UnansweredEnquiriesView.VIEW_NAME,
        		UnansweredEnquiriesView.VIEW_NAME, FontAwesome.EDIT);
        
        menu.addView(new PromotionView(), PromotionView.VIEW_NAME,
                PromotionView.VIEW_NAME, FontAwesome.EDIT);
        
        menu.addView(new ReportView(ui.bookingRepo, ui.tourRepo), ReportView.VIEW_NAME,
        ReportView.VIEW_NAME, FontAwesome.EDIT);

        navigator.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // Notify the view menu about view changes so that it can display the currently active view.
    private ViewChangeListener viewChangeListener = new ViewChangeListener() {
        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }
        
        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }
    };
}
