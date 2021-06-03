package service;

import java.util.List;
import java.util.Set;

import database.TypeDao;
import model.Type;

public class TypeService {
	
	private static TypeDao typeDao;
	
	public TypeService()
	{
		typeDao = new TypeDao();
	}
	
	public void persist(Type entity) {
		typeDao.openCurrentSessionwithTransaction();
		typeDao.persist(entity);
		typeDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Type entity) {
    	typeDao.openCurrentSessionwithTransaction();
    	typeDao.update(entity);
    	typeDao.closeCurrentSessionwithTransaction();
    }
 
    public Type findById(Long id) {
    	typeDao.openCurrentSession();
    	Type type = typeDao.findByPrimaryKey(id);
    	typeDao.closeCurrentSession();
        return type;
    }
    public Type findByName(String name) {
    	typeDao.openCurrentSession();
    	Type type = typeDao.findByName(name);
    	typeDao.closeCurrentSession();
        return type;
    }
 
    public void delete(Long id) {
    	typeDao.openCurrentSessionwithTransaction();
    	Type type = typeDao.findByPrimaryKey(id);
    	typeDao.delete(type);
    	typeDao.closeCurrentSessionwithTransaction();
    }
 
    public Set<Type> findAll() {
    	typeDao.openCurrentSession();
        Set<Type> types = typeDao.findAll();
        typeDao.closeCurrentSession();
        return types;
    }
 
    public TypeDao typeDao() {
        return typeDao;
    }
}
