package com.iekakmi.eshop_api.domainLayer.repositories;

import com.iekakmi.eshop_api.domainLayer.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>
{
	// findAll()    -> inherited from JpaRepository (returns List<Order>)
	// findById(id) -> inherited from JpaRepository (returns Optional<Order>)
	// save(entity) -> inherited from JpaRepository (insert or update)
	// deleteById() -> inherited from JpaRepository

	List<Order> findByUserId(int userId);

	List<Order> findByOrderDate(LocalDateTime orderDate);
}
