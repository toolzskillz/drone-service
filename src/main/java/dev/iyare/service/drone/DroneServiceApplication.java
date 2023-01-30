package dev.iyare.service.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@EntityScan("dev.iyare.service.*")
@Configuration
@SpringBootApplication
public class DroneServiceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(DroneServiceApplication.class, args);
	}

}
