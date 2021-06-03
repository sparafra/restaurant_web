package model;
import java.util.Set;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;  

@Entity
@Table(name="type")
public class Type {
	
	@Id
	@GeneratedValue(generator = "type_generator")
	@SequenceGenerator(name="type_generator", sequenceName = "type_seq",  allocationSize=1)

	Long id;
	String name;
	
	@ManyToMany(fetch=FetchType.EAGER, targetEntity = Product.class, cascade = { CascadeType.ALL })
	@JoinTable(
	    		name = "product_type",
	    		joinColumns = {@JoinColumn(name="type_id")},
	    		inverseJoinColumns = {@JoinColumn(name ="product_id")})
	Set<Product> listProducts;

	public Type(String name)
	{
		this.name = name;
	}
	public Type() { }
	
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
	public Set<Product> getListProducts() {
		return listProducts;
	}
	public void setListProducts(Set<Product> listProducts) {
		this.listProducts = listProducts;
	}
	
	//Hashcode and equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("name", name);
		
		JSONArray products = new JSONArray();
		
		if(listProducts != null)
		{
			for(Product p: listProducts)
			{
				products.put(p.getJson(Class.TYPE));
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
