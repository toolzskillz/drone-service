package dev.iyare.service.drone.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.iyare.service.drone.entities.EntityMedication;

public interface EntityMedicationRepository extends JpaRepository<EntityMedication, Long>
{
	
	@Query(value = "SELECT * FROM \"medication\" WHERE \"drone_serial_number\" = :serial_number", nativeQuery = true)
	List<EntityMedication> findByDroneSerialNo(@Param("serial_number") String serial_number);

}
