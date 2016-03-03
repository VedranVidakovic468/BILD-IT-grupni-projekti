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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class PretrazivanjeGradovaPoBrStanovnika {
	// uspostavljanje konekcije sa bazom podataka
	Connection connection = UpravljanjeKonekcijomDB_worldVedran.getInstance().getConnection();

	// lista za gradove
	private ArrayList<City> c = new ArrayList<>();

	// metoda koja pohranjuje u listu gradove izmedju dobijenih ogranicenja o
	// broju stanovnika
	public void countryCity(long start, long end) {

		// sql kod
		String sqlQuery = "SELECT * FROM City WHERE Population BETWEEN " + start + " AND " + end + ";";
		ResultSet rs = null;

		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);

			// rezultate iz baze podataka smestamo u listu
			while (rs.next()) {
				c.add(new City(rs.getString("ID"), rs.getString("Name"), rs.getString("CountryCode"),
						rs.getString("District"), rs.getInt("Population")));
			}
			// sortiramo listu prema uslovima metode iz interface
			Collections.sort(c);
			rs.close();
			// u slucaju greske
		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
	}

	// metoda koja stampa dobijene rezultate
	public void toPrint() {
		System.out.printf("\n        %s \t\t%s \t\t%s \t\t\t\t\t%s", "country code", "population", "name city",
				"district");
		System.out.println(
				"\n--------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < c.size(); i++) {
			System.out.printf("%d | %-25s \t\t%-12s \t %-26s \t\t%25s", (i + 1), c.get(i).getCountryCode(),
					c.get(i).getPopulation(), c.get(i).getName(), c.get(i).getDistrict());
			System.out.println(
					"\n--------------------------------------------------------------------------------------------------------------------------------");
		}
	}

}
