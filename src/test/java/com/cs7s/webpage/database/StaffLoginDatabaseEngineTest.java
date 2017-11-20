package com.cs7s.webpage.database;

import com.cs7s.webpage.database.StaffLoginDatabaseEngine;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
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
