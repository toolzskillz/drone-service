package dev.iyare.service.drone.service;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.iyare.service.drone.entities.EntityBatteryHistory;
import dev.iyare.service.drone.entities.EntityDrone;
import dev.iyare.service.drone.repositories.EntityBatteryHistoryRepository;
import dev.iyare.service.drone.repositories.EntityDroneRepository;
import dev.iyare.service.drone.utils.SessionUtil;

public class BatteryAuditService
{
	private final static long sleepTime = 30000 * 1L; // Every 30seconds
	private static final Log logger = LogFactory.getLog(BatteryAuditService.class);

	@Autowired
	EntityDroneRepository entityDroneRepository;

	@Autowired
	EntityBatteryHistoryRepository entityBatteryHistoryRepository;

	Thread auditThread;

	public BatteryAuditService()
	{

	}

	public BatteryAuditService(EntityDroneRepository entityDroneRepository,
			EntityBatteryHistoryRepository entityBatteryHistoryRepository)
	{
		this.entityDroneRepository = entityDroneRepository;
		this.entityBatteryHistoryRepository = entityBatteryHistoryRepository;
	}

	public void startService()
	{
		auditThread = new Thread(() ->
		{
			while (true)
			{

				if (SessionUtil.getInstance().isAuditInProgress() == true)
				{
					continue;
				}

				try
				{
					if (SessionUtil.getInstance().isStopAudit() == false)
					{

						auditBatteries();

					} else
					{
						if (auditThread.isAlive())
							auditThread.interrupt();
						return;
					}

				} catch (Exception e)
				{
					e.printStackTrace();
					SessionUtil.getInstance().setAuditInProgress(false);
				}

				sleepAudit();
			}

		});
		auditThread.start();
	}

	private synchronized void auditBatteries() throws Exception
	{
		SessionUtil.getInstance().setAuditInProgress(true);

		List<EntityDrone> entityDroneList = entityDroneRepository.findAll();

		EntityBatteryHistory entityBatteryHistory;

		for (EntityDrone entityDrone : entityDroneList)
		{
			entityBatteryHistory = new EntityBatteryHistory();
			entityBatteryHistory.setBattery_capacity(entityDrone.getBattery_capacity());
			entityBatteryHistory.setSerial_number(entityDrone.getSerial_number());
			entityBatteryHistory.setDate_audited(new Date(System.currentTimeMillis()));
			entityBatteryHistoryRepository.save(entityBatteryHistory);
		}

		SessionUtil.getInstance().setAuditInProgress(false);
	}

	private void sleepAudit()
	{
		try
		{
			logger.info(":::::::::::::::::::::: Sleep Audit. . . ");
			auditThread.sleep(sleepTime);

		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
