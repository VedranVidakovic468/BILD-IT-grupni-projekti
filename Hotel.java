 package grupniProjekat_HotelManagement;

import javax.swing.*; 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Hotel extends JFrame implements ActionListener {

	/**
	 * Version: 1.0 
	 * Radili: Halim Marmarac / Ahmed Salkiæ / Vedran Vidakoviæ /
	 * Sefer Kuduzoviæ
	 */
	private static final long serialVersionUID = 1L;

	String gen[] = { "?", "Male", "Female" };
	String Gender = "";
	String roomt[] = { "?", "Single", "Double", "Suite" };
	String Room = "";
	String GYM[] = { "NO", "YES" };
	String POOL[] = { "NO", "YES" };
	String RESTAURANT[] = { "NO", "YES" };
	String SAUNA[] = { "NO", "YES" };
	String CINEMA[] = { "NO", "YES" };
	String Gym = "NO";
	String Pool = "NO";
	String Restaurant = "NO";
	String Sauna = "NO";
	String Cinema = "NO";
	public static final int GYM_PRICE = 10;
	public static final int CINEMA_PRICE = 10;
	public static final int RESTAURANT_PRICE = 20;
	public static final int POOL_PRICE = 10;
	public static final int SAUNA_PRICE = 10;

	private JTable activeGuestTable;
	private JFrame guestwindow;
	private JFrame usernameWindow;

	HotelMgmt hotelmgmt = new MyConnector();
	
	/*
	 * Bugfix + Commentaries + Grammar: Sefer
	 */

	// main windows - Halim
	// ****************************************************************************************************************

	public Hotel() {
		super("Hotel Management");
		setSize(400, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		Container cont = getContentPane();
		cont.setLayout(null);
		// background panel
		JPanel start = new JPanel();
		start.setBounds(2, 2, 390, 217);
		start.setLayout(null);
		start.setBackground(Color.lightGray);
		cont.add(start);
		// admin button
		JButton admin = new JButton("Administrator");
		admin.setBounds(120, 40, 150, 30);
		// guest button
		JButton guest = new JButton("Guest");
		guest.setBounds(120, 80, 150, 30);
		// close app button
		JButton close = new JButton("Close");
		close.setBounds(120, 140, 150, 30);
		start.add(admin);
		start.add(guest);
		start.add(close);
		// JLabes za username i password
		JLabel username = new JLabel("Username: ");
		username.setBounds(50, 20, 100, 30);
		username.setVisible(false);
		JLabel password = new JLabel("Password: ");
		password.setBounds(50, 60, 100, 30);
		password.setVisible(false);
		// polje za unos username
		JTextField usernameinput = new JTextField(20);
		usernameinput.setBounds(150, 20, 190, 30);
		usernameinput.setVisible(false);
		// polje za unos password
		final JPasswordField passwordinput = new JPasswordField(16);
		passwordinput.setBounds(150, 60, 190, 30);
		passwordinput.setVisible(false);
		start.add(username);
		start.add(usernameinput);
		start.add(password);
		start.add(passwordinput);
		// ok button za login admin
		JButton okadmin = new JButton("OK");
		okadmin.setBounds(105, 160, 80, 30);
		okadmin.setVisible(false);
		// ok button za login guest
		JButton okguest = new JButton("OK");
		okguest.setBounds(105, 160, 80, 30);
		okguest.setVisible(false);
		// vraca program na pocetak
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(205, 160, 80, 30);
		cancel.setVisible(false);
		start.add(okadmin);
		start.add(okguest);
		start.add(cancel);

		// Bill window - Halim
		// ****************************************************************************************************

		JFrame billwindow = new JFrame("Administrator");
		billwindow.setSize(600, 500);
		billwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		billwindow.setResizable(false);
		billwindow.setLocationRelativeTo(null);
		billwindow.setVisible(false);
		// background panel
		JPanel billpanel = new JPanel();
		billpanel.setBounds(0, 0, 600, 500);
		billpanel.setLayout(null);
		billpanel.setBackground(Color.lightGray);
		billwindow.add(billpanel);
		// text area za prikaz racuna
		JTextArea billtext = new JTextArea();
		billtext.setBounds(4, 2, 585, 400);
		billtext.setEditable(false);
		billpanel.add(billtext);
		// zatvaranje pregleda racuna
		JButton billOK = new JButton("OK");
		billOK.setBounds(250, 415, 100, 30);
		billpanel.add(billOK);

		// Admin windows - Halim
		// **********************************************************************************************************************************

		JFrame administratorwindow = new JFrame("Administrator");
		administratorwindow.setSize(800, 600);
		administratorwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		administratorwindow.setResizable(false);
		administratorwindow.setLocationRelativeTo(null);
		administratorwindow.setVisible(false);
		// paneli za tabove u prozoru admin
		JPanel admintab1 = new JPanel();
		admintab1.setBounds(0, 0, 800, 600);
		admintab1.setLayout(null);
		admintab1.setBackground(Color.lightGray);
		administratorwindow.add(admintab1);
		JPanel admintab2 = new JPanel();
		admintab2.setBounds(0, 0, 800, 600);
		admintab2.setLayout(null);
		admintab2.setBackground(Color.lightGray);
		administratorwindow.add(admintab2);
		JPanel admintab3 = new JPanel();
		admintab3.setBounds(0, 0, 800, 600);
		admintab3.setLayout(null);
		admintab3.setBackground(Color.lightGray);
		administratorwindow.add(admintab3);

		// Room numbers - Halim
		// ******************************************************************************************************************************

		JFrame freeroomswindow = new JFrame("FREE ROOMS");
		freeroomswindow.setSize(300, 400);
		freeroomswindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		freeroomswindow.setResizable(false);
		freeroomswindow.setLocationRelativeTo(null);
		freeroomswindow.setVisible(false);

		JPanel freepanel = new JPanel();
		freepanel.setBounds(10, 0, 290, 390);
		freepanel.setLayout(null);
		freeroomswindow.add(freepanel);
		// text area to display free room for selected type
		JTextArea roomsarea = new JTextArea();
		roomsarea.setBounds(0, 0, 290, 390);
		roomsarea.setEditable(false);
		freepanel.add(roomsarea);

		// guest window - Halim  + Ahmed (mods)
		// **********************************************************************************************************************************

		guestwindow = new JFrame();
		guestwindow.setSize(800, 600);
		guestwindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		guestwindow.setResizable(false);
		guestwindow.setLocationRelativeTo(null);
		guestwindow.setVisible(false);
		// panel za dugmice
		JPanel guestpanelbtn = new JPanel();
		guestpanelbtn.setBounds(0, 0, 150, 600);
		guestpanelbtn.setBackground(Color.gray);
		guestpanelbtn.setLayout(null);
		guestwindow.add(guestpanelbtn);
		// panel za prikaz informacija
		JPanel guestpanel = new JPanel();
		guestpanel.setBounds(150, 0, 650, 600);
		guestpanel.setBackground(Color.lightGray);
		guestpanel.setLayout(null);
		guestwindow.add(guestpanel);
		// button za pregled racuna za datog korisnika
		JButton billpreview = new JButton("MY BILL");
		billpreview.setBounds(15, 30, 120, 30);
		guestpanelbtn.add(billpreview);
		// button kojim gost notifikuje admina da zeli check out
		JButton guestcheckout = new JButton("CHECK OUT");
		guestcheckout.setBounds(15, 80, 120, 30);
		guestpanelbtn.add(guestcheckout);
		// button kojim gost omogucava update tipa sobe
		JButton updateroom = new JButton("Change room");
		updateroom.setBounds(15, 150, 120, 30);
		guestpanelbtn.add(updateroom);
		// button kojim gost omogucaa update ostalih usluga hotela
		JButton updateservice = new JButton("Update services");
		updateservice.setBounds(12, 200, 126, 30);
		guestpanelbtn.add(updateservice);
		// button za spasavanje update-a gosta
		JButton saveupdates = new JButton("SAVE");
		saveupdates.setBounds(15, 260, 120, 30);
		saveupdates.setVisible(false);
		guestpanelbtn.add(saveupdates);
		// button za logout gosta iz svog account profila
		JButton guestlogout = new JButton("LOG OUT");
		guestlogout.setBounds(15, 500, 120, 30);
		guestpanelbtn.add(guestlogout);
		// polje za prikaz racuna na zahtjev gosta
		JTextArea guesttxt = new JTextArea();
		guesttxt.setBounds(155, 5, 634, 400);
		guesttxt.setEditable(false);
		guesttxt.setVisible(false);
		guesttxt.setText("");
		guestpanel.add(guesttxt);
		// panel sa boxovima za biranje usluga hotela
		JPanel cboxbuttonsg = new JPanel();
		cboxbuttonsg.setBounds(155, 410, 634, 30);
		cboxbuttonsg.setLayout(new GridLayout(1, 10));
		cboxbuttonsg.setBackground(Color.lightGray);
		cboxbuttonsg.setVisible(false);
		guestpanel.add(cboxbuttonsg);
		// panel sa boxom i poljem za unos nove sobe na zahtjev gosta
		JPanel roomtypeg = new JPanel();
		roomtypeg.setBounds(155, 470, 634, 50);
		roomtypeg.setLayout(null);
		roomtypeg.setBackground(Color.lightGray);
		roomtypeg.setVisible(false);
		guestpanel.add(roomtypeg);
		// label za room type chooser
		JLabel roomtypegl = new JLabel("Room type:");
		roomtypegl.setBounds(10, 10, 100, 30);
		roomtypeg.add(roomtypegl);
		// box za biranje tipa sobe
		JComboBox<String> roomtypec = new JComboBox<String>(roomt);
		roomtypec.setSelectedIndex(0);// uzet iz baze
		roomtypec.setBounds(90, 10, 100, 30);
		roomtypeg.add(roomtypec);
		// label
		JLabel roomnum = new JLabel("Room number:");
		roomnum.setBounds(240, 10, 100, 30);
		roomtypeg.add(roomnum);
		// polje za unos novog broja sobe
		JTextField roomnumtf = new JTextField(2);
		roomnumtf.setBounds(340, 10, 50, 30);
		roomtypeg.add(roomnumtf);
		// button za prikaz slobodnih soba datog tipa
		JButton viewFree = new JButton("View free");
		viewFree.setBounds(400, 10, 100, 30);
		viewFree.setEnabled(false);
		roomtypeg.add(viewFree);
		// labele za boxove usluga
		JLabel gymgl = new JLabel("Gym:");
		JLabel poolgl = new JLabel("Pool:");
		JLabel restaurantgl = new JLabel("Restaurant:");
		JLabel saunagl = new JLabel("Sauna:");
		JLabel cinemagl = new JLabel("Cinema:");
		// boxovi za biranje usluga
		JComboBox<String> gymg = new JComboBox<String>(GYM);
		JComboBox<String> poolg = new JComboBox<String>(POOL);
		JComboBox<String> restaurantg = new JComboBox<String>(RESTAURANT);
		JComboBox<String> saunag = new JComboBox<String>(SAUNA);
		JComboBox<String> cinemag = new JComboBox<String>(CINEMA);
		// dodavanje objekata na panele
		cboxbuttonsg.add(gymgl);
		cboxbuttonsg.add(gymg);
		cboxbuttonsg.add(poolgl);
		cboxbuttonsg.add(poolg);
		cboxbuttonsg.add(restaurantgl);
		cboxbuttonsg.add(restaurantg);
		cboxbuttonsg.add(saunagl);
		cboxbuttonsg.add(saunag);
		cboxbuttonsg.add(cinemagl);
		cboxbuttonsg.add(cinemag);

		// Tabs admin - Halim
		// ********************************************************************************************************************************

		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Check in", admintab1);
		tp.addTab("Lookup / Check out / Change", admintab2);
		tp.addTab("Monitoring", admintab3);
		administratorwindow.setLayout(new BorderLayout());
		administratorwindow.add(tp, BorderLayout.CENTER);
		// tabbed panel sa 3 taba akcije
		tp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int q = tp.getSelectedIndex();
				if (q == 0) {
					admintab1.setVisible(true);
				} else if (q == 1) {
					admintab2.setVisible(true);
				} else if (q == 2) {
					admintab3.setVisible(true);
				}
			}
		});

		// Tab 1: Halim
		// ********************************************************************************************************************************

		JLabel GUEST = new JLabel("GUEST");
		GUEST.setBounds(130, 10, 50, 20);
		admintab1.add(GUEST);
		// labele
		JLabel name = new JLabel("Name: ");
		name.setBounds(20, 40, 80, 30);
		admintab1.add(name);
		JLabel surname = new JLabel("Surname: ");
		surname.setBounds(20, 80, 80, 30);
		admintab1.add(surname);
		JLabel gender = new JLabel("Gender: ");
		gender.setBounds(20, 120, 80, 30);
		admintab1.add(gender);
		JLabel age = new JLabel("Age: ");
		age.setBounds(20, 160, 80, 30);
		admintab1.add(age);
		JLabel idnumber = new JLabel("ID number: ");
		idnumber.setBounds(20, 200, 80, 30);
		admintab1.add(idnumber);
		// polja za unos podataka o gostu
		JTextField nametf = new JTextField(20);
		nametf.setBounds(100, 40, 200, 30);
		admintab1.add(nametf);
		JTextField surnametf = new JTextField(30);
		surnametf.setBounds(100, 80, 200, 30);
		admintab1.add(surnametf);
		JTextField agetf = new JTextField(3);
		agetf.setBounds(100, 160, 50, 30);
		admintab1.add(agetf);
		JTextField idnumbertf = new JTextField(15);
		idnumbertf.setBounds(100, 200, 150, 30);
		admintab1.add(idnumbertf);

		JComboBox<String> genderchoser = new JComboBox<String>(gen);
		genderchoser.setSelectedIndex(0);
		genderchoser.setBounds(100, 120, 100, 30);
		admintab1.add(genderchoser);

		JLabel ROOM = new JLabel("ROOM & SERVICE");
		ROOM.setBounds(550, 10, 120, 20);
		admintab1.add(ROOM);

		JLabel roomnumber = new JLabel("Room number: ");
		roomnumber.setBounds(460, 80, 100, 30);
		admintab1.add(roomnumber);
		JLabel roomtype = new JLabel("Room type: ");
		roomtype.setBounds(460, 40, 100, 30);
		admintab1.add(roomtype);

		JTextField roomnumbertf = new JTextField(2);
		roomnumbertf.setBounds(560, 80, 50, 30);
		admintab1.add(roomnumbertf);

		JButton freerooms = new JButton("View free");
		freerooms.setBounds(640, 80, 100, 30);
		admintab1.add(freerooms);
		freerooms.setEnabled(false);

		JComboBox<String> roomtypechoser = new JComboBox<String>(roomt);
		roomtypechoser.setSelectedIndex(0);
		roomtypechoser.setBounds(560, 40, 100, 30);
		admintab1.add(roomtypechoser);

		JLabel days = new JLabel("Days: ");
		days.setBounds(460, 120, 100, 30);
		admintab1.add(days);

		JTextField daystf = new JTextField(4);
		daystf.setBounds(560, 120, 50, 30);
		admintab1.add(daystf);

		JPanel cboxbuttons = new JPanel();
		cboxbuttons.setBounds(460, 170, 200, 200);
		cboxbuttons.setLayout(new GridLayout(5, 1));
		cboxbuttons.setBackground(Color.lightGray);
		admintab1.add(cboxbuttons);
		// boxovi za biranje ostalih usluga hotela
		JLabel gymlab = new JLabel("Gym: ");
		JComboBox<String> gym = new JComboBox<String>(GYM);
		JLabel poollab = new JLabel("Pool: ");
		JComboBox<String> pool = new JComboBox<String>(POOL);
		JLabel restaurantlab = new JLabel("Restaurant: ");
		JComboBox<String> restaurant = new JComboBox<String>(RESTAURANT);
		JLabel saunalab = new JLabel("Sauna: ");
		JComboBox<String> sauna = new JComboBox<String>(SAUNA);
		JLabel cinemalab = new JLabel("Cinema: ");
		JComboBox<String> cinema = new JComboBox<String>(CINEMA);
		cboxbuttons.add(gymlab);
		cboxbuttons.add(gym);
		cboxbuttons.add(poollab);
		cboxbuttons.add(pool);
		cboxbuttons.add(restaurantlab);
		cboxbuttons.add(restaurant);
		cboxbuttons.add(saunalab);
		cboxbuttons.add(sauna);
		cboxbuttons.add(cinemalab);
		cboxbuttons.add(cinema);

		JLabel LOGIN = new JLabel("LOGIN INFO");
		LOGIN.setBounds(100, 300, 80, 20);
		admintab1.add(LOGIN);

		JButton checkdb = new JButton("Check DB");
		checkdb.setBounds(200, 295, 100, 30);
		admintab1.add(checkdb);

		JLabel usern = new JLabel("Username: ");
		usern.setBounds(20, 340, 100, 30);
		admintab1.add(usern);
		JLabel pass = new JLabel("Password: ");
		pass.setBounds(20, 380, 100, 30);
		admintab1.add(pass);

		JTextField userntf = new JTextField(20);
		userntf.setBounds(110, 340, 200, 30);
		admintab1.add(userntf);
		JTextField passtf = new JTextField(16);
		passtf.setBounds(110, 380, 200, 30);
		admintab1.add(passtf);

		JPanel btnpanel = new JPanel();
		btnpanel.setBounds(0, 470, 800, 100);
		btnpanel.setLayout(null);
		btnpanel.setBackground(Color.gray);
		admintab1.add(btnpanel);

		JButton checkin = new JButton("CHECK IN");
		checkin.setBounds(300, 12, 200, 40);
		btnpanel.add(checkin);

		// Tab2: Halim
		// *****************************************************************************************************************************

		JLabel search = new JLabel("INPUT (Name or IDnumber or Username):");
		search.setBounds(20, 10, 250, 30);
		admintab2.add(search);
		// polje za pretragu i lookup gosta
		JTextField searchtf = new JTextField(20);
		searchtf.setBounds(270, 10, 250, 30);
		admintab2.add(searchtf);
		// search button
		JButton Search = new JButton("SEARCH");
		Search.setBounds(550, 10, 100, 30);
		admintab2.add(Search);

		// other fields and buttons needed
		JLabel nodata = new JLabel("NO DATA FOUND");
		nodata.setBounds(270, 250, 400, 60);
		nodata.setFont(new Font("Tahoma", Font.BOLD, 30));
		nodata.setVisible(false);
		admintab2.add(nodata);

		JLabel GUEST1 = new JLabel("GUEST");
		GUEST1.setBounds(130, 60, 50, 20);
		admintab2.add(GUEST1);
		GUEST1.setVisible(false);

		JLabel name1 = new JLabel("Name: ");
		name1.setBounds(20, 90, 80, 30);
		admintab2.add(name1);
		name1.setVisible(false);
		JLabel surname1 = new JLabel("Surname: ");
		surname1.setBounds(20, 130, 80, 30);
		admintab2.add(surname1);
		surname1.setVisible(false);
		JLabel gender1 = new JLabel("Gender: ");
		gender1.setBounds(20, 170, 80, 30);
		admintab2.add(gender1);
		gender1.setVisible(false);
		JLabel age1 = new JLabel("Age: ");
		age1.setBounds(20, 210, 80, 30);
		admintab2.add(age1);
		age1.setVisible(false);
		JLabel idnumber1 = new JLabel("ID number: ");
		idnumber1.setBounds(20, 250, 80, 30);
		admintab2.add(idnumber1);
		idnumber1.setVisible(false);

		JTextField nametf1 = new JTextField(20);
		nametf1.setBounds(100, 90, 200, 30);
		admintab2.add(nametf1);
		nametf1.setVisible(false);
		nametf1.setEditable(false);
		JTextField surnametf1 = new JTextField(30);
		surnametf1.setBounds(100, 130, 200, 30);
		admintab2.add(surnametf1);
		surnametf1.setVisible(false);
		surnametf1.setEditable(false);
		JTextField gendertf1 = new JTextField(30);
		gendertf1.setBounds(100, 170, 100, 30);
		admintab2.add(gendertf1);
		gendertf1.setVisible(false);
		gendertf1.setEditable(false);
		JTextField agetf1 = new JTextField(3);
		agetf1.setBounds(100, 210, 50, 30);
		admintab2.add(agetf1);
		agetf1.setVisible(false);
		agetf1.setEditable(false);
		JTextField idnumbertf1 = new JTextField(15);
		idnumbertf1.setBounds(100, 250, 150, 30);
		admintab2.add(idnumbertf1);
		idnumbertf1.setVisible(false);
		idnumbertf1.setEditable(false);

		JLabel ROOM1 = new JLabel("ROOM & SERVICE");
		ROOM1.setBounds(480, 60, 120, 20);
		admintab2.add(ROOM1);
		ROOM1.setVisible(false);

		JLabel roomnumber1 = new JLabel("Room number: ");
		roomnumber1.setBounds(460, 140, 100, 30);
		admintab2.add(roomnumber1);
		roomnumber1.setVisible(false);
		JLabel roomtype1 = new JLabel("Room type: ");
		roomtype1.setBounds(460, 100, 100, 30);
		admintab2.add(roomtype1);
		roomtype1.setVisible(false);

		JTextField roomnumbertf1 = new JTextField(4);
		roomnumbertf1.setBounds(560, 140, 50, 30);
		admintab2.add(roomnumbertf1);
		roomnumbertf1.setVisible(false);
		roomnumbertf1.setEditable(false);

		JButton freerooms1 = new JButton("View free");
		freerooms1.setBounds(640, 140, 100, 30);
		admintab2.add(freerooms1);
		freerooms1.setEnabled(false);
		freerooms1.setVisible(false);

		JComboBox<String> roomtypechoser1 = new JComboBox<String>(roomt);
		roomtypechoser1.setSelectedIndex(0);
		roomtypechoser1.setBounds(560, 100, 100, 30);
		admintab2.add(roomtypechoser1);
		roomtypechoser1.setVisible(false);
		roomtypechoser1.setEnabled(false);

		JLabel days1 = new JLabel("Days: ");
		days1.setBounds(460, 180, 100, 30);
		admintab2.add(days1);
		days1.setVisible(false);

		JTextField daystf1 = new JTextField(4);
		daystf1.setBounds(560, 180, 50, 30);
		admintab2.add(daystf1);
		daystf1.setVisible(false);
		daystf1.setEditable(false);

		JButton edit = new JButton("EDIT");
		edit.setBounds(600, 55, 60, 30);
		admintab2.add(edit);
		edit.setVisible(false);

		JButton save = new JButton("SAVE");
		save.setBounds(670, 55, 70, 30);
		admintab2.add(save);
		save.setVisible(false);
		save.setEnabled(false);

		JPanel cboxbuttons1 = new JPanel();
		cboxbuttons1.setBounds(460, 220, 200, 200);
		cboxbuttons1.setLayout(new GridLayout(5, 1));
		cboxbuttons1.setBackground(Color.lightGray);
		admintab2.add(cboxbuttons1);
		cboxbuttons1.setVisible(false);

		JLabel gymlab1 = new JLabel("Gym: ");
		JComboBox<String> gym1 = new JComboBox<String>(GYM);
		gym1.setEnabled(false);
		JLabel poollab1 = new JLabel("Pool: ");
		JComboBox<String> pool1 = new JComboBox<String>(POOL);
		pool1.setEnabled(false);
		JLabel restaurantlab1 = new JLabel("Restaurant: ");
		JComboBox<String> restaurant1 = new JComboBox<String>(RESTAURANT);
		restaurant1.setEnabled(false);
		JLabel saunalab1 = new JLabel("Sauna: ");
		JComboBox<String> sauna1 = new JComboBox<String>(SAUNA);
		sauna1.setEnabled(false);
		JLabel cinemalab1 = new JLabel("Cinema: ");
		JComboBox<String> cinema1 = new JComboBox<String>(CINEMA);
		cinema1.setEnabled(false);
		cboxbuttons1.add(gymlab1);
		cboxbuttons1.add(gym1);
		cboxbuttons1.add(poollab1);
		cboxbuttons1.add(pool1);
		cboxbuttons1.add(restaurantlab1);
		cboxbuttons1.add(restaurant1);
		cboxbuttons1.add(saunalab1);
		cboxbuttons1.add(sauna1);
		cboxbuttons1.add(cinemalab1);
		cboxbuttons1.add(cinema1);

		JLabel LOGIN1 = new JLabel("LOGIN INFO");
		LOGIN1.setBounds(130, 330, 80, 20);
		admintab2.add(LOGIN1);
		LOGIN1.setVisible(false);

		JLabel usern1 = new JLabel("Username: ");
		usern1.setBounds(20, 360, 100, 30);
		admintab2.add(usern1);
		usern1.setVisible(false);
		JLabel pass1 = new JLabel("Password: ");
		pass1.setBounds(20, 400, 100, 30);
		admintab2.add(pass1);
		pass1.setVisible(false);

		JTextField userntf1 = new JTextField(20);
		userntf1.setBounds(110, 360, 200, 30);
		admintab2.add(userntf1);
		userntf1.setVisible(false);
		userntf1.setEditable(false);
		JTextField passtf1 = new JTextField(16);
		passtf1.setBounds(110, 400, 200, 30);
		admintab2.add(passtf1);
		passtf1.setVisible(false);
		passtf1.setEditable(false);

		JPanel btnpanel1 = new JPanel();
		btnpanel1.setBounds(0, 470, 800, 100);
		btnpanel1.setLayout(null);
		btnpanel1.setBackground(Color.gray);
		btnpanel1.setVisible(false);
		admintab2.add(btnpanel1);

		JButton bill = new JButton("BILL");
		bill.setBounds(290, 12, 100, 40);
		btnpanel1.add(bill);
		JButton checkout = new JButton("CHECK OUT");
		checkout.setBounds(410, 12, 100, 40);
		btnpanel1.add(checkout);

		// Tab3: Ahmed
		// ******************************************************************************************
		// parameters for log off button
		JButton logOffGuestButton = new JButton("Log Off Guest");
		logOffGuestButton.setBounds(15, 30, 120, 30);
		admintab3.add(logOffGuestButton);

		// parameters for log off all button
		JButton logOffAllButton = new JButton("Log Off All");
		logOffAllButton.setBounds(15, 60, 120, 30);
		admintab3.add(logOffAllButton);

		// parameters for refresh button
		// button refreshes Jtable
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setBounds(135, 30, 120, 30);
		admintab3.add(refreshButton);

		// Declaring Table
		activeGuestTable = populateJTable();
		// Declaring ScrolPane
		JScrollPane scrollPane = new JScrollPane();
		// dimensions and location for JTable
		activeGuestTable.setBounds(15, 120, 740, 400);
		scrollPane.setBounds(activeGuestTable.getBounds());
		// adding JTable to scrollPane as view port
		scrollPane.setViewportView(activeGuestTable);

		// adding the scroll pane to the layout at center
		admintab3.add(scrollPane);

		/** Button updates list with current data */
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// invoking method to update table
				activeGuestTable = populateJTable();
				// display updated table
				scrollPane.setViewportView(activeGuestTable);
			}
		});

		/** Button signs off selected user */
		logOffGuestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// creating a MyConnector Object
				MyConnector mc = new MyConnector();
				// getting selected row int
				int row = activeGuestTable.getSelectedRow();
				// assigning the first value of table to getUsername
				Object getUsername = activeGuestTable.getValueAt(row, 0);
				String username = getUsername.toString();
				// invoking setStatus method
				mc.setStatus(username, false);
				// Updating table and displaying it
				activeGuestTable = populateJTable();
				scrollPane.setViewportView(activeGuestTable);
				// closing guest window after guest is logged off
				System.out.print(usernameWindow.getTitle());

				// creating and array of JFrames
				@SuppressWarnings("static-access")
				Frame[] f = usernameWindow.getFrames();
				for (int i = 0; i < f.length; i++) {
					// closing window where title matches username
					if (f[i].getTitle().equals(username)) {
						f[i].dispose();
					}
				}
				// displaying information message
				JOptionPane.showMessageDialog(new JPanel(), username + " Logged Off", "Logging Out",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		/** method signs off all active users */
		logOffAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// creating a MyConnector object
				MyConnector mc = new MyConnector();
				// creating an array of username frames
				@SuppressWarnings("static-access")
				Frame[] f = usernameWindow.getFrames();
				// looping through table and signing off users
				for (int i = 0; i < activeGuestTable.getRowCount(); i++) {
					// geting value at first column
					Object getUsername = activeGuestTable.getValueAt(i, 0);
					String username = getUsername.toString();
					// invoking method set Status to false
					mc.setStatus(username, false);
					// looping through frames and closing all userName windows
					for (int j = 0; j < f.length; j++) {
						// closing window where title matches username
						if (f[j].getTitle().equals(username)) {
							f[j].dispose();
						}
					}
				}
				// displaying updated table
				activeGuestTable = populateJTable();
				scrollPane.setViewportView(activeGuestTable);
				// displaying information message that process is completed
				JOptionPane.showMessageDialog(new JPanel(), "All guests have been logged off", "Logging Out",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Button action Halim + Ahmed & Vedran (mods)
		// ********************************************************************************************************************************

		// gender chooser actions (admin panel 1st tab)
		genderchoser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = genderchoser.getSelectedIndex();
				if (q == 1) {
					Gender = "Male";
				} else if (q == 2) {
					Gender = "Female";
				}
			}
		});
		// room type chooser (admin panel tab1)
		roomtypechoser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = roomtypechoser.getSelectedIndex();
				if (q == 0) {
					freerooms.setEnabled(false);
				} else if (q == 1) {
					Room = "Single";
					freerooms.setEnabled(true);
				} else if (q == 2) {
					Room = "Double";
					freerooms.setEnabled(true);
				} else if (q == 3) {
					Room = "Suite";
					freerooms.setEnabled(true);
				}
			}
		});
		// room type chooser (admin panel tab2)
		roomtypechoser1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = roomtypechoser1.getSelectedIndex();
				if (q == 0) {
					freerooms1.setEnabled(false);
				} else if (q == 1) {
					Room = "Single";
					freerooms1.setEnabled(true);
				} else if (q == 2) {
					Room = "Double";
					freerooms1.setEnabled(true);
				} else if (q == 3) {
					Room = "Suite";
					freerooms1.setEnabled(true);
				}
			}
		});
		// room type chooser (guest window, update option)
		roomtypec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = roomtypec.getSelectedIndex();
				if (q == 0) {
					viewFree.setEnabled(false);
				} else if (q == 1) {
					Room = "Single";
					viewFree.setEnabled(true);
				} else if (q == 2) {
					Room = "Double";
					viewFree.setEnabled(true);
				} else if (q == 3) {
					Room = "Suite";
					viewFree.setEnabled(true);
				}
			}
		});

		/*
		 * sljedeæe linije su za panel 1 i panel 2 admin window za ostale usluge
		 * hotela
		 */
		gym.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = gym.getSelectedIndex();
				if (q == 0) {
					Gym = "NO";
				} else if (q == 1) {
					Gym = "YES";
				}
			}
		});

		pool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = pool.getSelectedIndex();
				if (q == 0) {
					Pool = "NO";
				} else if (q == 1) {
					Pool = "YES";
				}
			}
		});

		restaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = restaurant.getSelectedIndex();
				if (q == 0) {
					Restaurant = "NO";
				} else if (q == 1) {
					Restaurant = "YES";
				}
			}
		});

		sauna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = sauna.getSelectedIndex();
				if (q == 0) {
					Sauna = "NO";
				} else if (q == 1) {
					Sauna = "YES";
				}
			}
		});

		cinema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = cinema.getSelectedIndex();
				if (q == 0) {
					Cinema = "NO";
				} else if (q == 1) {
					Cinema = "YES";
				}
			}
		});

		gym1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = gym1.getSelectedIndex();
				if (q == 0) {
					Gym = "NO";
				} else if (q == 1) {
					Gym = "YES";
				}
			}
		});

		pool1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = pool1.getSelectedIndex();
				if (q == 0) {
					Pool = "NO";
				} else if (q == 1) {
					Pool = "YES";
				}
			}
		});

		restaurant1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = restaurant1.getSelectedIndex();
				if (q == 0) {
					Restaurant = "NO";
				} else if (q == 1) {
					Restaurant = "YES";
				}
			}
		});

		sauna1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = sauna1.getSelectedIndex();
				if (q == 0) {
					Sauna = "NO";
				} else if (q == 1) {
					Sauna = "YES";
				}
			}
		});

		cinema1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q = cinema1.getSelectedIndex();
				if (q == 0) {
					Cinema = "NO";
				} else if (q == 1) {
					Cinema = "YES";
				}
			}
		});
		// dugme za pretragu gosta
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ucitavanje gosta iz baze za trazeno ime, username ili
				// idnumber
				Guest guest1 = hotelmgmt.guestLookup(searchtf.getText());
				try {
					if (searchtf.getText().equals("") || guest1.getUsername().equals("")) {
						nodata.setVisible(true);
						GUEST1.setVisible(false);
						name1.setVisible(false);
						surname1.setVisible(false);
						gender1.setVisible(false);
						age1.setVisible(false);
						idnumber1.setVisible(false);
						nametf1.setVisible(false);
						nametf1.setText("");
						surnametf1.setVisible(false);
						surnametf1.setText("");
						gendertf1.setVisible(false);
						gendertf1.setText("");
						agetf1.setVisible(false);
						agetf1.setText("");
						idnumbertf1.setVisible(false);
						idnumbertf1.setText("");
						ROOM1.setVisible(false);
						roomnumber1.setVisible(false);
						roomnumbertf1.setVisible(false);
						roomnumbertf1.setText("");
						freerooms1.setVisible(false);
						roomtype1.setVisible(false);
						roomtypechoser1.setVisible(false);
						days1.setVisible(false);
						daystf1.setVisible(false);
						daystf1.setText("");
						LOGIN1.setVisible(false);
						usern1.setVisible(false);
						pass1.setVisible(false);
						userntf1.setVisible(false);
						userntf1.setText("");
						passtf1.setVisible(false);
						passtf1.setText("");
						edit.setVisible(false);
						save.setVisible(false);
						cboxbuttons1.setVisible(false);
						btnpanel1.setVisible(false);
					} else {
						nodata.setVisible(false);
						GUEST1.setVisible(true);
						name1.setVisible(true);
						surname1.setVisible(true);
						gender1.setVisible(true);
						age1.setVisible(true);
						idnumber1.setVisible(true);
						nametf1.setVisible(true);
						nametf1.setText(guest1.getName());
						surnametf1.setVisible(true);
						surnametf1.setText(guest1.getSurname());
						gendertf1.setVisible(true);
						gendertf1.setText(guest1.getGender());
						agetf1.setVisible(true);
						agetf1.setText(Integer.toString(guest1.getAge()));
						idnumbertf1.setVisible(true);
						idnumbertf1.setText(guest1.getIDnumber());
						ROOM1.setVisible(true);
						roomnumber1.setVisible(true);
						roomnumbertf1.setVisible(true);
						roomnumbertf1.setText(Integer.toString(guest1.getRoomNumber()));
						roomtype1.setVisible(true);
						roomtypechoser1.setSelectedItem(guest1.getRoomType());
						Room = guest1.getRoomType();
						roomtypechoser1.setVisible(true);
						days1.setVisible(true);
						daystf1.setVisible(true);
						daystf1.setText(Integer.toString(guest1.getNumOfDays()));
						LOGIN1.setVisible(true);
						usern1.setVisible(true);
						pass1.setVisible(true);
						userntf1.setVisible(true);
						userntf1.setText(guest1.getUsername());
						passtf1.setVisible(true);
						passtf1.setText(guest1.getPassword());
						edit.setVisible(true);
						save.setVisible(true);
						cboxbuttons1.setVisible(true);
						btnpanel1.setVisible(true);
						gym1.setSelectedItem(guest1.getGym());
						Gym = guest1.getGym();
						pool1.setSelectedItem(guest1.getPool());
						Pool = guest1.getPool();
						restaurant1.setSelectedItem(guest1.getRestaurant());
						Restaurant = guest1.getRestaurant();
						sauna1.setSelectedItem(guest1.getSauna());
						Sauna = guest1.getSauna();
						cinema1.setSelectedItem(guest1.getCinema());
						Cinema = guest1.getCinema();
					}
				} catch (NullPointerException ex) {
					nodata.setVisible(true);
				}
			}
		});
		// dugme za zatvaranje app-a
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// prilikom logina ako zelimo da odustanemo i vratimo se na poèetak
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setVisible(false);
				usernameinput.setVisible(false);
				password.setVisible(false);
				passwordinput.setVisible(false);
				admin.setVisible(true);
				guest.setVisible(true);
				close.setVisible(true);
				okadmin.setVisible(false);
				okguest.setVisible(false);
				cancel.setVisible(false);
				usernameinput.setText("");
				passwordinput.setText("");
			}
		});
		// klik na dugme admin na pocetku programa
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setVisible(true);
				usernameinput.setVisible(true);
				password.setVisible(true);
				passwordinput.setVisible(true);
				admin.setVisible(false);
				guest.setVisible(false);
				close.setVisible(false);
				okadmin.setVisible(true);
				okguest.setVisible(false);
				cancel.setVisible(true);
			}
		});
		// klik na dugme guest na pocetku programa
		guest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setVisible(true);
				usernameinput.setVisible(true);
				password.setVisible(true);
				passwordinput.setVisible(true);
				admin.setVisible(false);
				guest.setVisible(false);
				close.setVisible(false);
				okadmin.setVisible(false);
				okguest.setVisible(true);
				cancel.setVisible(true);
			}
		});
		// klik na dugme OK prilikom login admina
		okadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ucitava iz baze podatke o adminu i kasnije provjera da li je
				// validno
				Admin admin1 = hotelmgmt.getAdminData(usernameinput.getText());
				try {
					String Pass = String.valueOf(passwordinput.getPassword());
					if (admin1.getPassword().equals(Pass)) {
						username.setVisible(false);
						usernameinput.setVisible(false);
						password.setVisible(false);
						passwordinput.setVisible(false);
						admin.setVisible(true);
						guest.setVisible(true);
						close.setVisible(true);
						okadmin.setVisible(false);
						okguest.setVisible(false);
						cancel.setVisible(false);
						usernameinput.setText("");
						passwordinput.setText("");
						administratorwindow.setVisible(true);
						JFrame info = new JFrame();
						// prikaz notifikacija adminu ukoliko neki gost zeli
						// check out
						HashSet<String> not = hotelmgmt.viewNot();
						if (!not.isEmpty()) {
							JOptionPane.showMessageDialog(info, "Users checking-out: \n" + not);
						}
					} else {
						JFrame info = new JFrame();
						JOptionPane.showMessageDialog(info, "Username or Password is incorrect!");
						usernameinput.setText("");
						passwordinput.setText("");
					}
				} catch (NullPointerException ex) {
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "Username or Password is incorrect!");
					usernameinput.setText("");
					passwordinput.setText("");
				}
			}
		});
		// klik na dugme ok prilikom login guesta
		okguest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ucitavanje iz baze i provjera validnosti podataka za login
				// gosta
				Guest guest1 = hotelmgmt.getGuestData(usernameinput.getText());
				guestwindow.setTitle(usernameinput.getText());
				try {
					String Pass = String.valueOf(passwordinput.getPassword());
					if (guest1.getPassword().equals(Pass)) {
						username.setVisible(false);
						usernameinput.setVisible(false);
						password.setVisible(false);
						passwordinput.setVisible(false);
						admin.setVisible(true);
						guest.setVisible(true);
						close.setVisible(true);
						okadmin.setVisible(false);
						okguest.setVisible(false);
						cancel.setVisible(false);
						usernameinput.setText("");
						passwordinput.setText("");
						guesttxt.setVisible(false);
						guesttxt.setText("");
						// ahmed
						// creating MyConector object
						MyConnector mc = new MyConnector();
						// invoking method to change status of active to 1
						mc.setStatus(guest1.getUsername(), true);
						usernameWindow = new Hotel().guestwindow;
						usernameWindow.setTitle(guest1.getUsername());
						usernameWindow.setVisible(true);
					} else {
						JFrame info = new JFrame();
						JOptionPane.showMessageDialog(info, "Username or Password is incorrect!");
						usernameinput.setText("");
						passwordinput.setText("");
					}
				} catch (NullPointerException ex) {
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "Username or Password is incorrect!");
					usernameinput.setText("");
					passwordinput.setText("");
				}
			}
		});
		// dugme za pregled racuna prilikom lookup gosta tab2 admin window
		bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				billtext.setText("");
				String uname = userntf1.getText();
				// ucitavanje podataka o sobi i gostu za kreiranje racuna za
				// prikaz
				Room room1 = hotelmgmt.roomPrice(uname);
				Guest guest1 = hotelmgmt.guestBill(uname);
				double gymbill = 0;
				double poolbill = 0;
				double restaurantbill = 0;
				double saunabill = 0;
				double cinemabill = 0;
				billtext.append("  Checked in:  " + guest1.getTimeCheckedin() + "\n" + "  Room type:  "
						+ guest1.getRoomType() + "      Day price:  " + room1.getDayPrice() + "\n" + "  Days:  "
						+ guest1.getNumOfDays() + "\n");
				try {
					if (guest1.getGym().equals("YES")) {
						billtext.append("  Gym:  YES" + "     Day price:  " + GYM_PRICE + " (" + guest1.getNumOfDays()
								+ ")" + "\n");
						gymbill = guest1.getNumOfDays() * GYM_PRICE;
					}
					if (guest1.getPool().equals("YES")) {
						billtext.append("  Pool:  YES" + "     Day price:  " + POOL_PRICE + " (" + guest1.getNumOfDays()
								+ ")" + "\n");
						poolbill = guest1.getNumOfDays() * POOL_PRICE;
					}
					if (guest1.getRestaurant().equals("YES")) {
						billtext.append("  Restaurant:  YES" + "     Day price:  " + RESTAURANT_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						restaurantbill = guest1.getNumOfDays() * RESTAURANT_PRICE;
					}
					if (guest1.getSauna().equals("YES")) {
						billtext.append("  Sauna:  YES" + "     Day price:  " + SAUNA_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						saunabill = guest1.getNumOfDays() * SAUNA_PRICE;
					}
					if (guest1.getCinema().equals("YES")) {
						billtext.append("  Cinema:  YES" + "     Day price:  " + CINEMA_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						cinemabill = guest1.getNumOfDays() * CINEMA_PRICE;
					}
				} catch (NullPointerException ex) {
				}
				double BILL = (guest1.getNumOfDays() * room1.getDayPrice()) + gymbill + poolbill + restaurantbill
						+ saunabill + cinemabill;
				billtext.append(
						"........................................................................................"
								+ "\n\n" + "\t\t" + "TOTAL:  " + Double.toString(BILL));
				billwindow.setVisible(true);
			}
		});
		// ok dugme na prozoru za prikaz racuna
		billOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				billwindow.dispose();
				billtext.setText("");
			}
		});
		// pregled racuna na prozoru gosta
		billpreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateroom.setEnabled(true);
				updateservice.setEnabled(true);
				guesttxt.setVisible(true);
				guesttxt.setText("");
				// ucitavanje podata za prikaz racuna
				Room room1 = hotelmgmt.roomPrice(guestwindow.getTitle());
				Guest guest1 = hotelmgmt.guestBill(guestwindow.getTitle());
				double gymbill = 0;
				double poolbill = 0;
				double restaurantbill = 0;
				double saunabill = 0;
				double cinemabill = 0;
				guesttxt.append("  Checked in:  " + guest1.getTimeCheckedin() + "\n" + "  Room type:  "
						+ guest1.getRoomType() + "      Day price:  " + room1.getDayPrice() + "\n" + "  Days:  "
						+ guest1.getNumOfDays() + "\n");
				try {
					if (guest1.getGym().equals("YES")) {
						guesttxt.append("  Gym:  YES" + "     Day price:  " + GYM_PRICE + " (" + guest1.getNumOfDays()
								+ ")" + "\n");
						gymbill = guest1.getNumOfDays() * GYM_PRICE;
					}
					if (guest1.getPool().equals("YES")) {
						guesttxt.append("  Pool:  YES" + "     Day price:  " + POOL_PRICE + " (" + guest1.getNumOfDays()
								+ ")" + "\n");
						poolbill = guest1.getNumOfDays() * POOL_PRICE;
					}
					if (guest1.getRestaurant().equals("YES")) {
						guesttxt.append("  Restaurant:  YES" + "     Day price:  " + RESTAURANT_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						restaurantbill = guest1.getNumOfDays() * RESTAURANT_PRICE;
					}
					if (guest1.getSauna().equals("YES")) {
						guesttxt.append("  Sauna:  YES" + "     Day price:  " + SAUNA_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						saunabill = guest1.getNumOfDays() * SAUNA_PRICE;
					}
					if (guest1.getCinema().equals("YES")) {
						guesttxt.append("  Cinema:  YES" + "     Day price:  " + CINEMA_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						cinemabill = guest1.getNumOfDays() * CINEMA_PRICE;
					}
				} catch (NullPointerException ex) {
				}
				double BILL = (guest1.getNumOfDays() * room1.getDayPrice()) + gymbill + poolbill + restaurantbill
						+ saunabill + cinemabill;
				guesttxt.append(
						"............................................................................................."
								+ "\n\n" + "\t\t" + "TOTAL:  " + Double.toString(BILL));
			}
		});
		// prijava novog gosta u hotel
		checkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Guest guest1 = new Guest(userntf.getText(), passtf.getText(), nametf.getText(), surnametf.getText(),
							Gender, idnumbertf.getText(), Integer.parseInt(agetf.getText()),
							Integer.parseInt(roomnumbertf.getText()), Room,
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
							Integer.parseInt(daystf.getText()), Gym, Pool, Restaurant, Sauna, Cinema);
					// dodavanje gosta u hotelsku sobu
					hotelmgmt.addGuest(guest1);
					hotelmgmt.inRoom(guest1.getUsername(), guest1.getRoomNumber());
				} catch (NullPointerException ex) {
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "Check data!");
				}
				nametf.setText("");
				surnametf.setText("");
				genderchoser.setSelectedIndex(0);
				agetf.setText("");
				idnumbertf.setText("");
				roomnumbertf.setText("");
				daystf.setText("");
				userntf.setText("");
				passtf.setText("");
				roomtypechoser.setSelectedIndex(0);
				gym.setSelectedIndex(0);
				pool.setSelectedIndex(0);
				restaurant.setSelectedIndex(0);
				sauna.setSelectedIndex(0);
				cinema.setSelectedIndex(0);
			}
		});
		// odjava gosta iz hotela
		checkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				billtext.setText("");
				String uname = userntf1.getText();
				// izdavanje racuna gostu prije check-outa
				Room room1 = hotelmgmt.roomPrice(uname);
				Guest guest1 = hotelmgmt.guestBill(uname);
				double gymbill = 0;
				double poolbill = 0;
				double restaurantbill = 0;
				double saunabill = 0;
				double cinemabill = 0;
				billtext.append("  Checked in:  " + guest1.getTimeCheckedin() + "\n" + "  Room type:  "
						+ guest1.getRoomType() + "      Day price:  " + room1.getDayPrice() + "\n" + "  Days:  "
						+ guest1.getNumOfDays() + "\n");
				try {
					if (guest1.getGym().equals("YES")) {
						billtext.append("  Gym:  YES" + "     Day price:  " + GYM_PRICE + " (" + guest1.getNumOfDays()
								+ ")" + "\n");
						gymbill = guest1.getNumOfDays() * GYM_PRICE;
					}
					if (guest1.getPool().equals("YES")) {
						billtext.append("  Pool:  YES" + "     Day price:  " + POOL_PRICE + " (" + guest1.getNumOfDays()
								+ ")" + "\n");
						poolbill = guest1.getNumOfDays() * POOL_PRICE;
					}
					if (guest1.getRestaurant().equals("YES")) {
						billtext.append("  Restaurant:  YES" + "     Day price:  " + RESTAURANT_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						restaurantbill = guest1.getNumOfDays() * RESTAURANT_PRICE;
					}
					if (guest1.getSauna().equals("YES")) {
						billtext.append("  Sauna:  YES" + "     Day price:  " + SAUNA_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						saunabill = guest1.getNumOfDays() * SAUNA_PRICE;
					}
					if (guest1.getCinema().equals("YES")) {
						billtext.append("  Cinema:  YES" + "     Day price:  " + CINEMA_PRICE + " ("
								+ guest1.getNumOfDays() + ")" + "\n");
						cinemabill = guest1.getNumOfDays() * CINEMA_PRICE;
					}
				} catch (NullPointerException ex) {
				}
				double BILL = (guest1.getNumOfDays() * room1.getDayPrice()) + gymbill + poolbill + restaurantbill
						+ saunabill + cinemabill;
				billtext.append(
						"............................................................................................."
								+ "\n\n" + "\t\t" + "TOTAL:  " + Double.toString(BILL));
				try {
					Guest guest2 = new Guest(userntf1.getText(), passtf1.getText(), nametf1.getText(),
							surnametf1.getText(), gendertf1.getText(), idnumbertf1.getText(),
							Integer.parseInt(agetf1.getText()), Integer.parseInt(roomnumbertf1.getText()), Room,
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
							Integer.parseInt(daystf1.getText()), Gym, Pool, Restaurant, Sauna, Cinema);
					// brisanje notifikacije za check out
					hotelmgmt.notifyClear(guest2.getUsername());
					// arhiviranje gosta
					hotelmgmt.Archive(guest2.getIDnumber(), guest2.getUsername(), guest2.getPassword());
					// brisanje gosta iz baze
					hotelmgmt.guestCheckOut(guest2.getUsername());
				} catch (NullPointerException ex) {
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "Check data!");
				}
				nodata.setVisible(false);
				GUEST1.setVisible(false);
				name1.setVisible(false);
				surname1.setVisible(false);
				gender1.setVisible(false);
				age1.setVisible(false);
				idnumber1.setVisible(false);
				nametf1.setVisible(false);
				nametf1.setText("");
				surnametf1.setVisible(false);
				surnametf1.setText("");
				gendertf1.setVisible(false);
				gendertf1.setText("");
				agetf1.setVisible(false);
				agetf1.setText("");
				idnumbertf1.setVisible(false);
				idnumbertf1.setText("");
				ROOM1.setVisible(false);
				roomnumber1.setVisible(false);
				roomnumbertf1.setVisible(false);
				roomnumbertf1.setText("");
				freerooms1.setVisible(false);
				roomtype1.setVisible(false);
				roomtypechoser1.setVisible(false);
				days1.setVisible(false);
				daystf1.setVisible(false);
				daystf1.setText("");
				LOGIN1.setVisible(false);
				usern1.setVisible(false);
				pass1.setVisible(false);
				userntf1.setVisible(false);
				userntf1.setText("");
				passtf1.setVisible(false);
				passtf1.setText("");
				edit.setVisible(false);
				save.setVisible(false);
				cboxbuttons1.setVisible(false);
				btnpanel1.setVisible(false);
				searchtf.setText("");
				billwindow.setVisible(true);
				JFrame info = new JFrame();
				JOptionPane.showMessageDialog(info, "Bill issued !");
			}
		});
		// provjerava bazu da li ima vec username i pass od prije za korisnika
		// sa datim idnumberom
		checkdb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Guest guest1 = hotelmgmt.archiveCheck(idnumbertf.getText());
					userntf.setText(guest1.getUsername());
					passtf.setText(guest1.getPassword());
				} catch (NullPointerException ex) {
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "No data found!");
				}
			}
		});
		// editovanje podataka o gostu tab2 admin windows guest lookup and
		// change podataka
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomtypechoser1.setEnabled(true);
				roomnumbertf1.setEditable(true);
				daystf1.setEditable(true);
				gym1.setEnabled(true);
				pool1.setEnabled(true);
				restaurant1.setEnabled(true);
				sauna1.setEnabled(true);
				cinema1.setEnabled(true);
				save.setEnabled(true);
				freerooms1.setVisible(true);
			}
		});
		// save updates na lookup gosta admin window tab2
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotelmgmt.updateRoomNumber(userntf1.getText(), Integer.parseInt(roomnumbertf1.getText()));
				hotelmgmt.updateRoomType(userntf1.getText(), Room, Integer.parseInt(roomnumbertf1.getText()));
				hotelmgmt.updateDays(userntf1.getText(), Integer.parseInt(daystf1.getText()));
				roomtypechoser1.setEnabled(false);
				roomnumbertf1.setEditable(false);
				daystf1.setEditable(false);
				gym1.setEnabled(false);
				pool1.setEnabled(false);
				restaurant1.setEnabled(false);
				sauna1.setEnabled(false);
				cinema1.setEnabled(false);
				save.setEnabled(false);
				freerooms1.setEnabled(false);
			}
		});

		// prikaz slobodnih soba za izabrani tip admin window prilikom check
		// in-a by Vedran
		freerooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomsarea.setText("");
				ArrayList<Room> nums = hotelmgmt.freeRooms(Room);
				try {
					roomsarea.append("\n" + "       " + "FREE ROOMS:" + "\n");
					for (int i = 0; i < nums.size(); i++) {
						roomsarea.append("  " + nums.get(i).getNumber() + " ->> FREE" + "\n");
					}
					freeroomswindow.setVisible(true);
				} catch (NullPointerException ex) {
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "No data found!");
				}
			}
		});

		// prikaz slobodnih soba za izabrani tip prilikom update podataka o
		// gostu admin window tab2 by Vedran
		freerooms1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomsarea.setText("");
				ArrayList<Room> nums = hotelmgmt.freeRooms(Room);
				try {
					roomsarea.append("\n" + "       " + "FREE ROOMS:" + "\n");
					for (int i = 0; i < nums.size(); i++) {
						roomsarea.append("  " + nums.get(i).getNumber() + " ->> FREE" + "\n");
					}
					freeroomswindow.setVisible(true);
				} catch (NullPointerException ex) {
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "No data found!");
				}
			}
		});
		// guest window dugme omogucava update sobe gostu
		updateroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomtypeg.setVisible(true);
				saveupdates.setVisible(true);

			}
		});
		// sugme omogucava update servisa hotela gostu
		updateservice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboxbuttonsg.setVisible(true);
				saveupdates.setVisible(true);
			}
		});
		// gost sacuva podatke koje je mjenjao direktno u bazu
		saveupdates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = guestwindow.getTitle();
				Guest temp = hotelmgmt.guestBill(user);
				int roomnumb = temp.getRoomNumber();
				if (roomnumtf.getText().equals("")) {
					roomnumb = temp.getRoomNumber();
				} else {
					roomnumb = Integer.parseInt(roomnumtf.getText());
				}
				hotelmgmt.updateRoomType(user, Room, roomnumb);
				hotelmgmt.updateRoomNumber(user, roomnumb);
				String[] services = new String[5];
				services[0] = Gym;
				services[1] = Pool;
				services[2] = Restaurant;
				services[3] = Sauna;
				services[4] = Cinema;
				for (int i = 0; i < services.length; i++) {
					hotelmgmt.checkServices(user, services[i]);
				}
			}
		});
		// gost notifikuje adminu da zeli check out iz hotela
		guestcheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotelmgmt.notify(guestwindow.getTitle());
				JFrame info = new JFrame();
				JOptionPane.showMessageDialog(info, "Administrator will be notified. Thank You for staying with us!");
			}
		});
		// logout gosta window guest zatvara prozor i izloguje sa account
		// preview gosta edited by Ahmed
		guestlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ahmed
				// creating MyConector object
				MyConnector mc = new MyConnector();
				// invoking set status method to set active to 0
				mc.setStatus(guestwindow.getTitle(), false);
				// Halim
				guestwindow.dispose();
			}
		});

		// prikaz slobodnih soba za izabrani tip gostu koji ih zeli izmjeniti za
		// sebe by Verdan
		viewFree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomsarea.setText("");
				ArrayList<Room> nums = hotelmgmt.freeRooms(Room);
				try {
					roomsarea.append("\n" + "       " + "FREE ROOMS:" + "\n");
					for (int i = 0; i < nums.size(); i++) {
						roomsarea.append("  " + nums.get(i).getNumber() + " ->> FREE" + "\n");
					}
					freeroomswindow.setVisible(true);
				} catch (NullPointerException ex) {
					JFrame info = new JFrame();
					JOptionPane.showMessageDialog(info, "No data found!");
				}
			}
		});

	}

	// Ahmed
	// returns a JTable populated from database
	private JTable populateJTable() {
		// creating MyConnector object, to connect to database and return info
		MyConnector mq = new MyConnector();
		// duplicating ArrayList type Guest from database
		ArrayList<Guest> list = mq.bindTable(); // invoking MyConnector class
												// method

		// JTable Column headers
		String[] columnNames = { "Username", "Name", "Surname", "Gender", "ID Number`", "Age", "Room#", "Room Type" };

		// row data
		Object[][] rows = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			// filling rows with info from database
			rows[i][0] = list.get(i).getUsername();
			rows[i][1] = list.get(i).getName();
			rows[i][2] = list.get(i).getSurname();
			rows[i][3] = list.get(i).getGender();
			rows[i][4] = list.get(i).getIDnumber();
			rows[i][5] = list.get(i).getAge();
			rows[i][6] = list.get(i).getRoomNumber();
			rows[i][7] = list.get(i).getRoomType();
		}
		DefaultTableModel model = new DefaultTableModel(rows, columnNames);
		// creating a model for JTable of rows and columnNames
		JTable table = new JTable(model);
		table.setModel(model);
		return table;

	}

	// main method
	// **********************************************************************************************************************************

	public static void main(String[] args) {
		Hotel hotel = new Hotel();
		hotel.setVisible(true);
	}

	// other methods
	// ***********************************************************************************************************************************

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}