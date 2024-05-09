package mdps_sms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import mdps_sms.gui.App;
import mdps_sms.gui.Login;
import mdps_sms.util.Administrator;
import mdps_sms.util.Config;
import mdps_sms.util.Fleet;
import mdps_sms.util.PdfPrinter;
import mdps_sms.util.SchoolCalendar;
import mdps_sms.util.SchoolClass;
import mdps_sms.util.Staff;
import mdps_sms.util.Student;
import mdps_sms.util.Teacher;

public class Main extends Application{

	//data structure files
	 /** for admin */
	public static final String STORAGEFILE1   = "mdps1.bin";  
	
	/** for classrooms */
	public static final String STORAGEFILE_C  = "mdps_C.bin";  
	
	/** for students */
	public static final String STORAGEFILE_S  = "mdps_S.bin";
	
	/** for teachers */
	public static final String STORAGEFILE_T  = "mdps_T.bin";
	
	/** for staff */
	public static final String STORAGEFILE_s  = "mdps_s.bin";
	
	/** for fleets */
	public static final String STORAGEFILE_F  = "mdps_F.bin";
	
	/** for calendars */
	public static final String STORAGEFILE_C2 = "mdps_C2.bin"; 

	//data structures
	private static Administrator admin= null;
	public static ArrayList<Student> students = new ArrayList<>();
	public static ArrayList<Teacher> teachers = new ArrayList<>();
	public static ArrayList<Staff> staff = new ArrayList<>();
	public static ArrayList<SchoolClass> classrooms = new ArrayList<>();
	public static ArrayList<Fleet> fleet = new ArrayList<>();
	public static SchoolCalendar cal = new SchoolCalendar();
	
	public static Config configuration ;

	public static Popup popup = new Popup();
	public static Stage primaryStage = new Stage();

	//User interfaces
	private App app = new App();
	private Login login = new Login();

	//container for the UIs.
	public static StackPane mainContainer = new StackPane();
	
	//for logging
	Logger logger;
	
	File dir = new File("");

	@Override
	public void start(Stage primaryStage) throws SecurityException, IOException{
		
		configuration = (Config)loadData("cnfg") == null ? new Config() : (Config)loadData("cnfg");
		
		logger = Logger.getLogger(getClass().getName());
		logger.addHandler(new ConsoleHandler());
		logger.log(Level.FINEST, "application[sms] -> running..");
		
		Main.primaryStage = primaryStage;
		popup.setAutoHide(false);
		popup.setHeight(800);
		popup.setWidth(400);

		//load admin details and data structures.
		if(!new File(Main.STORAGEFILE1).exists() && !new File(Main.STORAGEFILE_C).exists() && !new File(Main.STORAGEFILE_C2).exists()
				&& !new File(Main.STORAGEFILE_F).exists() && !new File(Main.STORAGEFILE_s).exists() && !new File(Main.STORAGEFILE_S).exists() &&
				!new File(Main.STORAGEFILE_T).exists() && new File(Main.configuration.backupPath).exists()) restore();

		admin = (Administrator)loadData(STORAGEFILE1);
		students = (ArrayList<Student>)loadData(STORAGEFILE_S) == null ? new ArrayList<>(): (ArrayList<Student>)loadData(STORAGEFILE_S);
		teachers = (ArrayList<Teacher>)loadData(STORAGEFILE_T) == null ? new ArrayList<>(): (ArrayList<Teacher>)loadData(STORAGEFILE_T);
		classrooms = (ArrayList<SchoolClass>)loadData(STORAGEFILE_C) == null ? new ArrayList<>(): (ArrayList<SchoolClass>)loadData(STORAGEFILE_C);
		fleet = (ArrayList<Fleet>)loadData(STORAGEFILE_F) == null ? new ArrayList<>(): (ArrayList<Fleet>)loadData(STORAGEFILE_F);
		staff = (ArrayList<Staff>)loadData(STORAGEFILE_s) == null ? new ArrayList<>(): (ArrayList<Staff>)loadData(STORAGEFILE_s);
		cal = (SchoolCalendar)loadData(STORAGEFILE_C2) == null ? new SchoolCalendar() : (SchoolCalendar)loadData(STORAGEFILE_C2);
		
		app = new App(admin);
		
		//if the admin logged out, load the login ui, otherwise
		//continue with existing session.
		if(admin == null || admin.getSession() == 0) {
			
			login = new Login(admin, app);
			mainContainer = new StackPane(login);
		}
		else {
			mainContainer = new StackPane(app);
		}
		
	/*	try {
			PdfPrinter print = new PdfPrinter();
		} catch (Exception e1) {
		}*/

		popup.setOnShowing(e -> {
			app.setDisable(true);
			app.setStyle("-fx-background-color: " + configuration.theme);
			Main.fadeIn(popup.getContent().get(0), 500);
			popup.setHideOnEscape(true);
			popup.centerOnScreen();
			popup.setWidth(((Node)(popup.getContent().get(0))).computeAreaInScreen()/2);
			popup.setHeight(((Node)(popup.getContent().get(0))).computeAreaInScreen()/2);
		});
		popup.setOnHidden(e -> {
			app.setDisable(false);
			app.setStyle("-fx-background-color: transparent");
		});

		Scene scene = new Scene(mainContainer, 1500, 850);
		fadeIn(mainContainer, 800);
		scene.getStylesheets().add(configuration.theme.equals("#3A957D") ? "style2.css" : configuration.theme.equals("#3A7795") ? "style3.css" : "style.css");
		Main.primaryStage.setTitle("School Information System");
		Main.primaryStage.setScene(scene);
		Main.primaryStage.show();
		Main.primaryStage.setMinWidth(800);
		
	/*	try {
			PdfPrinter pdf = new PdfPrinter();
		}catch(Exception e) {
			System.out.println("error" + e.getMessage());
		}*/
		backUp();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public static synchronized Object loadData(String filename) {
		try {
			//use any combination (use the same for writing and reading)
			SecretKeySpec key64 = new SecretKeySpec( new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish" );
			Cipher cipher = Cipher.getInstance( "Blowfish" );
			
			//read object from file
			cipher.init( Cipher.DECRYPT_MODE, key64 );
			try(
				CipherInputStream cipherInputStream = new CipherInputStream( new BufferedInputStream( new FileInputStream( filename ) ), cipher );
				ObjectInputStream inputStream = new ObjectInputStream( cipherInputStream );
			){
				SealedObject sealedObject = (SealedObject) inputStream.readObject();
				return sealedObject.getObject( cipher );
			}

		}catch(Exception e) {
			//replace with a logger :)
			System.out.println("[Failed to load data from " + filename + " ] : " + e.getMessage());
			return null;
		}
	}

	public static synchronized void saveData(Object obj, String filename) {
		try {
			//You may use any combination, but you should use the same for writing and reading
			SecretKeySpec key64 = new SecretKeySpec( new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish" );
			Cipher cipher = Cipher.getInstance( "Blowfish" );
			
			//Code to write your object to file
			cipher.init( Cipher.ENCRYPT_MODE, key64 );
			SealedObject sealedObject = new SealedObject( (Serializable) obj, cipher);
			
			try(
				CipherOutputStream cipherOutputStream = new CipherOutputStream( new BufferedOutputStream( new FileOutputStream( filename ) ), cipher );
				ObjectOutputStream outputStream = new ObjectOutputStream( cipherOutputStream );
			){
				//file.writeObject(obj);
				outputStream.writeObject( sealedObject );
			}
		}catch(Exception e) {
			//replace with a logger :)
			System.out.println("[Failed to save data to " + filename + " ] : " + e.getMessage());
		}
	}

	public static void fadeIn(Node E, double duration) {
		FadeTransition fade = new FadeTransition(new Duration(duration), E);
		fade.setInterpolator(Interpolator.EASE_IN);
		fade.setAutoReverse(true);
		fade.setCycleCount(1);
		fade.setFromValue(0.2);
		fade.setToValue(1);
		fade.play();
	}
	
	public static void fadeOut(Node E, double duration) {
		FadeTransition fade = new FadeTransition(new Duration(duration), E);
		fade.setInterpolator(Interpolator.EASE_IN);
		fade.setAutoReverse(true);
		fade.setCycleCount(1);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.play();
		fade.setOnFinished(e -> {
			Main.popup.hide();
			Main.popup.getContent().clear();
		});
	}

	public static void swipeUp(Node E) {
		PathTransition swipe = new PathTransition();
		swipe.setPath(new Line());
		swipe.setAutoReverse(true);
		swipe.setCycleCount(1);
		swipe.setNode(E);
		swipe.play();

	}

	public static boolean sleep(double seconds) {
		long time  = System.currentTimeMillis();
		while(true) {
			long stopTime = System.currentTimeMillis();
			if((stopTime - time)/1000 > seconds) {
				break;
			}
		}
		return true;
	}
	public static synchronized void backUp() {
		try {
			//You may use any combination, but you should use the same for writing and reading
			SecretKeySpec key64 = new SecretKeySpec( new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish" );
			Cipher cipher = Cipher.getInstance( "Blowfish" );
			
			
			
			
			Administrator admin = (Administrator)loadData(STORAGEFILE1);
			ArrayList<Student> students = (ArrayList<Student>)loadData(STORAGEFILE_S) == null ? new ArrayList<>(): (ArrayList<Student>)loadData(STORAGEFILE_S);
			ArrayList<Teacher> teachers = (ArrayList<Teacher>)loadData(STORAGEFILE_T) == null ? new ArrayList<>(): (ArrayList<Teacher>)loadData(STORAGEFILE_T);
			ArrayList<Staff> staff = (ArrayList<Staff>)loadData(STORAGEFILE_s) == null ? new ArrayList<>(): (ArrayList<Staff>)loadData(STORAGEFILE_s);
			ArrayList<SchoolClass> classrooms = (ArrayList<SchoolClass>)loadData(STORAGEFILE_C) == null ? new ArrayList<>(): (ArrayList<SchoolClass>)loadData(STORAGEFILE_C);
			ArrayList<Fleet> fleet =  (ArrayList<Fleet>)loadData(STORAGEFILE_F) == null ? new ArrayList<>(): (ArrayList<Fleet>)loadData(STORAGEFILE_F);
			SchoolCalendar cal = (SchoolCalendar)loadData(STORAGEFILE_C2) == null ? new SchoolCalendar() : (SchoolCalendar)loadData(STORAGEFILE_C2);
			
			ArrayList<Object> obj = new ArrayList<>();
			obj.add(admin);
			obj.add(students);
			obj.add(teachers);
			obj.add(staff);
			obj.add(classrooms);
			obj.add(fleet);
			obj.add(cal);
			obj.add(new Date());
			
			//Code to write your object to file
			cipher.init( Cipher.ENCRYPT_MODE, key64 );
			SealedObject sealedObject = new SealedObject( (Serializable) obj, cipher);
			
			try(
				CipherOutputStream cipherOutputStream = new CipherOutputStream(
						new BufferedOutputStream( new FileOutputStream(Main.configuration.backupPath), 512 * Main.configuration.buffer), cipher );
				ObjectOutputStream outputStream = new ObjectOutputStream( cipherOutputStream );
			){
				//file.writeObject(obj);
				outputStream.writeObject( sealedObject );
			}
		}catch(Exception e) {
			//replace with a logger :)
			System.out.println("[Failed to create backup] : " + e.getMessage());
		}
	}
	
	public static void restore() {
		try {
			//You may use any combination, but you should use the same for writing and reading
			SecretKeySpec key64 = new SecretKeySpec( new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish" );
			Cipher cipher = Cipher.getInstance( "Blowfish" );
			
			//Code to read your object from file
			cipher.init( Cipher.DECRYPT_MODE, key64 );
			try(
				CipherInputStream cipherInputStream = new CipherInputStream( new BufferedInputStream( new FileInputStream("School.bck") ), cipher );
				ObjectInputStream inputStream = new ObjectInputStream( cipherInputStream );
			){
				SealedObject sealedObject = (SealedObject) inputStream.readObject();
				ArrayList<Object> files =  (ArrayList<Object>) sealedObject.getObject( cipher );
				
				saveData((Administrator)files.get(0), Main.STORAGEFILE1);
				saveData((ArrayList<Student>)files.get(1), Main.STORAGEFILE_S);
				saveData((ArrayList<Teacher>) files.get(2), Main.STORAGEFILE_T);
				saveData((ArrayList<Staff>)files.get(3), Main.STORAGEFILE_s);
				saveData((ArrayList<SchoolClass>)files.get(4), Main.STORAGEFILE_C);
				saveData((ArrayList<Fleet>)files.get(5), Main.STORAGEFILE_F);
				saveData((SchoolCalendar)files.get(6), Main.STORAGEFILE_C2);
			}
		}catch(Exception e) {
			//replace with a logger :)
			System.out.println("[Failed to retore backup] : " + e.getMessage());
		}
	}
}
