package service;

import java.util.List;
import java.util.Set;

import database.ProductDao;
import model.Product;

public class ProductService {
	
	private static ProductDao productDao;
	
	public ProductService()
	{
		productDao = new ProductDao();
	}
	
	public void persist(Product entity) {
		try {
			productDao.openCurrentSessionwithTransaction();
			productDao.persist(entity);
		}
		finally{
			productDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(Product entity) {
    	try {
	    	productDao.openCurrentSessionwithTransaction();
	    	productDao.update(entity);
    	}
    	finally{
    		productDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Product findById(Long id) {
    	Product product = null;
    	try {
	    	productDao.openCurrentSession();
	    	product = productDao.findByPrimaryKey(id);
    	}
    	finally{
    		productDao.closeCurrentSession();
    	}
        return product;
    }
 
    public void delete(Long id) {
    	try {
	    	productDao.openCurrentSessionwithTransaction();
	    	Product product = productDao.findByPrimaryKey(id);
	    	productDao.delete(product);
    	}
    	finally{
    		productDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<Product> findAll() {
    	Set<Product> products = null;
    	try {
	    	productDao.openCurrentSession();
	        products = productDao.findAll();
    	}
    	finally{
    		productDao.closeCurrentSession();
    	}
        return products;
    }
 
    public ProductDao productDao() {
        return productDao;
    }
}
