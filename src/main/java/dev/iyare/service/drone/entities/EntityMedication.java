package dev.iyare.service.drone.entities;

import javax.persistence.Entity;

@Entity(name = "medication")
public class EntityMedication extends AbstractEntity
{

	String name;// (allowed only letters,numbers,‘-‘,‘_’);
	String weight;
	String code;// (allowed only upper case letters, underscore and numbers);
	String image;// (picture of the medication case).

}
