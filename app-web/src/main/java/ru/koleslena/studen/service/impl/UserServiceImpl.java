package ru.koleslena.studen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.apache.commons.codec.binary.Base64;

import ru.koleslena.studen.orm.dao.BaseDao;
import ru.koleslena.studen.orm.dao.UserRoleDao;
import ru.koleslena.studen.orm.dto.Role;
import ru.koleslena.studen.orm.dto.User;
import ru.koleslena.studen.service.UserService;

/**
 * @author koleslena
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRoleDao userRoleDao;
	
	@Autowired
    private BaseDao baseDao;
	
    @Override
    public User authorizeUser(String login, String password) {

    	String security = encodeString(password);
    	
        User user = userRoleDao.authUser(login, security);

        return user;
    }

    @Override
    public void createUser(String login, String password, Role role) {

    	String security = encodeString(password);
    	
    	User user = new User();
		user.setName(login);
		user.setPassword(security);
		user.setRole(role);
		
		baseDao.persist(user);
    }
    
    private String encodeString(String str) {
    	return Base64.encodeBase64String(
                DigestUtils.md5DigestAsHex(str.getBytes()).getBytes()
        ).trim();
    }
}
