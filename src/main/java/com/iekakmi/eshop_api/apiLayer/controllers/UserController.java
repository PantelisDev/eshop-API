package com.iekakmi.eshop_api.apiLayer.controllers;

import com.iekakmi.eshop_api.dataAccessLayer.services.UserService;
import com.iekakmi.eshop_api.apiLayer.models.ResponseContainer;
import com.iekakmi.eshop_api.dataAccessLayer.models.UserDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
	private final UserService userService;

	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<ResponseContainer<List<UserDto>>> getUsers()
	{
		List<UserDto> response = userService.getUsers();
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseContainer<UserDto>> getUserById(@PathVariable Integer id)
	{
		UserDto response = userService.getUserById(id);
		return ResponseEntity.ok(new ResponseContainer<>(response));
	}

	@PostMapping
	public ResponseEntity<ResponseContainer<Integer>> createUser(@Valid @RequestBody UserDto dto)
	{
		int id = userService.createUser(dto);
		return new ResponseEntity<>(new ResponseContainer<>(id), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseContainer<UserDto>> updateUser(
			@PathVariable Integer id, @Valid @RequestBody UserDto dto)
	{
		dto.setId(id);
		UserDto updated = userService.updateUser(dto);
		return ResponseEntity.ok(new ResponseContainer<>(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id)
	{
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}
