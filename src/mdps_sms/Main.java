package mdps_sms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Popup;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.stage.Stage;
import javafx.util.Duration;
import mdps_sms.gui.App;
import mdps_sms.gui.Form;
import mdps_sms.gui.Login;
import mdps_sms.util.Administrator;
import mdps_sms.util.SchoolClass;
import mdps_sms.util.Staff;
import mdps_sms.util.Student;
import mdps_sms.util.Teacher;

public class Main extends Application{
	
	//data structure files
	public static final String STORAGEFILE1   = "mdps1.bin";   // for admin
	private final String STORAGEFILE_C  = "mdps_C.bin";  // for classrooms
	private final String STORAGEFILE_S  = "mdps_S.bin";  // for students
	private final String STORAGEFILE_T  = "mdps_T.bin";  // for teachers
	private final String STORAGEFILE_s  = "mdps_s.bin";  // for students
	private final String STORAGEFILE_F  = "mdps_F.bin";  // for fleets
	private final String STORAGEFILE_C2 = "mdps_C2.bin"; // for calendars
	
	//data structures
	private static Administrator admin= null;
	private static TreeSet<Student> students = new TreeSet<>();
	private static TreeSet<Teacher> teachers = new TreeSet<>();
	private static TreeSet<Staff> staff = new TreeSet<>();
	private static TreeSet<SchoolClass> classrooms = new TreeSet<>();
	
	public static Popup popup = new Popup();
	public static Stage primaryStage = new Stage();
	
	//User interfaces
	private App app = new App();
	private Login login = new Login();
	
	//container for the UIs.
	public static StackPane mainContainer = new StackPane();
	
	@Override
	public void start(Stage primaryStage){
		
		Main.primaryStage = primaryStage;
		popup.setAutoHide(false);
		popup.setHeight(800);
		popup.setWidth(400);
		
		//load admin details
		admin = (Administrator)loadData(STORAGEFILE1);
		//students = (TreeSet<Student>)loadAdmin(STORAGEFILE_S);
		//teachers = (TreeSet<Teacher>)loadAdmin(STORAGEFILE_T);
		//classrooms = (TreeSet<SchoolClass>)loadAdmin(STORAGEFILE_C);
		staff = (TreeSet<Staff>)loadData(STORAGEFILE_s);
		
		//System.out.print(admin.getName());
		
		app = new App(admin,students, teachers, staff, classrooms);
		
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
		});
		popup.setOnHidden(e -> {
			app.setDisable(false);
			app.setStyle("-fx-background-color: transparent");
		});
		
		Scene scene = new Scene(mainContainer, 1500, 850);
		scene.getStylesheets().add("style.css");
		Main.primaryStage.setTitle("School");
		Main.primaryStage.setScene(scene);
		Main.primaryStage.show();
		Main.primaryStage.setMinWidth(800);
		/*Main.primaryStage.setOnCloseRequest(e -> {
			saveData(staff, STORAGEFILE_s);
			saveData(admin, STORAGEFILE1);
		});*/
	}
	
	private synchronized Object loadData(String filename) {
		try {
			try( 
				ObjectInputStream file = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(filename), 512 * 3));
			){
				return file.readObject();
			}
			
		}catch(Exception e) {
			//replace with a logger :)
			System.out.println("[Failed to load admin] : " + e.getMessage());
			return null;
		}
	}
	
	public static synchronized void saveData(Object obj, String filename) {
		try {
			try( 
				ObjectOutputStream file = new ObjectOutputStream(
						new BufferedOutputStream(new FileOutputStream(filename), 512 * 3));
			){
				file.writeObject(obj);
			}
			
		}catch(Exception e) {
			//replace with a logger :)
			System.out.println("[Failed to save admin details] : " + e.getMessage());
		}
	}
	
	public static void fadeIn(Node E, double duration) {
		FadeTransition fade = new FadeTransition(new Duration(duration), E);
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
