package dev.iyare.service.drone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.iyare.service.drone.entities.EntityDrone;

public interface EntityDroneRepository extends JpaRepository<EntityDrone, Long>
{
	@Query(value = "SELECT * FROM \"drone\" WHERE \"serial_number\" = :serial_number AND \"model\" = :model AND \"state\" ='IDLE'  OR \"state\" ='LOADING'", nativeQuery = true)
	EntityDrone verifyDroneAvailable(String serial_number, String model);

	@Query(value = "SELECT * FROM \"drone\" WHERE \"state\" ='IDLE' OR \"state\" ='LOADING'", nativeQuery = true)
	List<EntityDrone> findAvailableDrones();

	@Query(value = "SELECT * FROM \"drone\" WHERE \"serial_number\" = :serial_number", nativeQuery = true)
	EntityDrone findBySerialNo(String serial_number);
}
