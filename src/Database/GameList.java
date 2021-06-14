package Database;

/**
 * Class to represent a single linked-list of Database.Game objects.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class GameList {

    public Game head;

	public GameList(Game head) {
		this.head = head;
    }
	
	public void addGame(Game game) {
		boolean flag=false;
		if(game == null) {
			throw new IllegalArgumentException();
		}
		if(head == null) {
			head = game;
		}else if(head.equals(game)) {
			return;
		}
		
		else {
			Game temp = head;
			
			while(temp.getNext()!=null) {
				
				if(temp.equals(game)) {
					flag=true;
					break;
				}
				temp = temp.getNext();
			}
			if(!flag) {
				System.out.println(game +" added");
				temp.setNext(game);
				
			}else {
				System.out.println(game +" not added");
			}
		}
	}

    public String toString() {
    	if(head==null)
    		return "Empty game list";
		Game current = head.getNext();
		String temp=head+"";
		while(current!=null) {
			temp = temp+ "\n" + current;
			current=current.getNext();
		}
		return temp;
    }

	public Game getGame(String name) {
		if (name==null) {
			throw new IllegalArgumentException();
		}
		Game temp = head;
		while(temp!=null) {
			if(temp.getName().equals(name)) {
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
		
	}
	
	public void removeGame(String name) {
		if(name==null)
			throw new IllegalArgumentException();
		if(head.getName().equals(name)) {
			head = head.getNext();
		}else {
			Game current = head.getNext();
			Game prev = head;
			while(current!=null) {
				if(current.getName().equals(name)) {
					prev.setNext(current.getNext());
				}
				current = current.getNext();
				prev = prev.getNext();
			}
			
		}
		
		
	}
	public void removeGame(Game game) {
		if(game==null)
			throw new IllegalArgumentException();
		if(head.equals(game)) {
			head = head.getNext();
		}else {
			Game current = head.getNext();
			Game prev = head;
			while(current!=null) {
				if(current.equals(game)) {
					prev.setNext(current.getNext());
				}
				current = current.getNext();
				prev = prev.getNext();
			}
			
		}
		
	}
}
