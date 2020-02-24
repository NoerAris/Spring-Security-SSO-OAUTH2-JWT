package com.app.ssoserver.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.app.ssoserver.Entity.MstUser;
import com.app.ssoserver.Entity.Role;
import com.app.ssoserver.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		MstUser user = userRepository.findByUsername(userId);
		if(user == null){
			log.error("Invalid username or password !");
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
    
    private Set<GrantedAuthority> getAuthorities(MstUser user) {
        Set<Role> roleByUserId = user.getRoles();
        final Set<GrantedAuthority> authorities = roleByUserId.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase())).collect(Collectors.toSet());
        return authorities;
    }

}
