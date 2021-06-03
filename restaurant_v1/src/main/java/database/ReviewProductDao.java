package database;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.ReviewProductDAOInterface;
import model.ReviewProduct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class ReviewProductDao implements ReviewProductDAOInterface {
	
	private EntityManager current_entityManager;
	private EntityTransaction current_transaction;
		
	public ReviewProductDao() {	}

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
    
	public void persist(ReviewProduct review_product)
	{
		getCurrentSession().persist(review_product);
	}
    public void update(ReviewProduct review_product)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(review_product);	
	}
    
	public void delete(ReviewProduct review_product)
	{
		getCurrentSession().remove(review_product);
	}
    
	public ReviewProduct findByPrimaryKey(Long id)
	{
		ReviewProduct review_product =  (ReviewProduct) getCurrentSession().find(ReviewProduct.class, id);
		return review_product;
	}
    @SuppressWarnings("unchecked")
	public Set<ReviewProduct> findAll()
	{
		Set<ReviewProduct> review_products = new HashSet<ReviewProduct>( getCurrentSession().createQuery("from model.ReviewProduct", ReviewProduct.class).getResultList());

		return review_products;
		
	}


}
