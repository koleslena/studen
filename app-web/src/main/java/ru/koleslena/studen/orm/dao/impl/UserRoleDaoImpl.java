package ru.koleslena.studen.orm.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.koleslena.studen.orm.dao.UserRoleDao;
import ru.koleslena.studen.orm.dto.User;

/**
 * @author koleslena
 *
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

    /**
     * Вспомогательный метод для получения текущей сессии
     *
     * @return
     */
    protected Session session() {
        return sessionFactory.getCurrentSession();
    }
    
	@Override
	@Transactional(readOnly=true)
	public User authUser(String login, String security) {
		return (User) session().createQuery("FROM User as u WHERE u.name like :name AND u.password like :pass ")
				.setParameter("name", login)
				.setParameter("pass", security).uniqueResult();
	}
}
