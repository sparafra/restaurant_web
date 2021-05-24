package interface_database;

import java.util.Set;

import model.Product;

public interface ProductDAOInterface {
	public void persist(Product product);  // Create
	public Product findByPrimaryKey(Long id);     // Retrieve
	public Set<Product> findAll();       
	public void update(Product product); //Update
	public void delete(Product product); //Delete	
}
