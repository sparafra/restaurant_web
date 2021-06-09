package service;

import java.util.List;
import java.util.Set;

import database.AnalyticDao;
import model.Analytic;

public class AnalyticService {
	
	private static AnalyticDao analyticDao;
	
	public AnalyticService()
	{
		analyticDao = new AnalyticDao();
	}
	
	public void persist(Analytic entity) {
		try {
			analyticDao.openCurrentSessionwithTransaction();
			analyticDao.persist(entity);
		}
		finally {
			analyticDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(Analytic entity) {
    	try {
	    	analyticDao.openCurrentSessionwithTransaction();
	    	analyticDao.update(entity);
    	}
    	finally{
    		analyticDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Analytic findById(Long id) {
    	Analytic analytic = null;
    	try {
	    	analyticDao.openCurrentSession();
	    	analytic = analyticDao.findByPrimaryKey(id);
    	}
    	finally{
    		analyticDao.closeCurrentSession();
    	}
        return analytic;
    }
 
    public void delete(Long id) {
    	try {
	    	analyticDao.openCurrentSessionwithTransaction();
	    	Analytic analytic = analyticDao.findByPrimaryKey(id);
	    	analyticDao.delete(analytic);
    	}
    	finally{
    		analyticDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<Analytic> findAll() {
    	Set<Analytic> analytics = null;
    	try {
	    	analyticDao.openCurrentSession();
	        analytics = analyticDao.findAll();
    	}
        finally{
        	analyticDao.closeCurrentSession();
        }
        return analytics;
    }
 
    public AnalyticDao analyticDao() {
        return analyticDao;
    }
}
