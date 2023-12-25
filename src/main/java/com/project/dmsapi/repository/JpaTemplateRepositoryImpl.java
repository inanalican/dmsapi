package com.project.dmsapi.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.dmsapi.entities.DocFile;
import com.project.dmsapi.entities.FilesType;
import com.project.dmsapi.entities.User;

import jakarta.persistence.Table;

@Repository
public class JpaTemplateRepositoryImpl implements JpaTemplateRepositoryCustom {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<DocFile> findAllFiles() {
		String tableName = DocFile.class.getDeclaredAnnotation(Table.class).name();
		String otherTableName = FilesType.class.getDeclaredAnnotation(Table.class).name();
		List<DocFile> files = jdbcTemplate.query("select "+tableName+".*, "+otherTableName+".file_type_name from " + tableName + " LEFT OUTER JOIN " + otherTableName + " ON files.files_type = files_type.id", new BeanPropertyRowMapper(DocFile.class));
		if(files.size() > 0)
			return files;
		return new ArrayList<>();
	}
	
	public DocFile getFileById(int fileId) {
		String tableName = DocFile.class.getDeclaredAnnotation(Table.class).name();
		List<DocFile> files = jdbcTemplate.query("select * from " + tableName + " where id = " + fileId, new BeanPropertyRowMapper(DocFile.class));
		if(files.size() > 0)
			return files.get(0);
		return null;
	}
	
	
	public FilesType getFilesTypeByName(String fileTypeName) {
		String tableName = FilesType.class.getDeclaredAnnotation(Table.class).name();
		List<FilesType> filesType = jdbcTemplate.query("select * from " + tableName + " where file_type_name = '" + fileTypeName + "'", new BeanPropertyRowMapper(FilesType.class));
		if(filesType.size() > 0)
			return filesType.get(0);
		return null;
	}
	
	
	public User findUserByUserName(String userName) {
		String tableName = User.class.getDeclaredAnnotation(Table.class).name();
		List<User> users = jdbcTemplate.query("select * from " + tableName + " where user_name = '" + userName + "'", new BeanPropertyRowMapper(User.class));
		if(users.size() > 0)
			return users.get(0);
		return null;
	}
	
	
	public User findUserByUserNameAndPassword(String userName, String password){
		String tableName = User.class.getDeclaredAnnotation(Table.class).name();
		List<User> users = jdbcTemplate.query("select * from " + tableName + " where user_name = '" + userName + "' AND password = '" + password + "'", new BeanPropertyRowMapper(User.class));
		if(users.size() > 0)
			return users.get(0);
		return null;
	}
		
}
