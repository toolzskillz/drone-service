package dev.iyare.service.drone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity(name = "medication")
public class EntityMedication extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Medication name is required")
	String name;

	@NotNull(message = "Medication weight is required")
	String weight;

	@Column(unique = true)
	@NotNull(message = "Medication code is required")
	String code;

	@NotNull(message = "Medication image is required")
	@Column(columnDefinition = "TEXT")
	String image; // in base64 

	@Nullable
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
