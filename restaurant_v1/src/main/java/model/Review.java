package model;

import java.util.Date;
import javax.persistence.*;

import org.json.JSONObject;  

@MappedSuperclass
public abstract class Review {

	int vote;
	Date date_time;
	
    public Review(int vote, Date date_time)
    {
        this.vote = vote;
        this.date_time = date_time;
    }
   
    public Review()
    {
    	
    }
    
    //Getters and Setters
	
	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
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

		obj.put("vote", vote);
		obj.put("date_time", date_time);
		
		
		return obj;
	}
    
}
    