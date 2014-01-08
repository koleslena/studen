package ru.koleslena.studen.orm.dao;

import ru.koleslena.studen.orm.dto.User;

/**
 * @author koleslena
 *
 */
public interface UserRoleDao {

	public User authUser(String login, String security);

}
