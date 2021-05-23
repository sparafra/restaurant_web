package interface_database;

import java.util.Set;

import model.Restaurant;

public interface RestaurantDAOInterface {
	public void persist(Restaurant restaurant);  // Create
	public Restaurant findByPrimaryKey(Long id);     // Retrieve
	public Set<Restaurant> findAll();       
	public void update(Restaurant restaurant); //Update
	public void delete(Restaurant restaurant); //Delete		
}
