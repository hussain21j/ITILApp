package com.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.model.User;
import com.model.UserRole;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		System.out.println("loadUserByUsername strats username passed:"+userName);
		User user = userDao.findUserByName(userName); //our own User model class
		
		if(user!=null){
			String password = user.getUserPassword();
			//additional information on the security object
			boolean enabled = user.getUserStatus().equals("A");
			boolean accountNonExpired = user.getUserStatus().equals("A");
			boolean credentialsNonExpired = user.getUserStatus().equals("A");
			boolean accountNonLocked = user.getUserStatus().equals("A");
			
			//Let's populate user roles
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(UserRole role : user.getRoles()){
				authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
			}
			
			//Now let's create Spring Security User object
			org.springframework.security.core.userdetails.User securityUser = new 
					org.springframework.security.core.userdetails.User(userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			return securityUser;
		}else{
			System.out.println("user is null");
			throw new UsernameNotFoundException("User Not Found!!!");
		}
	}

}
