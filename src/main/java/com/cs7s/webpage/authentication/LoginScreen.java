package com.cs7s.webpage.authentication;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * UI content when the user is not logged in yet.
 */
@SuppressWarnings("serial")
public class LoginScreen extends VerticalLayout {
    private TextField username;
    private PasswordField password;
    private Button login;
    private AccessControl accessControl;
    private Button vbutton = new Button();

    /**
     * Constructor to build the login screen.
     * @param accessControl the access controller.
     */
    public LoginScreen(AccessControl accessControl) {
        this.accessControl = accessControl;
        buildUI();
        username.focus();
    }

    private void buildUI() {
        addStyleName("login-screen");

//        addComponent(new Image(null, new ClassResource("images/logo.png")));
        
        // login form, centered in the available part of the screen
        final Panel loginPanel = new Panel("Login");
        addComponent(loginPanel);
        setSizeFull();
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        loginPanel.setWidth(null);
        
        Component loginForm = buildLoginForm();
        loginPanel.setContent(loginForm);
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.addStyleName("login-form");
        loginForm.setSizeUndefined();
        loginForm.setMargin(true);

        loginForm.addComponent(username = new TextField("Username"));
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(password = new PasswordField("Password"));
        password.setWidth(15, Unit.EM);
        CssLayout buttons = new CssLayout();
        buttons.setStyleName("buttons");
        loginForm.addComponent(buttons);

        buttons.addComponent(login = new Button("Login"));
        login.setDisableOnClick(true);
        login.addClickListener(e-> {
                try {
                    login();
                } finally {
                    login.setEnabled(true);
                }
            }
        );
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        return loginForm;
    }

    private void login() {
        if (accessControl.signIn(username.getValue(), password.getValue())) {
        	loginSuccessful();
        } else {
            showNotification(new Notification("Login failed",
                    "Please check your username and password, and try again.",
                    Notification.Type.ERROR_MESSAGE));
            username.focus();
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDelayMsec(2000);
        notification.setPosition(Position.TOP_CENTER);
        notification.show(Page.getCurrent());
    }
    
    //use its event to implement addEventListener
    
    /**
     * Adds a ClickListener to the button that handles transfer from the login page to the main page.
     * @param c the ClickListener to be added.
     */
    public void addEventListener(ClickListener c) {
    	vbutton.addClickListener(c);
    }
    
    /**
     * Activates the button that handles the transfer from the login page to the main page.
     */
    protected void loginSuccessful() {
    	vbutton.click();
    }
}