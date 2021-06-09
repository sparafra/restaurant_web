package service;

import java.util.List;
import java.util.Set;

import database.ReviewProductDao;
import model.ReviewProduct;

public class ReviewProductService {
	
	private static ReviewProductDao reviewproductDao;
	
	public ReviewProductService()
	{
		reviewproductDao = new ReviewProductDao();
	}
	
	public void persist(ReviewProduct entity) {
		try {
			reviewproductDao.openCurrentSessionwithTransaction();
			reviewproductDao.persist(entity);
		}
		finally{
			reviewproductDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(ReviewProduct entity) {
    	try {
	    	reviewproductDao.openCurrentSessionwithTransaction();
	    	reviewproductDao.update(entity);
    	}
    	finally{
    		reviewproductDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public ReviewProduct findById(Long id) {
    	ReviewProduct reviewproduct = null;
    	try {
	    	reviewproductDao.openCurrentSession();
	    	reviewproduct = reviewproductDao.findByPrimaryKey(id);
    	}
    	finally{
    		reviewproductDao.closeCurrentSession();
    	}
        return reviewproduct;
    }
 
    public void delete(Long id) {
    	try {
	    	reviewproductDao.openCurrentSessionwithTransaction();
	    	ReviewProduct reviewproduct = reviewproductDao.findByPrimaryKey(id);
	    	reviewproductDao.delete(reviewproduct);
    	}
    	finally{
    		reviewproductDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<ReviewProduct> findAll() {
    	Set<ReviewProduct> reviewproducts = null;
    	try {
	    	reviewproductDao.openCurrentSession();
	        reviewproducts = reviewproductDao.findAll();
    	}
    	finally{
    		reviewproductDao.closeCurrentSession();
    	}
        return reviewproducts;
    }
 
    public ReviewProductDao reviewproductDao() {
        return reviewproductDao;
    }
}
