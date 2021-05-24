package interface_database;

import java.util.Set;

import model.Order;

public interface OrderDAOInterface {
	public void persist(Order order);  // Create
	public Order findByPrimaryKey(Long id);     // Retrieve
	public Set<Order> findAll();       
	public void update(Order order); //Update
	public void delete(Order order); //Delete	
}
