package grupniProjekat_HotelManagement;

/*
 * Written by: Halim  
 * Edited by: Ahmed, Vedran, Sefer
 */

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyConnector implements HotelMgmt {
	/*
	 * Sefer Kuduzoviæ
	 */
	
	// Input connector za povezivanje na bazu podataka
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String DB = "hotel";

	boolean zauzeto = false;

	public static Connection connectToDB() throws SQLException {
		Connection connection = null;
		try {
			// SQL Driver
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DB, USERNAME, PASSWORD);
			System.out.println("Successfully connected to database!\n");
		} catch (Exception e) {
			System.err.println("Error! Check program!");
		}
		return connection;
	}

	// Admin username and password by Halim
	// *************************************************************************************************

	public Admin getAdminData(String username) {
		Admin admin = new Admin();
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("SELECT * FROM admin WHERE username = '" + username + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				admin = new Admin(result.getString("username"), result.getString("password"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return admin;
	}

	// Guest username and password by Halim
	// *************************************************************************************************

	public Guest getGuestData(String username) {
		Guest guest = new Guest();
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("SELECT * FROM guest WHERE username = '" + username + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest(result.getString("username"), result.getString("password"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return guest;
	}

	// read all guests by Halim
	// *****************************************************************************************************

	public Guest[] readAll() {
		Guest[] guests = new Guest[40];
		int counter = 0;
		try {
			PreparedStatement statement = connectToDB().prepareStatement("SELECT * FROM guest;");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				guests[counter] = new Guest(result.getString("username"), result.getString("password"),
						result.getString("name"), result.getString("surname"), result.getString("idnumber"),
						result.getString("gender"), result.getInt("age"), result.getInt("roomnumber"),
						result.getString("roomtype"), result.getString("timecheckedin"), result.getInt("numofdays"),
						result.getString("gym"), result.getString("pool"), result.getString("restaurant"),
						result.getString("sauna"), result.getString("cinema"));
				counter++;
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return guests;
	}

	// guest username i password ako je u bazi idnumber by Halim
	// ************************************************************************

	public Guest archiveCheck(String idnumber) {
		Guest guest = new Guest();
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("SELECT * FROM archive WHERE idnumber = '" + idnumber + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest(result.getString("username"), result.getString("password"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return guest;
	}

	// guest check in
	// ********************************************************************************************************
	/*
	 * Added room & username checking before adding guest - Sefer Kuduzovic
	 */
	public void addGuest(Guest guest) {
		try {
			String korisnickoIme = guest.getUsername();
			PreparedStatement statement2 = connectToDB()
					.prepareStatement("SELECT * FROM guest WHERE username = '" + korisnickoIme + "';");
			ResultSet result1 = statement2.executeQuery();
			while (result1.next()){
				String usernameDB = result1.getString("username");
				if (usernameDB.equals(korisnickoIme)){
					zauzeto = true;
					JFrame info1 = new JFrame();
					JOptionPane.showMessageDialog(info1, "Username already taken!");
				}
			}
			int brojSobe = guest.getRoomNumber();
			PreparedStatement statement1 = connectToDB()
					.prepareStatement("SELECT * FROM guest WHERE roomnumber = '" + brojSobe + "';");
			ResultSet result = statement1.executeQuery();
			while (result.next()) {
				int brojSobeDB = result.getInt("roomnumber");
				if (brojSobeDB == brojSobe) {
					zauzeto = true;
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "Room occupied!");
				}
			}
			if (zauzeto != true) {
				PreparedStatement statement = connectToDB().prepareStatement("INSERT INTO guest(username, password,"
						+ "name, surname, gender, idnumber, age, roomnumber, roomtype, timecheckedin, numofdays, gym, pool, "
						+ "restaurant, sauna, cinema) VALUES('" + guest.getUsername() + "','" + guest.getPassword()
						+ "','" + guest.getName() + "','" + guest.getSurname() + "','" + guest.getGender() + "','"
						+ guest.getIDnumber() + "'," + guest.getAge() + "," + guest.getRoomNumber() + ",'"
						+ guest.getRoomType() + "','" + guest.getTimeCheckedin() + "'," + guest.getNumOfDays() + ",'"
						+ guest.getGym() + "','" + guest.getPool() + "','" + guest.getRestaurant() + "','"
						+ guest.getSauna() + "','" + guest.getCinema() + "')");
				statement.executeUpdate();
			}
			;
		} catch (Exception e) {
			System.err.println("Ne radi addGuest check.");
		}
	}

	// setting username for room number guest checked in  by Halim
	// *********************************************************************************************************************************
	public void inRoom(String username, int roomnumber) {
		try {
			if (zauzeto != true) {
				PreparedStatement statement = connectToDB().prepareStatement(
						"UPDATE room SET username = '" + username + "' WHERE number = " + roomnumber + ";");
				statement.executeUpdate();
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// update servisa hotela by Halim
	// *************************************************************************************************************************************
	public void checkServices(String username, String service) {
		try {
			PreparedStatement statement1a = connectToDB()
					.prepareStatement("UPDATE guest SET gym = '" + service + "' WHERE username = '" + username + "' ;");
			PreparedStatement statement1b = connectToDB().prepareStatement(
					"UPDATE guest SET pool = '" + service + "' WHERE username = '" + username + "' ;");
			PreparedStatement statement1c = connectToDB().prepareStatement(
					"UPDATE guest SET restaurant = '" + service + "' WHERE username = '" + username + "' ;");
			PreparedStatement statement1d = connectToDB().prepareStatement(
					"UPDATE guest SET sauna = '" + service + "' WHERE username = '" + username + "' ;");
			PreparedStatement statement1e = connectToDB().prepareStatement(
					"UPDATE guest SET cinema = '" + service + "' WHERE username = '" + username + "' ;");
			statement1a.executeUpdate();
			statement1b.executeUpdate();
			statement1c.executeUpdate();
			statement1d.executeUpdate();
			statement1e.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// guest updates by Halim
	// ************************************************************************************************************************

	public void updateRoomNumber(String username, int roomnumber) {
		try {
			PreparedStatement statement1 = connectToDB()
					.prepareStatement("UPDATE room SET username = 'null' WHERE username = '" + username + "';");
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET roomnumber = " + roomnumber + " WHERE username = '" + username + "';");
			statement1.executeUpdate();
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void updateRoomType(String username, String roomtype, int roomnumber) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET roomtype = '" + roomtype + "' WHERE username = '" + username + "';");
			PreparedStatement statement1 = connectToDB()
					.prepareStatement("UPDATE room SET username = 'null' WHERE username = '" + username + "';");
			PreparedStatement statement2 = connectToDB().prepareStatement(
					"UPDATE room SET username = '" + username + "' WHERE number = " + roomnumber + ";");
			statement.executeUpdate();
			statement1.executeUpdate();
			statement2.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void updateDays(String username, int numofdays) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET numofdays = " + numofdays + " WHERE username = '" + username + "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// lookup guest search button by Halim
	// **************************************************************************************************************************

	public Guest guestLookup(String text) {
		Guest guest = new Guest();
		try {
			PreparedStatement statement = connectToDB().prepareStatement("SELECT * FROM guest WHERE name = '" + text
					+ "' OR idnumber = '" + text + "' OR username = '" + text + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest(result.getString("username"), result.getString("password"), result.getString("name"),
						result.getString("surname"), result.getString("gender"), result.getString("idnumber"),
						result.getInt("age"), result.getInt("roomnumber"), result.getString("roomtype"),
						result.getString("timecheckedin"), result.getInt("numofdays"), result.getString("gym"),
						result.getString("pool"), result.getString("restaurant"), result.getString("sauna"),
						result.getString("cinema"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return guest;
	}

	// guest bill by Halim
	// *****************************************************************************************************************************

	public Guest guestBill(String username) {
		Guest guest = new Guest();
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("SELECT * FROM guest WHERE username = '" + username + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest(result.getString("username"), result.getString("password"), result.getString("name"),
						result.getString("surname"), result.getString("idnumber"), result.getString("gender"),
						result.getInt("age"), result.getInt("roomnumber"), result.getString("roomtype"),
						result.getString("timecheckedin"), result.getInt("numofdays"), result.getString("gym"),
						result.getString("pool"), result.getString("restaurant"), result.getString("sauna"),
						result.getString("cinema"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return guest;
	}

	// guset check out by Halim
	// **********************************************************************************************************************

	public void guestCheckOut(String username) {
		try {
			PreparedStatement statement1 = connectToDB()
					.prepareStatement("UPDATE room SET username = 'null' WHERE username = '" + username + "';");
			PreparedStatement statement = connectToDB()
					.prepareStatement("DELETE FROM guest WHERE username = '" + username + "' ;");
			statement.executeUpdate();
			statement1.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// Vedran: Provjera slobodnih soba
	public ArrayList<Room> freeRooms(String type) {
		ArrayList<Room> nums = new ArrayList<>();
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("SELECT * FROM room WHERE type = '" + type + "' AND username = 'null' ;");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				nums.add(new Room(result.getInt("number")));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return nums;
	}

	public Room roomPrice(String username) {
		Room room = new Room();
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("SELECT * FROM room WHERE username = '" + username + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				room = new Room(result.getDouble("dayprice"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return room;
	}

	// arhiviranje gosta by Halim
	// *************************************************************************************************************************
	public void Archive(String idnumber, String username, String password) {
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("INSERT INTO archive(idnumber, username, password) VALUES('" + idnumber + "','"
							+ username + "','" + password + "')");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// notifikacija by Halim
	// ********************************************************************************************************************************
	public void notify(String username) {
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("INSERT INTO notifications(view) VALUES('" + username + "')");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void notifyClear(String username) {
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("DELETE FROM notifications WHERE view = '" + username + "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public HashSet<String> viewNot() {
		HashSet<String> notifications = new HashSet<String>();
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("SELECT * FROM notifications WHERE view IS NOT NULL ;");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				notifications.add((result.getString("view")));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return notifications;
	}

	// Ahmed code - Guest login / Logoff
	/** sets guest Status to 1 or 0, true or false */
	public void setStatus(String username, boolean status) {
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement("UPDATE guest SET active = " + status + " WHERE username = '" + username + "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public ArrayList<Guest> bindTable() {

		// creating a new list of type Guest
		ArrayList<Guest> list = new ArrayList<Guest>();
		// declaring a PreparedStatement and ResultSet
		try {
			PreparedStatement statement = connectToDB().prepareStatement("SELECT username, name, surname, gender,"
					+ " idnumber, age, roomnumber, roomtype FROM guest WHERE active = 1 ");
			ResultSet result = statement.executeQuery();
			Guest guest = null;
			while (result.next()) {
				guest = new Guest(result.getString("username"), result.getString("name"), result.getString("surname"),
						result.getString("gender"), result.getString("idnumber"), result.getInt("age"),
						result.getInt("roomnumber"), result.getString("roomtype"));
				list.add(guest);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		// return list
		return list;
	}
}