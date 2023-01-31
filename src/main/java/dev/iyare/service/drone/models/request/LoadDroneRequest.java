package dev.iyare.service.drone.models.request;

import java.io.Serializable;
import java.util.List;

public class LoadDroneRequest implements Serializable
{
	private static final long serialVersionUID = 1L;

	String model;

	String serial_number;

	List<MedicationRequest> medications;

	public List<MedicationRequest> getMedications()
	{
		return medications;
	}

	public void setMedications(List<MedicationRequest> medications)
	{
		this.medications = medications;
	}

	public String getSerial_number()
	{
		return serial_number;
	}

	public void setSerial_number(String serial_number)
	{
		this.serial_number = serial_number;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}
