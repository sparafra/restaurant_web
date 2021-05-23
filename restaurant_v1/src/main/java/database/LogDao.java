package database;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.LogDAOInterface;
import model.Log;

import org.hibernate.Session;

 
public class LogDao implements LogDAOInterface {
	
	private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	public LogDao() {	}

	public EntityManager openCurrentSession() {
		current_entityManager = getEntityManager();
        return current_entityManager;
    }
 
    public EntityManager openCurrentSessionwithTransaction() {
		current_entityManager = getEntityManager();
		current_transaction = current_entityManager.getTransaction();
		
        return current_entityManager;
    }
     
    public void closeCurrentSession() {
    	current_entityManager.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
    	current_transaction.commit();
        current_entityManager.close();
    }
    
    public EntityManager getCurrentSession() {
        return current_entityManager;
    }
    
    public void setCurrentSession(EntityManager current_entityManager) {
        this.current_entityManager = current_entityManager;
    }
 
    public EntityTransaction getCurrentTransaction() {
        return current_transaction;
    }
 
    public void setCurrentTransaction(EntityTransaction current_transaction) {
        this.current_transaction = current_transaction;
    }
    
    private static EntityManager getEntityManager() {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("restaurant_v1")
                .createEntityManager();

        return entityManager;
    }
    
    
	public void persist(Log log)
	{
		getCurrentSession().persist(log);
	}
    public void update(Log log)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(log);	
	}
    
	public void delete(Log log)
	{
		getCurrentSession().remove(log);
	}
    
	public Log findByPrimaryKey(Long id)
	{
		Log log =  (Log) getCurrentSession().find(Log.class, id);
		return log;
	}
    @SuppressWarnings("unchecked")
	public List<Log> findAll()
	{
		List<Log> logs = (List<Log>) getCurrentSession().createQuery("from model.Log", Log.class).getResultList();

		return logs;
		
	}


}
