package ru.koleslena.studen.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.util.DigestUtils;

import ru.koleslena.studen.orm.dao.BaseDao;
import ru.koleslena.studen.orm.dto.Role;
import ru.koleslena.studen.orm.dto.User;
import ru.koleslena.studen.service.UserService;

/**
 *  @author koleslena
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:TestServiceApplicationContext.xml")
@Configurable
public class UserServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BaseDao baseDao;
	
	@Transactional
	@Test
	@Rollback(true)
	public void authorizationTest() {
		assertNotNull("userService must be specified", userService);
		
		String pass = "1";
		
		Role role = new Role();
		role.setName("admin");
		role.setId(1);
		role.setSpringName(Role.STRING_ADMIN_ROLE_NAME);
		
		baseDao.persist(role);
		
		User user = new User();
		user.setName("elen");
		user.setPassword(encodeString(pass));
		user.setRole(role);
		
		baseDao.persist(user);
		
		assertNotNull("user is not found", user);
		
		assertNotNull("user is not authorized", userService.authorizeUser(user.getName(), pass));
	}
	
	@Transactional
	@Test
	@Rollback(true)
	public void createUserTest() {
		assertNotNull("userService must be specified", userService);
		
		String pass = "1";
		//String login = "elen";
		
		Role role = new Role();
		role.setName("admin");
		role.setId(1);
		role.setSpringName(Role.STRING_ADMIN_ROLE_NAME);
		
		baseDao.persist(role);
		
		userService.createUser("elen", pass, role);
		
		List<User> l = baseDao.findAll(User.class.getSimpleName());
		
		assertNotNull("users are not found", l);
		
		assertEquals("user is not created", new Long(1), new Long(l.size()));
	}
	
	private String encodeString(String str) {
    	return Base64.encodeBase64String(
                DigestUtils.md5DigestAsHex(str.getBytes()).getBytes()
        ).trim();
    }
	
}
