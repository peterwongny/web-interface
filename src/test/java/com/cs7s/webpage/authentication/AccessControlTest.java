package com.cs7s.webpage.authentication;

import com.cs7s.webpage.database.StaffLoginRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = { AccessControlTest.class, AccessControl.class })
public class AccessControlTest {
	@Mock
	private StaffLoginRepository staffLoginRepo;
	
	@InjectMocks 
	private AccessControl accessControl = new BasicAccessControl(staffLoginRepo);


	@Test
	public void signInwithNullTest() throws Exception {

		assertThat(accessControl.signIn(null," " )).isEqualTo(false);
		assertThat(accessControl.signIn(""," " )).isEqualTo(false);
		assertThat(accessControl.signIn(" ", null )).isEqualTo(false);
		assertThat(accessControl.signIn(" ", "" )).isEqualTo(false);
	}
	
	@Test
	public void signInwithRepoTest() throws Exception {

		when(staffLoginRepo.authenticate("wrong", "wrong")).thenReturn("false");
		when(staffLoginRepo.authenticate("exception", "exception")).thenThrow(new RuntimeException());
		
		
		assertThat(accessControl.signIn("exception", "exception" )).isEqualTo(false);
		assertThat(accessControl.signIn("wrong", "wrong")).isEqualTo(false);
	}

}