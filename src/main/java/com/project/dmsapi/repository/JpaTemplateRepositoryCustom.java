package com.project.dmsapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.dmsapi.entities.DocFile;
import com.project.dmsapi.entities.FilesType;
import com.project.dmsapi.entities.User;

@Repository
public interface JpaTemplateRepositoryCustom {

	public List<DocFile> findAllFiles();
	public DocFile getFileById(int fileId);
	public FilesType getFilesTypeByName(String fileTypeName);
	public User findUserByUserName(String userName);
	public User findUserByUserNameAndPassword(String userName, String password);
	
}
