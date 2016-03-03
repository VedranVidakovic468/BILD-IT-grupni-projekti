/*Zadatak koji treba rijesiti je sljedeci - napisati program koji dopusta krajnjem  
korisniku da pretrazuje attachanu bazu po imenu drzave, broju stanovnika ili
po gradu iz te drzave. Vas program treba da ispise sto vise podataka iz baze za 
svaki rezultat - ALI da to bude fino i citko predstavljeno. 
(Namjerno je vaguely definisan zadatak jer sam gurbet :D)*/
package dio1Priprema_pretrazivanje_DB;

/**
 * @author Vedran Vidakovic
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UpravljanjeKonekcijomDB_worldVedran {

	// lista u kojoj smestamo podatke iz txt dokumenta
	private ArrayList<String> a = new ArrayList<>();

	// objekat ove klase
	private static UpravljanjeKonekcijomDB_worldVedran instance = null;
	// uspostavljanje konekcije sa serverom mysql
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String CONN_STRING1 = "jdbc:mysql://localhost";
	// konekcija sa bazom podataka
	private final String CONN_STRING2 = "jdbc:mysql://localhost/world_vedran";

	// objekat konekcije
	private Connection connection = null;

	// metoda koja cita podtake iz txt dokumenta
	private void reading() {
		// dokument malo izmenjen to jeste napravljen da je jedan red iz
		// dokumenta sql kod
		File file = new File("world.txt");
		Scanner input2;
		try {
			input2 = new Scanner(file);
			// iz fajla recenice smestamo u listu
			while (input2.hasNextLine()) {
				a.add(input2.nextLine());
			}
			input2.close();
			// u slucaju greske
		} catch (FileNotFoundException e) {
			System.out.println("File not found!!!");
		}

	}

	// kreiramo bazu podataka
	public void createDatabaseWorldVedran() throws SQLException {
		String sqlQuery = "CREATE DATABASE world_vedran;";

		try (Connection connection = DriverManager.getConnection(CONN_STRING1, USERNAME, PASSWORD);
				Statement statement = connection.createStatement();) {
			// ako je izvrseno kreiranje baze podataka pokreni metode za citanje
			// fajlsa i unosenje podataka u bazu podataka
			statement.executeUpdate(sqlQuery);
			System.out.println("Created databases!!!");
			reading();
			createTablesAndDataEntry();
		} catch (SQLException e) {
		}
	}

	// metoda koja upisuje podatke u bazu podataka
	private void createTablesAndDataEntry() {
		// preko for-each iscitavamo podatke liste
		for (String p : a) {
			// ponistavanje sql koda
			String sqlQuery = "";
			// dodela vrednosti sql kodu
			sqlQuery = p;
			try (Connection connection = DriverManager.getConnection(CONN_STRING2, USERNAME, PASSWORD);
					Statement statement = connection.createStatement();) {
				// izvrsenje sql koda
				statement.executeUpdate(sqlQuery);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		System.out.println("Performed data entry into the database!!!");
	}

	// konstruktor konekcije
	public UpravljanjeKonekcijomDB_worldVedran() {
	}

	// metoda koja vraca objekat ove klase
	public static UpravljanjeKonekcijomDB_worldVedran getInstance() {
		// ako je instanca odnosno objekat prazan
		if (instance == null) {
			// napravi novi objekat ove klase
			instance = new UpravljanjeKonekcijomDB_worldVedran();
		}
		// vrati instancu
		return instance;
	}

	// metoda preko koje se konektujemo na bazu podataka
	private boolean openConnection() {
		try {
			connection = DriverManager.getConnection(CONN_STRING2, USERNAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	// metoda sa kojom proveravamo jesmo li konektovani na bazu podataka
	public Connection getConnection() {
		if (connection == null) {
			if (openConnection()) {
				return connection;
			} else {
				System.out.println("Connection not opened!!!");
				return null;
			}
		}
		return connection;
	}

}
