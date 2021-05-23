package model;

import java.util.Date;

import javax.persistence.*;

import org.json.JSONObject;

@Entity
@Table(name="review_local_user")
public class ReviewRestaurant extends Review {
	
	@EmbeddedId
	ReviewRestaurantUserId id;
	
	@ManyToOne
	@MapsId("telephone")
	@JoinColumn(name = "telephone")
	User user;
	
	@ManyToOne
	@MapsId("restaurant_id")
	@JoinColumn(name = "restaurant_id")
	Restaurant restaurant;
	
	String review;
	
	public ReviewRestaurant(Restaurant restaurant, User user, int vote, String review, Date date_time)
	{
		super(vote, date_time);
		this.id = new ReviewRestaurantUserId(restaurant.getId(), user.getTelephone());
		this.user = user;
		this.restaurant = restaurant;
		//this.user = user;
		this.review = review;
	}
	public ReviewRestaurant() {super();}
	
	//Getters and Setters
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id.getJson());
		obj.put("restaurant", restaurant);
		obj.put("review", review);
		obj.put("user", user.getJson());
		
		return obj;
	}
	
}
