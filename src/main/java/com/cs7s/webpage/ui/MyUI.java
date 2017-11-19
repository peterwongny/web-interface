package com.cs7s.webpage.ui;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs7s.webpage.authentication.AccessControl;
import com.cs7s.webpage.authentication.BasicAccessControl;
import com.cs7s.webpage.authentication.LoginScreen;
import com.cs7s.webpage.database.BookingRepository;
import com.cs7s.webpage.database.CustomerRepository;
import com.cs7s.webpage.database.StaffLoginRepository;
import com.cs7s.webpage.database.TourRepository;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Main UI class of the application that shows either the login screen or the
 * main view of the application depending on whether a user is signed in.
 */
@Component
@SpringUI
@Theme("valo")
@SuppressWarnings("serial")
public class MyUI extends UI {    
    @Autowired
    public TourRepository tourRepo;
    
    @Autowired
    public BookingRepository bookingRepo;
    
    @Autowired
    public CustomerRepository customerRepo;   
    
    @Autowired
    public StaffLoginRepository staffLoginRepo;
    
//    @Autowired
//    public UnansweredEnquiriesRepository unRepo;
   
    private AccessControl accessControl = new BasicAccessControl();
    MainScreen mainscreen;

    /**
     * Initializes the UI.
     * @param vaadinRequest
     */
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("CS Sevens");
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
        setContent(new MainScreen(MyUI.this));
        getNavigator().navigateTo(getNavigator().getState());
    }
    
    /**
     * Gets the current UI.
     * @return MyUI the UI.
     */
    public static MyUI get() {
        return (MyUI) UI.getCurrent();
    }
    
    /**
     * Returns the accessControl that handles authentication.
     * @return accessControl
     */
    public AccessControl getAccessControl() {
        return accessControl;
    }

    @WebServlet(urlPatterns={"/*","/VAADIN/*"}, asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class myUIServlet extends VaadinServlet {
    }
}