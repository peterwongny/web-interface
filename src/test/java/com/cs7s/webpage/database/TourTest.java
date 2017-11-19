package com.cs7s.webpage.database;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureTestDatabase(replace=Replace.NONE) //uses our psql db
@EnableAutoConfiguration
@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TourTest.class, Tour.class })
@SuppressWarnings("deprecation")
public class TourTest {
	@Autowired
	private TourRepository tourRepo;

	@SuppressWarnings("unused")
	@Test
	public void findAll() throws Exception {
		boolean thrown = false;
		int size = 0;
		Iterable<Tour> tours;

		try {
			tours = this.tourRepo.findAll();
			for(Tour tour: tours) {
				size++;
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(size).isGreaterThan(0);
	}

	@Test
	public void findByName() throws Exception {
		boolean thrown = false;
		String name = "";
		Iterable<Tour> tours;

		try {
			tours = this.tourRepo.findByName("shenzhen");
			for(Tour tour: tours) {
				name = tour.getName();
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(name).isEqualTo("Shenzhen city tour");
	}

	@Test
	public void findByDescription() throws Exception {
		Iterable<Tour> tours;
		boolean thrown = false;
		String name = "";

		try {
			tours = this.tourRepo.findByDescriptionContainingIgnoreCase("window of the");
			for(Tour tour: tours) {
				name = tour.getName();
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(name).isEqualTo("Shenzhen city tour");
	}

	@Test
	public void findByNameUnion() throws Exception {
		Iterable<Tour> tours;
		boolean thrown = false;
		String name1 = "";
		String name2 = "";

		try {
			tours = this.tourRepo.findByName("Shenzhen city tour omg!");
			for(Tour tour: tours) {
				name1=tour.getName();
			}
			tours = this.tourRepo.findByName("Shenzhen city");
			for(Tour tour: tours) {
				name2=tour.getName();
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(name1).isEqualTo("Shenzhen city tour");
		assertThat(name2).isEqualTo("Shenzhen city tour");
	}

	@Test
	public void findById() throws Exception {
		boolean thrown = false;
		String name = "";
		Iterable<Tour> tours;
		try {
			tours = this.tourRepo.findById("2D001");
			for(Tour tour: tours) {
				name=tour.getName();
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(name).isEqualTo("Shimen National Forest Tour");
	}

	@Test
	public void setAndGetTour() throws Exception {
		boolean thrown = false;
		Tour testTour = null;
		try {
			testTour = new Tour();
			testTour.setId("9D999");
			testTour.setName("Test Tour");
			testTour.setDescription("For testing");
			testTour.setDuration("999");
			testTour.setDay("Mon");
			testTour.setWeekday_price("999");
			testTour.setWeekend_price("999");
			testTour.setTag("Test");
			testTour.setHits(0);
			tourRepo.save(testTour);

			Iterable<Tour> tours = this.tourRepo.findByName("Test Tour");
			testTour = null;
			for (Tour tour : tours) {
				testTour = tour;
			}
		} catch (Exception e) {
			thrown = true;
		}

		assertThat(!thrown).isEqualTo(true);
		assertThat(testTour).isNotNull();
		assertThat(testTour.getId()).isEqualTo("9D999");
		assertThat(testTour.getName()).isEqualTo("Test Tour");
		assertThat(testTour.getDescription()).isEqualTo("For testing");
		assertThat(testTour.getDuration()).isEqualTo("999");
		assertThat(testTour.getDay()).isEqualTo("Mon");
		assertThat(testTour.getWeekday_price()).isEqualTo("999");
		assertThat(testTour.getWeekend_price()).isEqualTo("999");
		assertThat(testTour.getTag()).isEqualTo("Test");
		assertThat(testTour.getHits()).isGreaterThanOrEqualTo(0);
	}
}