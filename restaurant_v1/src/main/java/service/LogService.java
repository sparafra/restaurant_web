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
		logDao.openCurrentSessionwithTransaction();
		logDao.persist(entity);
		logDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Log entity) {
    	logDao.openCurrentSessionwithTransaction();
    	logDao.update(entity);
    	logDao.closeCurrentSessionwithTransaction();
    }
 
    public Log findById(Long id) {
    	logDao.openCurrentSession();
    	Log log = logDao.findByPrimaryKey(id);
    	logDao.closeCurrentSession();
        return log;
    }
 
    public void delete(Long id) {
    	logDao.openCurrentSessionwithTransaction();
    	Log log = logDao.findByPrimaryKey(id);
    	logDao.delete(log);
    	logDao.closeCurrentSessionwithTransaction();
    }
 
    public Set<Log> findAll() {
    	logDao.openCurrentSession();
        Set<Log> logs = logDao.findAll();
        logDao.closeCurrentSession();
        return logs;
    }
 
    public LogDao logDao() {
        return logDao;
    }
}
