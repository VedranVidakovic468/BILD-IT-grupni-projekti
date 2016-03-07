package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Vedran Vidakovic
 *
 */
public class User {

	// Connection connection =
	// UpravljanjeKonekcijomDB_worldVedran.getInstance().getConnection();

	// provera trenutnog racuna
	public double accountBalance(String username) {
		Guest guest = null;
		// sql kod
		String sqlQuery = "SELECT * FROM guest WHERE username LIKE '" + username + "';";
		ResultSet rs = null;
		// provera je li ima rezultata iz baze podataka i smestanje u objekat
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);
			if (rs.next()) {

				guest = new Guest(rs.getString("username"), rs.getString("password"), rs.getString("name"),
						rs.getString("surname"), rs.getString("gender"), rs.getInt("iDnumber"), rs.getInt("age"),
						rs.getInt("roomNumber"), rs.getString("roomType"), rs.getString("timeCheckedin"),
						rs.getDouble("bill"));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
		// vraca trenutno stanje racuna
		return guest.getBill();
	}

	// menja vrednost teretane tj povecava sate koristenja
	public boolean addGym(String username, double newGym) {
		boolean check = false;
		Services services = null;
		// sql kod
		String sqlQuery = "SELECT * FROM services WHERE username LIKE '" + username + "';";
		ResultSet rs = null;
		// provera je li ima rezultata iz baze podataka i smestanje istih u data
		// fields
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);
			if (rs.next()) {

				services = new Services(rs.getDouble("gym"), rs.getDouble("cinema"), rs.getDouble("pool"),
						rs.getDouble("resturant"), rs.getDouble("sauna"), rs.getString("username"));
			}

			rs.close();

		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
		// PREDHODNO STANJE KORISTENJA SABIRAMO SA NOVIM BROJ DANA KORISTENJA
		double x = services.getGym() + newGym;
		String sqlQuery2 = "UPDATE services SET gym = " + x + " WHERE username LIKE '" + username + "';";
		try (Statement statement2 = connection.createStatement();) {
			statement2.executeUpdate(sqlQuery2);
			check = true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return check;
	}

	// menja vrednost kina tj povecava sate koristenja
	public boolean addCinema(String username, double newCinema) {
		boolean check = false;
		Services services = null;
		// sql kod
		String sqlQuery = "SELECT * FROM services WHERE username LIKE '" + username + "';";
		ResultSet rs = null;
		// provera je li ima rezultata iz baze podataka i smestanje istih u data
		// fields
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);
			if (rs.next()) {

				services = new Services(rs.getDouble("gym"), rs.getDouble("cinema"), rs.getDouble("pool"),
						rs.getDouble("resturant"), rs.getDouble("sauna"), rs.getString("username"));
			}

			rs.close();

		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
		// PREDHODNO STANJE KORISTENJA SABIRAMO SA NOVIM BROJ DANA KORISTENJA
		double x = services.getCinema() + newCinema;
		String sqlQuery2 = "UPDATE services SET cinema = " + x + " WHERE username LIKE '" + username + "';";
		try (Statement statement2 = connection.createStatement();) {
			statement2.executeUpdate(sqlQuery2);
			check = true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return check;
	}

	// menja vrednost bazena tj povecava sate koristenja
	public boolean addPool(String username, double newPool) {
		boolean check = false;
		Services services = null;
		// sql kod
		String sqlQuery = "SELECT * FROM services WHERE username LIKE '" + username + "';";
		ResultSet rs = null;
		// provera je li ima rezultata iz baze podataka i smestanje istih u data
		// fields
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);
			if (rs.next()) {

				services = new Services(rs.getDouble("gym"), rs.getDouble("cinema"), rs.getDouble("pool"),
						rs.getDouble("resturant"), rs.getDouble("sauna"), rs.getString("username"));
			}

			rs.close();

		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
		// PREDHODNO STANJE KORISTENJA SABIRAMO SA NOVIM BROJ DANA KORISTENJA
		double x = services.getPool() + newPool;
		String sqlQuery2 = "UPDATE services SET pool = " + x + " WHERE username LIKE '" + username + "';";
		try (Statement statement2 = connection.createStatement();) {
			statement2.executeUpdate(sqlQuery2);
			check = true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return check;
	}

	// menja vrednost restorana tj povecava sate koristenja
	public boolean addResturant(String username, double newResturant) {
		boolean check = false;
		Services services = null;
		// sql kod
		String sqlQuery = "SELECT * FROM services WHERE username LIKE '" + username + "';";
		ResultSet rs = null;
		// provera je li ima rezultata iz baze podataka i smestanje istih u data
		// fields
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);
			if (rs.next()) {

				services = new Services(rs.getDouble("gym"), rs.getDouble("cinema"), rs.getDouble("pool"),
						rs.getDouble("resturant"), rs.getDouble("sauna"), rs.getString("username"));
			}

			rs.close();

		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
		// PREDHODNO STANJE KORISTENJA SABIRAMO SA NOVIM BROJ DANA KORISTENJA
		double x = services.getResturant() + newResturant;
		String sqlQuery2 = "UPDATE services SET resturant = " + x + " WHERE username LIKE '" + username + "';";
		try (Statement statement2 = connection.createStatement();) {
			statement2.executeUpdate(sqlQuery2);
			check = true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return check;
	}

	// menja vrednost saune tj povecava sate koristenja
	public boolean addSauna(String username, double newSauna) {
		boolean check = false;
		Services services = null;
		// sql kod
		String sqlQuery = "SELECT * FROM services WHERE username LIKE '" + username + "';";
		ResultSet rs = null;
		// provera je li ima rezultata iz baze podataka i smestanje istih u data
		// fields
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);
			if (rs.next()) {

				services = new Services(rs.getDouble("gym"), rs.getDouble("cinema"), rs.getDouble("pool"),
						rs.getDouble("resturant"), rs.getDouble("sauna"), rs.getString("username"));
			}

			rs.close();

		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
		// PREDHODNO STANJE KORISTENJA SABIRAMO SA NOVIM BROJ DANA KORISTENJA
		double x = services.getSauna() + newSauna;
		String sqlQuery2 = "UPDATE services SET sauna = " + x + " WHERE username LIKE '" + username + "';";
		try (Statement statement2 = connection.createStatement();) {
			statement2.executeUpdate(sqlQuery2);
			check = true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return check;
	}
}
