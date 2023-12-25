package com.project.dmsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.dmsapi.entities.FilesType;

@Repository
public interface FilesTypeRepository extends JpaRepository<FilesType, Integer> {

}
