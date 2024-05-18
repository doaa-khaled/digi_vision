package com.managing.files.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managing.files.model.PermissionGroup;
import com.managing.files.model.PermissionGroups;
import com.managing.files.repository.PermissionGroupRepository;

@Service
public class PermissionGroupService {
	
	@Autowired
	PermissionGroupRepository permissionGroupRepository;
	
	public List<PermissionGroup> setInitialPermissionGroups() {
		List<PermissionGroup> permissionGroups = new LinkedList<>();
		PermissionGroup permissionGroup;
		for (PermissionGroups perGroup : PermissionGroups.values()) {
			permissionGroup = new PermissionGroup();
			permissionGroup.setGroupName(perGroup.name());
			permissionGroups.add(permissionGroupRepository.save(permissionGroup));
		}
		return permissionGroups;
	}
	
	public PermissionGroup getPermissionGroupByName(String name) {
		return permissionGroupRepository.findByGroupName(name);
	}

}
