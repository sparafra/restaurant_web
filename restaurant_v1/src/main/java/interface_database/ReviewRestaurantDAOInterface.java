package interface_database;

import java.util.Set;

import model.ReviewRestaurant;

public interface ReviewRestaurantDAOInterface {
	public void persist(ReviewRestaurant review_restaurant);  // Create
	public ReviewRestaurant findByPrimaryKey(Long id);     // Retrieve
	public Set<ReviewRestaurant> findAll();       
	public void update(ReviewRestaurant review_restaurant); //Update
	public void delete(ReviewRestaurant review_restaurant); //Delete	
}
