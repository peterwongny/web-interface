package com.cs7s.webpage.ui.tourList;

import com.cs7s.webpage.database.Tour;
import com.cs7s.webpage.database.TourRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = { TourViewTest.class, TourView.class })
public class TourViewTest {
	@Mock
	private TourRepository tourRepo;
	
	
	private List<Tour> tourList;
	
	@Mock
	private Tour tour;
	

	@Before
	public void setup() {
		tourList = new ArrayList<Tour>();
		tourList.add(tour);
		tourList.add(tour);
		tourList.add(tour);
		tourList.add(tour);
	}
	
	@InjectMocks 
	private TourView tourView;

	
	@Test
	public void saveTest() throws Exception {

		when(tourRepo.findAll()).thenReturn(tourList);
		
		tourView = new TourView(tourRepo);
		
		tourView.save(tour);
	}
	

}