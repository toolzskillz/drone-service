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

	public interface WEIGHT
	{
		String Lightweight = "Lightweight";
		String Middleweight = "Middleweight";
		String Cruiserweight = "Cruiserweight";
		String Heavyweight = "Heavyweight";
	}
}
