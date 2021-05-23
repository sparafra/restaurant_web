package service;

import java.util.List;

import database.OrderDao;
import model.Order;

public class OrderService {
	
	private static OrderDao orderDao;
	
	public OrderService()
	{
		orderDao = new OrderDao();
	}
	
	public void persist(Order entity) {
		orderDao.openCurrentSessionwithTransaction();
		orderDao.persist(entity);
		orderDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Order entity) {
    	orderDao.openCurrentSessionwithTransaction();
    	orderDao.update(entity);
    	orderDao.closeCurrentSessionwithTransaction();
    }
 
    public Order findById(Long id) {
    	orderDao.openCurrentSession();
    	Order order = orderDao.findByPrimaryKey(id);
    	orderDao.closeCurrentSession();
        return order;
    }
    
    public List<Order> findByState(String state) {
    	orderDao.openCurrentSession();
        List<Order> orders = orderDao.findByState(state);
    	orderDao.closeCurrentSession();
        return orders;
    }
 
    public void delete(Long id) {
    	orderDao.openCurrentSessionwithTransaction();
    	Order order = orderDao.findByPrimaryKey(id);
    	orderDao.delete(order);
    	orderDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Order> findAll() {
    	orderDao.openCurrentSession();
        List<Order> orders = orderDao.findAll();
        orderDao.closeCurrentSession();
        return orders;
    }
 
    public OrderDao orderDao() {
        return orderDao;
    }
}
