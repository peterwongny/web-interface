package com.web.webpage.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;



import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;

import java.util.*;


@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace=Replace.NONE) //uses our psql db
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = { OrmTest.class, Tour.class })
	public class OrmTest {
        
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