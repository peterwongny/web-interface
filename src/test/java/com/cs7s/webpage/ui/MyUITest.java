//package com.cs7s.webpage.ui;
//
//import com.cs7s.webpage.authentication.AccessControl;
//import com.cs7s.webpage.database.Booking;
//import com.cs7s.webpage.database.BookingRepository;
//import com.vaadin.navigator.Navigator;
//import com.vaadin.server.VaadinRequest;
//import com.vaadin.ui.Button;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = { MyUITest.class, MyUI.class })
//public class MyUITest {
//	@Mock
//    private VaadinRequest vaadinRequest;
//	
//	@Mock
//	private AccessControl accessControl;
//
//	@InjectMocks 
//	@Spy
//	private MyUI myUI = new MyUI();
//
//	@Mock
//    private Navigator navigator;
//
//	
//	@Test
//	public void initMyUITest() throws Exception {
//		when(vaadinRequest.getLocale()).thenReturn(new Locale(""));
////		when(myUI.getNavigator()).thenReturn(navigator);
//		doReturn(navigator).when(myUI).getNavigator();
//		when(navigator.getState()).thenReturn("");
//		when(accessControl.isUserSignedIn()).thenReturn(true);
//		
//		myUI.init(vaadinRequest);
//	}
//	
//	
//
//}