package model;

import java.util.Date;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name="log")
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_generator")
	@SequenceGenerator(name="log_generator", sequenceName = "log_seq",  allocationSize=50)
	Long id;
	
	String event;
	//String NumeroTelefono;
	//Long idLocale;
	Date date_time;
	
	
	public Log(String event, Date date_time)
	{
		this.event = event;
		this.date_time = date_time;
	}

	public Log() { }

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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
		obj.put("event", event);
		obj.put("date_time", date_time);
		
		
		return obj;
	}
	
}
