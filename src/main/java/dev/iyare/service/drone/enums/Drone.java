package dev.iyare.service.drone.enums;

public enum Drone
{
	LIGHT("125", "LightWeight"), MIDDLE("250", "MiddleWeight"), CRUISER("375", "CruiserWeight"),
	HEAVY("500", "HeavyWeight");

	private String weightLimit, model;

	Drone(String weightLimit, String model)
	{
		this.weightLimit = weightLimit;
		this.model = model;
	}

	public String getWeightLimit()
	{
		return weightLimit;
	}

	public void setWeightLimit(String code)
	{
		this.weightLimit = code;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String description)
	{
		this.model = description;
	}
}
