package com.task.OnlineBook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

@EnableCaching
@ComponentScan(basePackages = {"com.task"})
@SpringBootApplication
@EnableFeignClients
@Slf4j
public class OnlineBookApplication implements CommandLineRunner {

	@Bean
	DispatcherServlet dispatcherServlet () {
		DispatcherServlet ds = new DispatcherServlet();
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}

	@Bean
	RestTemplate getRestTemplate()
	{
		return new RestTemplate() ;
	}


	public static void main(String[] args) {
		try {
			SpringApplication.run(OnlineBookApplication.class, args);

		} catch(Throwable ex) {
			ex.printStackTrace();
		}
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("everything is cool");
	}
}
