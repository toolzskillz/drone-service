package dev.iyare.service.drone.enums;

public enum DroneState
{
	IDLE("IDLE"), LOADING("LOADING"), LOADED("LOADED"), DELIVERING("DELIVERING"), DELIVERED("DELIVERED"),
	RETURNING("RETURNING");

	private String description;

	DroneState(String description)
	{
		this.description = description;
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
