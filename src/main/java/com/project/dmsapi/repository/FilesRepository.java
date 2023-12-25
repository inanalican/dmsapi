package com.project.dmsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.dmsapi.entities.DocFile;

@Repository
public interface FilesRepository extends JpaRepository<DocFile, Integer> {

}
