package dev.iyare.service.drone.models.request;

import java.io.Serializable;
import java.util.List;

public class LoadedDroneRequest implements Serializable
{
	private static final long serialVersionUID = 1L;

	String serial_number;

	public String getSerial_number()
	{
		return serial_number;
	}

	public void setSerial_number(String serial_number)
	{
		this.serial_number = serial_number;
	}

}
