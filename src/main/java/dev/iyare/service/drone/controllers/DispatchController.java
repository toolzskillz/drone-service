package dev.iyare.service.drone.controllers;

import java.util.Arrays;
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
import dev.iyare.service.drone.entities.EntityMedication;
import dev.iyare.service.drone.enums.DroneState;
import dev.iyare.service.drone.models.request.LoadDroneRequest;
import dev.iyare.service.drone.models.request.RegisterDroneRequest;
import dev.iyare.service.drone.models.response.AbstractResponse;
import dev.iyare.service.drone.models.response.DroneBatteryLevelResponse;
import dev.iyare.service.drone.models.response.DronesAvailableResponse;
import dev.iyare.service.drone.models.response.LoadDroneResponse;
import dev.iyare.service.drone.models.response.RegisterDroneResponse;
import dev.iyare.service.drone.repositories.EntityDroneRepository;
import dev.iyare.service.drone.repositories.EntityMedicationRepository;
import dev.iyare.service.drone.utils.JsonUtil;
import dev.iyare.service.drone.utils.RegExPatternUtil;

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

			EntityDrone entityDroneFound;
			if (Objects.nonNull(serialNumber))
			{
				entityDroneFound = entityDroneRepository.verifyDroneAvailable(serialNumber);
				logger.info("entityDrone: " + JsonUtil.toJson(entityDroneFound));

				if (Objects.nonNull(entityDroneFound))
				{

					if (Integer.parseInt(entityDroneFound.getBattery_capacity()) < 25)
					{
						loadDroneResponse = (LoadDroneResponse) failed(new LoadDroneResponse(),
								entityDroneFound.getModel() + " battery Level is below operating capacity");
						response = JsonUtil.toJson(loadDroneResponse);
						return response;
					}

					List<EntityMedication> medications = Arrays.asList(JsonUtil
							.fromJson(JsonUtil.toJson(loadDroneRequest.getMedications()), EntityMedication[].class));

					int totalMedicationsWeight = 0;
					for (EntityMedication medication : medications)
					{
						boolean medName = RegExPatternUtil.matchMedName(medication.getName());

						if (medName == false)
						{
							loadDroneResponse = (LoadDroneResponse) failed(new LoadDroneResponse(),
									"Invalid Medication Name");
							response = JsonUtil.toJson(loadDroneResponse);
							break;
						}

						boolean medCode = RegExPatternUtil.matchMedCode(medication.getCode());
						if (medCode == false)
						{
							loadDroneResponse = (LoadDroneResponse) failed(new LoadDroneResponse(),
									"Invalid Medication Code");
							response = JsonUtil.toJson(loadDroneResponse);
							break;
						}

						totalMedicationsWeight += Integer.parseInt(medication.getWeight());

						medication.setDrone_serial_number(entityDroneFound.getSerial_number());
					}

					int droneWeightLimit = Integer.parseInt(entityDroneFound.getWeight_limit());
					if (totalMedicationsWeight > droneWeightLimit)
					{
						loadDroneResponse = (LoadDroneResponse) failed(new LoadDroneResponse(),
								"Medications weight exceeds Drone's carrying capacity.");
						return JsonUtil.toJson(loadDroneResponse);
					} else if (totalMedicationsWeight < droneWeightLimit)
					{

						entityMedicationRepository.saveAll(medications);

						entityDroneRepository.updateState(DroneState.LOADING.getDescription(),
								entityDroneFound.getSerial_number());

					} else if (totalMedicationsWeight == droneWeightLimit)
					{
						entityMedicationRepository.saveAll(medications);

						entityDroneRepository.updateState(DroneState.LOADED.getDescription(),
								entityDroneFound.getSerial_number());
					}

					loadDroneResponse = new LoadDroneResponse();
					loadDroneResponse.setResponseCode(AbstractResponse.SUCCESSFUL_CODE);
					loadDroneResponse.setResponseMessage(AbstractResponse.SUCCESSFUL);
					loadDroneResponse.setResponseDescription("Medications Loaded!");
					response = JsonUtil.toJson(loadDroneResponse);

				} else
				{
					loadDroneResponse = (LoadDroneResponse) failed(new LoadDroneResponse(),
							"Drone not available/found");
					response = JsonUtil.toJson(loadDroneResponse);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();

			loadDroneResponse = (LoadDroneResponse) failed(new LoadDroneResponse(), e.getMessage());
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
			if (isHeadersValid(headers) == false)
			{
				return JsonUtil.toJson(invalidHeader(new LoadDroneResponse()));
			}
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
				dronesAvailableResponse = (DronesAvailableResponse) failed(new DronesAvailableResponse(),
						"No Drone(s) available");
				response = JsonUtil.toJson(dronesAvailableResponse);
			}

		} catch (Exception e)
		{
			e.printStackTrace();

			dronesAvailableResponse = (DronesAvailableResponse) failed(new DronesAvailableResponse(), e.getMessage());
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
				droneBatteryLevelResponse = (DroneBatteryLevelResponse) failed(new DroneBatteryLevelResponse(),
						"Drone not found!");
				response = JsonUtil.toJson(droneBatteryLevelResponse);
			}

		} catch (Exception e)
		{
			e.printStackTrace();

			droneBatteryLevelResponse = (DroneBatteryLevelResponse) failed(new DroneBatteryLevelResponse(),
					e.getMessage());
			response = JsonUtil.toJson(droneBatteryLevelResponse);
		}

		return response;
	}

	private boolean isHeadersValid(HttpHeaders headers)
	{
		boolean emptyValues, validValues;
		try
		{
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

	AbstractResponse failed(AbstractResponse response, String message)
	{
		response.setResponseCode(AbstractResponse.FAILED_CODE);
		response.setResponseMessage(AbstractResponse.FAILED);
		response.setResponseDescription(message);
		return response;
	}
}
