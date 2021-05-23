package database;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.ProductDAOInterface;
import model.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class ProductDao implements ProductDAOInterface {
	
	private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	public ProductDao() {	}

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
    
	public void persist(Product product)
	{
		getCurrentSession().persist(product);
	}
    public void update(Product product)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(product);
	}
    
	public void delete(Product product)
	{
		getCurrentSession().remove(product);
	}
    
	public Product findByPrimaryKey(Long id)
	{
		Product product =  (Product) getCurrentSession().find(Product.class, id);
		return product;
	}
    @SuppressWarnings("unchecked")
	public List<Product> findAll()
	{
		List<Product> products = (List<Product>) getCurrentSession().createQuery("from model.Product", Product.class).getResultList();

		return products;
	}

}
