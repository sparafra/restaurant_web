package restaurant.restaurant_v1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.*;  
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import model.Product;
import model.Type;
import service.TypeService;



/**
 * Unit test.
 */
@RunWith(JUnitPlatform.class)


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TypeTest 
{
    /**
     * Test Case
     *
     *
     */
	
	Type type;
	TypeService type_service;
	
	static HashSet<Type> setAdded;

    
	@BeforeAll
	public static void init() throws Exception
	{
    	setAdded = new HashSet<Type>();

	}
	
    @BeforeEach
    public void setUp() throws Exception
    {
    	type = new Type();
    	type_service = new TypeService();
    }
    
    @Test
    @Order(1) 
    public void testPersist()
    {
    	type.setName("Pizza");
    	//type.setListProducts(new HashSet<Product>());

    	type_service.persist(type);
    	
    	setAdded.add(type);
    }
    
    @Test
    @Order(2) 
    public void testFindById()
    {
    	for(Type T: setAdded)
    	{
	    	Type type_ = type_service.findById(T.getId());
	    	assertNotNull(type_);
    	}
    }
    
    @Test
    @Order(3) 
    public void testUpdate()
    {
    	for(Type T: setAdded)
    	{
	    	Type type_ = type_service.findById(T.getId());
	    	type_.setName("Aggiornato");
	    	type_service.update(type_);
    	}	
    }
    
    @Test
    @Order(4) 
    public void testFindAll()
    {
    	Set<Type> types = type_service.findAll();
    	for(Type T: types)
    		System.out.println(T.getJson());	
    }
    
    @Test
    @Order(5) 
    public void testDelete()
    {
    	for(Type T: setAdded)
    	{
    		type_service.delete(T.getId());
    		Type type_ = type_service.findById(T.getId());
        	assertNull(type_);
    	}
    }
}
