package com.project.dmsapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BaseClass {
	@Id
	int id;
}
