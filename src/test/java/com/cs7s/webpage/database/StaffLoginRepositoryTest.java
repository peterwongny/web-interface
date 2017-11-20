package com.cs7s.webpage.database;
//package com.web.webpage.database;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.io.InputStream;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.WebApplicationContext;
//
//
//
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
//
//import java.util.*;
//
//@EnableAutoConfiguration
//@AutoConfigureTestDatabase(replace=Replace.NONE) //uses our psql db
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@SpringBootTest(classes = { StaffLoginRepositoryTest.class, StaffLogin.class })
//public class StaffLoginRepositoryTest {
//
//	@Autowired
//	StaffLoginRepository staffLoginRepo;
//	
//	@Test
//	public void successfulLoginExactUsername() throws Exception {
//		String username = "gian";
//		String password = "gian";
//		String successful = this.staffLoginRepo.authenticate(username, password);
//		assertThat(successful).isEqualTo("true");
//	}
//	
//	@Test
//	public void successfulLoginDifferentCaseUsername() throws Exception {
//		String username = "Gian";
//		String password = "gian";
//		String successful = this.staffLoginRepo.authenticate(username, password);
//		assertThat(successful).isEqualTo("true");
//	}
//	
//	@Test
//	public void wrongUsername() throws Exception {
//		String username = "gina";
//		String password = "gian";
//		String successful = this.staffLoginRepo.authenticate(username, password);
//		assertThat(successful).isEqualTo("false");
//	}
//	
//	@Test
//	public void wrongPasswordCase() throws Exception {
//		String username = "gian";
//		String password = "Gian";
//		String successful = this.staffLoginRepo.authenticate(username, password);
//		assertThat(successful).isEqualTo("false");
//	}
//	
//	@Test
//	public void wrongPasswordArrangement() throws Exception {
//		String username = "gian";
//		String password = "gina";
//		String successful = this.staffLoginRepo.authenticate(username, password);
//		assertThat(successful).isEqualTo("false");
//	}
//	
//	@Test
//	public void wrongUsernameAndPassword() throws Exception {
//		String username = "gina";
//		String password = "Gian";
//		String successful = this.staffLoginRepo.authenticate(username, password);
//		assertThat(successful).isEqualTo("false");
//	}
//}
