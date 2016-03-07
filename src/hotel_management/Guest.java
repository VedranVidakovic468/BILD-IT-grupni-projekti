package hotel_management;

/**
 * @author Vedran Vidakovic
 *
 */

public class Guest {

	private String username;
	private String password;
	private String name;
	private String surname;
	private String gender;
	private int IDnumber;
	private int age;
	private int roomNumber;
	private String roomType;
	private String timeCheckedin;
	private double bill;

	// no-arg konstruktor
	public Guest() {
	}

	// konstruktor sa data fields
	public Guest(String username, String password, String name, String surname, String gender, int iDnumber, int age,
			int roomNumber, String roomType, String timeCheckedin, double bill) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		IDnumber = iDnumber;
		this.age = age;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.timeCheckedin = timeCheckedin;
		this.bill = bill;
	}

	// GET METODE KOJE VRACAJU VREDNOSTI ZA DATA FIELDS
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getGender() {
		return gender;
	}

	public int getIDnumber() {
		return IDnumber;
	}

	public int getAge() {
		return age;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public String getTimeCheckedin() {
		return timeCheckedin;
	}

	public double getBill() {
		return bill;
	}

}
