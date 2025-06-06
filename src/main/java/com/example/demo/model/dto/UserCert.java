package com.example.demo.model.dto;

import com.example.demo.model.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCert {
	private Integer userId;
	private String userName;
	private Role role;
	private Boolean emailVerified;
}
