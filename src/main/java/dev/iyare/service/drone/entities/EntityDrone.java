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

	@NotNull(message = "Lightweight, Middleweight, Cruiserweight, Heavyweight")
	String model;

	@NotNull
	@Max(value = 500, message = "weight should not be greater than 500")
	String weight_limit;

	@NotNull
	@Min(value = 25, message = "Age should not be less than 18")
    @Max(value = 100, message = "Age should not be greater than 150")
	String battery_capacity;

	@NotNull
	String state;// (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

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
