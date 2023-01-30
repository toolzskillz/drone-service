package dev.iyare.service.drone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.iyare.service.drone.entities.EntityDrone;

public interface EntityDroneRepository extends JpaRepository<EntityDrone, Long>
{

}
