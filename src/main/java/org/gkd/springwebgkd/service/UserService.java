package org.gkd.springwebgkd.service;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.gkd.springwebgkd.bean.jpa.WebgkdUsersecEntity;
import org.gkd.springwebgkd.repo.WebgkdUsersecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;

public class UserService implements UserDetailsService {
	
	@Autowired
	private WebgkdUsersecRepository webgkdUsersecRepository;
	
	@PostConstruct	
	public void initialize() {
//		webgkdUsersecRepository.save(new WebgkdUsersecRepository("user", "demo", "ROLE_USER"));
//		webgkdUsersecRepository.save(new WebgkdUsersecRepository("admin", "admin", "ROLE_ADMIN"));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		WebgkdUsersecEntity account = webgkdUsersecRepository.findByNpk(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	public void signin(WebgkdUsersecEntity account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(WebgkdUsersecEntity account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));		
	}
	
	private User createUser(WebgkdUsersecEntity account) {
		return new User(account.getNpk(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(WebgkdUsersecEntity account) {
		return new SimpleGrantedAuthority(account.getStUser());
	}

}
