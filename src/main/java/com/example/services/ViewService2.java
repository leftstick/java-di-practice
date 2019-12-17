package com.example.services;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.entities.User;
import com.example.services.atom.impls.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ViewService2 implements IViewService2 {

	private UserService userService = new UserService();

	@Override
	public List<User> getUsersWithPhoneMarked(Integer top) {

		List<User> users = userService.list();

		if (Objects.isNull(users) || users.isEmpty()) {
			return Collections.emptyList();
		}

		if (!Objects.isNull(top)) {
			users = users.subList(0, top > users.size() ? users.size() : top);
		}

		return users.stream().map(user -> {
			User u = new User();
			BeanUtils.copyProperties(user, u);
			if (!Objects.isNull(user.getMobile())) {
				u.setMobile(user.getMobile().replaceFirst("[0-9]{6}$", "******"));
			}
			return u;
		}).collect(Collectors.toList());

	}

}