package mdps_sms;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
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

public class App extends BorderPane {
	private StackPane leftPanel = new StackPane();
	private BorderPane centralPanel = new BorderPane();
	
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
		profileBox.setStyle("-fx-background-color:" + UiComponents.backgroundcolor);
		profileBox.setMinHeight(35);
		Rectangle profRec = new Rectangle(230, 35);
		profRec.setArcHeight(11.5);
		profRec.setArcWidth(11.5);
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
		navigation.setStyle("-fx-background-color: " + UiComponents.backgroundcolor);
		navigation.setMaxHeight(362);
		navigation.setFixedCellSize(45);
		Rectangle navRec = new Rectangle(200, 362);
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
		leftPanel.setStyle("-fx-background-color: #232323");
		
		
		centralPanel.maxWidthProperty().bind(this.widthProperty().subtract(230));
		centralPanel.maxHeightProperty().bind(this.heightProperty().subtract(10));
		centralPanel.setPadding(new Insets(15, 5, 5, 5));
		centralPanel.setStyle("-fx-background-color: #232323");
		centralPanel.setCenter(new ItemList(centralPanel));
		centralPanel.setBottom(new ActionBar());
		BorderPane.setAlignment(centralPanel.getBottom(), Pos.CENTER);
		Rectangle centRec = new Rectangle(centralPanel.getWidth(), centralPanel.getHeight());
		centRec.widthProperty().bind(centralPanel.widthProperty());
		centRec.heightProperty().bind(centralPanel.heightProperty());
		centRec.setArcHeight(15);
		centRec.setArcWidth(15);
		centralPanel.setClip(centRec);
		
		
		this.setLeft(leftPanel);
		this.setCenter(centralPanel);
		this.setStyle("-fx-background-color: " + UiComponents.backgroundcolor);
	}
}
class ItemList extends VBox{
		private HBox columnHeads = new HBox();
		private Separator separate = new Separator();
		private ListView<HBox> list = new ListView<>();
		
		Label placeholder = new Label("loading..."); 
		StackPane placeholderContainer = new StackPane(placeholder); 
		
		LinkedList<HBox> studentList = new LinkedList<>();
		
		ItemList(BorderPane s){
			
			columnHeads.getChildren().addAll(new Label("StudentID"), new Label("Name"), new Label("Gender") ,
					new Label("Class room"), new Label ("Parent"), new Label ("Date addded"));
			columnHeads.maxWidthProperty().bind(s.widthProperty().subtract(30));
			columnHeads.setMinHeight(35);
			columnHeads.setSpacing(160);
			columnHeads.spacingProperty().bind(columnHeads.maxWidthProperty().divide(7.5));
			columnHeads.setAlignment(Pos.CENTER_LEFT);
			columnHeads.setPadding(new Insets(0, 0, 0, 15));
			columnHeads.setStyle("-fx-background-color:" + UiComponents.backgroundcolor);
			for( Node elem : columnHeads.getChildren()) {
				((Label)elem).setFont(Font.font("ubuntu mono", FontWeight.BOLD, 16));
			}
			Rectangle colRec = new Rectangle(columnHeads.getMaxWidth(), 35);
			colRec.widthProperty().bind(columnHeads.widthProperty());
			colRec.setArcHeight(15);
			colRec.setArcWidth(15);
			columnHeads.setClip(colRec);
			
			separate.maxWidthProperty().bind(colRec.widthProperty().subtract(10));
			
			placeholder.setFont(Font.font("Outfit SemiBold", 16));
			placeholderContainer.setStyle("-fx-background-color: " + UiComponents.backgroundcolor);
			
			//Dummy list for testing
			for(int i = 0; i <= 250; i++) {
				HBox entry = new HBox();
				entry.setSpacing(160);
				entry.spacingProperty().bind(columnHeads.maxWidthProperty().divide(6.7));
				entry.maxWidthProperty().bind(s.widthProperty().subtract(30));
				entry.setAlignment(Pos.CENTER_LEFT);
				entry.setPadding(new Insets(0, 0, 0, 15));
				entry.getChildren().addAll(new Label(Math.round(10000 + Math.random() * 10000) + ""), new Label(Math.round(10000 + Math.random() * 10000) + ""), 
						new Label(Math.round(10000 + Math.random() * 10000) + "") , new Label(Math.round(10000 + Math.random() * 10000) + ""), 
						new Label (Math.round(10000 + Math.random() * 10000) + ""), new Label (Math.round(10000 + Math.random() * 10000) + ""));
				for( Node elem : entry.getChildren()) {
					((Label)elem).setFont(Font.font("ubuntu mono", FontWeight.BOLD, 14));
				}
				studentList.add(entry);
			}
			
			list.setItems(FXCollections.observableList(studentList));
			list.setFixedCellSize(40);
			list.setPlaceholder(placeholderContainer);
			list.maxWidthProperty().bind(s.widthProperty().subtract(30));
			list.minHeightProperty().bind(s.heightProperty().subtract(125));
			Rectangle listRec = new Rectangle(list.getMaxWidth(), list.getMinHeight());
			listRec.widthProperty().bind(list.maxWidthProperty());
			listRec.heightProperty().bind(list.minHeightProperty());
			listRec.setArcHeight(15);
			listRec.setArcWidth(15);
			list.setClip(listRec);
			
			
			this.getChildren().addAll(columnHeads, list);
			this.maxWidthProperty().bind(s.maxWidthProperty().subtract(20));
			this.setSpacing(20);
			this.setAlignment(Pos.TOP_CENTER);
			this.setScaleShape(false);
		}
}