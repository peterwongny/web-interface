package com.cs7s.webpage.ui;

import com.cs7s.webpage.database.Booking;
import com.cs7s.webpage.database.BookingRepository;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = { MenuTest.class, Menu.class })
public class MenuTest {
	@Mock
    private Navigator navigator;
	
	
	private List<Button> buttonList;
	
	@Mock
    private Map<String, Button> viewButtons;
	
	@Mock
	private Button button;

	@InjectMocks 
	private Menu menu = new Menu(navigator);

	@Before
	public void setup() {
		buttonList = new ArrayList<Button>();
		buttonList.add(button);
		buttonList.add(button);
		buttonList.add(button);
		buttonList.add(button);
	}
	
	

	
	@Test
	public void NullButtonTest() throws Exception {
		
		menu.setActiveView("");
	}
	
	@Test
	public void selectViewTest() throws Exception {
		
		when(viewButtons.values()).thenReturn(buttonList);
		when(viewButtons.get("test")).thenReturn(button);
		menu.setActiveView("test");
	}
	

}