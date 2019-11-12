package nhuconghaui.webdidong;

import nhuconghaui.webdidong.repository.UserRepository;
import nhuconghaui.webdidong.service.RoleService;
import nhuconghaui.webdidong.service.UserService;
import nhuconghaui.webdidong.service.impl.RoleServiceImpl;
import nhuconghaui.webdidong.service.impl.UserDetailsServiceImpl;
import nhuconghaui.webdidong.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WebdidongApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebdidongApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(){
		return new UserDetailsServiceImpl();
	}

	@Bean
	public UserService userService(){
		return new UserServiceImpl();
	}

	@Bean
	public RoleService roleService(){
		return new RoleServiceImpl();
	}
}
