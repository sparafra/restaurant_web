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
		analyticDao.openCurrentSessionwithTransaction();
		analyticDao.persist(entity);
		analyticDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Analytic entity) {
    	analyticDao.openCurrentSessionwithTransaction();
    	analyticDao.update(entity);
    	analyticDao.closeCurrentSessionwithTransaction();
    }
 
    public Analytic findById(Long id) {
    	analyticDao.openCurrentSession();
    	Analytic analytic = analyticDao.findByPrimaryKey(id);
    	analyticDao.closeCurrentSession();
        return analytic;
    }
 
    public void delete(Long id) {
    	analyticDao.openCurrentSessionwithTransaction();
    	Analytic analytic = analyticDao.findByPrimaryKey(id);
    	analyticDao.delete(analytic);
    	analyticDao.closeCurrentSessionwithTransaction();
    }
 
    public Set<Analytic> findAll() {
    	analyticDao.openCurrentSession();
        Set<Analytic> analytics = analyticDao.findAll();
        analyticDao.closeCurrentSession();
        return analytics;
    }
 
    public AnalyticDao analyticDao() {
        return analyticDao;
    }
}
