package Database;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;

import Database.Trophy.Rank;

/**
 * Uses a binary search tree to store a database of PlayStation users. Nodes are
 * ordered by user unique key (see the User class for more detail). Created for
 * Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @version 1.0
 */
public class BinaryTree {
	public User root;

	/**
	 * Making new friends is great. This method should add your new bestie to your
	 * database (tree). Remember that they should be added according to their key.
	 * 
	 * @param friend The friend to be added
	 * @return true if successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean beFriend(User friend) {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			root = friend; 
			return true;
		}
		User prev = null;
		User temp = root; 
		while (temp != null) { //traverse all the nodes
			if (temp.getKey() > friend.getKey()) {  //if friend is less then current then move left
				prev = temp;
				temp = temp.getLeft();
			} else if (temp.getKey() < friend.getKey()) { //move right
				prev = temp;
				temp = temp.getRight();
			} else {
				return false;  //if node is already available then return false
			}
		}
		if (prev.getKey() > friend.getKey()) {
			prev.setLeft(friend);
		} else {
			prev.setRight(friend);
		}
		return true;

	}

	/**
	 * Sometimes friendships don't work out. In those cases it's best to remove that
	 * "friend" altogether. This method should remove all trace of that "friend" in
	 * the database (tree).
	 * 
	 * @param friend the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException if "friend" is null
	 */
	//traverse all the node then remove selected friend and adjust the pointer
	public boolean deFriend(User friend) {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			return false;
		}
		
		User curr = root;
		User prev = null;
		while(curr !=null && !curr.equals(friend)) {
			prev = curr;
			if (friend.getKey() < curr.getKey())
				curr = curr.getLeft();
			else
				curr = curr.getRight();
		}
		if(curr==null) {
			System.out.println("Friend not found");
			return false;
		}
		//if current have only one child
		if(curr.getLeft() == null || curr.getRight()==null) {
			User newCurr;
			if (curr.getLeft() == null) 
				newCurr = curr.getRight();
			else
				newCurr = curr.getLeft();
			
			if(prev == null)
			{
				root = newCurr;
				return true;
			}
			if (curr == prev.getLeft()) 
				prev.setLeft(newCurr);
			else
				prev.setRight(newCurr);
			return true;
		}else {  //User node have two child
			User p = null;
			User temp;
			temp = curr.getRight();
			while(temp.getLeft()!=null) {
				p = temp;
				temp = temp.getLeft();
			}
			if(p!= null)
				p.setLeft(temp.getRight());
			else
				curr.setRight(temp.getRight());
			
			curr.setGames(temp.getGames());
			curr.setTrophies(temp.getTrophies());
			curr.setKey(temp.getKey());
		}
		return true;
	}
	
	

	/**
	 * In your quest to be the very best you need to know how many of your friends
	 * are ranked higher than you. This method should return the number of higher
	 * ranked users that the provided reference user, or zero if there are none
	 * (woot!).
	 * 
	 * @param reference The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countBetterPlayers(User reference) {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			return 0;
		}
		Stack<User> s = new Stack<User>();
		User current = root;
		//traverse all the node and count the player which are better

		while (current != null || s.size() > 0) {
			while (current != null) {
				s.push(current);
				current = current.getLeft();
			}
			current = s.pop();
			if (current.equals(reference)) {
				//player found then return the total count
				return countPlayer(reference, true);
			}
			current = current.getRight();
		}
//if player not found
		return -1;
	}

	private int countPlayer(User reference, boolean isBetter) {
		//if isBetter true then find better otherwise worst player
		int count = 0;
		if (root == null)
			return 0;
		Stack<User> s = new Stack<User>();
		User current = root;
		while (current != null || s.size() > 0) {
			while (current != null) {
				s.push(current);
				current = current.getLeft();
			}
			current = s.pop();

			if (isBetter) {
				//check the key and also match the level are not equal
				if (current.getKey() > reference.getKey() && current.getLevel() != reference.getLevel())
					count++;

			} else {
				//check the key and also match the level are not equal
				if (current.getKey() < reference.getKey() && current.getLevel() != reference.getLevel())
					count++;

			}
			current = current.getRight();
		}
		return count;

	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're
	 * actually better than those over whom you're lording your achievements. This
	 * method should find all those friends who have a lower level than you, or zero
	 * if there are none (you suck).
	 * 
	 * @param reference The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countWorsePlayers(User reference) {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			return 0;
		}

		Stack<User> s = new Stack<User>();
		User current = root;

		while (current != null || s.size() > 0) {
			while (current != null) {
				s.push(current);
				current = current.getLeft();
			}
			current = s.pop();
			if (current.equals(reference)) {
				//player found then return the total count
				return countPlayer(reference, false);
			}
			current = current.getRight();
		}
		//player not found

		return -1;

	}

	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum
	 * trophies.
	 * 
	 * @return the user with the most platinum trophies, or null if there are none
	 */
	public User mostPlatinums() {

		if (root == null)
			return null;
		Stack<User> s = new Stack<User>();
		User current = root;
		User platinums = root;
		while (current != null || s.size() > 0) {
			while (current != null) {
				s.push(current);
				current = current.getLeft();
			}
			current = s.pop();
			//check the player and match the platinum rank if rank is equal then check the Gold 
			if (platinums.getTrophies(Rank.PLATINUM) < current.getTrophies(Rank.PLATINUM)) {
				platinums = current;
			} else if (platinums.getTrophies(Rank.PLATINUM) == current.getTrophies(Rank.PLATINUM)) {
				if (platinums.getTrophies(Rank.GOLD) < current.getTrophies(Rank.GOLD)) {
					platinums = current;
				}
			}
			current = current.getRight();
		}
		return platinums;

	}

	/**
	 * You or one of your friends bought a new game! This method should add it to
	 * their GameList.
	 * 
	 * @param username The user who has bought the game
	 * @param game     The game to be added
	 */
	public void addGame(String username, Game game) {
		if (username == null || game == null) {
			throw new IllegalArgumentException();
		}
		Stack<User> s = new Stack<User>();
		User current = root;

		//search player then add the game to player
		while (current != null || s.size() > 0) {
			while (current != null) {
				s.push(current);
				current = current.getLeft();
			}
			current = s.pop();
			if (current.getUsername().equals(username)) {
				GameList gList = current.getGames();
				//seach the game and add if not already added
				if (gList.getGame(game.getName()) == null) {
					gList.addGame(game);
				}
			}
			current = current.getRight();
		}

	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add it
	 * to their trophies.
	 * 
	 * @param username The user who has earned a new trophy
	 * @param trophy   The trophy to be added
	 */
	public void addTrophy(String username, Trophy trophy) {

		if (username == null || trophy == null) {
			throw new IllegalArgumentException();
		}

		Stack<User> s = new Stack<User>();
		User current = root;

		while (current != null || s.size() > 0) {
			while (current != null) {
				s.push(current);
				current = current.getLeft();
			}
			current = s.pop();
			//match with player and add the trophy 
			if (current.getUsername().equals(username)) {
				current.addTrophy(trophy);
			}
			current = current.getRight();
		}
	}

	/**
	 * You or one of your friends has gained one level! This is great news, except
	 * that it may have ruined your tree structure! A node move may be in order.
	 * 
	 * @param username The user whose level has increased
	 */
	
	//search the player then remove that , increase the level then add again to the tree
	public void levelUp(String username) {
		if (username == null)
			throw new IllegalArgumentException();

		Stack<User> s = new Stack<User>();
		User current = root;

		while (current != null || s.size() > 0) {
			while (current != null) {
				s.push(current);
				current = current.getLeft();
			}
			current = s.pop();
			if (current.getUsername().equals(username)) {
				break;
			}
			current = current.getRight();
		}
		deFriend(current);

	}

	/**
	 * As your friends list grows, adding with regular binary tree rules will result
	 * in an unbalanced and inefficient tree. One approach to fix this is to
	 * implement an add method that uses AVL balancing. This method should work in
	 * the same way as beFriend, but maintain a balanced tree according to AVL
	 * rules.
	 * 
	 * @param friend The friend to be added
	 * @return true if successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		return false;
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret
	 * scrap-book that you keep hidden underneath your pillow. This method should
	 * print out the details of each user, traversing the tree in order.
	 * 
	 * @return A string version of the tree
	 */
	public String toString() {
		String temp = "";
		Stack<User> s = new Stack<User>();
		User current = root;

		while (current != null || s.size() > 0) {
			while (current != null) {
				s.push(current);
				current = current.getLeft();
			}
			current = s.pop();
			temp += current + "\n";
			current = current.getRight();
		}
		System.out.println(temp);
		return temp.substring(0, temp.length() - 1);
	}
}
