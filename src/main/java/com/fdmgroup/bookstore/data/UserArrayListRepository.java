package com.fdmgroup.bookstore.data;

import java.util.List;

import com.fdmgroup.bookstore.model.User;

public class UserArrayListRepository implements UserRepository{
	private List<User> users;
	public static int id = 1;
	
	public UserArrayListRepository() {
		
	}
	
	public UserArrayListRepository(List<User> users) {
		this.users = users;
	}
	
	public static int generateId() {
		return ++id;
	}
	
	public User findById(int id) {
		for(User user: users) {
			if(user.getUserId() == id) return user;
		}
		
		return null;
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if (user.getUserId() == 0) {
			user.setUserId(generateId());
		}
		else if(findById(user.getUserId()) != null) {
			users.remove(findById(user.getUserId()));
		}
		
		users.add(user);
		
		return user;
	}
	
	public void delete(User user) {
		users.remove(user);
	}
	
	public boolean validate(String username, String password) {
		for(User user: users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) return true;
		}
		
		return false;
	}
	public User findByUsername(String username) {
		for(User user: users) {
			if(user.getUsername().equals(username)) return user;
		}
		
		return null;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		UserArrayListRepository.id = id;
	}
}
