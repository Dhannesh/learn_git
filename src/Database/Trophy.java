package Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in
 * four ranks: bronze, silver, gold and platinum. The date the trophy was
 * earned and its respective game is also stored.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Trophy {
	public enum Rank { 
		BRONZE, SILVER, GOLD, PLATINUM 
		} 


	public enum Rarity {
		COMMON, UNCOMMON, RARE, VERY_RARE, ULTRA_RARE
		}

	private String name; 
	private Rank rank; 
	private Rarity rarity; 
	private Calendar obtained; 
	private Game game;

	public Trophy() {}

    public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
    	this.name = name;
    	this.rank = rank;
    	this.rarity = rarity;
    	this.obtained = obtained;
    	this.game = game;
    }
    
    public boolean equals(Object o) {
    	Trophy g = (Trophy)o;
    	if(name.equals(g.getName()) && game.equals(g.getGame()))
    		return true;
		return false;
    }

    public String toString() {
    	Date date = obtained.getTime();
    	DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
		return String.format("\"%s\", rank: %s, rarity: %s, obtained on: %s",name,rank,rarity,dateFormat.format(date));
    }

	public Object getName() {
		
		return name;
	}

	public Rank getRank() {
		
		return rank;
	}

	public Object getRarity() {
		
		return rarity;
	}

	public Object getObtained() {
		
		return obtained;
	}

	public Object getGame() {
		
		return game;
	}
}
