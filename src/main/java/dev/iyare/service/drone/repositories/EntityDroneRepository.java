package dev.iyare.service.drone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.iyare.service.drone.entities.EntityDrone;

public interface EntityDroneRepository extends JpaRepository<EntityDrone, Long>
{
	@Query(value = "SELECT * FROM \"drone\" WHERE \"serial_number\" = :serial_number AND \"model\" = :model AND \"state\" ='IDLE'  OR \"state\" ='LOADING'", nativeQuery = true)
	EntityDrone verifyDroneAvailable(@Param("serial_number") String serial_number, @Param("model") String model);

	@Query(value = "SELECT * FROM \"drone\" WHERE \"state\" ='IDLE' OR \"state\" ='LOADING'", nativeQuery = true)
	List<EntityDrone> findAvailableDrones();

	@Query(value = "SELECT * FROM \"drone\" WHERE \"serial_number\" = :serial_number", nativeQuery = true)
	EntityDrone findBySerialNo(@Param("serial_number") String serial_number);
}
