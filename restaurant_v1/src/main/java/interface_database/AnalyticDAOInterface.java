package interface_database;

import java.util.List;

import model.Analytic;


public interface AnalyticDAOInterface {
	public void persist(Analytic analytic);  // Create
	public Analytic findByPrimaryKey(Long id);     // Retrieve
	public List<Analytic> findAll();       
	public void update(Analytic analytic); //Update
	public void delete(Analytic analytic); //Delete	
}
