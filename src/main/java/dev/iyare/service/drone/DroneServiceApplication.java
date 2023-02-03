package dev.iyare.service.drone;

import java.util.ArrayList;
import java.util.List;

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

import dev.iyare.service.drone.entities.EntityDrone;
import dev.iyare.service.drone.enums.Drone;
import dev.iyare.service.drone.enums.DroneState;
import dev.iyare.service.drone.repositories.EntityDroneRepository;
import dev.iyare.service.drone.repositories.EntityMedicationRepository;
import dev.iyare.service.drone.utils.StringUtil;

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

	EntityDroneRepository entityDroneRepository;
	EntityMedicationRepository entityMedicationRepository;

	public DroneServiceApplication(EntityDroneRepository entityDroneRepository,
			EntityMedicationRepository entityMedicationRepository)
	{
		this.entityDroneRepository = entityDroneRepository;
		this.entityMedicationRepository = entityMedicationRepository;
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
				logger.info("+++++++++++++++++ Preloading required data in db");

				preLoadDrones();

				preLoadMedications();

			}

			@Override
			public void contextDestroyed(ServletContextEvent event)
			{
				logger.info("+++++++++++++++++ ServletContextListener destroyed");
			}
		};
	}

	protected void preLoadMedications()
	{
		// TODO Auto-generated method stub

	}

	protected void preLoadDrones()
	{
		EntityDrone entityDrone = null;

		List<EntityDrone> drones = new ArrayList<EntityDrone>();

		entityDrone = new EntityDrone();
		entityDrone.setSerial_number(StringUtil.generateUniqueId(7));
		entityDrone.setModel(Drone.LIGHT.getModel());
		entityDrone.setWeight_limit(Drone.LIGHT.getWeightLimit());
		entityDrone.setBattery_capacity("25");
		entityDrone.setState(DroneState.IDLE.getDescription());
		drones.add(entityDrone);
		
		entityDrone = new EntityDrone();
		entityDrone.setSerial_number(StringUtil.generateUniqueId(7));
		entityDrone.setModel(Drone.MIDDLE.getModel());
		entityDrone.setWeight_limit(Drone.MIDDLE.getWeightLimit());
		entityDrone.setBattery_capacity("30");
		entityDrone.setState(DroneState.DELIVERING.getDescription());
		drones.add(entityDrone);
		
		entityDrone = new EntityDrone();
		entityDrone.setSerial_number(StringUtil.generateUniqueId(7));
		entityDrone.setModel(Drone.CRUISER.getModel());
		entityDrone.setWeight_limit(Drone.CRUISER.getWeightLimit());
		entityDrone.setBattery_capacity("50");
		entityDrone.setState(DroneState.LOADED.getDescription());
		drones.add(entityDrone);
		
		entityDrone = new EntityDrone();
		entityDrone.setSerial_number(StringUtil.generateUniqueId(7));
		entityDrone.setModel(Drone.HEAVY.getModel());
		entityDrone.setWeight_limit(Drone.HEAVY.getWeightLimit());
		entityDrone.setBattery_capacity("70");
		entityDrone.setState(DroneState.RETURNING.getDescription());
		drones.add(entityDrone);
		
	

		entityDroneRepository.saveAll(drones);

	}

}
