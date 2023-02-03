package dev.iyare.service.drone.controllers;

import java.util.List;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.iyare.service.drone.entities.EntityDrone;
import dev.iyare.service.drone.models.request.LoadDroneRequest;
import dev.iyare.service.drone.models.request.MedicationRequest;
import dev.iyare.service.drone.models.request.RegisterDroneRequest;
import dev.iyare.service.drone.models.response.AbstractResponse;
import dev.iyare.service.drone.models.response.DroneBatteryLevelResponse;
import dev.iyare.service.drone.models.response.DronesAvailableResponse;
import dev.iyare.service.drone.models.response.LoadDroneResponse;
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

		try
		{
			if (isHeadersValid(headers) == false)
			{
				return JsonUtil.toJson(invalidHeader(new RegisterDroneResponse()));
			}

			RegisterDroneRequest registerDroneRequest = JsonUtil.fromJson(request, RegisterDroneRequest.class);
//			logger.info("registerDroneRequest: " + JsonUtil.toJson(registerDroneRequest));

			String serialNumber = registerDroneRequest.getSerial_number();
			String model = registerDroneRequest.getModel();
			String weightLimit = registerDroneRequest.getWeight_limit();
			String batteryCapacity = registerDroneRequest.getBattery_capacity();
			String state = registerDroneRequest.getState().toUpperCase();

			EntityDrone entityDrone = new EntityDrone();
			entityDrone.setSerial_number(serialNumber);
			entityDrone.setModel(model);
			entityDrone.setWeight_limit(weightLimit);
			entityDrone.setBattery_capacity(batteryCapacity);
			entityDrone.setState(state);

			entityDroneRepository.save(entityDrone);

			registerDroneResponse = new RegisterDroneResponse();
			registerDroneResponse.setResponseCode(AbstractResponse.SUCCESSFUL_CODE);
			registerDroneResponse.setResponseMessage(AbstractResponse.SUCCESSFUL);
			registerDroneResponse.setResponseDescription("Drone Registration was Successful!");

			response = JsonUtil.toJson(registerDroneResponse);

		} catch (Exception e)
		{
			e.printStackTrace();

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
		LoadDroneResponse loadDroneResponse = null;

		try
		{
			if (isHeadersValid(headers) == false)
			{
				return JsonUtil.toJson(invalidHeader(new LoadDroneResponse()));
			}

			LoadDroneRequest loadDroneRequest = JsonUtil.fromJson(request, LoadDroneRequest.class);
//			logger.info("loadDroneRequest: " + JsonUtil.toJson(loadDroneRequest));

			String serialNumber = loadDroneRequest.getSerial_number();
			String model = loadDroneRequest.getModel();

			logger.info("serialNumber: " + serialNumber);
			logger.info("model: " + model);

			EntityDrone entityDrone;
			if (Objects.nonNull(serialNumber) && Objects.nonNull(model))
			{
				entityDrone = entityDroneRepository.verifyDroneAvailable(serialNumber, model);
				logger.info("entityDrone: " + JsonUtil.toJson(entityDrone));
			}

			List<MedicationRequest> medications = loadDroneRequest.getMedications();
			for (MedicationRequest medication : medications)
			{
				logger.info("medication: " + JsonUtil.toJson(medication));
			}

		} catch (Exception e)
		{
			e.printStackTrace();

			loadDroneResponse = new LoadDroneResponse();
			loadDroneResponse.setResponseCode(AbstractResponse.FAILED_CODE);
			loadDroneResponse.setResponseMessage(AbstractResponse.FAILED);
			loadDroneResponse.setResponseDescription(e.getMessage());

			response = JsonUtil.toJson(loadDroneResponse);
		}

		return response;
	}

	@PostMapping(value = "/check-medlist-for-drone")
	public @ResponseBody String checkMedicationsForDrone(@RequestHeader HttpHeaders headers,
			@RequestBody String request)
	{
		String response = null;

		try
		{

		} catch (Exception e)
		{

		}

		return response;
	}

	@GetMapping(value = "/get-available-drones")
	public @ResponseBody String availableDrones(@RequestHeader HttpHeaders headers)
	{
		String response = null;
		DronesAvailableResponse dronesAvailableResponse = null;

		try
		{
			List<EntityDrone> entityDronesList = entityDroneRepository.findAvailableDrones();

			if (Objects.nonNull(entityDronesList) && entityDronesList.size() > 0)
			{
				dronesAvailableResponse = new DronesAvailableResponse();
				dronesAvailableResponse.setData(JsonUtil.toJson(entityDronesList));
				dronesAvailableResponse.setResponseCode(AbstractResponse.SUCCESSFUL_CODE);
				dronesAvailableResponse.setResponseMessage(AbstractResponse.SUCCESSFUL);
				dronesAvailableResponse.setResponseDescription("Available Drone(s) Found!");

				response = JsonUtil.toJson(dronesAvailableResponse);
			} else
			{
				dronesAvailableResponse = new DronesAvailableResponse();
				dronesAvailableResponse.setResponseCode(AbstractResponse.FAILED_CODE);
				dronesAvailableResponse.setResponseMessage(AbstractResponse.FAILED);
				dronesAvailableResponse.setResponseDescription("No Drone(s) available");

				response = JsonUtil.toJson(dronesAvailableResponse);
			}

		} catch (Exception e)
		{
			e.printStackTrace();

			dronesAvailableResponse = new DronesAvailableResponse();
			dronesAvailableResponse.setResponseCode(AbstractResponse.FAILED_CODE);
			dronesAvailableResponse.setResponseMessage(AbstractResponse.FAILED);
			dronesAvailableResponse.setResponseDescription(e.getMessage());

			response = JsonUtil.toJson(dronesAvailableResponse);
		}

		return response;
	}

	@GetMapping(value = "/get-drone-battery-level/{serial_number}")
	public @ResponseBody String droneBatteryLevel(@RequestHeader HttpHeaders headers,
			@PathVariable String serial_number)
	{
		String response = null;

		DroneBatteryLevelResponse droneBatteryLevelResponse = null;
		try
		{
			EntityDrone entityDrone = entityDroneRepository.findBySerialNo(serial_number);
			if (Objects.nonNull(entityDrone))
			{
				droneBatteryLevelResponse = new DroneBatteryLevelResponse();
				droneBatteryLevelResponse.setBatteryLevel(entityDrone.getBattery_capacity());
				droneBatteryLevelResponse.setModel(entityDrone.getModel());
				droneBatteryLevelResponse.setResponseCode(AbstractResponse.SUCCESSFUL_CODE);
				droneBatteryLevelResponse.setResponseMessage(AbstractResponse.SUCCESSFUL);
				droneBatteryLevelResponse.setResponseDescription("Drone Found!");

				response = JsonUtil.toJson(droneBatteryLevelResponse);
			} else
			{
				droneBatteryLevelResponse = new DroneBatteryLevelResponse();
				droneBatteryLevelResponse.setResponseCode(AbstractResponse.FAILED_CODE);
				droneBatteryLevelResponse.setResponseMessage(AbstractResponse.FAILED);
				droneBatteryLevelResponse.setResponseDescription("Drone not found!");

				response = JsonUtil.toJson(droneBatteryLevelResponse);
			}

		} catch (Exception e)
		{
			e.printStackTrace();

			droneBatteryLevelResponse = new DroneBatteryLevelResponse();
			droneBatteryLevelResponse.setResponseCode(AbstractResponse.FAILED_CODE);
			droneBatteryLevelResponse.setResponseMessage(AbstractResponse.FAILED);
			droneBatteryLevelResponse.setResponseDescription(e.getMessage());

			response = JsonUtil.toJson(droneBatteryLevelResponse);
		}

		return response;
	}

	private boolean isHeadersValid(HttpHeaders headers)
	{
		boolean emptyValues, validValues;
		try
		{
			logger.info("headers: " + JsonUtil.toJson(headers));
			String contentType = headers.get("Content-Type").get(0);
			String publicKey = headers.get("PublicKey").get(0);

			emptyValues = Objects.isNull(contentType) || contentType.contentEquals("") || Objects.isNull(publicKey)
					|| publicKey.contentEquals("");

			if (emptyValues == true)
				return false;

			validValues = contentType.contentEquals("application/json")
					&& publicKey.contentEquals("TXVzYWxhIHNvZnQgSW50ZXJ2aWV3");

			if (validValues == true)
				return true;

		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

		return emptyValues ^ validValues;
	}

	AbstractResponse invalidHeader(AbstractResponse response)
	{
		response.setResponseCode(AbstractResponse.FAILED_CODE);
		response.setResponseMessage(AbstractResponse.FAILED);
		response.setResponseDescription("Missing or Invalid Header(s)");
		return response;
	}
}
