package nhuconghaui.webdidong;

import nhuconghaui.webdidong.formatter.RoleFormatter;
import nhuconghaui.webdidong.service.RoleService;
import nhuconghaui.webdidong.service.UserService;
import nhuconghaui.webdidong.service.impl.RoleServiceImpl;
import nhuconghaui.webdidong.service.impl.UserDetailsServiceImpl;
import nhuconghaui.webdidong.service.impl.UserServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public RoleService roleService() {
		return new RoleServiceImpl();
	}

	@Configuration
	class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

		private ApplicationContext appContext;

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			appContext = applicationContext;
		}

		@Override
		public void addFormatters(FormatterRegistry registry) {
			RoleService locationService = appContext.getBean(RoleService.class);
			Formatter locationFormatter = new RoleFormatter(locationService);
			registry.addFormatter(locationFormatter);
		}

	}
}
