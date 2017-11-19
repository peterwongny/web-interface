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
@SpringBootTest(classes = { StaffLoginDatabaseEngineTest.class, StaffLoginDatabaseEngine.class })
public class StaffLoginDatabaseEngineTest {

	@Autowired
	StaffLoginDatabaseEngine staffLoginDBE;

	@Test
	public void successfulLoginExactUsername() throws Exception {
		boolean thrown = false;
		boolean successful = false;
		try {
			String username = "test";
			String password = "test";
			successful = this.staffLoginDBE.authenticate(username, password);
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isFalse();
		assertThat(successful).isTrue();
	}

	@Test
	public void successfulLoginDifferentCaseUsername() throws Exception {
		boolean thrown = false;
		boolean successful = false;
		try {
			String username = "Test";
			String password = "test";
			successful = this.staffLoginDBE.authenticate(username, password);
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isFalse();
		assertThat(successful).isTrue();
	}

	@Test
	public void wrongUsername() throws Exception {
		boolean thrown = false;
		boolean successful = false;
		try {
			String username = "t3st";
			String password = "test";
			successful = this.staffLoginDBE.authenticate(username, password);
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isFalse();
		assertThat(successful).isFalse();
	}

	@Test
	public void wrongPasswordCase() throws Exception {
		boolean thrown = false;
		boolean successful = false;
		try {
			String username = "test";
			String password = "TesT";
			successful = this.staffLoginDBE.authenticate(username, password);
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isFalse();
		assertThat(successful).isFalse();
	}

	@Test
	public void wrongPasswordArrangement() throws Exception {
		boolean thrown = false;
		boolean successful = false;
		try {
			String username = "test";
			String password = "tset";
			successful = this.staffLoginDBE.authenticate(username, password);
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isFalse();
		assertThat(successful).isFalse();
	}

	@Test
	public void wrongUsernameAndPassword() throws Exception {
		boolean thrown = false;
		boolean successful = false;
		try {
			String username = "t3st";
			String password = "TesT";
			successful = this.staffLoginDBE.authenticate(username, password);
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isFalse();
		assertThat(successful).isFalse();
	}
}
