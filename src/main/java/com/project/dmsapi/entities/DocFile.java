package com.project.dmsapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name="files")
@Data
public class DocFile {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="file_name")
	private String fileName;
	@Column(name="files_type")
	private int filesType;
	@Column(name="file_path")
	private String filePath;
	@Column(name="file_size")
	private float fileSize;
	
	@Transient
	@Column(name = "file_type_name")
	private String fileTypeName;
	
	/**
	 * @param id
	 * @param fileName
	 * @param filesType
	 * @param filePath
	 * @param fileSize
	 */
	public DocFile(int id, String fileName, int filesType, String filePath, float fileSize) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filesType = filesType;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	
	/**
	 * @param fileName
	 * @param filesType
	 * @param filePath
	 * @param fileSize
	 */
	public DocFile(String fileName, int filesType, String filePath, float fileSize) {
		super();
		this.fileName = fileName;
		this.filesType = filesType;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}
	/**
	 * 
	 */
	public DocFile() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFilesType() {
		return filesType;
	}
	public void setFilesType(int filesType) {
		this.filesType = filesType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public float getFileSize() {
		return fileSize;
	}
	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}


	/**
	 * @return the fileTypeName
	 */
	public String getFileTypeName() {
		return fileTypeName;
	}


	/**
	 * @param fileTypeName the fileTypeName to set
	 */
	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}
	
	
	
	
}