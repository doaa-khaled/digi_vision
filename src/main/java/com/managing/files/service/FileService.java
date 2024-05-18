package com.managing.files.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managing.files.model.File;
import com.managing.files.model.Item;
import com.managing.files.model.Permissions;
import com.managing.files.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	PermissionService permissionService;

	public File getFile(Integer fileId, String userEmail) {
		List<String> permissionsOfUser = permissionService.getPermissionLevelsForUser(userEmail);
		if(!permissionsOfUser.contains(Permissions.EDIT_SPACE.toString()) && !permissionsOfUser.contains(Permissions.EDIT_FOLDER.toString()))
			return null;
		return fileRepository.getById((long)fileId);
	}

	
	public File createFile(Item item, byte[] binary) {
		File file = new File();
		file.setBinaryD(binary);
		file.setItem(item);
		return fileRepository.save(file);
		
	}
	
	

}
