package grupniProjekat_HotelManagement;

/**
 * Vedran Vidakovic + Halim, Ahmed, Sefer (mods)
 *
 */

public class Guest {

	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String gender;
	private String IDnumber;
	private int age;
	private int roomNumber;
	private String roomType;
	private String timeCheckedin;
	private int numofdays;
	private String gym;
	private String pool;
	private String restaurant;
	private String sauna;
	private String cinema;

	// Ahmed code
	private boolean status;

	// constructor to return list of active users
	public Guest(String username, String name, String surname, String gender, String idnumber, int age, int roomnumber,
			String roomtype) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.IDnumber = idnumber;
		this.age = age;
		this.roomNumber = roomnumber;
		this.roomType = roomtype;

	}

	// constructor to change status of user in databse
	public Guest(String username, boolean status) {
		this.username = username;
		this.status = status;
	}

	/** return boolean status */
	public boolean getStatus() {
		return status;
	}

	/** sets status tp true or false */
	public void setActive(boolean status) {
		this.status = status;
	}

	// no-arg konstruktor
	public Guest() {
	}

	public Guest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Guest(String gym, String pool, String restaurant, String sauna, String cinema) {
		this.gym = gym;
		this.pool = pool;
		this.restaurant = restaurant;
		this.sauna = sauna;
		this.cinema = cinema;
	}

	public Guest(String IDnumber) {
		this.IDnumber = IDnumber;
	}

	// konstruktor sa data fields
	public Guest(String username, String password, String name, String surname, String gender, String IDnumber, int age,
			int roomNumber, String roomType, String timeCheckedin, int numofdays, String gym, String pool,
			String restaurant, String sauna, String cinema) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.IDnumber = IDnumber;
		this.age = age;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.timeCheckedin = timeCheckedin;
		this.numofdays = numofdays;
		this.gym = gym;
		this.pool = pool;
		this.restaurant = restaurant;
		this.sauna = sauna;
		this.cinema = cinema;
	}

	// GET METODE KOJE VRACAJU VREDNOSTI ZA DATA FIELDS
	/**Sefer readded */
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

	public String getIDnumber() {
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

	public int getNumOfDays() {
		return numofdays;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setIDnumber(String IDnumber) {
		this.IDnumber = IDnumber;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setTimeCheckedin(String timeCheckedin) {
		this.timeCheckedin = timeCheckedin;
	}

	public int getNumofdays() {
		return numofdays;
	}

	public void setNumofdays(int numofdays) {
		this.numofdays = numofdays;
	}

	public String getGym() {
		return gym;
	}

	public void setGym(String gym) {
		this.gym = gym;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getSauna() {
		return sauna;
	}

	public void setSauna(String sauna) {
		this.sauna = sauna;
	}

	public String getCinema() {
		return cinema;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

}