package com.iekakmi.eshop_api.dataAccessLayer.models.exceptions;

public class EntityNotFoundException extends RuntimeException
{
	public EntityNotFoundException(String entityName, int id)
	{
		super(entityName + " not found with id: " + id);
	}
}
