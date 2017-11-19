package com.cs7s.webpage.database;

import com.cs7s.webpage.database.Tour;
import com.cs7s.webpage.database.TourRepository;

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

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE) //uses our psql db
@SpringBootTest(classes = { TourRepositoryTest.class, Tour.class })
public class TourRepositoryTest {
	@Autowired
	private TourRepository tourRepo;

	@Test
	public void findAll() throws Exception {
		boolean thrown = false;
		String name = "";
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
				name=tour.getName();
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
				name=tour.getName();
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
}