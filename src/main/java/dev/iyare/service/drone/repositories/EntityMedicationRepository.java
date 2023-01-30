package dev.iyare.service.drone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.iyare.service.drone.entities.EntityMedication;

public interface EntityMedicationRepository extends JpaRepository<EntityMedication, Long>
{

}
