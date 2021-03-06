package com.gt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
 /**
  * This class defines some conditional beans and one of the reason for its existence is @ComponentScan which provides
  * beans auto-detection facility.Alternatively, put the component scan logic in application context level.
  * The beans defined by this class are environment specific, which means each bean will be created only if the corresponding profile
  * is activated.  
  * @author Kaustubh
  *
  */
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
@Configuration
@PropertySource("classpath:application-test.properties")
@ComponentScan(basePackages = {"com.gt","com.schedules"})
@EnableScheduling
public class AppConfig {
	
	@Autowired
	Environment env;
	
	@Bean(name="config")
	@Profile("local")
	public Config local() {
		System.out.println("========= local server ========");
		System.out.println("Operator API - " + env.getProperty("api.testUrl"));
		return new Config(env.getProperty("api.testUrl"));
	}
	@Bean(name="config")
	@Profile("qa")
	public Config test() {
		System.out.println("========= test server ========");
		System.out.println("Operator API - " + env.getProperty("api.testUrl"));
		return new Config(env.getProperty("api.testUrl"));
	}
	@Bean(name="config")
	@Profile("staging")
	public Config staging() {
		System.out.println("========= staging server ========");
		System.out.println("Operator API - " + env.getProperty("api.stagingUrl"));
		return new Config(env.getProperty("api.stagingUrl"));
	}
	@Bean(name="config")
	@Profile("prod")
	public Config prod() {
		System.out.println("========= production server ========");
		System.out.println("Operator API - " + env.getProperty("api.prodUrl"));
		return new Config(env.getProperty("api.prodUrl"));
	}
	
}
