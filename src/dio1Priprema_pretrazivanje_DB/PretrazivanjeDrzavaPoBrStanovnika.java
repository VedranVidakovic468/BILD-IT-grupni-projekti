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

public class PretrazivanjeDrzavaPoBrStanovnika {

	// uspostavljanje konekcije sa bazom podataka
	Connection connection = UpravljanjeKonekcijomDB_worldVedran.getInstance().getConnection();

	// lista sa drzavama
	private ArrayList<Countries> c = new ArrayList<>();

	// metoda koja vraca drzave izmedju ogranicenja dobijenih o broju stanovnika
	public void countries(long start, long end) {

		// sql kod
		String sqlQuery = "SELECT * FROM Country WHERE Population BETWEEN " + start + " AND " + end + ";";
		ResultSet rs = null;

		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);

			// rezultate smestamo u listu
			while (rs.next()) {
				c.add(new Countries(rs.getString("Code"), rs.getString("Name"), rs.getString("Continent"),
						rs.getString("Region"), rs.getString("SurfaceArea"), rs.getString("IndepYear"),
						rs.getInt("Population"), rs.getString("LifeExpectancy"), rs.getString("GNP"),
						rs.getString("GNPOld"), rs.getString("LocalName"), rs.getString("GovernmentForm"),
						rs.getString("HeadOfState"), rs.getString("Capital"), rs.getString("Code2")));
			}
			// sortiramo prema uslovima u metodi iz interface
			Collections.sort(c);
			rs.close();
			// u slucaju greske
		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
	}

	// metoda koja stampa rezultate
	public void toPrint() {
		System.out.printf("\n        %-12s %-15s  \t%-15s \t\t\t\t\t%-30s \t\t\t\t%-30s \t\t\t\t%-30s ", "code",
				"population", "name", "continent", "surface area", "indep year");

		System.out.println("\n--------------------------------------------------------------------------------"
				+ "------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < c.size(); i++) {
			System.out.printf("%d | %-3s \t\t%-10d \t\t%-40s \t\t%-30s  \t\t%-30s \t\t\t\t%20s ", (i + 1),
					c.get(i).getCode(), c.get(i).getPopulation(), c.get(i).getContryName(), c.get(i).getContinent(),
					c.get(i).getSurfaceArea(), c.get(i).getIndepYear());
			System.out.println("\n--------------------------------------------------------------------------------"
					+ "------------------------------------------------------------------------------------------------------");
		}
	}
}
