package is.hi.lucky7;
import java.util.ArrayList;

public class Player {
	private String name; //Nafn leikmanns.
	private int id; //Au�kenni leikmanns.
	private int todo;
	private ArrayList<Country> countries = new ArrayList(); //Au�kenni landa sem leikma�ur �.
	public Player(int i, String n) {
		name = n;
		id = i;
		todo = 0;
	}
	public void setName(String n) {
		name = n;
	}
	public void setId(int i) {
		id = i;
	}
	public void addCountry(Country c) {
		c.getOwner().removeCountry(c);
		countries.add(c);
	}
	public void removeCountry(Country c) {
		countries.remove(c);
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public ArrayList<Country> getCountries() {
		return countries;
	}
	public void setTodo(int t) {
		todo = t;
	}
	public int getTodo() {
		return todo;
	}
}
