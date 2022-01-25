package bootcamp.qna.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bootcamp.qna.domain.User;
import bootcamp.qna.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepository = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.get().getPassword(), 
				user.get().getAuthorities().stream().map(au -> new SimpleGrantedAuthority(au.getName())).collect(Collectors.toList()));
		return userDetails;
	}

}
