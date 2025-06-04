package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PasswordInvalidException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertService;
import com.example.demo.util.Hash;
@Service
public class CertServiceImpl implements CertService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserCert getCert(String userName, String password) throws UserNotFoundException, PasswordInvalidException {
		User user = userRepository.getUser(userName);
		if(user.getUserName() == null) {
			throw new UserNotFoundException("使用者名稱不存在");
		}
		String hashPassword = Hash.getHash(password, user.getSalt());
		if(hashPassword != user.getPasswordHash()) {
			throw new PasswordInvalidException("密碼輸入錯誤");
		}
		UserCert userCert = new UserCert(user.getUserId(), user.getUserName(), user.getRole());
		return userCert;
	}

}
