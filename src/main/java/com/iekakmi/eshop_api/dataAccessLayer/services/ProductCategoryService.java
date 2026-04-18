package com.iekakmi.eshop_api.dataAccessLayer.services;

import com.iekakmi.eshop_api.domainLayer.repositories.ProductCategoryRepository;
import com.iekakmi.eshop_api.dataAccessLayer.models.ProductCategoryDto;
import com.iekakmi.eshop_api.domainLayer.models.entities.ProductCategory;
import com.iekakmi.eshop_api.dataAccessLayer.models.exceptions.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import java.util.List;

@Service
@Validated
public class ProductCategoryService
{
	private final ProductCategoryRepository productCategoryRepository;

	public ProductCategoryService(ProductCategoryRepository productCategoryRepository)
	{
		this.productCategoryRepository = productCategoryRepository;
	}

	public List<ProductCategoryDto> getProductCategories()
	{
		return productCategoryRepository.findAll().stream()
				.map(c -> {
					ProductCategoryDto dto = new ProductCategoryDto();
					dto.setId(c.getId());
					dto.setName(c.getName());
					return dto;
				})
				.collect(Collectors.toList());
	}

	public ProductCategoryDto getProductCategoryById(int id)
	{
		ProductCategory p = productCategoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ProductCategory", id));
		ProductCategoryDto dto = new ProductCategoryDto();
		dto.setId(p.getId());
		dto.setName(p.getName());
		return dto;
	}

	@Transactional
	public int createProductCategory(@Valid ProductCategoryDto p)
	{
		ProductCategory entity = new ProductCategory();
		entity.setName(p.getName());
		ProductCategory saved = productCategoryRepository.save(entity);
		return saved.getId();
	}

	@Transactional
	public ProductCategoryDto updateProductCategory(@Valid ProductCategoryDto p)
	{
		ProductCategory entity = productCategoryRepository.findById(p.getId())
				.orElseThrow(() -> new EntityNotFoundException("ProductCategory", p.getId()));
		entity.setName(p.getName());
		productCategoryRepository.save(entity);
		return p;
	}

	@Transactional
	public void deleteProductCategory(int id)
	{
		productCategoryRepository.deleteById(id);
	}
}
