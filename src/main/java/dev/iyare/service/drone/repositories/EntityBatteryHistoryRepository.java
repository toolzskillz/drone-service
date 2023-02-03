package dev.iyare.service.drone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.iyare.service.drone.entities.EntityBatteryHistory;

public interface EntityBatteryHistoryRepository extends JpaRepository<EntityBatteryHistory, Long>
{

}
