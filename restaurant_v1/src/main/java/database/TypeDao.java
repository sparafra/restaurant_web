package database;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.TypeDAOInterface;
import model.Type;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class TypeDao implements TypeDAOInterface {
	
	private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	public TypeDao() {	}

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
    
	public void persist(Type type)
	{
		getCurrentSession().persist(type);
	}
    public void update(Type type)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(type);	
	}
    
	public void delete(Type type)
	{
		getCurrentSession().remove(type);
	}
    
	public Type findByPrimaryKey(Long id)
	{
		Type type =  (Type) getCurrentSession().find(Type.class, id);
		return type;
	}
	public Type findByName(String name)
	{
		List<Type> types =  getCurrentSession().createQuery("from Type where name='" + name +"'", Type.class).getResultList();
		if(types != null)
			return types.get(0);
		else
			return null;
	}
    @SuppressWarnings("unchecked")
	public Set<Type> findAll()
	{
		Set<Type> types = new HashSet<Type>( getCurrentSession().createQuery("from Type").getResultList());

		return types;
		
	}


}
