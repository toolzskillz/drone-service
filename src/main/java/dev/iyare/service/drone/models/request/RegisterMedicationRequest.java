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

	public RegisterMedicationRequest(String name, String weight, String code, String image)
	{
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.image = image;
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
