package pl.komoor.pcbuilder.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.komoor.pcbuilder.security.jwt.AuthEntryPointJwt;
import pl.komoor.pcbuilder.security.jwt.AuthTokenFilter;
import pl.komoor.pcbuilder.services.impl.users.UserServiceImpl;
import pl.komoor.pcbuilder.services.users.UserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/auth/**").permitAll()
			.antMatchers("/test/**").permitAll()
			.antMatchers("/users/**").permitAll()
			.antMatchers("/products/**").permitAll()
			.antMatchers("/product/**").permitAll()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/manufacturer/**").permitAll()
			.antMatchers("/category/**").permitAll()
			.antMatchers("/compatibility/**").permitAll()
				.antMatchers("/pcpartslist/**").permitAll().antMatchers("/pcbuild/**").permitAll().antMatchers("/productReview/**").permitAll().antMatchers("/pcBuildReview/**").permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
