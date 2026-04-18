package com.iekakmi.eshop_api.apiLayer.controllers;

import com.iekakmi.eshop_api.dataAccessLayer.services.ProductCategoryService;
import com.iekakmi.eshop_api.dataAccessLayer.models.ProductCategoryDto;
import com.iekakmi.eshop_api.apiLayer.models.ResponseContainer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController
{
	private final ProductCategoryService productCategoryService;

	public ProductCategoryController(ProductCategoryService productCategoryService)
	{
		this.productCategoryService = productCategoryService;
	}

	@GetMapping
	public ResponseEntity<ResponseContainer<List<ProductCategoryDto>>> getProductCategories()
	{
		List<ProductCategoryDto> response = productCategoryService.getProductCategories();
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseContainer<ProductCategoryDto>> getProductCategoryById(@PathVariable Integer id)
	{
		ProductCategoryDto response = productCategoryService.getProductCategoryById(id);
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@PostMapping
	public ResponseEntity<ResponseContainer<Integer>> createProductCategory(@Valid @RequestBody ProductCategoryDto dto)
	{
		int id = productCategoryService.createProductCategory(dto);
		return new ResponseEntity<>(new ResponseContainer<>(id), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseContainer<ProductCategoryDto>> updateProductCategory(
			@PathVariable Integer id, @Valid @RequestBody ProductCategoryDto dto)
	{
		dto.setId(id);
		ProductCategoryDto updated = productCategoryService.updateProductCategory(dto);
		return ResponseEntity.ok(new ResponseContainer<>(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductCategory(@PathVariable Integer id)
	{
		productCategoryService.deleteProductCategory(id);
		return ResponseEntity.ok().build();
	}
}
