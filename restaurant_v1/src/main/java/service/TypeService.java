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
		try {
			typeDao.openCurrentSessionwithTransaction();
			typeDao.persist(entity);
		}
		finally{
			typeDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(Type entity) {
    	try {
	    	typeDao.openCurrentSessionwithTransaction();
	    	typeDao.update(entity);
    	}
    	finally{
    		typeDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Type findById(Long id) {
    	Type type = null;
    	try {
	    	typeDao.openCurrentSession();
	    	type = typeDao.findByPrimaryKey(id);
    	}
    	finally{
    		typeDao.closeCurrentSession();
    	}
        return type;
    }
    public Type findByName(String name) {
    	Type type = null;
    	try {
	    	typeDao.openCurrentSession();
	    	type = typeDao.findByName(name);
    	}
    	finally{
    		typeDao.closeCurrentSession();
    	}
        return type;
    }
 
    public void delete(Long id) {
    	try {
	    	typeDao.openCurrentSessionwithTransaction();
	    	Type type = typeDao.findByPrimaryKey(id);
	    	typeDao.delete(type);
    	}
    	finally{
    		typeDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<Type> findAll() {
    	Set<Type> types = null;
    	try {
	    	typeDao.openCurrentSession();
	        types = typeDao.findAll();
    	}
    	finally{
    		typeDao.closeCurrentSession();
    	}
        return types;
    }
 
    public TypeDao typeDao() {
        return typeDao;
    }
}
