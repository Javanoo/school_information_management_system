package mdps_sms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import mdps_sms.gui.App;
import mdps_sms.gui.Login;
import mdps_sms.util.Administrator;
import mdps_sms.util.Fleet;
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

	public static Popup popup = new Popup();
	public static Stage primaryStage = new Stage();

	//User interfaces
	private App app = new App();
	private Login login = new Login();

	//container for the UIs.
	public static StackPane mainContainer = new StackPane();
	
	//for logging
	Logger logger;

	@Override
	public void start(Stage primaryStage) throws SecurityException, IOException{
		
		logger = Logger.getLogger(getClass().getName());
		logger.log(Level.FINEST, "application[sms] -> running..");
		logger.addHandler(new FileHandler());
		
		Main.primaryStage = primaryStage;
		popup.setAutoHide(false);
		popup.setHeight(800);
		popup.setWidth(400);

		//load admin details and data structures.

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

		popup.setOnShowing(e -> {
			app.setDisable(true);
			app.setStyle("-fx-background-color: #232323");
			Main.fadeIn(popup.getContent().get(0), 500);
			popup.setHideOnEscape(true);
		});
		popup.setOnHidden(e -> {
			app.setDisable(false);
			app.setStyle("-fx-background-color: transparent");
		});

		Scene scene = new Scene(mainContainer, 1500, 850);
		fadeIn(mainContainer, 800);
		scene.getStylesheets().add("style.css");
		Main.primaryStage.setTitle("School");
		Main.primaryStage.setScene(scene);
		Main.primaryStage.show();
		Main.primaryStage.setMinWidth(800);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	private synchronized Object loadData(String filename) {
		try {
			try(
				ObjectInputStream file = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(filename)));
			){
				return file.readObject();
			}

		}catch(Exception e) {
			//replace with a logger :)
			System.out.println("[Failed to load data from " + filename + " ] : " + e.getMessage());
			return null;
		}
	}

	public static synchronized void saveData(Object obj, String filename) {
		try {
			try(
				ObjectOutputStream file = new ObjectOutputStream(
						new BufferedOutputStream(new FileOutputStream(filename)));
			){
				file.writeObject(obj);
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

	public static void swipeUp(Node E) {
		PathTransition swipe = new PathTransition();
		swipe.setPath(new Line());
		swipe.setAutoReverse(true);
		swipe.setCycleCount(1);
		swipe.setNode(E);
		swipe.play();

	}

	public static void sleep(double m) {
		long time  = System.currentTimeMillis();
		while(true) {
			long stopTime = System.currentTimeMillis();
			if((stopTime - time)/1000 > m) {
				break;
			}
		}
	}
}
