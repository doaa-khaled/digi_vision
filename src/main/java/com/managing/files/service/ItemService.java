package com.managing.files.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managing.files.model.File;
import com.managing.files.model.Item;
import com.managing.files.model.PermissionGroup;
import com.managing.files.model.PermissionGroups;
import com.managing.files.model.Permissions;
import com.managing.files.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	PermissionGroupService permissionGroupService;
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	FileService fileService;

	public Item createSpace(String itemName, String userEmail) {

		Item item = new Item();
		item.setTypeI("Space");
		item.setName(itemName);
		PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupByName(PermissionGroups.ADMIN_SPACE.name());
		item.setPermissionGroup(permissionGroup);
		item = itemRepository.save(item);
		permissionService.createPermission(Permissions.EDIT_SPACE.toString(), userEmail, permissionGroup, item);
		permissionService.createPermission(Permissions.VIEW_SPACE.toString(), userEmail, permissionGroup, item);
		return item;
	}

	public Item createFolder(Integer parentId, String itemName, String userEmail) {
		
		List<String> permissionsOfUser = permissionService.getPermissionLevelsForUser(userEmail);
		if(!permissionsOfUser.contains(Permissions.EDIT_SPACE.toString()))
			return null;
		Item item = new Item();
		item.setTypeI("Folder");
		item.setName(itemName);
		item.setParentId(parentId);
		PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupByName(PermissionGroups.ADMIN_FOLDER.name());
		item.setPermissionGroup(permissionGroup);
		item = itemRepository.save(item);
		permissionService.createPermission(Permissions.EDIT_FOLDER.toString(), userEmail, permissionGroup, item);
		permissionService.createPermission(Permissions.VIEW_FOLDER.toString(), userEmail, permissionGroup, item);
		return item;
	}

	public File createFile(Integer parentId, String itemName, String userEmail, byte[] bs) {
		List<String> permissionsOfUser = permissionService.getPermissionLevelsForUser(userEmail);
		if(!permissionsOfUser.contains(Permissions.EDIT_FOLDER.toString()))
			return null;
		Item item = new Item();
		item.setTypeI("File");
		item.setName(itemName);
		item.setParentId(parentId);
		PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupByName(PermissionGroups.ADMIN_FILE.name());
		item.setPermissionGroup(permissionGroup);
		item = itemRepository.save(item);
		File file = fileService.createFile(item, bs);
		permissionService.createPermission(Permissions.EDIT_FILE.toString(), userEmail, permissionGroup, item);
		permissionService.createPermission(Permissions.VIEW_FILE.toString(), userEmail, permissionGroup, item);		
		return file;
	}

	public Item getFile(Integer fileId, String userEmail) {
		List<String> permissionsOfUser = permissionService.getPermissionLevelsForUser(userEmail);
		if(!permissionsOfUser.contains(Permissions.EDIT_FILE.toString()))
			return null;
		return itemRepository.getById((long)fileId);
	}
}
