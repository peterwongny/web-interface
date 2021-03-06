package com.cs7s.webpage.ui;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The website menu that provides the ability to change to different views.
 * @author Wong Ngo Yin
 */
@SuppressWarnings({"deprecation", "serial"})
public class Menu extends CssLayout {
	private static final String VALO_MENUITEMS = "valo-menuitems";
	private static final String VALO_MENU_TOGGLE = "valo-menu-toggle";
	private static final String VALO_MENU_VISIBLE = "valo-menu-visible";
	private Navigator navigator;
	private Map<String, Button> viewButtons = new HashMap<String, Button>();

	private CssLayout menuItemsLayout;
	private CssLayout menuPart;

	/**
	 * The constructor of Menu.
	 * @param navigator the view navigation controller.
	 */
	public Menu(Navigator navigator) {
		this.navigator = navigator;
		setPrimaryStyleName(ValoTheme.MENU_ROOT);
		menuPart = new CssLayout();
		menuPart.addStyleName(ValoTheme.MENU_PART);

		// Header of the menu
		final HorizontalLayout top = new HorizontalLayout();
		top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		top.addStyleName(ValoTheme.MENU_TITLE);
		top.setSpacing(true);
		Label title = new Label("CS Sevens");
		title.addStyleName(ValoTheme.LABEL_H3);
		title.setSizeUndefined();
		top.addComponent(title);
		menuPart.addComponent(top);

		// Logout menu item
		MenuBar logoutMenu = new MenuBar();
		logoutMenu.addItem("Logout", FontAwesome.SIGN_OUT, new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				VaadinSession.getCurrent().getSession().invalidate();
				Page.getCurrent().reload();
			}
		});

		logoutMenu.addStyleName("user-menu");
		menuPart.addComponent(logoutMenu);

		// Button for toggling the visibility of the menu when on a small screen
		Button showMenu = new Button("Menu");
		showMenu.addClickListener(e -> {
			if (menuPart.getStyleName().contains(VALO_MENU_VISIBLE)) {
				menuPart.removeStyleName(VALO_MENU_VISIBLE);
			} else {
				menuPart.addStyleName(VALO_MENU_VISIBLE);
			}
		});

		showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
		showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
		showMenu.addStyleName(VALO_MENU_TOGGLE);
		showMenu.setIcon(FontAwesome.NAVICON);
		menuPart.addComponent(showMenu);

		// Container for the navigation buttons, which are added by addView()
		menuItemsLayout = new CssLayout();
		menuItemsLayout.setPrimaryStyleName(VALO_MENUITEMS);
		menuPart.addComponent(menuItemsLayout);

		addComponent(menuPart);
	}

	/**
	 * Register a pre-created view instance in the navigation menu and in the
	 * {@link Navigator}.
	 * @see Navigator#addView(String, View)
	 * @param view the view instance to register.
	 * @param name the view name.
	 * @param caption the view caption in the menu.
	 * @param icon the view icon in the menu.
	 */
	public void addView(View view, final String name, String caption,
			Resource icon) {
		navigator.addView(name, view);
		createViewButton(name, caption, icon);
	}

	/**
	 * Register a view in the navigation menu and in the {@link Navigator} based
	 * on a view class.
	 * @see Navigator#addView(String, Class)
	 * @param viewClass class of the views to create.
	 * @param name view name.
	 * @param caption view caption in the menu.
	 * @param icon view icon in the menu.
	 */
	public void addView(Class<? extends View> viewClass, final String name,
			String caption, Resource icon) {
		navigator.addView(name, viewClass);
		createViewButton(name, caption, icon);
	}

	private void createViewButton(final String name, String caption,
			Resource icon) {

		Button button = new Button(caption);
		button.addClickListener(e -> navigator.navigateTo(name));

		button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		button.setIcon(icon);
		menuItemsLayout.addComponent(button);
		viewButtons.put(name, button);
	}

	/**
	 * Highlights a view navigation button as the currently active view in the
	 * menu. This method does not perform the actual navigation.
	 * @param viewName the name of the view to show as active
	 */
	public void setActiveView(String viewName) {
		for (Button button : viewButtons.values()) {
			button.removeStyleName("selected");
		}
		Button selected = viewButtons.get(viewName);
		if (selected != null) {
			selected.addStyleName("selected");
		}
		menuPart.removeStyleName(VALO_MENU_VISIBLE);
	}
}