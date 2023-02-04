package dev.iyare.service.drone.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity(name = "battery_history")
public class EntityBatteryHistory extends AbstractEntity
{
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Drone serial number is required")
	@Length(min = 1, max = 100, message = "Serial number should not be greater than 100 characters")
	String serial_number;

	@NotNull(message = "Drone battery capacity is required")
	String battery_capacity;

	@NotNull(message = "Date created required")
	Date date_audited;

	public EntityBatteryHistory()
	{

	}

	public Date getDate_audited()
	{
		return date_audited;
	}

	public void setDate_audited(Date date_audited)
	{
		this.date_audited = date_audited;
	}

	public String getSerial_number()
	{
		return serial_number;
	}

	public void setSerial_number(String serial_number)
	{
		this.serial_number = serial_number;
	}

	public String getBattery_capacity()
	{
		return battery_capacity;
	}

	public void setBattery_capacity(String battery_capacity)
	{
		this.battery_capacity = battery_capacity;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}
