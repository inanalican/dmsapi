package com.project.dmsapi.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.project.dmsapi.entities.DocFile;
import com.project.dmsapi.entities.FilesType;
import com.project.dmsapi.helper.BaseHelper;
import com.project.dmsapi.servies.FilesService;
import com.project.dmsapi.servies.FilesTypeService;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;

@Controller
@RestController
@CrossOrigin
@RequestMapping(value = "/files")
public class FilesController {
	@Autowired
	FilesService filesService;
	@Autowired
	FilesTypeService filesTypeService;
	@Autowired
	BaseHelper baseHelper;
	
	@Value("${file.path}")
	private String filePath;

	@GetMapping("/get-all")
	public List<DocFile> getFiles() {
		return filesService.findAllFiles();
	}
	
	@GetMapping("/get-file/{fileId}")
	public byte[] getFileDocById(@PathVariable int fileId) {
		try {
			DocFile  file = filesService.getFileById(fileId);
			
			byte[] fileContent = Files.readAllBytes(Paths.get(file.getFilePath()));
			
			return fileContent;
			
		}catch(Exception ex) {
			return null;
		}
	}
	

	@PostMapping("/create")
	public ResponseEntity createFile(@RequestParam(value = "file") MultipartFile file, @RequestParam("fileName") String fileName){
		try {
			if(Float.valueOf(file.getSize()) > 5242880)
				return new ResponseEntity<>("UPLOAD LIMIT EXCEEDED!", HttpStatus.OK);
			String fileExtension = baseHelper.getExtensionFromFileName(file.getOriginalFilename());
			FilesType filesType = filesTypeService.getFilesTypeByName(fileExtension.toLowerCase());
			if(filesType == null)
				return new ResponseEntity<>("UNEXPECTED FILE TYPE!", HttpStatus.OK);
			String uuid = UUID.randomUUID().toString();
			String newFilePath = filePath + uuid+"."+fileExtension;
			Files.copy(file.getInputStream(), Paths.get(newFilePath), StandardCopyOption.REPLACE_EXISTING);
			DocFile fileToCreate = new DocFile(
					fileName, 
					filesType.getId(), 
					newFilePath, 
					Float.valueOf(file.getSize()));
			filesService.saveFile(fileToCreate);
			return new ResponseEntity<>("OK!", HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
		}
	}

	@PutMapping("/update")
	public ResponseEntity updateFile(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value="fileName", required=true) String fileName, @RequestParam(value="id", required=true) int id) {
		try {
			DocFile fileToUpdate = filesService.getFileById(id);
			if(fileToUpdate == null)
				return new ResponseEntity<>("FILE NOT FOUND!", HttpStatus.OK);
			if(file != null){
				String fileExtension = baseHelper.getExtensionFromFileName(file.getOriginalFilename());
				FilesType filesType = filesTypeService.getFilesTypeByName(fileExtension.toLowerCase());
				if(filesType == null)
					return new ResponseEntity<>("UNEXPECTED FILE TYPE!", HttpStatus.OK);
				String uuid = UUID.randomUUID().toString();
				String newFilePath = filePath + uuid+"."+fileExtension;
				Files.copy(file.getInputStream(), Paths.get(newFilePath), StandardCopyOption.REPLACE_EXISTING);
				fileToUpdate.setFilePath(newFilePath);
				fileToUpdate.setFilesType(filesType.getId());
			}
			if(StringUtils.isNotBlank(fileName))
				fileToUpdate.setFileName(fileName);
			filesService.saveFile(fileToUpdate);
			return new ResponseEntity<>("OK!", HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete/{fileId}")
	public ResponseEntity deleteFile(@PathVariable int fileId) {
		DocFile file = filesService.getFileById(fileId);

	    java.io.File myObj = new java.io.File(file.getFilePath()); 
	    if (myObj.delete()) { 
			filesService.deleteFiles(fileId);
			return new ResponseEntity<>("OK!", HttpStatus.OK);
	    } else {
			return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
	   } 
	}

}
