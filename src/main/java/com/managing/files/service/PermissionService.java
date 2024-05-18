package com.managing.files.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managing.files.model.Item;
import com.managing.files.model.Permission;
import com.managing.files.model.PermissionGroup;
import com.managing.files.repository.PermissionGroupRepository;
import com.managing.files.repository.PermissionRepository;

@Service
public class PermissionService {
	
	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	PermissionGroupRepository permissionGroupRepository;
	
	
	public Permission createPermission(String level, String email, PermissionGroup permissionGroup, Item item) {
		Permission permission = new Permission();
		permission.setPermissionLevel(level);
		permission.setUserEmail(email);
		permission.setGroup(permissionGroup);
		permission.setItem(item);
		return permissionRepository.save(permission);
	}
	
	public List<String> getPermissionLevelsForUser (String email) {
		return permissionRepository.findByUserEmail(email).stream().map(e->e.getPermissionLevel()).collect(Collectors.toList());
	}

}
