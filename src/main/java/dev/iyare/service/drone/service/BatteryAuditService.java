package dev.iyare.service.drone.service;

import dev.iyare.service.drone.repositories.EntityBatteryHistoryRepository;
import dev.iyare.service.drone.repositories.EntityDroneRepository;
import dev.iyare.service.drone.repositories.EntityMedicationRepository;

public class BatteryAuditService
{

	EntityDroneRepository entityDroneRepository;
	EntityBatteryHistoryRepository entityBatteryHistoryRepository;

	public BatteryAuditService(EntityDroneRepository entityDroneRepository,
			EntityBatteryHistoryRepository entityBatteryHistoryRepository)
	{
		this.entityDroneRepository = entityDroneRepository;
		this.entityBatteryHistoryRepository = entityBatteryHistoryRepository;
	}

	public static void startSyncService()
	{
		new Thread(() ->
		{
			while (true)
			{

			}

		}).start();
	}
}
