package dev.iyare.service.drone.models.response;

public class DroneBatteryLevelResponse extends AbstractResponse
{
	private static final long serialVersionUID = 1L;
	String model;
	String batteryLevel;

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public String getBatteryLevel()
	{
		return batteryLevel;
	}

	public void setBatteryLevel(String batteryLevel)
	{
		this.batteryLevel = batteryLevel;
	}
}
