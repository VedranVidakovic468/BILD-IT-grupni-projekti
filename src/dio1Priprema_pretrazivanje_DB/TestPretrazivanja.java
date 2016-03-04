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

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestPretrazivanja {

	public static void main(String[] args) {

		try {
			// PRVI PUTA MORA MALO DA SE SACEKA DA SE IZVRSI UPIS U BAZU
			// PODATAKA, AL DRUGI PUTA IDE PRETRAZIVANJE MNOGO BRZE
			// kreiranje baze podataka ako ne postoji
			UpravljanjeKonekcijomDB_worldVedran con = new UpravljanjeKonekcijomDB_worldVedran();
			con.createDatabaseWorldVedran();
		} catch (Exception e) {
			// na kraju izvrsi
		} finally {
			// kreiramo skene
			Scanner input = new Scanner(System.in);
			// nastavi unos varijable za proveru je li korisniik uneo pravilan
			// unos
			boolean continueInput = true;
			// varijabla za izbor opcije
			int selection = 0;

			// provera unosa korisnikova
			while (continueInput) {
				System.out.print("To print all data on the country enter 1,"
						+ "\nto print a state with a population of from-to enter the 2, "
						+ "\nand 3 to print the cities with a population from-to: ");
				try {
					selection = input.nextInt();
					// da li je izbor korisnikov pravilan
					if (selection == 1 || selection == 2 || selection == 3) {
						// ako je tacan zustavi petlju
						continueInput = false;
					}
					// u slucaju greske
				} catch (InputMismatchException ex) {
					input.nextLine(); // Discard input, odbaci predhodni unos
				}
			}
			// odbacujemo predhodni unos
			input.nextLine();
			// ponistavamo varijablu za proveru
			continueInput = true;
			// u slucaju da korisnik zeli dobiti podatke sve o nekoj drzavi
			if (selection == 1) {
				Countries c = new Countries();
				while (continueInput) {
					System.out.println("\nEnter the name of the country: ");
					String name = input.nextLine();
					// Yugoslavia
					// provera je li postoji ta drzava u bazi podataka
					boolean check = c.country(name);
					if (check) {
						c.toPrint();
						continueInput = false;
					} else {
						System.out.println("This country does not exist in the database try again!!!");
					}
				}
				// u slucaju da korisnik zeli pretraziti drzave sa brojem
				// stanovnika od do
			} else if (selection == 2) {
				long start = 0;
				long end = 0;
				while (continueInput) {
					System.out.println(
							"\nEnter the initial and final number of the population to see populations in the states: ");
					start = input.nextLong();
					end = input.nextLong();
					// provera je li broj veci od 0 i startni broj manji od
					// krajnjeg
					if ((start < end) && start >= 0 && end >= 0) {
						PretrazivanjeDrzavaPoBrStanovnika c = new PretrazivanjeDrzavaPoBrStanovnika();
						c.countries(start, end);
						c.toPrint();
						continueInput = false;
					} else {
						System.out.println("Invalid input, please try again!!!");
					}
				}
				// u slucaju da korisnik zeli pretraziti gradove sa brojem
				// stanovnika od do
			} else {
				long start = 0;
				long end = 0;
				while (continueInput) {
					System.out.println(
							"\nEnter the initial and final number of the population to see the population in cities: ");
					start = input.nextLong();
					end = input.nextLong();
					// provera je li broj veci od 0 i startni broj manji od
					// krajnjeg
					if ((start < end) && start >= 0 && end >= 0) {
						PretrazivanjeGradovaPoBrStanovnika c = new PretrazivanjeGradovaPoBrStanovnika();
						c.countryCity(start, end);
						c.toPrint();
						continueInput = false;
					} else {
						System.out.println("Invalid input, please try again!!!");
					}
				}
			}
			// zatvaramo unos
			input.close();
		}
	}

}
