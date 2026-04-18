package com.iekakmi.eshop_api.dataAccessLayer.services;
import com.iekakmi.eshop_api.domainLayer.repositories.ProductRepository;
import com.iekakmi.eshop_api.domainLayer.repositories.ProductCategoryRepository;
import com.iekakmi.eshop_api.dataAccessLayer.models.ProductDto;
import com.iekakmi.eshop_api.domainLayer.models.entities.*;
import com.iekakmi.eshop_api.dataAccessLayer.models.exceptions.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import java.util.List;
@Service
@Validated
public class ProductService
{
	private final ProductRepository productRepository;
	private final ProductCategoryRepository productCategoryRepository;
	public ProductService(ProductRepository productRepository,
						  ProductCategoryRepository productCategoryRepository)
	{
		this.productRepository = productRepository;
		this.productCategoryRepository = productCategoryRepository;
	}
	public List<ProductDto> getProducts()
	{
		return productRepository.findAll().stream()
				.map(c -> {
					ProductDto dto = new ProductDto();
					dto.setId(c.getId());
					dto.setTitle(c.getTitle());
					dto.setDescription(c.getDescription());
					dto.setPrice(c.getPrice());
					dto.setCategoryId(c.getCategory().getId());
					dto.setCategoryName(c.getCategory().getName());
					dto.setImageUrl(c.getImageUrl());
					return dto;
				})
				.collect(Collectors.toList());
	}
	public ProductDto getProductById(int id)
	{
		Product p = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product", id));
		ProductDto dto = new ProductDto();
		dto.setId(p.getId());
		dto.setTitle(p.getTitle());
		dto.setDescription(p.getDescription());
		dto.setPrice(p.getPrice());
		dto.setCategoryId(p.getCategory().getId());
		dto.setCategoryName(p.getCategory().getName());
		dto.setImageUrl(p.getImageUrl());
		return dto;
	}
	@Transactional
	public int createProduct(@Valid ProductDto p)
	{
		ProductCategory category = productCategoryRepository.findById(p.getCategoryId())
				.orElseThrow(() -> new EntityNotFoundException("ProductCategory", p.getCategoryId()));
		Product entity = new Product();
		entity.setTitle(p.getTitle());
		entity.setDescription(p.getDescription());
		entity.setPrice(p.getPrice());
		entity.setCategory(category);
		entity.setImageUrl(p.getImageUrl());
		Product saved = productRepository.save(entity);
		return saved.getId();
	}
	@Transactional
	public ProductDto updateProduct(@Valid ProductDto p)
	{
		Product entity = productRepository.findById(p.getId())
				.orElseThrow(() -> new EntityNotFoundException("Product", p.getId()));
		entity.setTitle(p.getTitle());
		entity.setDescription(p.getDescription());
		entity.setPrice(p.getPrice());
		entity.setImageUrl(p.getImageUrl());
		productRepository.save(entity);
		return p;
	}
	@Transactional
	public void deleteProduct(int id)
	{
		productRepository.deleteById(id);
	}
}