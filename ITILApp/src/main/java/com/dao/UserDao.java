package com.dao;

import java.util.List;

import com.model.User;

public interface UserDao {
	public void addUser(User user);
	public void editUser(User user);
	public void deleteUser(int userId);
	public User findUser(String userId);
	public User findUserByName(String username);
	public List<User> getAllUsers();
	public User getUserRoles(int userId);
	public User getUserDetails(String userId);
}
