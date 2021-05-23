package interface_database;

import java.util.List;

import model.User;

public interface UserDAOInterface {
	public void persist(User user);  // Create
	public User findByPrimaryKey(String telephone);     // Retrieve
	public List<User> findAll();       
	public void update(User user); //Update
	public void delete(User user); //Delete	
}
