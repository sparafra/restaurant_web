package model;
import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
@Table(name="notice")
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_generator")
	@SequenceGenerator(name="notice_generator", sequenceName = "notice_seq",  allocationSize=50)
	Long id;
	
	boolean state;
	String created_by;
	String message;
	String received_by;
	String type;
	String title;

	public Notice() {}
	
	

	public Notice(boolean state, String created_by, String message, String received_by, String type, String title) {
		super();
		this.state = state;
		this.created_by = created_by;
		this.message = message;
		this.received_by = received_by;
		this.type = type;
		this.title = title;
	}


	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReceived_by() {
		return received_by;
	}

	public void setReceived_by(String received_by) {
		this.received_by = received_by;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();

		obj.put("id", id);
		obj.put("state", state);
		obj.put("created_by", created_by);
		obj.put("message", message);
		obj.put("received_by", received_by);
		obj.put("type", type);
		obj.put("title", title);
		
		return obj;
	}
	
}
