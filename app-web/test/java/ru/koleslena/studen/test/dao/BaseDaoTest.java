package ru.koleslena.studen.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ru.koleslena.studen.orm.dao.BaseDao;
import ru.koleslena.studen.orm.dto.Group;
import ru.koleslena.studen.orm.dto.Role;

/**
 * @author koleslena
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:TestDaoApplicationContext.xml")
@Configurable
@Transactional
public class BaseDaoTest {

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Before
	public void setUpInTransaction() {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Group");
		query.executeUpdate();
		
		query = session.createQuery("delete from Role");
		
		query.executeUpdate();
		
		//assertEquals("role deleting doesnot work", new Long(0), baseDao.count(Role.class.getSimpleName()));
	}
	
	@Test
	@Rollback(true)
	public void testAddEntities() {
		assertNotNull("BaseDao must be specified", baseDao);
		
		Role role = new Role();
		role.setName("admin");
		role.setId(1);
		role.setSpringName(Role.STRING_ADMIN_ROLE_NAME);
		
		baseDao.persist(role);
		
		assertEquals("role inserting doesnot work", new Long(1), baseDao.count(role.getClass().getSimpleName()));
		
		
		Group group = new Group();
		group.setCurator("admin");
		group.setGroupName("ad");;
		group.setSpeciality("sfgfh");
		
		baseDao.persist(group);
		
		assertEquals("role inserting doesnot work", new Long(1), baseDao.count(group.getClass().getSimpleName()));
	}
	
	@Test
	@Rollback(true)
	public void findAllTest() {
		assertNotNull("BaseDao must be specified", baseDao);
		
		Group group = new Group();
		group.setCurator("admin");
		group.setGroupName("ad");;
		group.setSpeciality("sfgfh");
		
		baseDao.persist(group);
		
		group = new Group();
		group.setCurator("admin");
		group.setGroupName("ad");;
		group.setSpeciality("sfgfh");
		
		baseDao.persist(group);
		
		List<Group> l = baseDao.findAll(Group.class.getSimpleName());
		
		assertNotNull("groups are not found", l);
		
		assertEquals("groups is not 2 in list", new Long(2), new Long(l.size()));
	}
}
