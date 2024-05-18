package com.managing.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managing.files.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
