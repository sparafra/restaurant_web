package interface_database;


import java.util.Set;

import model.Type;

public interface TypeDAOInterface {
	public void persist(Type type);  // Create
	public Type findByPrimaryKey(Long id);     // Retrieve
	public Set<Type> findAll();       
	public void update(Type type); //Update
	public void delete(Type type); //Delete	
}
