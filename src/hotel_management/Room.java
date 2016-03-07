package hotel_management;

/**
 * @author Vedran Vidakovic
 *
 */
public class Room {

	private int number;
	private String type;
	private double dayPrice;

	// no-arg konstruktor
	public Room() {

	}

	// konstruktor sa data fields
	public Room(int number, String type, double dayPrice) {
		this.number = number;
		this.type = type;
		this.dayPrice = dayPrice;
	}

	// GET METODE KOJE VRACAJU VREDNOSTI ZA DATA FIELDS
	public long getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public double getDayPrice() {
		return dayPrice;
	}

}
