package com.iekakmi.eshop_api.apiLayer.controllers;

import com.iekakmi.eshop_api.dataAccessLayer.services.CityService;
import com.iekakmi.eshop_api.apiLayer.models.ResponseContainer;
import com.iekakmi.eshop_api.dataAccessLayer.models.CityDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController
{
	private final CityService cityService;

	public CityController(CityService cityService)
	{
		this.cityService = cityService;
	}

	@GetMapping
	public ResponseEntity<ResponseContainer<List<CityDto>>> getCities()
	{
		List<CityDto> response = cityService.getCities();
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseContainer<CityDto>> getCityById(@PathVariable Integer id)
	{
		CityDto response = cityService.getCityById(id);
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@PostMapping
	public ResponseEntity<ResponseContainer<Integer>> createCity(@Valid @RequestBody CityDto dto)
	{
		int id = cityService.createCity(dto);
		return new ResponseEntity<>(new ResponseContainer<>(id), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseContainer<CityDto>> updateCity(
			@PathVariable Integer id, @Valid @RequestBody CityDto dto)
	{
		dto.setId(id);
		CityDto updated = cityService.updateCity(dto);
		return ResponseEntity.ok(new ResponseContainer<>(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCity(@PathVariable Integer id)
	{
		cityService.deleteCity(id);
		return ResponseEntity.ok().build();
	}
}
