package dev.iyare.service.drone.models.response;

import java.io.Serializable;

public abstract class AbstractResponse implements Serializable
{
    public static final String INVALID_USER_CREDENTIALS = "Invalid credentials";
    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String ALREADY_IN_USE = "ACCOUNT ALREADY IN USE";
    public static String ALREADY_SYNCED_CODE = "077";
    public static final String ALREADY_SYNCED = "ALREADY_SYNCED";
    private static final long serialVersionUID = 1L;

    public static String FAILED = "FAILED";
    public static String SUCCESSFUL = "SUCCESSFUL";
    public static String SUCCESS = "SUCCESS";
    public static String ABANDONED = "ABANDONED";
    public static String WELCOME = "Welcome";
    public String responseMessage = FAILED;

    public static String FAILED_CODE = "99";
    public static String SUCCESSFUL_CODE = "00";
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
