package mdps_sms.gui;

import java.util.Date;
import java.util.LinkedList;
import java.util.TreeSet;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.effect.Effect;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import mdps_sms.Main;
import mdps_sms.util.Administrator;
import mdps_sms.util.Person;

public class App extends BorderPane {
	Administrator admin = null;
	
	private static BorderPane centralPanel = new BorderPane();
	
	private HBox profileBox = new HBox();
	private Label loggedUser = new Label("Admin");
	
	private Label dashboard = new Label("DashBoard");
	private Label students = new Label("Students");
	private Label teachers = new Label("Teachers");
	private Label staff = new Label("Staff");
	private Label calendar = new Label("Calendar");
	private Label exams = new Label("Exams");
	private Label fees = new Label("Fees");
	private Label fleet = new Label("Fleet");
	private ListView<Label> navigation = new ListView<>();
	
	private Button settings = new Button("Settings");
	private Button logout = new Button("Logout");
	
	private ContextMenu conMenu = new ContextMenu();
	
	private VBox utilButtons = new VBox();
	public static BorderPane leftPanelContents = new BorderPane();
	
	//testing
	TreeSet<Person> list = new TreeSet<>();
	
	public App() {}
	
	public App(Administrator admin) {
		
		this.admin = admin;
		
		DirectoryChooser dir = new DirectoryChooser();
		
		//user profile
		loggedUser.setGraphic(UiComponents.createIcon("userBlack.png", 24));
		loggedUser.setFont(Font.font("Outfit SemiBold", 15));
		loggedUser.setTextFill(Color.BLACK);
		loggedUser.setPadding(new Insets(5, 0, 2, 19));
		loggedUser.setGraphicTextGap(30);
		profileBox.getChildren().add(loggedUser);
		profileBox.setStyle("-fx-background-color:" + UiComponents.backgroundcolor);
		profileBox.setMinHeight(35);
		Rectangle profRec = new Rectangle(230, 35);
		profRec.setArcHeight(11.5);
		profRec.setArcWidth(11.5);
		profileBox.setClip(profRec);
		
		//navigation tabs
		dashboard.setGraphic(UiComponents.createIcon("comboChartBlack.png", 24));
		dashboard.setTextFill(Color.BLACK);
		dashboard.setFont(Font.font("Inter SemiBold", 15));
		dashboard.setPadding(new Insets(5, 0, 5, 0));
		dashboard.setGraphicTextGap(10);
		
		students.setGraphic(UiComponents.createIcon("studentBlack.png", 24));
		students.setTextFill(Color.BLACK);
		students.setFont(Font.font("Inter SemiBold", 15));
		students.setPadding(new Insets(5, 0, 5, 0));
		students.setGraphicTextGap(10);
		
		teachers.setGraphic(UiComponents.createIcon("teacherBlack.png", 24));
		teachers.setTextFill(Color.BLACK);
		teachers.setFont(Font.font("Inter SemiBold", 15));
		teachers.setPadding(new Insets(5, 0, 5, 0));
		teachers.setGraphicTextGap(10);
		
		staff.setGraphic(UiComponents.createIcon("staffBlack.png", 24));
		staff.setTextFill(Color.BLACK);
		staff.setFont(Font.font("Inter SemiBold", 15));
		staff.setPadding(new Insets(5, 0, 5, 0));
		staff.setGraphicTextGap(10);
		
		calendar.setGraphic(UiComponents.createIcon("calendarBlack.png", 24));
		calendar.setTextFill(Color.BLACK);
		calendar.setFont(Font.font("Inter SemiBold", 15));
		calendar.setPadding(new Insets(5, 0, 5, 0));
		calendar.setGraphicTextGap(10);
		
		exams.setGraphic(UiComponents.createIcon("courseAssignBlack.png", 24));
		exams.setTextFill(Color.BLACK);
		exams.setFont(Font.font("Inter SemiBold", 15));
		exams.setPadding(new Insets(5, 0, 5, 0));
		exams.setGraphicTextGap(10);
		
		fees.setGraphic(UiComponents.createIcon("cashBlack.png", 24));
		fees.setTextFill(Color.BLACK);
		fees.setFont(Font.font("Inter SemiBold", 15));
		fees.setPadding(new Insets(5, 0, 5, 0));
		fees.setGraphicTextGap(10);
		
		fleet.setGraphic(UiComponents.createIcon("shuttleBusBlack.png", 24));
		fleet.setTextFill(Color.BLACK);
		fleet.setFont(Font.font("Inter SemiBold", 15));
		fleet.setPadding(new Insets(5, 0, 5, 0));
		fleet.setGraphicTextGap(10);
		
		
		navigation.setItems(FXCollections.observableArrayList(dashboard, students,
				teachers, calendar, exams, staff, fees, fleet));
	//	navigation.setStyle("-fx-background-color: " + UiComponents.backgroundcolor);
		navigation.setMaxHeight(360);
		navigation.setFixedCellSize(44);
		navigation.setMaxWidth(200);
		navigation.getSelectionModel().selectedItemProperty().addListener(e -> 
			{
				switch(navigation.getSelectionModel().selectedItemProperty().get().getText()) {
					case "Dashboard" : {
						switchView(null);
						break;
					}
					case "Students" : { 
						switchView(new ItemList());
						break;
					}
					case "Teachers" : {
						switchView(new TeacherForm());
						break;
					}
					case "Staff" : { 
						switchView(new Form());
						break;
					}
					case "Fees" : { 
						switchView(null);
						break;
					}
					case "Fleet" : {
						switchView(new FleetForm());
						break;
					}
					default: break;
				}
			}
		);
		
		
		//bottom action buttons
		settings.setGraphic(UiComponents.createIcon("settingsBlack.png", 24));
		settings.setFont(Font.font("Inter SemiBold", 15));
		settings.setTextFill(Color.BLACK);
		settings.setGraphicTextGap(10);
		settings.setMinWidth(200);
		settings.setPadding(new Insets(5));
		settings.setAlignment(Pos.CENTER_LEFT);
		settings.setStyle("-fx-background-color: white");
		settings.setOnAction(e -> {
			switchView(new Settings(list));
			leftPanelContents.setDisable(true);
		});
		
		logout.setGraphic(UiComponents.createIcon("logOutBlack.png", 24));
		logout.setFont(Font.font("Inter SemiBold", 15));
		logout.setTextFill(Color.BLACK);
		logout.setGraphicTextGap(10);
		logout.setMinWidth(200);
		logout.setPadding(new Insets(5));
		logout.setAlignment(Pos.CENTER_LEFT);
		logout.setStyle("-fx-background-color: white");
		logout.setOnAction(e -> logout());
		
		utilButtons.getChildren().addAll(settings, logout);
		utilButtons.setSpacing(10);
		
		leftPanelContents.setTop(navigation);
		leftPanelContents.setBottom(utilButtons);
		BorderPane.setAlignment(navigation, Pos.CENTER);
		leftPanelContents.setPadding(new Insets(5,10,5,10));
		leftPanelContents.setStyle("-fx-background-color: #232323");
		
		
		//generate random things
		for(int i = 0; i < 200; i++) {
	//		Student student = new Student("stud" + i, "Male", new Date(), "Malawian", new StringBuilder("nice boy"), new Parent());
	//		list.add(student);
		}
		
		centralPanel.setPadding(new Insets(5, 5, 0, 5));
	//	centralPanel.setMaxWidth(1200);
		centralPanel.setCenter(new ItemList(list));
		
		
		this.setLeft(leftPanelContents);
		this.setCenter(centralPanel);
		this.setStyle("-fx-background-color: white");
		//this.setPadding(new Insets(0, 0, 5, 0));
	}
	
	public void logout() {
		admin.setSession(0);
		Main.saveAdmin(admin);
		System.exit(0);
	}
	
	public void sleep(double m) {
		long time  = System.currentTimeMillis();
		while(true) {
			long stopTime = System.currentTimeMillis();
			if((stopTime - time)/1000 > m) {
				break;
			}
		}
	}
	
	public static void switchView(Node s) {
		centralPanel.getChildren().clear();
		centralPanel.setRight(s);
		fadeIn(centralPanel);
	}
	
	public static void fadeIn(Node E) {
		FadeTransition fade = new FadeTransition(new Duration(350), E);
		fade.setAutoReverse(true);
		fade.setCycleCount(1);
		fade.setFromValue(0.2);
		fade.setToValue(1);
		fade.play();
	}
}

class CustomCell extends ListCell<Label>{
	
	public CustomCell() {
		
	}
	
	@Override
	protected void updateItem(Label item, boolean empty) {
		super.updateItem(item, empty);
		if(item != null) {
			if(isSelected()) {
				item.setTextFill(Color.WHITE);
				item.setStyle("-fx-background-color: gray");
			}
		}
	}
	
}