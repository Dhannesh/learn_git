package Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Database.Trophy.Rank;

/**
 * Class to represent a PlayStation user.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class User {
	private String username; 
	private int level; 
	private double key; 
	private ArrayList<Trophy> trophies; 
	private GameList games; 
	private Calendar dob; 
	private User left; 
	private User right;

	public void addTrophy(Trophy trophy) {
		for(Trophy t:trophies) {
			if(t.equals(trophy))
				return;
		}
		trophies.add(trophy);
		
	}
	
    public boolean equals(Object o) {
    	User user = (User)o;
    	if(username.equals(user.getUsername()) && key == user.key && level == user.level)
    		return true;
		return false;
    }
    public void setKey(double key) {
    	this.key = key;
    }
	
	public void levelUp() {
		level ++;
		key = calculateKey();
	}

	public User getLeft() {
		return left;
	}

	public void setLeft(User left) {
		this.left = left;
	}

	public User getRight() {
		return right;
	}

	public void setRight(User right) {
		this.right = right;
	}

	public User(String username, Calendar dob, int level) {
		this.username = username;
		this.level = level;
		this.dob = dob;
		key = calculateKey();
		left = null;
		right = null;
		
    }

    /**
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash
        return level + hash / divisor;
    }

    public String toString() {
    	Date date = dob.getTime();
    	DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
    	String temp = "User: "+username;
    	temp += "\n\nTrophies: " +"\n";
    	for(Trophy t:trophies) {
    		temp += t+"\n";
    	}
    	temp += "\nGames: \n";
    	
    	temp += games;
    	
    	temp += "\n\n";
    	temp += "Birth Date: "+dateFormat.format(date);
		return temp;
		}

	public Object getUsername() {
		
		return username;
	}

	public Object getDob() {
		
		return dob;
	}

	public int getLevel() {
		return level;
	}

	public double getKey() {
		return key;
	}

	public void setGames(GameList games) {
		this.games = games;
		
	}
	public GameList getGames() {
		return games;
	}

	public void setTrophies(ArrayList<Trophy> trophies) {
		
		this.trophies = trophies;
		
	}
	public int getTrophies(Rank type) {
		int count=0;
		for(Trophy t:trophies) {
			if (t.getRank() == type) {
				count++;
			}
		}
		return count;
	}
	public ArrayList<Trophy> getTrophies() {
		return trophies;
	}
	
}
