package service;

import java.util.List;
import java.util.Set;

import database.UserDao;
import model.User;

public class UserService {
	
	private static UserDao userDao;
	
	public UserService()
	{
		userDao = new UserDao();
	}
	
	public void persist(User entity) {
		try {
			userDao.openCurrentSessionwithTransaction();
			userDao.persist(entity);
		}
		finally{
			userDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(User entity) {
    	try {
	    	userDao.openCurrentSessionwithTransaction();
	    	userDao.update(entity);
    	}
    	finally{
    		userDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public User findById(String id) {
    	User user = null;
    	try {
	    	userDao.openCurrentSession();
	    	user = userDao.findByPrimaryKey(id);
    	}
    	finally{
    		userDao.closeCurrentSession();
    	}
        return user;
    }
    public User findByMail(String mail) {
    	User user = null;
    	try {
    	userDao.openCurrentSession();
    	user = userDao.findByMail(mail);
    	}
    	finally{
    		userDao.closeCurrentSession();
    	}
        return user;
    }
 
    public void delete(String id) {
    	try {
	    	userDao.openCurrentSessionwithTransaction();
	    	User user = userDao.findByPrimaryKey(id);
	    	userDao.delete(user);
    	}
    	finally{
    		userDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<User> findAll() {
    	Set<User> users = null;
    	try {
	    	userDao.openCurrentSession();
	        users = userDao.findAll();
    	}
    	finally{
    		userDao.closeCurrentSession();
    	}
        return users;
    }
 
    public UserDao userDao() {
        return userDao;
    }
}
