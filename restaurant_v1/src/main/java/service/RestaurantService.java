package service;

import java.util.List;
import java.util.Set;

import database.RestaurantDao;
import model.Restaurant;

public class RestaurantService {
	
	private static RestaurantDao restaurantDao;
	
	public RestaurantService()
	{
		restaurantDao = new RestaurantDao();
	}
	
	public void persist(Restaurant entity) {
		try {
			restaurantDao.openCurrentSessionwithTransaction();
			restaurantDao.persist(entity);
		}
		finally{
			restaurantDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(Restaurant entity) {
    	try {
	    	restaurantDao.openCurrentSessionwithTransaction();
	    	restaurantDao.update(entity);
    	}
    	finally{
    		restaurantDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Restaurant findById(Long id) {
    	
    	Restaurant restaurant = null;
    	try {
    		System.out.println(id);
	    	restaurantDao.openCurrentSession();
	    	System.out.println("APERTA SERVICE");
	    	restaurant = restaurantDao.findByPrimaryKey(id);
	    	System.out.println("APERTA SERVICE: " + restaurant.getJson().toString());
	    	if(restaurantDao == null)
		    	System.out.println("SERVICE NULL");
	    	else
		    	System.out.println("SERVICE NOT NULL");


    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage().toString());
    	}
    	finally{
    		restaurantDao.closeCurrentSession();
	    	System.out.println("CHIUSA SERVICE");

    	}
        return restaurant;
    }
    
    public Restaurant findByMail(String mail) {
    	
    	Restaurant restaurant = null;
    	try {
	    	restaurantDao.openCurrentSession();
	    	restaurant = restaurantDao.findByMail(mail);
    	}
    	finally{
    		restaurantDao.closeCurrentSession();
    	}
        return restaurant;
    }
 
    public void delete(Long id) {
    	
    	try {
	    	restaurantDao.openCurrentSessionwithTransaction();
	    	Restaurant restaurant = restaurantDao.findByPrimaryKey(id);
	    	restaurantDao.delete(restaurant);
    	}
    	finally{
    		restaurantDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<Restaurant> findAll() {
    	
    	Set<Restaurant> restaurants = null;
    	try {
	    	restaurantDao.openCurrentSession();
	    	restaurants = restaurantDao.findAll();
    	}
        finally{
        	restaurantDao.closeCurrentSession();
        }
        return restaurants;
    }
    
 
    public RestaurantDao restaurantDao() {
        return restaurantDao;
    }
}
