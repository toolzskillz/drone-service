package dev.iyare.service.drone.models.response;

import java.io.Serializable;

public abstract class AbstractResponse implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String FAILED = "FAILED";
	public static String FAILED_CODE = "99";

	public static String SUCCESSFUL = "SUCCESSFUL";
	public static String SUCCESSFUL_CODE = "00";

	public String responseMessage = FAILED;

	public String responseCode = FAILED_CODE;
	public String responseDescription;
	public String data;

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public String getResponseDescription()
	{
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription)
	{
		this.responseDescription = responseDescription;
	}

	public String getResponseMessage()
	{
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}

	public String getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(String responseCode)
	{
		this.responseCode = responseCode;
	}

	@Override
	public String toString()
	{
		return "AbstractResponse [responseMessage=" + responseMessage + ", responseCode=" + responseCode
				+ ", responseDescription=" + responseDescription + "]";
	}

}
