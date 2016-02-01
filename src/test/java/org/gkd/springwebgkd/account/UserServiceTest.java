package org.gkd.springwebgkd.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

import org.gkd.springwebgkd.bean.jpa.WebgkdUsersecEntity;
import org.gkd.springwebgkd.repo.WebgkdUsersecRepository;
import org.gkd.springwebgkd.service.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserService();

	@Mock
	private WebgkdUsersecRepository accountRepositoryMock;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldInitializeWithTwoDemoUsers() {
		// act
		userService.initialize();
		// assert
		verify(accountRepositoryMock, times(2)).saveData(any(WebgkdUsersecEntity.class));
	}

	@Test
	public void shouldThrowExceptionWhenUserNotFound() {
		// arrange
		thrown.expect(UsernameNotFoundException.class);
		thrown.expectMessage("user not found");

		when(accountRepositoryMock.findByEmail("user@example.com")).thenReturn(null);
		// act
		userService.loadUserByUsername("user@example.com");
	}

	@Test
	public void shouldReturnUserDetails() {
		// arrange
		WebgkdUsersecEntity demoUser = new WebgkdUsersecEntity("user@example.com", "demo", "ROLE_USER", "17549");
		when(accountRepositoryMock.findByEmail("user@example.com")).thenReturn(demoUser);

		// act
		UserDetails userDetails = userService.loadUserByUsername("user@example.com");

		// assert
		assertThat(demoUser.getEmail()).isEqualTo(userDetails.getUsername());
		assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
//        assertThat(hasAuthority(userDetails, demoUser.getRole()));
	}

	private boolean hasAuthority(UserDetails userDetails, String role) {
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
}
