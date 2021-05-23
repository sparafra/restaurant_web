package interface_database;

import java.util.List;

import model.ReviewProduct;

public interface ReviewProductDAOInterface {
	public void persist(ReviewProduct review_product);  // Create
	public ReviewProduct findByPrimaryKey(Long id);     // Retrieve
	public List<ReviewProduct> findAll();       
	public void update(ReviewProduct review_product); //Update
	public void delete(ReviewProduct review_product); //Delete	
}
