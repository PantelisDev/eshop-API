package com.iekakmi.eshop_api;

import com.iekakmi.eshop_api.apiLayer.controllers.CityController;
import com.iekakmi.eshop_api.apiLayer.configurations.GlobalExceptionHandler;
import com.iekakmi.eshop_api.dataAccessLayer.models.CityDto;
import com.iekakmi.eshop_api.dataAccessLayer.models.exceptions.EntityNotFoundException;
import com.iekakmi.eshop_api.dataAccessLayer.services.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ApiLayerTests
{
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Mock
	private CityService cityService;

	@BeforeEach
	void setup()
	{
		CityController controller = new CityController(cityService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new GlobalExceptionHandler())
				.build();
	}

	@Test
	void getCities_returnsOkWithList() throws Exception
	{
		CityDto city = new CityDto();
		city.setId(1);
		city.setName("Athens");
		when(cityService.getCities()).thenReturn(List.of(city));

		mockMvc.perform(get("/cities"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].id").value(1))
				.andExpect(jsonPath("$.data[0].name").value("Athens"));
	}

	@Test
	void getCityById_returnsOkWithCity() throws Exception
	{
		CityDto city = new CityDto();
		city.setId(1);
		city.setName("Athens");
		when(cityService.getCityById(1)).thenReturn(city);

		mockMvc.perform(get("/cities/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(1))
				.andExpect(jsonPath("$.data.name").value("Athens"));
	}

	@Test
	void getCityById_notFound_returns404() throws Exception
	{
		when(cityService.getCityById(999)).thenThrow(new EntityNotFoundException("City", 999));

		mockMvc.perform(get("/cities/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("City not found with id: 999"));
	}

	@Test
	void createCity_returnsCreated() throws Exception
	{
		when(cityService.createCity(any(CityDto.class))).thenReturn(1);

		CityDto dto = new CityDto();
		dto.setName("NewCity");

		mockMvc.perform(post("/cities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data").value(1));
	}

	@Test
	void createCity_invalidBody_returnsBadRequest() throws Exception
	{
		CityDto dto = new CityDto();
		// name is null — should fail @NotNull validation

		mockMvc.perform(post("/cities")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").isNotEmpty());
	}

	@Test
	void deleteCity_returnsOk() throws Exception
	{
		doNothing().when(cityService).deleteCity(1);

		mockMvc.perform(delete("/cities/1"))
				.andExpect(status().isOk());
	}
}
