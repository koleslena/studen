package ru.koleslena.studen.service;

import ru.koleslena.studen.orm.dto.Role;
import ru.koleslena.studen.orm.dto.User;

/**
 *  @author koleslena
 */
public interface UserService {

	public User authorizeUser(String login, String password);
	
	public void createUser(String login, String password, Role role);
}
