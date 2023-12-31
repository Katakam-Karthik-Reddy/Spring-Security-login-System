package com.karthik.oauthsecurity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.karthik.oauthsecurity.models.Role;
import com.karthik.oauthsecurity.models.User;
import com.karthik.oauthsecurity.repository.RoleRepository;
import com.karthik.oauthsecurity.repository.UserRepository;

@SpringBootApplication
public class OauthsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthsecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return ;
			Role adminrole = roleRepository.save(new Role(1, "ADMIN")); 
			roleRepository.save(new Role(2, "USER"));
			
			Set<Role> roles = new HashSet<>();
			roles.add(adminrole);

			User adminuser = new User(1, "admin", passwordEncoder.encode("admin"), roles);
			userRepository.save(adminuser); 
			
		};
	}
	
}
