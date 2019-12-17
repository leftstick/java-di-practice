package com.example.services;

import java.util.List;

import com.example.entities.User;

public interface IViewService2 {

	public List<User> getUsersWithPhoneMarked(Integer top);

}