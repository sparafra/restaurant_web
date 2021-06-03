package model;
import java.util.Set;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;


@Entity
@Table(name="user")
public class User {
	
	@Id
    String telephone;
   
	String mail;

    String name;
    String surname;
    String address;
    @Column(columnDefinition = "LONGTEXT")
    String password;
    @Column(columnDefinition = "LONGTEXT")
    String salt;
    boolean approved;
    boolean admin;
    boolean disabled;

    @ManyToMany(fetch=FetchType.EAGER, targetEntity = Restaurant.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "restaurant_user", 
	joinColumns = { @JoinColumn(name = "user_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "restaurant_id") })
    Set<Restaurant> listRestaurants;
    
    @OneToMany(fetch=FetchType.EAGER, targetEntity = Order.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="telephone")
    Set<Order> listOrders;
    
    @OneToMany(fetch=FetchType.EAGER, targetEntity = Log.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="telephone")
    Set<Log> listLogs;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "restaurant", targetEntity = ReviewRestaurant.class)
    Set<ReviewRestaurant> listReviewRestaurant;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "product", targetEntity = ReviewProduct.class)
    Set<ReviewProduct> listReviewProduct;

    public User(){disabled = false; }
    

    public User(String telephone, String name, String surname, String mail, String address, String password, String salt,
			boolean approved, boolean admin, boolean disabled, Set<Restaurant> listRestaurants, Set<Order> listOrders,
			Set<Log> listLogs, Set<ReviewRestaurant> listReviewRestaurant, Set<ReviewProduct> listReviewProduct) {
		super();
		this.telephone = telephone;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.address = address;
		this.password = password;
		this.salt = salt;
		this.approved = approved;
		this.admin = admin;
		this.disabled = disabled;
		this.listRestaurants = listRestaurants;
		this.listOrders = listOrders;
		this.listLogs = listLogs;
		this.listReviewRestaurant = listReviewRestaurant;
		this.listReviewProduct = listReviewProduct;
	}

    //Getters and Setters
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Set<Restaurant> getListRestaurants() {
		return listRestaurants;
	}

	public void setListRestaurants(Set<Restaurant> listRestaurants) {
		this.listRestaurants = listRestaurants;
	}

	public Set<Order> getListOrders() {
		return listOrders;
	}

	public void setListOrders(Set<Order> listOrders) {
		this.listOrders = listOrders;
	}

	public Set<Log> getListLogs() {
		return listLogs;
	}

	public void setListLogs(Set<Log> listLogs) {
		this.listLogs = listLogs;
	}

	public Set<ReviewRestaurant> getListReviewRestaurant() {
		return listReviewRestaurant;
	}

	public void setListReviewRestaurant(Set<ReviewRestaurant> listReviewRestaurant) {
		this.listReviewRestaurant = listReviewRestaurant;
	}

	public Set<ReviewProduct> getListReviewProduct() {
		return listReviewProduct;
	}

	public void setListReviewProduct(Set<ReviewProduct> listReviewProduct) {
		this.listReviewProduct = listReviewProduct;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
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
		User other = (User) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}
	
	
	public JSONObject getJson()
	{
		
		JSONObject obj = new JSONObject();

		obj.put("telephone", telephone);
		obj.put("mail", mail);
		obj.put("name", name);
		obj.put("surname", surname);
		obj.put("address", address);
		obj.put("password", password);
		obj.put("approved", approved);
		obj.put("admin", admin);
		obj.put("disabled", disabled);
		
		JSONArray restaurants = new JSONArray();
		if(listRestaurants != null )
		{
			for(Restaurant r: listRestaurants)
			{
				restaurants.put(r.getJson(Class.USER));
			}
		}
		obj.put("listRestaurants", restaurants);
		
		JSONArray orders = new JSONArray();
		if(listOrders != null )
		{
			for(Order o: listOrders)
			{
				orders.put(o.getJson(Class.USER));
			}
		}
		obj.put("listOders", orders);
		
		JSONArray logs = new JSONArray();
		if(listLogs != null )
		{
			for(Log l: listLogs)
			{
				logs.put(l.getJson(Class.USER));
			}
		}
		obj.put("listLogs", logs);
		
		JSONArray reviewrestaurants = new JSONArray();
		if(listReviewRestaurant != null )
		{
			for(ReviewRestaurant rr: listReviewRestaurant)
			{
				reviewrestaurants.put(rr.getJson(Class.USER));
			}
		}
		obj.put("listReviewRestaurant", reviewrestaurants);
		
		JSONArray reviewproduct = new JSONArray();
		if(listReviewProduct != null)
		{
			for(ReviewProduct rp: listReviewProduct)
			{
				reviewproduct.put(rp.getJson(Class.USER));
			}
		}
		obj.put("listReviewProduct", reviewproduct);
		
		
		return obj;
	}
	public JSONObject getJson(Class c)
	{
		
		JSONObject obj = new JSONObject();

		obj.put("telephone", telephone);
		obj.put("mail", mail);
		obj.put("name", name);
		obj.put("surname", surname);
		obj.put("address", address);
		obj.put("password", password);
		obj.put("approved", approved);
		obj.put("admin", admin);
		obj.put("disabled", disabled);
		
		JSONArray restaurants = new JSONArray();
		if(listRestaurants != null && c != Class.RESTAURANT)
		{
			for(Restaurant r: listRestaurants)
			{
				restaurants.put(r.getJson());
			}
		}
		obj.put("listRestaurants", restaurants);
		
		JSONArray orders = new JSONArray();
		if(listOrders != null && c != Class.ORDER)
		{
			for(Order o: listOrders)
			{
				orders.put(o.getJson());
			}
		}
		obj.put("listOders", orders);
		
		JSONArray logs = new JSONArray();
		if(listLogs != null && c != Class.LOG)
		{
			for(Log l: listLogs)
			{
				logs.put(l.getJson());
			}
		}
		obj.put("listLogs", logs);
		
		JSONArray reviewrestaurants = new JSONArray();
		if(listReviewRestaurant != null && c != Class.REVIEWRESTAURANT)
		{
			for(ReviewRestaurant rr: listReviewRestaurant)
			{
				reviewrestaurants.put(rr.getJson());
			}
		}
		obj.put("listReviewRestaurant", reviewrestaurants);
		
		JSONArray reviewproduct = new JSONArray();
		if(listReviewProduct != null && c != Class.REVIEWPRODUCT)
		{
			for(ReviewProduct rp: listReviewProduct)
			{
				reviewproduct.put(rp.getJson());
			}
		}
		obj.put("listReviewProduct", reviewproduct);
		
		
		return obj;
	}
	
}
