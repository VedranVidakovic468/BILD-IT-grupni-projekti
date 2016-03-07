package hotel_management;

public class Services {
	private double gym;
	private double cinema;
	private double pool;
	private double resturant;
	private double sauna;
	private String username;
	
	// no-arg konstruktor
	public Services() {

	}

	// konstruktor sa data fields
	public Services(double gym, double cinema, double pool, double resturant, double sauna, String username) {
		super();
		this.gym = gym;
		this.cinema = cinema;
		this.pool = pool;
		this.resturant = resturant;
		this.sauna = sauna;
		this.username = username;
	}

	// GET METODE KOJE VRACAJU VREDNOSTI ZA DATA FIELDS
	public double getGym() {
		return gym;
	}

	public double getCinema() {
		return cinema;
	}

	public double getPool() {
		return pool;
	}

	public double getResturant() {
		return resturant;
	}

	public double getSauna() {
		return sauna;
	}

	public String getUsername() {
		return username;
	}
	
	
	
}
