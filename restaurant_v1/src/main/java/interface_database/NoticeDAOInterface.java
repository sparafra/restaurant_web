package interface_database;


import java.util.Set;

import model.Notice;

public interface NoticeDAOInterface {
	public void persist(Notice notice);  // Create
	public Notice findByPrimaryKey(Long id);     // Retrieve
	public Set<Notice> findAll();       
	public void update(Notice notice); //Update
	public void delete(Notice notice); //Delete	
}
