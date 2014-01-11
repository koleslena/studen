package ru.koleslena.studen.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ru.koleslena.studen.orm.dao.BaseDao;
import ru.koleslena.studen.orm.dao.UserRoleDao;
import ru.koleslena.studen.orm.dto.Role;
import ru.koleslena.studen.orm.dto.User;

/**
 *  @author koleslena
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:TestDaoApplicationContext.xml")
@Configurable
public class UserRoleDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(UserRoleDaoTest.class);

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private BaseDao baseDao;
	
	@Transactional
	@Test
	@Rollback(true)
	public void AuthorizationTest() {
		assertNotNull("BaseDao must be specified", baseDao);
		List<User> l = baseDao.findAll(User.class.getSimpleName());
		User user;
		
		logger.info("{}", l.size());
		
		if(l != null && l.size() > 0) {
			user = l.get(0);
		} else {
			Role role = new Role();
			role.setName("admin");
			role.setId(1);
			role.setSpringName(Role.STRING_ADMIN_ROLE_NAME);
			
			baseDao.persist(role);
			
			user = new User();
			user.setName("elen");
			user.setPassword("1");
			user.setRole(role);
			
			baseDao.persist(user);
		}
		
		assertNotNull("user is not found", user);
		
		assertNotNull("user is not authorized", userRoleDao.authUser(user.getName(), user.getPassword()));
	}
	
}
