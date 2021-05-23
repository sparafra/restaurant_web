package interface_database;

import java.util.List;

import model.Order;

public interface OrderDAOInterface {
	public void persist(Order order);  // Create
	public Order findByPrimaryKey(Long id);     // Retrieve
	public List<Order> findAll();       
	public void update(Order order); //Update
	public void delete(Order order); //Delete	
}
