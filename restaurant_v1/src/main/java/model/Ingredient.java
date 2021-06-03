package model;

import java.util.Set;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;    

@Entity
@Table(name="ingredient")
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_generator")
	@SequenceGenerator(name="ingredient_generator", sequenceName = "ingredient_seq",  allocationSize=1)
	Long id;
	
    String name;
    float price;
    
    @ManyToMany(fetch=FetchType.EAGER, targetEntity = Product.class, cascade = { CascadeType.ALL })
    @JoinTable(
    		name = "product_ingredient",
    		joinColumns = {@JoinColumn(name="ingredient_id")},
    		inverseJoinColumns = {@JoinColumn(name ="product_id")})
    Set<Product> listProducts;
    
    
   
    public Ingredient(String name, float price)
    {
    	this.name = name;
        this.price = price;
    }
   
    public Ingredient(){}
	
    // Getters and Setters
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Set<Product> getListProducts() {
		return listProducts;
	}
	public void setListProducts(Set<Product> listProducts) {
		this.listProducts = listProducts;
	}

	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("name", name);
		obj.put("price", price);
		
		JSONArray products = new JSONArray();
		
		if(listProducts != null)
		{
			for(Product p: listProducts)
			{
				products.put(p.getJson(Class.INGREDIENT));
			}
		}
		obj.put("listProducts", listProducts);
		
		
		return obj;
	}
	public JSONObject getJson(Class c)
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("name", name);
		obj.put("price", price);
		
		JSONArray products = new JSONArray();
		
		if(listProducts != null && c == Class.PRODUCT)
		{
			for(Product p: listProducts)
			{
				products.put(p.getJson());
			}
		}
		obj.put("listProducts", listProducts);
		
		
		return obj;
	}
}
