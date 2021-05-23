package database;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.UserDAOInterface;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class UserDao implements UserDAOInterface {
	
	private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	public UserDao() {	}

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
    
	public void persist(User user)
	{
		getCurrentSession().persist(user);
	}
    public void update(User user)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(user);	
	}
    
	public void delete(User user)
	{
		getCurrentSession().remove(user);
	}
    
	public User findByPrimaryKey(String telephone)
	{
		User user =  (User) getCurrentSession().find(User.class, telephone);
		return user;
	}
    @SuppressWarnings("unchecked")
	public List<User> findAll()
	{
		List<User> users = (List<User>) getCurrentSession().createQuery("from model.User").getResultList();

		return users;
		
	}
    public User findByMail(String mail)
	{
		User user =  (User) getCurrentSession().createQuery("from User where mail=" + mail).getResultList().get(0);
		return user;
	}

}
