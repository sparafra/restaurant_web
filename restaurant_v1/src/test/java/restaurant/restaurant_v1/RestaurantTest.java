package restaurant.restaurant_v1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.*;  
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import model.Restaurant;
import service.RestaurantService;  


/**
 * Unit test for simple App.
 */
@TestMethodOrder(OrderAnnotation.class)

public class RestaurantTest 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	
	Restaurant restaurant;
	RestaurantService restaurant_service;
	
    
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

    }
    
    @Test
    @Order(2) 
    public void testFindById()
    {
    	Restaurant restaurant = restaurant_service.findById(Long.valueOf(102));
    	//System.out.println(restaurant.getJson());

    	assertNotNull(restaurant);
    	//System.out.println(restaurant.getJson());
    }
    
    @Test
    @Order(3) 
    public void testUpdate()
    {
    	
    }
    
    @Test
    @Order(4) 
    public void testFindAll()
    {
    	List<Restaurant> restaurants = restaurant_service.findAll();
    	for(Restaurant R:restaurants)
    		System.out.println(R.getJson());
    	
    }
    
    @Test
    @Order(5) 
    public void testDelete()
    {
    	
    }
    
    @Test
    public void testApp()
    {
        assertTrue( true );
    }
    
}
