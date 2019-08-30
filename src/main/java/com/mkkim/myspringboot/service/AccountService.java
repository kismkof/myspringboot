package com.mkkim.myspringboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mkkim.myspringboot.entity.Account;
import com.mkkim.myspringboot.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> byUsername = repository.findByUsername(username);
		Account account = byUsername.orElseThrow(() ->new UsernameNotFoundException(username));
		return new User(account.getUsername(), account.getPassword(), authorities());
	}
	
	private Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@PostConstruct
	public void init() {
		Optional<Account> byUsername = repository.findByUsername("mkkim");
		if ( !byUsername.isPresent() ) {
			Account newAcct = this.createAccount("mkkim", "1234");
			System.out.println(newAcct.toString());
		}
	}
	
	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Account createAccount(String username, String password) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(passwordEncoder.encode(password));
		//account.setPassword(password);
		return repository.save(account);
	}
}
