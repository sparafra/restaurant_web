package database;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import interface_database.IngredientDAOInterface;
import model.Ingredient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class IngredientDao implements IngredientDAOInterface {
	
	private EntityManager current_entityManager;
    private EntityTransaction current_transaction;
	
	
	public IngredientDao() {	}

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
    
	public void persist(Ingredient ingredient)
	{
		getCurrentSession().persist(ingredient);
	}
    public void update(Ingredient ingredient)
	{
    	Session session = getCurrentSession().unwrap(Session.class);

		session.update(ingredient);	}
    
	public void delete(Ingredient ingredient)
	{
		getCurrentSession().remove(ingredient);
	}
    
	public Ingredient findByPrimaryKey(Long id)
	{
		Ingredient ingredient =  (Ingredient) getCurrentSession().find(Ingredient.class, id);
		return ingredient;
	}
    @SuppressWarnings("unchecked")
	public Set<Ingredient> findAll()
	{
		Set<Ingredient> ingredients = new HashSet<Ingredient> (getCurrentSession().createQuery("from model.Ingredient", Ingredient.class).getResultList());

		return ingredients;
		
	}


}
