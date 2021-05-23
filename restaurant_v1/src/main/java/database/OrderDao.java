package database;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.OrderDAOInterface;
import model.Order;

import org.hibernate.Session;

 
public class OrderDao implements OrderDAOInterface {
	
	private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	public OrderDao() {	}

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
    
	public void persist(Order order)
	{
		getCurrentSession().persist(order);
	}
    public void update(Order order)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(order);
	}
    
	public void delete(Order order)
	{
		getCurrentSession().remove(order);
	}
    
	public Order findByPrimaryKey(Long id)
	{
		Order order =  (Order) getCurrentSession().find(Order.class, id);
		return order;
	}
	public List<Order> findByState(String state)
	{
		List<Order> orders = (List<Order>) getCurrentSession().createQuery("from model.Order where state = "+state, Order.class).getResultList();
		return orders;
	}
    @SuppressWarnings("unchecked")
	public List<Order> findAll()
	{
		List<Order> orders = (List<Order>) getCurrentSession().createQuery("from model.Order", Order.class).getResultList();

		return orders;
		
	}
    


}
