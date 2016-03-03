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

public class City implements Comparable<City>{

	// data fields za podatke sadrzane u tabeli gradovi
	private String ID = "";
	private String name = "";
	private String countryCode = "";
	private String district = "";
	private int population = 0;
	
	// konstruktor sa data fields 
	public City(String iD, String name, String countryCode, String district, int population) {
		super();
		this.ID = iD;
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
	}

	//  GET METODE KOJE VRACAJU VREDNOST DADA FIELDS
	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public int getPopulation() {
		return population;
	}

	// izmenjena metoda iz interfejsa definise da sortiranje objekata bude
		// izvrseno po populacijij zastupljenoj u gradovima
	@Override
	public int compareTo(City o) {
		if (this.getPopulation() < o.getPopulation()) {
			return 1;
		} else if (this.getPopulation() > o.getPopulation()) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
