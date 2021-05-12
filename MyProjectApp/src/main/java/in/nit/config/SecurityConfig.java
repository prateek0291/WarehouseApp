package in.nit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//Container:Java Config File
@Configuration
//enable this class
@EnableWebSecurity 
public class SecurityConfig
	//a. link with Security Adapter
	extends WebSecurityConfigurerAdapter
{
	//b. link with UserDetailsService
	@Autowired
	private UserDetailsService service;
	
	//c. link with PasswrodEncoder
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	//d. provide method to verfiy un/pwd
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
		auth.userDetailsService(service)
		.passwordEncoder(pwdEncoder);
	}
	
	//e. provide method to verify roles,FormLoginPage, Logout details
	/**
	 *   Roles with URLs and Login Form Details and Logout Details 
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()

		.antMatchers("/user/**").permitAll()

		.antMatchers("/uom/**","/whusertype/**","/whusertype**",
				"/part/**","/ordermethod/**","/shipment/**")
		.hasAnyAuthority("EMPLOYEE","ADMIN")

		.antMatchers("/po/**","/grn/**","/so/**","/shipping/**")
		.hasAnyAuthority("ADMIN","EMPLOYEE")
		//remaining URLS can be accessed by
		.anyRequest()
		.authenticated() //Who just login (no role required)

		.and()
		.formLogin() 
		.loginPage("/user/login") //URL to show login page
		.loginProcessingUrl("/login") //on click Form Login Submit
		.defaultSuccessUrl("/uom/setup", true) //on login success
		.failureUrl("/user/login?error=true") //on login failure

		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //link for Logout
		.logoutSuccessUrl("/user/login?logout=true"); //on logout success
	}
	
	
}






