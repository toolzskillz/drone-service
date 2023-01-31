package dev.iyare.service.drone.models.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

public class RegisterMedicationRequest implements Serializable
{
	private static final long serialVersionUID = 1L;

	String name;
	String weight;
	String code;
	String image;
	String drone_serial_number;

	public String getDrone_serial_number()
	{
		return drone_serial_number;
	}

	public void setDrone_serial_number(String drone_serial_number)
	{
		this.drone_serial_number = drone_serial_number;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getWeight()
	{
		return weight;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

}
