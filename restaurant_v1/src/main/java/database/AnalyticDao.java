package database;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.AnalyticDAOInterface;
import model.Analytic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class AnalyticDao implements AnalyticDAOInterface{
	
	private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	public AnalyticDao() {	}

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
    
	public void persist(Analytic analytic)
	{
		getCurrentSession().persist(analytic);
	}
    public void update(Analytic analytic)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(analytic);	
	}
    
	public void delete(Analytic analytic)
	{
		getCurrentSession().remove(analytic);
	}
    
	public Analytic findByPrimaryKey(Long id)
	{
		Analytic analytic =  (Analytic) getCurrentSession().find(Analytic.class, id);
		return analytic;
	}
    @SuppressWarnings("unchecked")
	public List<Analytic> findAll()
	{
		List<Analytic> analytics = (List<Analytic>) getCurrentSession().createQuery("from model.Analytic", Analytic.class).getResultList();

		return analytics;
		
	}


}
