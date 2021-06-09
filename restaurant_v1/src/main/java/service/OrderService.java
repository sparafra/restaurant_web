package service;

import java.util.List;
import java.util.Set;

import database.OrderDao;
import model.Order;

public class OrderService {
	
	private static OrderDao orderDao;
	
	public OrderService()
	{
		orderDao = new OrderDao();
	}
	
	public void persist(Order entity) {
		try {
			orderDao.openCurrentSessionwithTransaction();
			orderDao.persist(entity);
		}
		finally{
			orderDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(Order entity) {
    	try {
	    	orderDao.openCurrentSessionwithTransaction();
	    	orderDao.update(entity);
    	}
    	finally{
    		orderDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Order findById(Long id) {
    	Order order = null;
    	try {
	    	orderDao.openCurrentSession();
	    	order = orderDao.findByPrimaryKey(id);
    	}
    	finally{
    		orderDao.closeCurrentSession();
    	}
        return order;
    }
    
    public Set<Order> findByState(String state) {
    	Set<Order> orders = null;
    	try {
	    	orderDao.openCurrentSession();
	    	orders = orderDao.findByState(state);
    	}
    	finally{
    		orderDao.closeCurrentSession();
    	}
        return orders;
    }
 
    public void delete(Long id) {
    	try {
	    	orderDao.openCurrentSessionwithTransaction();
	    	Order order = orderDao.findByPrimaryKey(id);
	    	orderDao.delete(order);
    	}
    	finally{
    		orderDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<Order> findAll() {
    	Set<Order> orders = null;
    	try {
	    	orderDao.openCurrentSession();
	    	orders = orderDao.findAll();
    	}
        finally{
        	orderDao.closeCurrentSession();
        }
        return orders;
    }
 
    public OrderDao orderDao() {
        return orderDao;
    }
}
