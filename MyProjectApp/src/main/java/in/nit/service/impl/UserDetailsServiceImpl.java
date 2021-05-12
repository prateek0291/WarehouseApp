package in.nit.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.dao.IUserDao;

@Service
public class UserDetailsServiceImpl 
implements UserDetailsService
{
	@Autowired
	private IUserDao dao;

	/*
	 * Here username means emailId
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		//a. Get Model class User object using dao with username(email)
		in.nit.model.User model=dao.getUserByEmail(username);

		//b. Read Roles from Model class user
		Set<String> roles=model.getRoles();

		//c. create Set<GA> [GrantedAuthority] object
		// JDK 1.7 empty diamon operator : Collections Type Inference
		Set<GrantedAuthority> authorities=new HashSet<>();

		//d. convert Model class one Role into one GA[GrantedAuthority] object
		for(String role:roles) {
			authorities.add(
					new SimpleGrantedAuthority(role)
				);
		}

		//e. create spring f/w model class user
		org.springframework.security.core.userdetails.User secure=
				new org.springframework.security.core.userdetails.User(
						username, model.getUserPwd(), authorities);

		//f.  return model class user
		return secure;
	}
}


