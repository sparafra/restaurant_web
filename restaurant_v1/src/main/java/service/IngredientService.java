package service;

import java.util.List;
import java.util.Set;

import database.IngredientDao;
import model.Ingredient;

public class IngredientService {
	
	private static IngredientDao ingredientDao;
	
	public IngredientService()
	{
		ingredientDao = new IngredientDao();
	}
	
	public void persist(Ingredient entity) {
		try {
			ingredientDao.openCurrentSessionwithTransaction();
			ingredientDao.persist(entity);
		}
		finally{
			ingredientDao.closeCurrentSessionwithTransaction();
		}
    }
 
    public void update(Ingredient entity) {
    	try {
	    	ingredientDao.openCurrentSessionwithTransaction();
	    	ingredientDao.update(entity);
    	}
    	finally{
    		ingredientDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Ingredient findById(Long id) {
    	Ingredient ingredient = null;
    	try {
	    	ingredientDao.openCurrentSession();
	    	ingredient = ingredientDao.findByPrimaryKey(id);
    	}
    	finally{
    		ingredientDao.closeCurrentSession();
    	}
        return ingredient;
    }
 
    public void delete(Long id) {
    	try {
	    	ingredientDao.openCurrentSessionwithTransaction();
	    	Ingredient ingredient = ingredientDao.findByPrimaryKey(id);
	        ingredientDao.delete(ingredient);
    	}
    	finally{
    		ingredientDao.closeCurrentSessionwithTransaction();
    	}
    }
 
    public Set<Ingredient> findAll() {
    	Set<Ingredient> ingredients = null;
    	try {
	    	ingredientDao.openCurrentSession();
	        ingredients = ingredientDao.findAll();
    	}
        finally{
        	ingredientDao.closeCurrentSession();
        }
        return ingredients;
    }
 
    public IngredientDao ingredientDao() {
        return ingredientDao;
    }
}
