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
		reviewproductDao.openCurrentSessionwithTransaction();
		reviewproductDao.persist(entity);
		reviewproductDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(ReviewProduct entity) {
    	reviewproductDao.openCurrentSessionwithTransaction();
    	reviewproductDao.update(entity);
    	reviewproductDao.closeCurrentSessionwithTransaction();
    }
 
    public ReviewProduct findById(Long id) {
    	reviewproductDao.openCurrentSession();
    	ReviewProduct reviewproduct = reviewproductDao.findByPrimaryKey(id);
    	reviewproductDao.closeCurrentSession();
        return reviewproduct;
    }
 
    public void delete(Long id) {
    	reviewproductDao.openCurrentSessionwithTransaction();
    	ReviewProduct reviewproduct = reviewproductDao.findByPrimaryKey(id);
    	reviewproductDao.delete(reviewproduct);
    	reviewproductDao.closeCurrentSessionwithTransaction();
    }
 
    public Set<ReviewProduct> findAll() {
    	reviewproductDao.openCurrentSession();
        Set<ReviewProduct> reviewproducts = reviewproductDao.findAll();
        reviewproductDao.closeCurrentSession();
        return reviewproducts;
    }
 
    public ReviewProductDao reviewproductDao() {
        return reviewproductDao;
    }
}
