package Database;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to represent a PlayStation game.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Game {
	private String name; 
	private Calendar released; 
	private Game next; 
	private int totalTrophies; 


    public Game() {}

    public Game(String name, Calendar released, int totalTrophies) {
    	this.name = name;
    	this.released = released;
    	this.totalTrophies = totalTrophies;
    	this.next = null;
    }

    public String toString() {
    	
    	Date date = released.getTime();
    	DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
    	return String.format("\"%s\", released on: %s", name,dateFormat.format(date));
    	
    }

    @Override
    public boolean equals(Object o) {
    	Game g = (Game)o;
    	if(name.equals(g.getName())) {
    		if(released.compareTo(g.released)==0) {
    			if(totalTrophies == g.totalTrophies)
    				return true;
    		}
    	}
		return false;
    }

	public Object getReleased() {
		
		return released;
	}

	public Object getTotalTrophies() {
		
		return totalTrophies;
	}

	public String getName() {
		
		return name;
	}

	public void setNext(Game g2) {
		
		next = g2;
	}
	public Game getNext() {
		return next;
	}
}
