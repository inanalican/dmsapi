package com.project.dmsapi.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dmsapi.entities.DocFile;
import com.project.dmsapi.repository.FilesRepository;
import com.project.dmsapi.repository.JpaTemplateRepository;

@Service
public class FilesService {
	@Autowired
    FilesRepository filesRepository;
	@Autowired
	JpaTemplateRepository jpaTemplateRepository;

    public List<DocFile> findAllFiles(){
        return jpaTemplateRepository.findAllFiles();
    }
    public DocFile saveFile(DocFile file){
        return filesRepository.save(file);
    }
    public void deleteFiles(int fileId){
    	filesRepository.deleteById(fileId);
    }
    public DocFile getFileById(int fileId) {
    	return jpaTemplateRepository.getFileById(fileId);
    }
    
}
