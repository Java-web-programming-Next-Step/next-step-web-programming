package next.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import next.model.User;

import java.util.List;

public class UserDaoTest {
    UserDao userDao;

    @Before
    public void beforeEach() throws Exception {
        userDao = new UserDao();
    }

    @After
    public void afterEach() throws Exception {
        userDao.deleteAll();
    }

    @Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        userDao.insert(expected);

        User actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User("userId", "password", "name", "javajigi@email.com");
        userDao.insert(user);
        User expected = new User("userId", "password123", "name123", "javajigi123@email.com");
        userDao.update(expected);

        User actual = userDao.findByUserId(user.getUserId());
        assertEquals(expected, actual);
    }

    @Test
    public void findAll() throws Exception {
        User expected0 = new User("userId0", "password", "name", "javajigi@email.com");
        User expected1 = new User("userId1", "password", "name", "javajigi@email.com");
        User expected2 = new User("userId2", "password", "name", "javajigi@email.com");
        userDao.insert(expected0);
        userDao.insert(expected1);
        userDao.insert(expected2);

        List<User> actual = userDao.findAll();
        assertEquals(3, actual.size());
    }

    @Test
    public void deleteAll() throws Exception {
        User expected0 = new User("userId0", "password", "name", "javajigi@email.com");
        User expected1 = new User("userId1", "password", "name", "javajigi@email.com");
        User expected2 = new User("userId2", "password", "name", "javajigi@email.com");
        userDao.insert(expected0);
        userDao.insert(expected1);
        userDao.insert(expected2);

        List<User> actual = userDao.findAll();
        assertEquals(3, actual.size());
        userDao.deleteAll();
        actual = userDao.findAll();
        assertEquals(0, actual.size());
    }
}
