package dev.iyare.service.drone.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Max;

@Entity(name = "drone")
public class EntityDrone extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@Max(100)
	String serial_number;// (100 characters max);
	String model;// (Lightweight, Middleweight, Cruiserweight, Heavyweight);
	@Max(500)
	String weight_limit;// (500gr max);
	String battery_capacity;// (percentage);
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
