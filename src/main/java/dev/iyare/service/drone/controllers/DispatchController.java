package dev.iyare.service.drone.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.iyare.service.drone.repositories.EntityDroneRepository;
import dev.iyare.service.drone.repositories.EntityMedicationRepository;

@RestController
@RequestMapping("/drone/app/v1/service")
public class DispatchController
{
	private static final Log LOG = LogFactory.getLog(DispatchController.class);

	EntityDroneRepository entityDroneRepository;
	EntityMedicationRepository entityMedicationRepository;

	public DispatchController(EntityDroneRepository entityDroneRepository,
			EntityMedicationRepository entityMedicationRepository)
	{
		this.entityDroneRepository = entityDroneRepository;
		this.entityMedicationRepository = entityMedicationRepository;
	}

	@PostMapping(value = "/register-drone")
	public @ResponseBody String registerDrone(@RequestHeader HttpHeaders headers, @RequestBody String request)
	{
		String response = null;

//		EntityDrone entityDrone = new EntityDrone();
//		entityDroneRepository.save(entityDrone);

		return response;
	}

	@PostMapping(value = "/load-drone-with-meds")
	public @ResponseBody String loadDroneWithMeds(@RequestHeader HttpHeaders headers, @RequestBody String request)
	{
		String response = null;

		return response;
	}

	@PostMapping(value = "/check-medlist-for-drone")
	public @ResponseBody String checkMedicationsForDrone(@RequestHeader HttpHeaders headers,
			@RequestBody String request)
	{
		String response = null;

		return response;
	}

	@GetMapping(value = "/get-available-drones")
	public @ResponseBody String availableDrones(@RequestHeader HttpHeaders headers)
	{
		String response = null;

		return response;
	}

	@GetMapping(value = "/get-drone-battery-level")
	public @ResponseBody String droneBatteryLevel(@RequestHeader HttpHeaders headers)
	{
		String response = null;

		return response;
	}
}
