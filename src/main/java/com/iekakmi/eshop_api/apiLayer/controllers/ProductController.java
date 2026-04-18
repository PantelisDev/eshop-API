package com.iekakmi.eshop_api.apiLayer.controllers;

import com.iekakmi.eshop_api.dataAccessLayer.services.ProductService;
import com.iekakmi.eshop_api.apiLayer.models.ResponseContainer;
import com.iekakmi.eshop_api.dataAccessLayer.models.ProductDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
	private final ProductService productService;

	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<ResponseContainer<List<ProductDto>>> getProducts()
	{
		List<ProductDto> response = productService.getProducts();
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseContainer<ProductDto>> getProductById(@PathVariable Integer id)
	{
		ProductDto response = productService.getProductById(id);
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@PostMapping
	public ResponseEntity<ResponseContainer<Integer>> createProduct(@Valid @RequestBody ProductDto dto)
	{
		int id = productService.createProduct(dto);
		return new ResponseEntity<>(new ResponseContainer<>(id), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseContainer<ProductDto>> updateProduct(
			@PathVariable Integer id, @Valid @RequestBody ProductDto dto)
	{
		dto.setId(id);
		ProductDto updated = productService.updateProduct(dto);
		return ResponseEntity.ok(new ResponseContainer<>(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer id)
	{
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}
}
