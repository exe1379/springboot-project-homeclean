package com.example.demo.model.dto;

import com.example.demo.model.entity.Role;

import lombok.Data;
@Data
public class UserDto {
	private Integer userId;
	private String userName;
	private String name;
	private String email;
	private Role role;
	private String phoneNumber;
}
