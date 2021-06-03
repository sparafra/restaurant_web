package model;

import java.util.Date;

import javax.persistence.*;


import org.json.JSONObject;

@Entity
@Table(name="analytic")

public class Analytic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analytic_generator")
	@SequenceGenerator(name="analytic_generator", sequenceName = "analytic_seq",  allocationSize=1)
	Long id;
	
	String page;
	Date date_time;
	
	public Analytic()
	{
		
	}

	public Analytic(String page, Date date_time)
	{
		this.page = page;
		this.date_time = date_time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("page", page);
		obj.put("date_time", date_time);
		
		return obj;
	}
	public JSONObject getJson(Class c)
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("page", page);
		obj.put("date_time", date_time);
		
		return obj;
	}
	
}
