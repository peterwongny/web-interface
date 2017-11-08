package com.web.webpage.UI;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import com.web.webpage.authentication.AccessControl;
import com.web.webpage.authentication.BasicAccessControl;
import com.web.webpage.authentication.LoginScreen;
import com.web.webpage.database.Tour;
import com.web.webpage.database.TourRepository;
//import com.web.webpage.database.BookingRepository;
//import com.web.webpage.database.CustomerRepository;
//import com.web.webpage.database.UnansweredEnquiriesRepository;


@Component
@SpringUI
@Theme("valo")
/**
 * Main UI class of the application that shows either the login screen or the
 * main view of the application depending on whether a user is signed in.
 *
 * The @Viewport annotation configures the viewport meta tags appropriately on
 * mobile devices. Instead of device based scaling (default), using responsive
 * layouts.
 */
public class myUI extends UI {

    private AccessControl accessControl = new BasicAccessControl();
    
    @Autowired
    public TourRepository tourRepo;
    
    
    MainScreen mainscreen;
    
//    @Autowired
//    public BookingRepository bookingRepo;
//    
//    @Autowired
//    public CustomerRepository customerRepo;    
//    
//    @Autowired
//    public UnansweredEnquiriesRepository unRepo; 
   
    

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("TourCompany");
        if (!accessControl.isUserSignedIn()) {
        	
        	LoginScreen loginScreen = new LoginScreen(accessControl);
        	loginScreen.addEventListener(e -> showMainView());
        	
        	setContent(loginScreen);
        	
        } else {
            showMainView();
        }
    }

    protected void showMainView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreen(myUI.this));
        getNavigator().navigateTo(getNavigator().getState());
    }

    public static myUI get() {
        return (myUI) UI.getCurrent();
    }

    public AccessControl getAccessControl() {
        return accessControl;
    }

    @WebServlet(urlPatterns = "/*", name = "myUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = myUI.class, productionMode = true)
    public static class myUIServlet extends VaadinServlet {
    }

}