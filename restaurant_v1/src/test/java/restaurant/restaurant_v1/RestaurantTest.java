package restaurant.restaurant_v1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.*;  

import model.Restaurant;
import service.RestaurantService;  


/**
 * Unit test.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestaurantTest 
{
    /**
     * Test Case
     *
     *
     */
	
	Restaurant restaurant;
	RestaurantService restaurant_service;
	
    
	@BeforeAll
	public static void init() throws Exception
	{
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
    	
    		
    }
    
    @Test
    @Order(2) 
    public void testFindById()
    {
    	
    	//Add Restaurant
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
    	
    	//Find Restaurant
	    Restaurant restaurant_finded = restaurant_service.findById(restaurant.getId());
	    assertNotNull(restaurant_finded);
    	
    }
    
    @Test
    @Order(3) 
    public void testUpdate()
    {
    	//Add Restaurant
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
    	
    	//Update 
    	restaurant.setName("Aggiornato");
    	restaurant_service.update(restaurant);
    	
    }
    
    @Test
    @Order(4) 
    public void testFindAll()
    {
    	//Add Restaurant
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

    	restaurant.setId(null);
    	restaurant.setName("pachino2");
    	
    	restaurant_service.persist(restaurant);
    	
    	
    	Set<Restaurant> restaurants = restaurant_service.findAll();
    	for(Restaurant R: restaurants)
    		System.out.println(R.getJson());	
    	
    	assertEquals(restaurants.size(), 2);
    }
    
    @Test
    @Order(5) 
    public void testDelete()
    {
    	//Add Restaurant
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
    	
    	
    	restaurant_service.delete(restaurant.getId());
        Restaurant R = restaurant_service.findById(restaurant.getId());
        assertNull(R);
    	
    }
    
    @AfterAll
    public void clear()
    {
    	Set<Restaurant> restaurants = restaurant_service.findAll();
    	for(Restaurant R: restaurants)
    		restaurant_service.delete(R.getId());
    }
}
