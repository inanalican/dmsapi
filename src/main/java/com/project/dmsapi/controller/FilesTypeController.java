package com.project.dmsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dmsapi.entities.FilesType;
import com.project.dmsapi.servies.FilesTypeService;

@Controller
@RestController
@CrossOrigin
@RequestMapping(value = "/files-type")
public class FilesTypeController {
	@Autowired
	FilesTypeService filesTypeService;
	
	@Value("${file.path}")
	private String filePath;

	@GetMapping("/get-all")
	public List<FilesType> getFiles() {
		return filesTypeService.findAllFilesType();
	}
	
	@GetMapping("/get/{fileTypeName}")
	public FilesType getFilesTypeByName(@PathVariable String fileTypeName) {
		return filesTypeService.getFilesTypeByName(fileTypeName);		
	}	
}
