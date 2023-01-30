package dev.iyare.service.drone.controllers;

import java.util.List;

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

import dev.iyare.service.drone.entities.EntityDrone;
import dev.iyare.service.drone.models.request.RegisterDroneRequest;
import dev.iyare.service.drone.models.response.AbstractResponse;
import dev.iyare.service.drone.models.response.RegisterDroneResponse;
import dev.iyare.service.drone.repositories.EntityDroneRepository;
import dev.iyare.service.drone.repositories.EntityMedicationRepository;
import dev.iyare.service.drone.utils.JsonUtil;

@RestController
@RequestMapping("/drone/app/v1/service")
public class DispatchController
{
	private static final Log logger = LogFactory.getLog(DispatchController.class);

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
		RegisterDroneResponse registerDroneResponse = null;

		logger.info("Register Request: " + request);

		try
		{
			List<String> headersList = headers.get("CHANNEL");
			logger.info("headersList: " + headersList);
			String channel = headersList.get(0);
			logger.info("channel: " + channel);

			RegisterDroneRequest registerDroneRequest = JsonUtil.fromJson(request, RegisterDroneRequest.class);
			logger.info("registerDroneRequest: " + registerDroneRequest);

			String serialNumber = registerDroneRequest.getSerial_number();
			String model = registerDroneRequest.getModel();
			String weightLimit = registerDroneRequest.getWeight_limit();
			String batteryCapacity = registerDroneRequest.getBattery_capacity();
			String state = registerDroneRequest.getState().toUpperCase();

			EntityDrone entityDrone = new EntityDrone(serialNumber, model, weightLimit, batteryCapacity, state);
			entityDroneRepository.save(entityDrone);

			registerDroneResponse = new RegisterDroneResponse();
			registerDroneResponse.setResponseCode(AbstractResponse.SUCCESSFUL_CODE);
			registerDroneResponse.setResponseMessage(AbstractResponse.SUCCESSFUL);
			registerDroneResponse.setResponseDescription("Drone Registration was Successful!");

			response = JsonUtil.toJson(registerDroneResponse);

		} catch (Exception e)
		{
			e.getMessage();

			registerDroneResponse = new RegisterDroneResponse();
			registerDroneResponse.setResponseCode(AbstractResponse.FAILED_CODE);
			registerDroneResponse.setResponseMessage(AbstractResponse.FAILED);
			registerDroneResponse.setResponseDescription(e.getMessage());

			response = JsonUtil.toJson(registerDroneResponse);
		}

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
