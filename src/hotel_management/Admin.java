package hotel_management;

/**
 * @author Vedran Vidakovic
 *
 */

public class Admin {

	private String username;
	private String password;

	// no-arg konstruktor
	public Admin() {
	}

	// konstruktor sa data fields
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// GET METODE KOJE VRACAJU VREDNOSTI ZA DATA FIELDS
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
