package dev.iyare.service.drone.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "drone")
public class EntityDrone extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@NotNull
	@Max(value = 100, message = "serial number should not be greater than 100")
	String serial_number;

	@NotNull(message = "Drone model is required i.e. Lightweight, Middleweight, Cruiserweight, Heavyweight")
	String model;

	@NotNull
	@Max(value = 500, message = "weight should not be greater than 500 grams")
	String weight_limit;

	@NotNull(message = "Drone battery capacity is required")
	@Min(value = 25, message = "Drone battery capacity should not be less than 25")
	@Max(value = 100, message = "Drone battery capacity should not be greater than 100")
	String battery_capacity;

	@NotNull(message = "Drone state is required i.e. IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING")
	String state;

	public EntityDrone(
			@NotNull @Max(value = 100, message = "serial number should not be greater than 100") String serial_number,
			@NotNull(message = "Drone model is required i.e. Lightweight, Middleweight, Cruiserweight, Heavyweight") String model,
			@NotNull @Max(value = 500, message = "weight should not be greater than 500 grams") String weight_limit,
			@NotNull(message = "Drone battery capacity is required") @Min(value = 25, message = "Drone battery capacity should not be less than 25") @Max(value = 100, message = "Drone battery capacity should not be greater than 100") String battery_capacity,
			@NotNull(message = "Drone state is required i.e. IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING") String state)
	{
		super();
		this.serial_number = serial_number;
		this.model = model;
		this.weight_limit = weight_limit;
		this.battery_capacity = battery_capacity;
		this.state = state;
	}

	public String getSerial_number()
	{
		return serial_number;
	}

	public void setSerial_number(String serial_number)
	{
		this.serial_number = serial_number;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public String getWeight_limit()
	{
		return weight_limit;
	}

	public void setWeight_limit(String weight_limit)
	{
		this.weight_limit = weight_limit;
	}

	public String getBattery_capacity()
	{
		return battery_capacity;
	}

	public void setBattery_capacity(String battery_capacity)
	{
		this.battery_capacity = battery_capacity;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
