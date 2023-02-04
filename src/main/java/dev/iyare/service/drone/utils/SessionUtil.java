package dev.iyare.service.drone.utils;

public class SessionUtil
{
	private static SessionUtil sessionUtil;

	public static SessionUtil getInstance()
	{
		if (sessionUtil == null)
		{
			synchronized (SessionUtil.class)
			{
				sessionUtil = new SessionUtil();
			}
		}
		return sessionUtil;
	}

	boolean auditInProgress = false;

	public void setAuditInProgress(boolean auditInProgress)
	{
		this.auditInProgress = auditInProgress;
	}

	public boolean isAuditInProgress()
	{
		return auditInProgress;
	}

	boolean stopAudit = false;

	public boolean isStopAudit()
	{
		return stopAudit;
	}

	public void setStopAudit(boolean stopAudit)
	{
		this.stopAudit = stopAudit;
	}

}
