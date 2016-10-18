package com.shop.interfaces;

import java.util.List;

import com.shop.objects.User;

public interface IUserDAO {
	public List<User> getUsers();

	public User getUserById(User user);

	public User getUserByLogin(User user);

	public int updateUser(User user);

	public int deleteUser(User user);

	public int createUser(User user);
}
