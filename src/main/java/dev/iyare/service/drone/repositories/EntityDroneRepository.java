package dev.iyare.service.drone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.iyare.service.drone.entities.EntityDrone;

public interface EntityDroneRepository extends JpaRepository<EntityDrone, Long>
{
	@Query(value = "SELECT * FROM \"drone\" WHERE \"serial_number\" = :serial_number AND \"model\" = :model AND \"state\" ='IDLE'", nativeQuery = true)
	EntityDrone findBySerialNoAndModel(String serial_number, String model);
}
