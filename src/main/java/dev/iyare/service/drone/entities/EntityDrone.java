package dev.iyare.service.drone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity(name = "drone")
public class EntityDrone extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Drone serial number is required")
	@Column(unique = true)
	@Length(min = 1, max = 100, message = "Serial number should not be greater than 100 characters")
	
	String serial_number;

	@NotNull(message = "Drone model is required i.e. Lightweight, Middleweight, Cruiserweight, Heavyweight")
	String model;

	@NotNull(message = "Drone model is required")
	@Max(value = 500, message = "weight should not be greater than 500 grams")
	String weight_limit;

	@NotNull(message = "Drone battery capacity is required")
	@Min(value = 25, message = "Drone battery capacity should not be less than 25")
	@Max(value = 100, message = "Drone battery capacity should not be greater than 100")
	String battery_capacity;

	@NotNull(message = "Drone state is required i.e. IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING")
	String state;

	public EntityDrone()
	{

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
