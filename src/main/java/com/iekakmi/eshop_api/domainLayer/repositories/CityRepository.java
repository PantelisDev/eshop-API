package com.iekakmi.eshop_api.domainLayer.repositories;

import com.iekakmi.eshop_api.domainLayer.models.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer>
{
	// findAll()    -> inherited from JpaRepository (returns List<City>)
	// findById(id) -> inherited from JpaRepository (returns Optional<City>)
	// save(entity) -> inherited from JpaRepository (insert or update)
	// deleteById() -> inherited from JpaRepository

	List<City> findByName(String name);
}
