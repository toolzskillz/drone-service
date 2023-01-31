package dev.iyare.service.drone.models.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LoadDroneRequest implements Serializable
{
	private static final long serialVersionUID = 1L;

	String serial_number;

	String model;

	String weight_limit;

	String battery_capacity;

	String state;


	
	
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
