package com.iekakmi.eshop_api.domainLayer.repositories;

import com.iekakmi.eshop_api.domainLayer.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
	// findAll()    -> inherited from JpaRepository (returns List<User>)
	// findById(id) -> inherited from JpaRepository (returns Optional<User>)
	// save(entity) -> inherited from JpaRepository (insert or update)
	// deleteById() -> inherited from JpaRepository

	Optional<User> findByUserName(String userName);

	List<User> findByCityId(int cityId);
}
