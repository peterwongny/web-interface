//package com.web.webpage;
//
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.vaadin.server.DefaultUIProvider;
//import com.vaadin.server.UICreateEvent;
//import com.vaadin.server.VaadinServlet;
//import com.vaadin.ui.UI;
//import com.web.webpage.UI.myUI.myUIServlet;
//
//public class SpringUIProvider extends DefaultUIProvider {
//
//     @Override
//     public UI createInstance(UICreateEvent event) {
//         ApplicationContext ctx =  WebApplicationContextUtils
//              .getWebApplicationContext(myUIServlet.getCurrent().getServletContext());
//
//         return ctx.getBean(event.getUIClass());
//     }
//}
