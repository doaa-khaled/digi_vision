package com.managing.files.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managing.files.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	public List<Permission> findByUserEmail(String userEmail);
}
