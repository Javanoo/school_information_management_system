package mdps_sms;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class App extends BorderPane {
	private StackPane leftPanel = new StackPane();
	private StackPane centralPanel = new StackPane();
	
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
	
	private VBox leftPanelContents = new VBox();
	
	
	public App(Scene scene) {
		//user profile
		loggedUser.setGraphic(UiComponents.createIcon("userBlack.png", 24));
		loggedUser.setFont(Font.font("Outfit SemiBold", 15));
		loggedUser.setTextFill(Color.BLACK);
		loggedUser.setPadding(new Insets(5, 0, 2, 19));
		loggedUser.setGraphicTextGap(30);
		profileBox.getChildren().add(loggedUser);
		profileBox.setStyle("-fx-background-color: #D9D9D9");
		profileBox.setMinHeight(35);
		Rectangle profRec = new Rectangle(230, 35);
		profRec.setArcHeight(12);
		profRec.setArcWidth(12);
		profileBox.setClip(profRec);
		
		//navigation tabs
		dashboard.setGraphic(UiComponents.createIcon("comboChartBlack.png", 24));
		dashboard.setTextFill(Color.BLACK);
		dashboard.setFont(Font.font("Outfit SemiBold", 15));
		dashboard.setPadding(new Insets(10));
		dashboard.setGraphicTextGap(30);
		
		students.setGraphic(UiComponents.createIcon("studentBlack.png", 24));
		students.setTextFill(Color.BLACK);
		students.setFont(Font.font("Outfit SemiBold", 15));
		students.setPadding(new Insets(10));
		students.setGraphicTextGap(30);
		
		teachers.setGraphic(UiComponents.createIcon("teacherBlack.png", 24));
		teachers.setTextFill(Color.BLACK);
		teachers.setFont(Font.font("Outfit SemiBold", 15));
		teachers.setPadding(new Insets(10));
		teachers.setGraphicTextGap(30);
		
		staff.setGraphic(UiComponents.createIcon("staffBlack.png", 24));
		staff.setTextFill(Color.BLACK);
		staff.setFont(Font.font("Outfit SemiBold", 15));
		staff.setPadding(new Insets(10));
		staff.setGraphicTextGap(30);
		
		calendar.setGraphic(UiComponents.createIcon("calendarBlack.png", 24));
		calendar.setTextFill(Color.BLACK);
		calendar.setFont(Font.font("Outfit SemiBold", 15));
		calendar.setPadding(new Insets(10));
		calendar.setGraphicTextGap(30);
		
		exams.setGraphic(UiComponents.createIcon("courseAssignBlack.png", 24));
		exams.setTextFill(Color.BLACK);
		exams.setFont(Font.font("Outfit SemiBold", 15));
		exams.setPadding(new Insets(10));
		exams.setGraphicTextGap(30);
		
		fees.setGraphic(UiComponents.createIcon("cashBlack.png", 24));
		fees.setTextFill(Color.BLACK);
		fees.setFont(Font.font("Outfit SemiBold", 15));
		fees.setPadding(new Insets(10));
		fees.setGraphicTextGap(30);
		
		fleet.setGraphic(UiComponents.createIcon("shuttleBusBlack.png", 24));
		fleet.setTextFill(Color.BLACK);
		fleet.setFont(Font.font("Outfit SemiBold", 15));
		fleet.setPadding(new Insets(10));
		fleet.setGraphicTextGap(30);
		
		navigation.setItems(FXCollections.observableArrayList(dashboard, students,
				teachers, staff, calendar, exams, fees, fleet));
		navigation.setMaxHeight(365);
		navigation.setFixedCellSize(45);
		Rectangle navRec = new Rectangle(200, 365);
		navRec.setArcHeight(15);
		navRec.setArcWidth(15);
		navigation.setClip(navRec);
		
		//bottom action buttons
		settings.setGraphic(UiComponents.createIcon("settingsBlack.png", 24));
		settings.setFont(Font.font("Outfit SemiBold", 14));
		settings.setTextFill(Color.BLACK);
		settings.setPadding(new Insets(5, 0, 5, 13));
		settings.setGraphicTextGap(30);
		settings.setMinWidth(170);
		settings.setTranslateX(6);
		settings.setAlignment(Pos.CENTER_LEFT);
		settings.setStyle("-fx-background-color: " + UiComponents.backgroundcolor);
		Rectangle setRec = new Rectangle(160, 34);
		setRec.setArcHeight(30);
		setRec.setArcWidth(30);
		settings.setClip(setRec);
		
		logout.setGraphic(UiComponents.createIcon("logOutBlack.png", 24));
		logout.setFont(Font.font("Outfit SemiBold", 14));
		logout.setTextFill(Color.BLACK);
		logout.setPadding(new Insets(5, 0, 5, 20));
		logout.setGraphicTextGap(30);
		logout.setMinWidth(200);
		logout.translateYProperty().bind(this.heightProperty().subtract(495));
		logout.setAlignment(Pos.CENTER_LEFT);
		logout.setStyle("-fx-background-color: " + UiComponents.backgroundcolor);
		Rectangle logRec = new Rectangle(180, 34);
		logRec.setArcHeight(12);
		logRec.setArcWidth(12);
		logout.setClip(logRec);
		
		leftPanelContents.getChildren().addAll(profileBox, navigation, settings, logout);
		leftPanelContents.setPadding(new Insets(0, 0, 5, 10));
		leftPanelContents.setSpacing(6);
		leftPanelContents.setTranslateX(5);
		leftPanel.getChildren().add(leftPanelContents);
		leftPanel.setMaxWidth(200);
		leftPanel.minHeightProperty().bind(scene.heightProperty().subtract(20));
		leftPanel.setStyle("-fx-background-color: #404040");
		
		
		centralPanel.maxWidthProperty().bind(this.widthProperty().subtract(230));
		centralPanel.maxHeightProperty().bind(this.heightProperty().subtract(10));
		centralPanel.setPadding(new Insets(10));
		centralPanel.setStyle("-fx-background-color: #404040");
		//centralPanel.getChildren().add(new Summary(centralPanel));
		Rectangle centRec = new Rectangle(centralPanel.getWidth(), centralPanel.getHeight());
		centRec.widthProperty().bind(centralPanel.widthProperty());
		centRec.heightProperty().bind(centralPanel.heightProperty());
		centRec.setArcHeight(24);
		centRec.setArcWidth(24);
		centralPanel.setClip(centRec);
		
		
		this.setLeft(leftPanel);
		this.setCenter(centralPanel);
		this.setStyle("-fx-background-color: #D9D9D9");
	}
}

class Summary extends VBox{
	private Label forID = new Label("ID");
	private Text id = new Text("YgU22");
	private GridPane iDContainer = new GridPane();
	
	private Label forName = new Label("Name");
	private Text name = new Text("Yeong shu");
	private GridPane nameContainer = new GridPane();
	
	private Label forGender = new Label("Gender");
	private Text gender = new Text("Female");
	private GridPane genderContainer = new GridPane();
	
	private Label forNationality = new Label("Nationality");
	private Text nationality = new Text("Malawian");
	private GridPane nationalityContainer = new GridPane();
	
	private Label forClassRoom = new Label("Classroom");
	private Text classRoom = new Text("Standard 8");
	private GridPane classRoomContainer = new GridPane();
	
	private Label forDateAdded = new Label("Date Added");
	private Text dateAdded = new Text("20/11/2023");
	private GridPane dateAddedContainer = new GridPane();
	
	private Label forParent = new Label("Parent");
	private Text parent = new Text("Ms Meow");
	private GridPane parentContainer = new GridPane();
	
	Summary(Pane s){
		forID.setTextFill(Color.BLACK);
		forID.setFont(Font.font("Outfit SemiBold", 16));
		id.setFill(Color.BLACK);
		id.setFont(Font.font("Outfit SemiBold", 14));
		iDContainer.add(forID, 0, 0);
		iDContainer.add(id, 0, 1);
		
		forName.setTextFill(Color.BLACK);
		forName.setFont(Font.font("Outfit SemiBold", 16));
		name.setFill(Color.GREY);
		name.setFont(Font.font("Outfit SemiBold", 14));
		nameContainer.add(forName, 0, 0);
		nameContainer.add(name, 0, 1);
		
		forGender.setTextFill(Color.BLACK);
		forGender.setFont(Font.font("Outfit SemiBold", 16));
		gender.setFill(Color.BLACK);
		gender.setFont(Font.font("Outfit SemiBold", 14));
		genderContainer.add(forGender, 0, 0);
		genderContainer.add(gender, 0, 1);
		
		forNationality.setTextFill(Color.BLACK);
		forNationality.setFont(Font.font("Outfit SemiBold", 16));
		nationality.setFill(Color.BLACK);
		nationality.setFont(Font.font("Outfit SemiBold", 14));
		nationalityContainer.add(forNationality, 0, 0);
		nationalityContainer.add(nationality, 0, 1);
		
		forClassRoom.setTextFill(Color.BLACK);
		forClassRoom.setFont(Font.font("Outfit SemiBold", 16));
		classRoom.setFill(Color.BLACK);
		classRoom.setFont(Font.font("Outfit SemiBold", 14));
		classRoomContainer.add(forClassRoom, 0, 0);
		classRoomContainer.add(classRoom, 0, 1);
		
		
		forDateAdded.setTextFill(Color.BLACK);
		forDateAdded.setFont(Font.font("Outfit SemiBold", 16));
		dateAdded.setFill(Color.BLACK);
		dateAdded.setFont(Font.font("Outfit SemiBold", 14));
		dateAddedContainer.add(forDateAdded, 0, 0);
		dateAddedContainer.add(dateAdded, 0, 1);
		
		forParent.setTextFill(Color.BLACK);
		forParent.setFont(Font.font("Outfit SemiBold", 16));
		parent.setFill(Color.BLACK);
		parent.setFont(Font.font("Outfit SemiBold", 14));
		parentContainer.add(forParent, 0, 0);
		parentContainer.add(parent, 0, 1);
		
		this.getChildren().addAll(iDContainer, nameContainer, genderContainer, 
				nationalityContainer, classRoomContainer, dateAddedContainer, parentContainer);
		this.setMinWidth(320);
		this.setMinHeight(450);
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		this.setStyle("-fx-background-color: #D9D9D9");
		Rectangle sumRec = new Rectangle(320, 450);
		sumRec.setArcHeight(24);
		sumRec.setArcWidth(24);
		this.setClip(sumRec);
		this.setTranslateY(370 - s.getHeight());
		this.setTranslateX(920 - s.getWidth());
	}
	
}
