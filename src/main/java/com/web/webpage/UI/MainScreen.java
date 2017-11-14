package com.web.webpage.UI;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.web.webpage.UI.bookingTable.BookingView;
import com.web.webpage.UI.customerTable.CustomerView;
import com.web.webpage.UI.promotion.PromotionView;
import com.web.webpage.UI.report.ReportView;
import com.web.webpage.UI.tourList.TourView;


@SpringComponent
@UIScope
public class MainScreen extends HorizontalLayout {
	
	
    private Menu menu;

    public myUI ui;

    public MainScreen(myUI ui) {
    	

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
        
//        menu.addView(new UnansweredEnquiriesView(ui.unRepo), UnansweredEnquiriesView.VIEW_NAME,
//        		UnansweredEnquiriesView.VIEW_NAME, FontAwesome.EDIT);
        
        menu.addView(new PromotionView(), PromotionView.VIEW_NAME,
                PromotionView.VIEW_NAME, FontAwesome.EDIT);
        
        
        menu.addView(new ReportView(), ReportView.VIEW_NAME,
        ReportView.VIEW_NAME, FontAwesome.EDIT);

        navigator.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

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
