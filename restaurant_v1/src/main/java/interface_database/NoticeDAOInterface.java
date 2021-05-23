package interface_database;

import java.util.List;

import model.Notice;

public interface NoticeDAOInterface {
	public void persist(Notice notice);  // Create
	public Notice findByPrimaryKey(Long id);     // Retrieve
	public List<Notice> findAll();       
	public void update(Notice notice); //Update
	public void delete(Notice notice); //Delete	
}
