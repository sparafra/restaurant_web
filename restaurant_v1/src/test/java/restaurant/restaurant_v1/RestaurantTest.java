package restaurant.restaurant_v1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.*;  
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import model.Restaurant;
import service.RestaurantService;  


/**
 * Unit test for simple App.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestaurantTest 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	
	Restaurant restaurant;
	RestaurantService restaurant_service;
	
	static HashSet<Restaurant> setAdded;
    
	@BeforeAll
	public static void init() throws Exception
	{
    	setAdded = new HashSet<Restaurant>();

	}
	
    @BeforeEach
    public void setUp() throws Exception
    {
    	restaurant = new Restaurant();
    	restaurant_service = new RestaurantService();
    }
    
    @Test
    @Order(1) 
    public void testPersist()
    {

    	restaurant.setActive(true);
    	restaurant.setAddress("Via aldo moro");
    	restaurant.setBackground_url("//url//");
    	restaurant.setListAnalytics(null);
    	restaurant.setListLogs(null);
    	restaurant.setListNotices(null);
    	restaurant.setListOrders(null);
    	restaurant.setListProducts(null);
    	restaurant.setListReviewRestaurant(null);
    	restaurant.setListUsers(null);
    	restaurant.setLogo_url("//url//");
    	restaurant.setMail("info@mail.it");
    	restaurant.setName("pachino");
    	restaurant.setTelephone("3213323123");

    	restaurant_service.persist(restaurant);
    	System.out.println(restaurant.getJson());
    	
    	Restaurant R = restaurant_service.findById(restaurant.getId());

    	setAdded.add(R);
    	
    }
    
    @Test
    @Order(2) 
    public void testFindById()
    {

    	for(Restaurant R: setAdded)
    	{
	    	Restaurant restaurant = restaurant_service.findById(R.getId());
        	System.out.println("??? "+restaurant.getJson());

	    	assertNotNull(restaurant);
    	}
    }
    
    @Test
    @Order(3) 
    public void testUpdate()
    {

    	for(Restaurant R: setAdded)
    	{
    		System.out.println("ID"+R.getId());
        	Restaurant Rest = restaurant_service.findById(R.getId());
        	System.out.println("!!! "+Rest.getJson());
        	Rest.setName("Aggiornato");
        	restaurant_service.update(Rest);
    	}
    	
    }
    
    @Test
    @Order(4) 
    public void testFindAll()
    {

    	Set<Restaurant> restaurants = restaurant_service.findAll();
    	for(Restaurant R:restaurants)
    		System.out.println(R.getJson());
    	
    }
    
    @Test
    @Order(5) 
    public void testDelete()
    {

    	for(Restaurant R: setAdded)
    	{
    		restaurant_service.delete(R.getId());
        	Restaurant restaurant = restaurant_service.findById(R.getId());
        	assertNull(restaurant);

    	}

    }
    
   
}
