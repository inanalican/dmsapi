package com.project.dmsapi.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.dmsapi.entities.BaseClass;

@Repository
public interface JpaTemplateRepository<T extends BaseClass, IdType extends Serializable> extends JpaRepository<T, IdType>, JpaTemplateRepositoryCustom {

}
