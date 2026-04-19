package com.iekakmi.eshop_api.dataAccessLayer.services;
import com.iekakmi.eshop_api.domainLayer.repositories.UserRepository;
import com.iekakmi.eshop_api.domainLayer.models.entities.User;
import com.iekakmi.eshop_api.dataAccessLayer.models.UserDto;
import com.iekakmi.eshop_api.dataAccessLayer.models.exceptions.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import java.util.List;
@Service
@Validated
public class UserService
{
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	public List<UserDto> getUsers()
	{
		return userRepository.findAll().stream()
				.map(u -> {
					UserDto dto = new UserDto();
					dto.setId(u.getId());
					dto.setFirstName(u.getFirstName());
					dto.setLastName(u.getLastName());
					dto.setCityId(u.getCityId());
					dto.setAddress(u.getAddress());
					dto.setPhoneNo(u.getPhoneNo());
					dto.setUserName(u.getUserName());
					dto.setPassWord(u.getPassWord());
					return dto;
				})
				.collect(Collectors.toList());
	}
	public UserDto getUserById(int id)
	{
		User u = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User", id));
		UserDto dto = new UserDto();
		dto.setId(u.getId());
		dto.setFirstName(u.getFirstName());
		dto.setLastName(u.getLastName());
		dto.setCityId(u.getCityId());
		dto.setAddress(u.getAddress());
		dto.setPhoneNo(u.getPhoneNo());
		dto.setUserName(u.getUserName());
		dto.setPassWord(u.getPassWord());
		return dto;
	}
	@Transactional
	public int createUser(@Valid UserDto u)
	{
		User entity = new User();
		entity.setFirstName(u.getFirstName());
		entity.setLastName(u.getLastName());
		entity.setCityId(u.getCityId());
		entity.setAddress(u.getAddress());
		entity.setPhoneNo(u.getPhoneNo());
		entity.setUserName(u.getUserName());
		entity.setPassWord(u.getPassWord());
		User saved = userRepository.save(entity);
		return saved.getId();
	}
	@Transactional
	public UserDto updateUser(@Valid UserDto u)
	{
		User entity = userRepository.findById(u.getId())
				.orElseThrow(() -> new EntityNotFoundException("User", u.getId()));
		entity.setFirstName(u.getFirstName());
		entity.setLastName(u.getLastName());
		entity.setCityId(u.getCityId());
		entity.setAddress(u.getAddress());
		entity.setPhoneNo(u.getPhoneNo());
		entity.setUserName(u.getUserName());
		userRepository.save(entity);
		return u;
	}
	@Transactional
	public void deleteUser(int id)
	{
		userRepository.deleteById(id);
	}
}