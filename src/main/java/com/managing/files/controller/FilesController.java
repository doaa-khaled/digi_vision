package com.managing.files.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.managing.files.model.File;
import com.managing.files.model.Item;
import com.managing.files.model.PermissionGroup;
import com.managing.files.service.FileService;
import com.managing.files.service.ItemService;
import com.managing.files.service.PermissionGroupService;

@RestController
public class FilesController {

	@Autowired
	PermissionGroupService permissionGroupService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	FileService fileService;
	
	@PostMapping("/initate-permission-groups")
	public ResponseEntity<List<PermissionGroup>> initiatePermissionGroups () {
		List<PermissionGroup> permissionGroups = permissionGroupService.setInitialPermissionGroups();
		return ResponseEntity.ok(permissionGroups);
	}
	
	@PostMapping("/stc-assessments/{itemName}/{userEmail}")
	public ResponseEntity<Item> createSpace (@PathVariable String itemName, @PathVariable String userEmail) {
		Item item = itemService.createSpace(itemName, userEmail);
		return ResponseEntity.ok(item);
	}
	
	@PostMapping("/stc-assessments/{parentId}/backend/{itemName}/{userEmail}")
	public ResponseEntity<Item> createFolder (@PathVariable Integer parentId, @PathVariable String itemName, @PathVariable String userEmail) {
		Item item = itemService.createFolder(parentId, itemName, userEmail);
		if(item == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		return ResponseEntity.ok(item);
	}
	
	@PostMapping("/stc-assessments/backend/{parentId}/{itemName}/{userEmail}")
	public ResponseEntity<File> createFile (@PathVariable Integer parentId, @PathVariable String itemName, @PathVariable String userEmail, @RequestBody MultipartFile passedFile) throws IOException {
		File file = itemService.createFile(parentId, itemName, userEmail, passedFile.getBytes());
		if(file == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		return ResponseEntity.ok(file);
	}
	
	@GetMapping("/get-file/{itemId}/{userEmail}")
	public ResponseEntity<Item> getFile (@PathVariable Integer itemId, @PathVariable String userEmail) {
		Item item = itemService.getFile(itemId, userEmail);
		if(item == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		return ResponseEntity.ok(item);
	}
	
	@GetMapping("/download-file/{itemId}/{userEmail}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Integer itemId, @PathVariable String userEmail) {
	    File file = fileService.getFile(itemId, userEmail);
		if(file == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getItem().getName() + "\"")
	            .body(file.getBinaryD());
	}

}
