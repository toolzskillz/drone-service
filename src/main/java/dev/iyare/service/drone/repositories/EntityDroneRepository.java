package dev.iyare.service.drone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import dev.iyare.service.drone.entities.EntityDrone;

public interface EntityDroneRepository extends JpaRepository<EntityDrone, Long>
{
	@Query(value = "SELECT * FROM \"drone\" WHERE \"serial_number\" = :serial_number AND \"state\" ='IDLE' OR \"state\" ='LOADING'", nativeQuery = true)
	EntityDrone verifyDroneAvailable(@Param("serial_number") String serial_number);

	@Query(value = "SELECT * FROM \"drone\" WHERE \"state\" ='IDLE' OR \"state\" ='LOADING'", nativeQuery = true)
	List<EntityDrone> findAvailableDrones();

	@Query(value = "SELECT * FROM \"drone\" WHERE \"serial_number\" = :serial_number", nativeQuery = true)
	EntityDrone findBySerialNo(@Param("serial_number") String serial_number);

	@Transactional
	@Modifying
	@Query(value = "UPDATE \"drone\" SET \"state\" =: state WHERE \"serial_number\" = :serial_number", nativeQuery = true)
	void updateState(@Param("state") String state, @Param("serial_number") String serial_number);
}
