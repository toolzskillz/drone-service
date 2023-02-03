package dev.iyare.service.drone.constants;

public class Drone
{
	public interface STATE
	{
		String IDLE = "IDLE";
		String LOADING = "LOADING";
		String LOADED = "LOADED";
		String DELIVERING = "DELIVERING";
		String DELIVERED = "DELIVERED";
		String RETURNING = "RETURNING";
	}

	public interface WEIGHT_LIMIT
	{
		String LIGHT = "Lightweight";
		String MIDDLE = "Middleweight";
		String CRUISER = "Cruiserweight";
		String HEAVY = "Heavyweight";
	}
}
