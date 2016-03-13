package grupniProjekat_HotelManagement;

/**
 * @author Vedran Vidakovic
 */

public final class Admin {

	private String username;
	private String password;

	// konstruktor sa data fields
	/**@Sefer
	 * 
	 * @param username
	 * @param password
	 */
	
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Admin() {
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

}
