package com.fdmgroup.bookstore.data;

import java.util.List;

import com.fdmgroup.bookstore.model.User;

public interface Searchable {
	public abstract User findById(int id);
	public abstract List<User> findAll();
}
