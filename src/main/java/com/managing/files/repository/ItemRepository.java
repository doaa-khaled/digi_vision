package com.managing.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managing.files.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
