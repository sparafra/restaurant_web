package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;  

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_generator")
	@SequenceGenerator(name="orders_generator", sequenceName = "orders_seq",  allocationSize=1)
    Long id;
	
    String state;
    Boolean take_away;
    Float price;
    Date date_time;  
    boolean paid;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "product")
    Set<ProductOrder> listProductOrder;

    
    public Order(){}
    

	public Order(String state, Boolean take_away, Set<ProductOrder> listProductOrder, Float price, Date date_time,
			boolean paid) {
		super();
		this.state = state;
		this.take_away = take_away;
		this.listProductOrder = listProductOrder;
		this.price = price;
		this.date_time = date_time;
		this.paid = paid;
	}

	
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getTake_away() {
		return take_away;
	}

	public void setTake_away(Boolean take_away) {
		this.take_away = take_away;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Set<ProductOrder> getListProductOrder() {
		return listProductOrder;
	}

	public void setListProductOrder(Set<ProductOrder> listProductOrder) {
		this.listProductOrder = listProductOrder;
	}

	public float getTotaleCosto()
    {
    	if(price == null || price == 0)
    	{
	        float Tot=0;
	        for(ProductOrder PO : listProductOrder)
	        {	        		        
	        	Tot += PO.getProduct().getPrice() *PO.getQuantity() ;
	            
	        }
	        return  Tot;
	    	}
    	else
    		return price;
    }

	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("state", state);
		obj.put("take_away", take_away);
		obj.put("price", price);
		obj.put("date_time", date_time);
		obj.put("paid", paid);
		
		JSONArray productorders = new JSONArray();
		
		if(listProductOrder != null)
		{
			for(ProductOrder po: listProductOrder)
			{
				productorders.put(po.getJson());
			}
		}
		obj.put("listProductsOrder", productorders);
		
		
		return obj;
	}
	public JSONObject getJson(Class c)
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("state", state);
		obj.put("take_away", take_away);
		obj.put("price", price);
		obj.put("date_time", date_time);
		obj.put("paid", paid);
		
		JSONArray productorders = new JSONArray();
		
		if(listProductOrder != null && c == Class.PRODUCTORDER)
		{
			for(ProductOrder po: listProductOrder)
			{
				productorders.put(po.getJson());
			}
		}
		obj.put("listProductsOrder", productorders);
		
		
		return obj;
	}
    
}
