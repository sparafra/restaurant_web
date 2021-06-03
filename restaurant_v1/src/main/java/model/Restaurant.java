package model;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Analytic;
import model.Log;
import model.Notice;
import model.Order;
import model.Product;
import model.ReviewRestaurant;
import model.User;


@Entity
@Table(name="restaurant")
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_generator")
	@SequenceGenerator(name="restaurant_generator", sequenceName = "restaurant_seq",  allocationSize=1)
	Long id;
	
	String name;
	String address;
	String mail;
	String telephone;
	String logo_url;
	String background_url;
	Boolean active;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Product.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="restaurant_id")
	Set<Product> listProducts;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Log.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="restaurant_id")
	Set<Log> listLogs;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Notice.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="restaurant_id")
	Set<Notice> listNotices;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Order.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="restaurant_id")
	Set<Order> listOrders;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Analytic.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="restaurant_id")
	Set<Analytic> listAnalytics;
	
	@ManyToMany(fetch=FetchType.EAGER, targetEntity = User.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "restaurant_user", 
				joinColumns = { @JoinColumn(name = "restaurant_id") }, 
				inverseJoinColumns = { @JoinColumn(name = "user_id") })
	Set<User> listUsers;
	
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "user")
    Set<ReviewRestaurant> listReviewRestaurant;


   
	public Restaurant(String name, String address, String mail, String telephone, String logo_url,
			String background_url, Boolean active, Set<Product> listProducts, Set<Log> listLogs,
			Set<Notice> listNotices, Set<Order> listOrders, Set<Analytic> listAnalytics, Set<User> listUsers,
			Set<ReviewRestaurant> listReviewRestaurant) {
		super();
		this.name = name;
		this.address = address;
		this.mail = mail;
		this.telephone = telephone;
		this.logo_url = logo_url;
		this.background_url = background_url;
		this.active = active;
		this.listProducts = listProducts;
		this.listLogs = listLogs;
		this.listNotices = listNotices;
		this.listOrders = listOrders;
		this.listAnalytics = listAnalytics;
		this.listUsers = listUsers;
		this.listReviewRestaurant = listReviewRestaurant;
	}

	public Restaurant() {}

	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getBackground_url() {
		return background_url;
	}

	public void setBackground_url(String background_url) {
		this.background_url = background_url;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Product> getListProducts() {
		return listProducts;
	}

	public void setListProducts(Set<Product> listProducts) {
		this.listProducts = listProducts;
	}

	public Set<Log> getListLogs() {
		return listLogs;
	}

	public void setListLogs(Set<Log> listLogs) {
		this.listLogs = listLogs;
	}

	public Set<Notice> getListNotices() {
		return listNotices;
	}

	public void setListNotices(Set<Notice> listNotices) {
		this.listNotices = listNotices;
	}

	public Set<Order> getListOrders() {
		return listOrders;
	}

	public void setListOrders(Set<Order> listOrders) {
		this.listOrders = listOrders;
	}

	public Set<Analytic> getListAnalytics() {
		return listAnalytics;
	}

	public void setListAnalytics(Set<Analytic> listAnalytics) {
		this.listAnalytics = listAnalytics;
	}

	public Set<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(Set<User> listUsers) {
		this.listUsers = listUsers;
	}

	public Set<ReviewRestaurant> getListReviewRestaurant() {
		return listReviewRestaurant;
	}

	public void setListReviewRestaurant(Set<ReviewRestaurant> listReviewRestaurant) {
		this.listReviewRestaurant = listReviewRestaurant;
	}

	public JSONObject getJson()
	{
		
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("name", name);
		obj.put("address", address);
		obj.put("mail", mail);
		obj.put("logo_url", logo_url);
		obj.put("background_url", background_url);
		obj.put("active", active);

		JSONArray products = new JSONArray();
		if(listProducts != null)
		{
			for(Product p: listProducts)
			{
				products.put(p.getJson(Class.RESTAURANT));
			}
		}
		obj.put("listProducts", products);
		
		JSONArray logs = new JSONArray();
		if(listLogs != null)
		{
			for(Log l: listLogs)
			{
				logs.put(l.getJson(Class.RESTAURANT));
			}
		}
		obj.put("listLogs", logs);
		
		JSONArray notices = new JSONArray();
		if(listNotices != null)
		{
			for(Notice n: listNotices)
			{
				notices.put(n.getJson(Class.RESTAURANT));
			}
		}
		obj.put("listNotices", notices);
		
		JSONArray orders = new JSONArray();
		if(listOrders != null)
		{
			for(Order o: listOrders)
			{
				orders.put(o.getJson(Class.RESTAURANT));
			}
		}
		obj.put("listOrders", orders);
		
		JSONArray analytics = new JSONArray();
		if(listAnalytics != null)
		{
			for(Analytic a: listAnalytics)
			{
				analytics.put(a.getJson(Class.RESTAURANT));
			}
		}
		obj.put("listAnalytics", analytics);
		
		JSONArray users = new JSONArray();
		if(listUsers != null)
		{
			for(User u: listUsers)
			{
				users.put(u.getJson(Class.RESTAURANT));
			}
		}
		obj.put("listUsers", users);
		
		JSONArray reviewrestaurants = new JSONArray();
		if(listReviewRestaurant != null)
		{
			for(ReviewRestaurant rr: listReviewRestaurant)
			{
				reviewrestaurants.put(rr.getJson(Class.RESTAURANT));
			}
		}
		obj.put("listReviewRestaurant", reviewrestaurants);
		
		return obj;
	}
	
	public JSONObject getJson(Class c)
	{
		
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("name", name);
		obj.put("address", address);
		obj.put("mail", telephone);
		obj.put("logo_url", logo_url);
		obj.put("background_url", background_url);
		obj.put("active", active);

		JSONArray products = new JSONArray();
		if(listProducts != null && c != Class.PRODUCT)
		{
			for(Product p: listProducts)
			{
				products.put(p.getJson());
			}
		}
		obj.put("listProducts", products);
		
		JSONArray logs = new JSONArray();
		if(listLogs != null && c != Class.LOG)
		{
			for(Log l: listLogs)
			{
				logs.put(l.getJson());
			}
		}
		obj.put("listLogs", logs);
		
		JSONArray notices = new JSONArray();
		if(listNotices != null && c != Class.NOTICE)
		{
			for(Notice n: listNotices)
			{
				notices.put(n.getJson());
			}
		}
		obj.put("listNotices", notices);
		
		JSONArray orders = new JSONArray();
		if(listOrders != null && c != Class.ORDER)
		{
			for(Order o: listOrders)
			{
				orders.put(o.getJson());
			}
		}
		obj.put("listOrders", orders);
		
		JSONArray analytics = new JSONArray();
		if(listAnalytics != null && c != Class.ANALYTIC)
		{
			for(Analytic a: listAnalytics)
			{
				analytics.put(a.getJson());
			}
		}
		obj.put("listAnalytics", analytics);
		
		JSONArray users = new JSONArray();
		if(listUsers != null && c != Class.USER)
		{
			for(User u: listUsers)
			{
				users.put(u.getJson());
			}
		}
		obj.put("listUsers", users);
		
		JSONArray reviewrestaurants = new JSONArray();
		if(listReviewRestaurant != null && c != Class.REVIEWRESTAURANT)
		{
			for(ReviewRestaurant rr: listReviewRestaurant)
			{
				reviewrestaurants.put(rr.getJson());
			}
		}
		obj.put("listReviewRestaurant", reviewrestaurants);
		
		return obj;
	}
}
