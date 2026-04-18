package com.iekakmi.eshop_api.domainLayer.repositories;

import com.iekakmi.eshop_api.domainLayer.models.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>
{
	// findAll()    -> inherited from JpaRepository (returns List<OrderItem>)
	// findById(id) -> inherited from JpaRepository (returns Optional<OrderItem>)
	// save(entity) -> inherited from JpaRepository (insert or update)
	// deleteById() -> inherited from JpaRepository

	List<OrderItem> findByOrderId(int orderId);

	List<OrderItem> findByProductId(int productId);
}
