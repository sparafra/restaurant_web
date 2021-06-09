package service;

import java.util.List;
import java.util.Set;

import database.LogDao;
import model.Log;

public class LogService {
	
	private static LogDao logDao;
	
	public LogService()
	{
		logDao = new LogDao();
	}
	
	public void persist(Log entity) {
		try {
			logDao.openCurrentSessionwithTransaction();
			logDao.persist(entity);
		}
		finally{
			logDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(Log entity) {
    	try {
	    	logDao.openCurrentSessionwithTransaction();
	    	logDao.update(entity);
    	}
    	finally{
    		logDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Log findById(Long id) {
    	Log log = null;
    	try {
	    	logDao.openCurrentSession();
	    	log = logDao.findByPrimaryKey(id);
    	}
    	finally{
    		logDao.closeCurrentSession();
    	}
        return log;
    }
 
    public void delete(Long id) {
    	try {
    		logDao.openCurrentSessionwithTransaction();
    		Log log = logDao.findByPrimaryKey(id);
    		logDao.delete(log);
    	}
    	finally{
    		logDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<Log> findAll() {
    	Set<Log> logs = null;
    	try {
	    	logDao.openCurrentSession();
	        logs = logDao.findAll();
    	}
    	finally{
    		logDao.closeCurrentSession();
    	}
        return logs;
    }
 
    public LogDao logDao() {
        return logDao;
    }
}
