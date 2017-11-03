package com.web.webpage.UI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

public class MainScreen extends HorizontalLayout {
	
	
    private Menu menu;
    

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
        
//        menu.addView(new BookingView(), BookingView.VIEW_NAME,
//                BookingView.VIEW_NAME, FontAwesome.EDIT);
//        
//        menu.addView(new CustomerView(), CustomerView.VIEW_NAME,
//                CustomerView.VIEW_NAME, FontAwesome.EDIT);
        
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
