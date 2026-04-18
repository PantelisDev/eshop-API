package com.iekakmi.eshop_api.dataAccessLayer.services;

import com.iekakmi.eshop_api.domainLayer.repositories.CityRepository;
import com.iekakmi.eshop_api.domainLayer.models.entities.City;
import com.iekakmi.eshop_api.dataAccessLayer.models.CityDto;
import com.iekakmi.eshop_api.dataAccessLayer.models.exceptions.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import java.util.*;

@Service
@Validated
public class CityService
{
	private final CityRepository cityRepository;

	public CityService(CityRepository cityRepository)
	{
		this.cityRepository = cityRepository;
	}

	public List<CityDto> getCities()
	{
		return cityRepository.findAll().stream()
				.map(c -> {
					CityDto dto = new CityDto();
					dto.setId(c.getId());
					dto.setName(c.getName());
					return dto;
				})
				.collect(Collectors.toList());
	}

	public CityDto getCityById(int id)
	{
		City c = cityRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("City", id));
		CityDto dto = new CityDto();
		dto.setId(c.getId());
		dto.setName(c.getName());
		return dto;
	}

	@Transactional
	public int createCity(@Valid CityDto c)
	{
		City entity = new City();
		entity.setName(c.getName());
		City saved = cityRepository.save(entity);
		return saved.getId();
	}

	@Transactional
	public CityDto updateCity(@Valid CityDto c)
	{
		City entity = cityRepository.findById(c.getId())
				.orElseThrow(() -> new EntityNotFoundException("City", c.getId()));
		entity.setName(c.getName());
		cityRepository.save(entity);
		return c;
	}

	@Transactional
	public void deleteCity(int id)
	{
		cityRepository.deleteById(id);
	}
}
