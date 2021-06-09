package service;

import java.util.List;
import java.util.Set;

import database.NoticeDao;
import model.Notice;

public class NoticeService {
	
	private static NoticeDao noticeDao;
	
	public NoticeService()
	{
		noticeDao = new NoticeDao();
	}
	
	public void persist(Notice entity) {
		try {
			noticeDao.openCurrentSessionwithTransaction();
			noticeDao.persist(entity);
		}
		finally{
			noticeDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(Notice entity) {
    	try {
	    	noticeDao.openCurrentSessionwithTransaction();
	    	noticeDao.update(entity);
    	}
    	finally{
    		noticeDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Notice findById(Long id) {
    	Notice notice = null;
    	try {
	    	noticeDao.openCurrentSession();
	    	notice = noticeDao.findByPrimaryKey(id);
    	}
    	finally{
    		noticeDao.closeCurrentSession();
    	}
        return notice;
    }
 
    public void delete(Long id) {
    	try {
	    	noticeDao.openCurrentSessionwithTransaction();
	    	Notice notice = noticeDao.findByPrimaryKey(id);
	    	noticeDao.delete(notice);
    	}
    	finally{
    		noticeDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<Notice> findAll() {
    	Set<Notice> notices = null;
    	try {
	    	noticeDao.openCurrentSession();
	        notices = noticeDao.findAll();
    	}
        finally{
        	noticeDao.closeCurrentSession();
        }
        return notices;
    }
 
    public NoticeDao noticeDao() {
        return noticeDao;
    }
}
