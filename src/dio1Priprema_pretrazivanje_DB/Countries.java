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

public class Countries implements Comparable<Countries> {

	// data fields za podatke koji se nalaze u tabeli drzave
	private String code = "";
	private String contryName = "";
	private String continent = "";
	private String region = "";
	private String surfaceArea = "";
	private String indepYear = "";
	private int population = 0;
	private String lifeExpectancy = "";
	private String GNP = "";
	private String GNPOld = "";
	private String localName = "";
	private String governmentForm = "";
	private String headOfState = "";
	private String capital = "";
	private String code2 = "";

	// lista jezika koji se govore u nekoj drzavi
	private ArrayList<CountryLanguage> l = new ArrayList<>();
	// lista gradova
	private ArrayList<City> c = new ArrayList<>();

	// objekat klase jezici
	CountryLanguage o = null;

	// uspostavljanje konekcije sa bazom podataka
	Connection connection = UpravljanjeKonekcijomDB_worldVedran.getInstance().getConnection();

	// no-arg konstruktor
	public Countries() {

	}

	// konstruktor sa data fields
	public Countries(String code, String contryName, String continent, String region, String surfaceArea,
			String indepYear, int population, String lifeExpectancy, String gNP, String gNPOld, String localName,
			String governmentForm, String headOfState, String capital, String code2) {
		this.code = code;
		this.contryName = contryName;
		this.continent = continent;
		this.region = region;
		this.surfaceArea = surfaceArea;
		this.indepYear = indepYear;
		this.population = population;
		this.lifeExpectancy = lifeExpectancy;
		GNP = gNP;
		GNPOld = gNPOld;
		this.localName = localName;
		this.governmentForm = governmentForm;
		this.headOfState = headOfState;
		this.capital = capital;
		this.code2 = code2;
	}

	// metoda koja proverava da li postoji u bazi drzava sa dobijenim imenom
	public boolean country(String nameCountry) {
		boolean check = false;
		// sql kod
		String sqlQuery = "SELECT * FROM Country WHERE Name LIKE '" + nameCountry + "';";
		ResultSet rs = null;
		// provera je li ima rezultata iz baze podataka i smestanje istih u data
		// fields
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);
			if (rs.next()) {

				code = rs.getString("Code");
				contryName = rs.getString("Name");
				continent = rs.getString("Continent");
				region = rs.getString("Region");
				surfaceArea = rs.getString("SurfaceArea");
				indepYear = rs.getString("IndepYear");
				population = rs.getInt("Population");
				lifeExpectancy = rs.getString("LifeExpectancy");
				GNP = rs.getString("GNP");
				GNPOld = rs.getString("GNPOld");
				localName = rs.getString("LocalName");
				governmentForm = rs.getString("GovernmentForm");
				headOfState = rs.getString("HeadOfState");
				capital = rs.getString("Capital");
				code2 = rs.getString("Code2");
				// ako je bilo rezultata metoda vraca true inace vraca false
				check = true;
			}

			rs.close();
			countryLanguage();
			countryCity();

		} catch (SQLException e) {
			System.err.println("Error!!!");
		}
		return check;
	}

	// metoda koja na osnovu koda zemlje u drugogoj tabeli proverava koliko se
	// jezika govori u toj zemlji
	private void countryLanguage() {
		String sqlQuery = "SELECT * FROM CountryLanguage WHERE CountryCode LIKE '" + code + "';";
		ResultSet rs = null;

		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);

			// rezultate smestamo u listu kao objekte klase
			while (rs.next()) {
				l.add(new CountryLanguage(rs.getString("CountryCode"), rs.getString("Language"),
						rs.getString("IsOfficial"), rs.getFloat("Percentage")));
			}
			Collections.sort(l);
			rs.close();
			// u slucaju greske
		} catch (SQLException e) {
			System.err.println("Error!!!");
		}

	}

	// metoda koja na osnovu koda drzave proverava iz druge tabele koji su
	// gradovi veci te drzave
	private void countryCity() {

		String sqlQuery = "SELECT * FROM City WHERE CountryCode LIKE '" + code + "';";
		ResultSet rs = null;

		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(sqlQuery);

			// rezultate smestamo un listu kao objekte klase gradovi
			while (rs.next()) {
				c.add(new City(rs.getString("ID"), rs.getString("Name"), rs.getString("CountryCode"),
						rs.getString("District"), rs.getInt("Population")));
			}
			// sortiramo niz prema uslovima metode compare to
			Collections.sort(c);
			rs.close();
			// u slucaju greske
		} catch (SQLException e) {
			System.err.println("Error!!!");
		}

	}

	// metoda koja stampa rezultate dobijene iz baze podataka
	public void toPrint() {
		System.out.println("\nCountry code:\t" + code + ";\nContry name:\t" + contryName + ";\nContinent:\t" + continent
				+ ";\nRegion:\t" + region + ";\nSurface arae:\t" + surfaceArea + ";\nIndep year:\t" + indepYear
				+ ";\nPopulation:\t" + population + ";\nLife Expectancy:\t" + lifeExpectancy + ";\nGNP:\t" + GNP
				+ ";\nGNPO id:\t" + GNPOld + ";\nLocal name:\t" + localName + ";\nGovernmen from:\t" + governmentForm
				+ ";\nHead of state:\t" + headOfState + ";\nCapital:\t" + capital + ";\nCode 2:\t" + code2 + ".");

		System.out.println("\nThe languages spoken in " + contryName + " country:\n");
		System.out.printf("        %-20s \t\t%-15s \t%-15s", "language", "is official", "percentage");
		System.out.println("\n--------------------------------------------------------------------");
		for (int i = 0; i < l.size(); i++) {
			System.out.printf("%-4d | %-25s \t\t%-12s \t%12s", (i + 1), l.get(i).getLanguage(),
					l.get(i).getIsOfficial(), l.get(i).getPercentage());
			System.out.println("\n--------------------------------------------------------------------");
		}
		System.out.println();
		System.out.println("Major cities in the country " + contryName + ": \n");
		System.out.printf("        %-20s \t\t%-15s \t\t%-15s", "name city", "district", "population");
		System.out.println("\n--------------------------------------------------------------------");
		for (int i = 0; i < c.size(); i++) {
			System.out.printf("%-4d | %-25s \t%-18s \t%12s", (i + 1), c.get(i).getName(), c.get(i).getDistrict(),
					c.get(i).getPopulation());
			System.out.println("\n--------------------------------------------------------------------");
		}
	}

	// get metode za data fields
	public String getCode() {
		return code;
	}

	public String getContryName() {
		return contryName;
	}

	public String getContinent() {
		return continent;
	}

	public String getRegion() {
		return region;
	}

	public String getSurfaceArea() {
		return surfaceArea;
	}

	public String getIndepYear() {
		return indepYear;
	}

	public int getPopulation() {
		return population;
	}

	public String getLifeExpectancy() {
		return lifeExpectancy;
	}

	public String getGNP() {
		return GNP;
	}

	public String getGNPOld() {
		return GNPOld;
	}

	public String getLocalName() {
		return localName;
	}

	public String getGovernmentForm() {
		return governmentForm;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public String getCapital() {
		return capital;
	}

	public String getCode2() {
		return code2;
	}

	public ArrayList<CountryLanguage> getL() {
		return l;
	}

	public ArrayList<City> getC() {
		return c;
	}

	// metoda izmenjena iz interface Comparable da lista bude sortirana prema
	// velicini populacije i to opadajucim redosledom
	@Override
	public int compareTo(Countries o) {
		if (this.getPopulation() < o.getPopulation()) {
			return 1;
		} else if (this.getPopulation() > o.getPopulation()) {
			return -1;
		} else {
			return 0;
		}
	}
}
