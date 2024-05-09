package mdps_sms.gui;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import mdps_sms.Main;
import mdps_sms.util.Administrator;
import mdps_sms.util.Fleet;
import mdps_sms.util.Person;
import mdps_sms.util.Staff;
import mdps_sms.util.Student;
import mdps_sms.util.Teacher;

public class App extends BorderPane {

	public static ArrayList<Student> studentData = new ArrayList<>();
	public static ArrayList<Teacher> teacherData = new ArrayList<>();
	public static ArrayList<Staff> staffData = new ArrayList<>();
	public static ArrayList<Fleet> fleetData = new ArrayList<>();
	//public static SchoolCalendar calendarData = new SchoolCalendar();
	//public static ArrayList<SchoolClass> classroomsData = new ArrayList<>();

	/*//private Settings settingsForm = new Settings();
	//private ItemList<Student> studentItemList;
	//private ItemList<Teacher> teacherItemList;
	public static ItemList<Staff> staffItemList;
	public static ItemList<Student> studentItemList;
	public static ItemList<Teacher> teacherItemList;
	public static ItemList<Fleet> fleetItemList;
	public  static CalendarTable calendar = new CalendarTable(calendarData.getDates());
	public static  FeesList feesList = new FeesList(studentData);
	public static Classrooms classrooms = new Classrooms(classroomsData);*/

	Administrator admin;

	private static BorderPane centralPanel = new BorderPane();

	private Label forDashboard = new Label("DashBoard");
	private Label forStudents = new Label("Students");
	private Label forTeachers = new Label("Teachers");
	private Label forStaff = new Label("Staff");
	private Label forClasses = new Label("Classes");
	private Label forCalendar = new Label("Calendar");
	private Label forExams = new Label("Exams");
	private Label forFees = new Label("Fees");
	private Label forFleet = new Label("Fleet");
	private ListView<Labeled> navigation = new ListView<>();

	private Button settings = new Button("Settings");
	private Button logout = new Button("Logout");

	private VBox utilButtons = new VBox();
	public static BorderPane leftPanelContents = new BorderPane();

	public static HashMap<String, Object> navTable = new HashMap<>();

	//testing
	ArrayList<Person> list = new ArrayList<>();

	public App() {}

	public App(Administrator admin) {
		if(admin != null) {
			this.admin = admin;

			//navTable.put("DashBoard", new DashBoard(admin, Main.cal));
			navTable.put("Students", new ItemList<>(new Student(), Main.students));
			navTable.put("Teachers", new ItemList<>(new Teacher(), Main.teachers));
			navTable.put("Classes", new Classrooms());
			navTable.put("Staff", new ItemList<>(new Staff(), Main.staff));
			navTable.put("Calendar", new CalendarTable(Main.cal));
			navTable.put("Exams", new ExamsTable(Main.cal));
			navTable.put("Fees", new FeesList(Main.students));
			navTable.put("Fleet",  new ItemList<>(new Fleet(), Main.fleet));

			//navigation tabs
			forDashboard.setGraphic(UiComponents.createIcon("comboChartBlack.png", 24));
			styleNavElem(forDashboard);

			forStudents.setGraphic(UiComponents.createIcon("studentBlack.png", 24));
			styleNavElem(forStudents);

			forTeachers.setGraphic(UiComponents.createIcon("teacherBlack.png", 24));
			styleNavElem(forTeachers);

			forStaff.setGraphic(UiComponents.createIcon("staffBlack.png", 24));
			styleNavElem(forStaff);
			forStaff.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.TAB)) {
				centralPanel.requestFocus();
			}});

			forClasses.setGraphic(UiComponents.createIcon("userBlack.png", 24));
			styleNavElem(forClasses);

			forCalendar.setGraphic(UiComponents.createIcon("calendarBlack.png", 24));
			styleNavElem(forCalendar);

			forExams.setGraphic(UiComponents.createIcon("courseAssignBlack.png", 24));
			styleNavElem(forExams);

			forFees.setGraphic(UiComponents.createIcon("cashBlack.png", 24));
			styleNavElem(forFees);

			forFleet.setGraphic(UiComponents.createIcon("shuttleBusBlack.png", 24));
			styleNavElem(forFleet);

			navigation.setItems(FXCollections.observableArrayList(/*forDashboard,*/ forCalendar, forClasses,  forExams, forFees, forTeachers,
					forStudents, forStaff, forFleet));
			navigation.setMaxHeight(356);
			navigation.setFixedCellSize(44);
			navigation.setMaxWidth(200);
			navigation.setId("mainNav");
			navigation.getSelectionModel().selectFirst();
			navigation.getSelectionModel().selectedItemProperty().addListener(e ->
				{
					//navTable.put("DashBoard", new DashBoard(admin, Main.cal));
					navTable.put("Students", new ItemList<>(new Student(), Main.students));
					navTable.put("Teachers", new ItemList<>(new Teacher(), Main.teachers));
					Classrooms.classesTable.refresh();
					navTable.put("Staff", new ItemList<>(new Staff(), Main.staff));
					navTable.put("Fees", new FeesList(Main.students));
					navTable.put("Calendar", new CalendarTable(Main.cal));
					navTable.put("Exams", new ExamsTable(Main.cal));
					switchView((Node)navTable.get(navigation.getSelectionModel().getSelectedItem().getText()));
				}
			);
		Rectangle navRec = new Rectangle(200, 356);
		navRec.setArcHeight(15);
		navRec.setArcWidth(15);
		navigation.setClip(navRec);


		//bottom action buttons
		settings.setGraphic(UiComponents.createIcon("settingsBlack.png", 22));
		styleNavElem(settings);
		settings.setMinWidth(120);
		settings.setAlignment(Pos.CENTER_LEFT);
		settings.setPadding(new Insets(5, 0, 5, 10));
		settings.setStyle("-fx-background-color: white");
		settings.setOnAction(e -> {
			Settings setting = new Settings(admin); 
			setting.cancel.setOnAction(v -> {
				switchView((Node)navTable.get(navigation.getSelectionModel().getSelectedItem().getText()));
				setLeft(leftPanelContents);
				centralPanel.setPadding(new Insets(5, 5, 0, 5));
			});
			switchView(setting);
			setLeft(null);
			centralPanel.setPadding(new Insets(0));
		});
		Rectangle setRec = new Rectangle(120, 33);
		setRec.setArcHeight(30);
		setRec.setArcWidth(30);
		settings.setClip(setRec);

		logout.setGraphic(UiComponents.createIcon("logOutBlack.png", 22));
		styleNavElem(logout);
		logout.setMinWidth(200);
		logout.setAlignment(Pos.CENTER_LEFT);
		logout.setPadding(new Insets(5, 0, 5, 10));
		logout.setStyle("-fx-background-color: white");
		logout.setOnAction(e -> logout());
		logout.setTooltip(new Tooltip("end session"));
		Rectangle logRec = new Rectangle(200, 33);
		logRec.setArcHeight(20);
		logRec.setArcWidth(20);
		logout.setClip(logRec);

		utilButtons.getChildren().addAll(settings, logout);
		utilButtons.setSpacing(10);

		leftPanelContents.setTop(navigation);
		leftPanelContents.setBottom(utilButtons);
		BorderPane.setAlignment(navigation, Pos.CENTER);
		leftPanelContents.setPadding(new Insets(5,10,7,10));
		leftPanelContents.setStyle("-fx-background-color: #232323");

		centralPanel.setPadding(new Insets(5, 5, 0, 5));
		centralPanel.setCenter(new CalendarTable(Main.cal));


		setLeft(leftPanelContents);
		setCenter(centralPanel);
		}
	}

	public void logout() {
		admin.setSession(0);
		Main.saveData(admin, Main.STORAGEFILE1);
		Main.primaryStage.close();
	}

	public static void switchView(Node s) {
		centralPanel.getChildren().clear();
		centralPanel.setCenter(s);
		fadeIn(centralPanel);
	}

	public static void fadeIn(Node E) {
		FadeTransition fade = new FadeTransition(new Duration(300), E);
		fade.setInterpolator(Interpolator.EASE_BOTH);
		fade.setAutoReverse(true);
		fade.setCycleCount(1);
		fade.setFromValue(0.2);
		fade.setToValue(1);
		fade.play();
	}
	protected void styleNavElem(Labeled label) {
		label.setTextFill(Color.BLACK);
		label.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 1));
		label.setPadding(new Insets(5, 0, 5, 0));
		label.setGraphicTextGap(10);
	}
}

