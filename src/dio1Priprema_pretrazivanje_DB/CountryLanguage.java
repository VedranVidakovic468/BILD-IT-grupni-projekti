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

public class CountryLanguage implements Comparable<CountryLanguage> {

	// data fields za polja iz tabele jezici
	private String language = "";
	private String isOfficial = "";
	private float percentage = 0;
	private String CountryCode = "";

	// no-arg konstruktor
	public String getCountryCode() {
		return CountryCode;
	}

	// konstruktor sa data fields
	public CountryLanguage(String CountryCode, String language, String isOfficial, float percentage) {
		this.CountryCode = CountryCode;
		this.language = language;
		this.isOfficial = isOfficial;
		this.percentage = percentage;
	}

	// GET METODE ZA DATA FIELDS
	public String getLanguage() {
		return language;
	}

	public String getIsOfficial() {
		return isOfficial;
	}

	public float getPercentage() {
		return percentage;
	}

	// izmenjena metoda ic interfejsa definise da sortiranje objekata bude
	// izvrseno po procentu zastupljenosti jezika u nekoj drzavi
	@Override
	public int compareTo(CountryLanguage o) {
		if (this.getPercentage() < o.getPercentage()) {
			return 1;
		} else if (this.getPercentage() > o.getPercentage()) {
			return -1;
		} else {
			return 0;
		}
	}
}
