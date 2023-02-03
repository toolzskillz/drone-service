package dev.iyare.service.drone.enums;

public enum Drone
{
	LIGHT("125", "LightWeight"), MIDDLE("250", "MiddleWeight"), CRUISER("375", "CruiserWeight"),
	HEAVY("500", "HeavyWeight");

	private String code, description;

	Drone(String code, String description)
	{
		this.code = code;
		this.description = description;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
