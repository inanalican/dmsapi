package com.project.dmsapi.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dmsapi.entities.FilesType;
import com.project.dmsapi.repository.FilesRepository;
import com.project.dmsapi.repository.FilesTypeRepository;
import com.project.dmsapi.repository.JpaTemplateRepository;

@Service
public class FilesTypeService {
	@Autowired
	FilesTypeRepository filesTypeRepository;
	@Autowired
	JpaTemplateRepository jpaTemplateRepository;

    public List<FilesType> findAllFilesType(){
        return filesTypeRepository.findAll();
    }    
    public FilesType getFilesTypeByName(String fileTypeName) {
    	return jpaTemplateRepository.getFilesTypeByName(fileTypeName);
    }
    
}
