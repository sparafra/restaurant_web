package model;

import java.util.Set;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;  

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(generator = "product_generator")
	@SequenceGenerator(name="product_generator", sequenceName = "product_seq",  allocationSize=1)
	Long id;
	
    String name;
    float price;
    String image_url;
    
    @ManyToMany(fetch=FetchType.EAGER, targetEntity = Ingredient.class, cascade = {CascadeType.ALL})
    @JoinTable(
    		name = "product_ingredient",
    		joinColumns = {@JoinColumn(name="product_id")},
    		inverseJoinColumns = {@JoinColumn(name ="ingredient_id")})
    Set<Ingredient> listIngredients;
        
	@ManyToMany(fetch=FetchType.EAGER, targetEntity = Type.class, cascade = {CascadeType.ALL})
    @JoinTable(
    		name = "product_type",
    		joinColumns = {@JoinColumn(name="product_id")},
    		inverseJoinColumns = {@JoinColumn(name ="type_id")})
	Set<Type> listTypes;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "order")
	Set<ProductOrder> listProductOrder;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "user")
	Set<ReviewProduct> listReviewProduct;

    
    public  Product() { }
    

    public Product(String name, float price, String image_url, Set<Ingredient> listIngredients, Set<Type> listTypes,
    		Set<ProductOrder> listProductOrder, Set<ReviewProduct> listReviewProduct) {
		super();
		this.name = name;
		this.price = price;
		this.image_url = image_url;
		this.listIngredients = listIngredients;
		this.listTypes = listTypes;
		this.listProductOrder = listProductOrder;
		this.listReviewProduct = listReviewProduct;
	}

    //Getters and Setters
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

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	public Set<Ingredient> getListIngredients() {
		return listIngredients;
	}

	public void setListIngredients(Set<Ingredient> listIngredients) {
		this.listIngredients = listIngredients;
	}

	public Set<Type> getListTypes() {
		return listTypes;
	}

	public void setListTypes(Set<Type> listTypes) {
		this.listTypes = listTypes;
	}

	public Set<ProductOrder> getListProductOrder() {
		return listProductOrder;
	}

	public void setListProductOrder(Set<ProductOrder> listProductOrder) {
		this.listProductOrder = listProductOrder;
	}

	public Set<ReviewProduct> getListReviewProduct() {
		return listReviewProduct;
	}

	public void setListReviewProduct(Set<ReviewProduct> listReviewProduct) {
		this.listReviewProduct = listReviewProduct;
	}  

	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("name", name);
		obj.put("price", price);
		obj.put("image_url", image_url);

		JSONArray ingredients = new JSONArray();
		
		if(listIngredients != null)
		{
			for(Ingredient i: listIngredients)
			{
				ingredients.put(i.getJson(Class.PRODUCT));
			}
		}
		
		obj.put("listIngredients", ingredients);
		
		JSONArray types = new JSONArray();
		
		if(listTypes != null)
		{
			for(Type t: listTypes)
			{
				types.put(t.getJson(Class.PRODUCT));
			}
		}
		obj.put("listTypes", types);
		
		JSONArray productorders = new JSONArray();
		
		if(listProductOrder != null)
		{
			for(ProductOrder po: listProductOrder)
			{
				productorders.put(po.getJson(Class.PRODUCT));
			}
		}
		
		obj.put("listProductOrder", productorders);
		
		JSONArray reviewproducts = new JSONArray();
		
		if(listReviewProduct != null)
		{
			for(ReviewProduct rp: listReviewProduct)
			{
				reviewproducts.put(rp.getJson(Class.PRODUCT));
			}
		}
		
		obj.put("listReviewProduct", reviewproducts);
		
		
		return obj;
	}
	public JSONObject getJson(Class c)
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("name", name);
		obj.put("price", price);
		obj.put("image_url", image_url);

		JSONArray ingredients = new JSONArray();
		
		if(listIngredients != null && c == Class.INGREDIENT)
		{
			for(Ingredient i: listIngredients)
			{
				ingredients.put(i.getJson());
			}
		}
		
		obj.put("listIngredients", ingredients);
		
		JSONArray types = new JSONArray();
		
		if(listTypes != null && c == Class.TYPE)
		{
			for(Type t: listTypes)
			{
				types.put(t.getJson());
			}
		}
		obj.put("listTypes", types);
		
		JSONArray productorders = new JSONArray();
		
		if(listProductOrder != null && c == Class.PRODUCTORDER)
		{
			for(ProductOrder po: listProductOrder)
			{
				productorders.put(po.getJson());
			}
		}
		
		obj.put("listProductOrder", productorders);
		
		JSONArray reviewproducts = new JSONArray();
		
		if(listReviewProduct != null && c == Class.REVIEWPRODUCT)
		{
			for(ReviewProduct rp: listReviewProduct)
			{
				reviewproducts.put(rp.getJson());
			}
		}
		
		obj.put("listReviewProduct", reviewproducts);
		
		
		return obj;
	}

}
