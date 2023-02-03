package dev.iyare.service.drone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EntityScan("dev.iyare.service.*")
@Configuration
@SpringBootApplication
public class DroneServiceApplication extends SpringBootServletInitializer
{
	private static Log logger = LogFactory.getLog(DroneServiceApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(DroneServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(DroneServiceApplication.class);
	}

	@Bean
	protected ServletContextListener listener()
	{
		return new ServletContextListener()
		{
			@Override
			public void contextInitialized(ServletContextEvent event)
			{
				logger.info("+++++++++++++++++ ServletContextListener initialized");

			}

			@Override
			public void contextDestroyed(ServletContextEvent event)
			{
				logger.info("+++++++++++++++++ ServletContextListener destroyed");
			}
		};
	}

}
