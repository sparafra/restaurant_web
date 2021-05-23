package database;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.ReviewRestaurantDAOInterface;
import model.ReviewRestaurant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class ReviewRestaurantDao implements ReviewRestaurantDAOInterface {
	
	private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	public ReviewRestaurantDao() {	}

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
    
	public void persist(ReviewRestaurant review_restaurant)
	{
		getCurrentSession().persist(review_restaurant);
	}
    public void update(ReviewRestaurant review_restaurant)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(review_restaurant);
	}
    
	public void delete(ReviewRestaurant review_restaurant)
	{
		getCurrentSession().remove(review_restaurant);
	}
    
	public ReviewRestaurant findByPrimaryKey(Long id)
	{
		ReviewRestaurant review_restaurant =  (ReviewRestaurant) getCurrentSession().find(ReviewRestaurant.class, id);
		return review_restaurant;
	}
    @SuppressWarnings("unchecked")
	public List<ReviewRestaurant> findAll()
	{
		List<ReviewRestaurant> review_restaurants = (List<ReviewRestaurant>) getCurrentSession().createQuery("from model.ReviewRestaurant", ReviewRestaurant.class).getResultList();

		return review_restaurants;
		
	}


}
