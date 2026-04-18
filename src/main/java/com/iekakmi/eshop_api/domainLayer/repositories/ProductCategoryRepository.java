package com.iekakmi.eshop_api.domainLayer.repositories;

import com.iekakmi.eshop_api.domainLayer.models.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>
{
	// findAll()    -> inherited from JpaRepository (returns List<ProductCategory>)
	// findById(id) -> inherited from JpaRepository (returns Optional<ProductCategory>)
	// save(entity) -> inherited from JpaRepository (insert or update)
	// deleteById() -> inherited from JpaRepository

	List<ProductCategory> findByName(String name);
}
