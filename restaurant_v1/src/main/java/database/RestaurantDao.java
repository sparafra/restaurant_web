package database;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import interface_database.RestaurantDAOInterface;
import model.Restaurant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class RestaurantDao implements RestaurantDAOInterface {
	    
    private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	public RestaurantDao() {	}

	public EntityManager openCurrentSession() {
		current_entityManager = getEntityManager();
        return current_entityManager;
    }
 
    public EntityManager openCurrentSessionwithTransaction() {
		current_entityManager = getEntityManager();
		current_transaction = current_entityManager.getTransaction();
		current_transaction.begin();
		
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
    
	public void persist(Restaurant restaurant)
	{
		getCurrentSession().persist(restaurant);
	}
    public void update(Restaurant restaurant)
	{
        Session session = getCurrentSession().unwrap(Session.class);

		session.update(restaurant);
	}
    
	public void delete(Restaurant restaurant)
	{
		getCurrentSession().remove(restaurant);
	}
    
	public Restaurant findByPrimaryKey(Long id)
	{
		Restaurant restaurant =  (Restaurant) getCurrentSession().find(Restaurant.class, id);
		return restaurant;
	}
    @SuppressWarnings("unchecked")
	public Set<Restaurant> findAll()
	{
    	HashSet<Restaurant> restaurants = new HashSet<Restaurant>(getCurrentSession().createQuery("FROM model.Restaurant", Restaurant.class).getResultList());

		return restaurants;
		
	}
    
    
   
}
