package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;

public interface UserRepository extends Searchable, Persistable, Removable{
	public abstract boolean validate(String username, String password);
	public abstract User findByUsername(String username);
}
