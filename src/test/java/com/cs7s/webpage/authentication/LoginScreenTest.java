//package com.cs7s.webpage.authentication;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.vaadin.ui.PasswordField;
//import com.vaadin.ui.TextField;
//
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = { AccessControlTest.class, AccessControl.class })
//public class LoginScreenTest {
//	
//	@Mock
//	private AccessControl accessControl;
//	@Mock
//	private TextField username;
//	@Mock
//	private PasswordField password;
//	
//	@InjectMocks
//	private LoginScreen loginScreen = new LoginScreen(accessControl);
//
//
//	@Test
//	public void loginSuccessTest() throws Exception {
//
//		when(accessControl.signIn("user", "pass")).thenReturn(true);
//		when(username.getValue()).thenReturn("user");
//		when(password.getValue()).thenReturn("pass");
//		
//	}
//	
//	@Test
//	public void loginFailureTest() throws Exception {
//
//		when(accessControl.signIn("wrong", "wrong")).thenReturn(false);
//		when(username.getValue()).thenReturn("wrong");
//		when(password.getValue()).thenReturn("wrong");
//	}
//
//}