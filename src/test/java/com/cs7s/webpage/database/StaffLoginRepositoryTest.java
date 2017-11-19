package com.cs7s.webpage.database;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureTestDatabase(replace=Replace.NONE) //uses our psql db
@DataJpaTest
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { StaffLoginRepositoryTest.class, StaffLogin.class })
@SuppressWarnings("deprecation")
public class StaffLoginRepositoryTest {
	@Autowired
	StaffLoginRepository staffLoginRepo;
	
	@Test
	public void successfulLoginExactUsername() throws Exception {
		String username = "gian";
		String password = "gian";
		String successful = this.staffLoginRepo.authenticate(username, password);
		assertThat(successful).isEqualTo("true");
	}
	
	@Test
	public void successfulLoginDifferentCaseUsername() throws Exception {
		String username = "Gian";
		String password = "gian";
		String successful = this.staffLoginRepo.authenticate(username, password);
		assertThat(successful).isEqualTo("true");
	}
	
	@Test
	public void wrongUsername() throws Exception {
		String username = "gina";
		String password = "gian";
		String successful = this.staffLoginRepo.authenticate(username, password);
		assertThat(successful).isEqualTo("false");
	}
	
	@Test
	public void wrongPasswordCase() throws Exception {
		String username = "gian";
		String password = "Gian";
		String successful = this.staffLoginRepo.authenticate(username, password);
		assertThat(successful).isEqualTo("false");
	}
	
	@Test
	public void wrongPasswordArrangement() throws Exception {
		String username = "gian";
		String password = "gina";
		String successful = this.staffLoginRepo.authenticate(username, password);
		assertThat(successful).isEqualTo("false");
	}
	
	@Test
	public void wrongUsernameAndPassword() throws Exception {
		String username = "gina";
		String password = "Gian";
		String successful = this.staffLoginRepo.authenticate(username, password);
		assertThat(successful).isEqualTo("false");
	}
}
