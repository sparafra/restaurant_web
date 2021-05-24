package interface_database;


import java.util.Set;

import model.Ingredient;

public interface IngredientDAOInterface {
	public void persist(Ingredient ingredient);  // Create
	public Ingredient findByPrimaryKey(Long id);     // Retrieve
	public Set<Ingredient> findAll();       
	public void update(Ingredient ingredient); //Update
	public void delete(Ingredient ingredient); //Delete	
}
