package bootcamp.qna.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import bootcamp.qna.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
		System.out.println(encoder.encode("presenter"));
		return encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable().authorizeRequests()
			.antMatchers("/", "/checkin", "/css/**", "/js/**", "/resources/**", "/webjars/**", "/api/v1/participant/**").permitAll()
			.antMatchers("/presenter/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRESENTER")
			.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
}
