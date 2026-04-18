package com.iekakmi.eshop_api.domainLayer.repositories;

import com.iekakmi.eshop_api.domainLayer.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
	// findAll()    -> inherited from JpaRepository (returns List<Product>)
	// findById(id) -> inherited from JpaRepository (returns Optional<Product>)
	// save(entity) -> inherited from JpaRepository (insert or update)
	// deleteById() -> inherited from JpaRepository

	List<Product> findByTitle(String title);

	List<Product> findByCategoryId(int categoryId);
}
