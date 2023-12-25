package com.project.dmsapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "files_type")
@Data
public class FilesType {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="file_type_name")
	private String fileTypeName;

	/**
	 * @param id
	 * @param fileTypeName
	 */
	public FilesType(int id, String fileTypeName) {
		super();
		this.id = id;
		this.fileTypeName = fileTypeName;
	}

	/**
	 * 
	 */
	public FilesType() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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