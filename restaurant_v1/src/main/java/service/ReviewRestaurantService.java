package service;

import java.util.List;
import java.util.Set;

import database.ReviewRestaurantDao;
import model.ReviewRestaurant;

public class ReviewRestaurantService {
	
	private static ReviewRestaurantDao reviewrestaurantDao;
	
	public ReviewRestaurantService()
	{
		reviewrestaurantDao = new ReviewRestaurantDao();
	}
	
	public void persist(ReviewRestaurant entity) {
		try {
			reviewrestaurantDao.openCurrentSessionwithTransaction();
			reviewrestaurantDao.persist(entity);
		}
		finally{
			reviewrestaurantDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(ReviewRestaurant entity) {
    	try {
	    	reviewrestaurantDao.openCurrentSessionwithTransaction();
	    	reviewrestaurantDao.update(entity);
    	}
    	finally{
    		reviewrestaurantDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public ReviewRestaurant findById(Long id) {
    	ReviewRestaurant reviewrestaurant = null;
    	try {
	    	reviewrestaurantDao.openCurrentSession();
	    	reviewrestaurant = reviewrestaurantDao.findByPrimaryKey(id);
    	}
    	finally{
    		reviewrestaurantDao.closeCurrentSession();
    	}
        return reviewrestaurant;
    }
 
    public void delete(Long id) {
    	try {
	    	reviewrestaurantDao.openCurrentSessionwithTransaction();
	    	ReviewRestaurant reviewrestaurant = reviewrestaurantDao.findByPrimaryKey(id);
	    	reviewrestaurantDao.delete(reviewrestaurant);
    	}
    	finally{
    		reviewrestaurantDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<ReviewRestaurant> findAll() {
    	Set<ReviewRestaurant> reviewrestaurants = null;
    	try {
	    	reviewrestaurantDao.openCurrentSession();
	    	reviewrestaurants = reviewrestaurantDao.findAll();
    	}
    	finally{
    		reviewrestaurantDao.closeCurrentSession();
    	}
        return reviewrestaurants;
    }
 
    public ReviewRestaurantDao reviewrestaurantDao() {
        return reviewrestaurantDao;
    }
}
