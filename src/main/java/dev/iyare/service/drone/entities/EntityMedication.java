package dev.iyare.service.drone.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name = "medication")
public class EntityMedication extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Medication name is required")
	String name;// (allowed only letters,numbers,‘-‘,‘_’);

	@NotNull(message = "Medication name is required")
	String weight;

	@NotNull
	String code;// (allowed only upper case letters, underscore and numbers);

	@NotNull
	String image;// (picture of the medication case).

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
