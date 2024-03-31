package mdps_sms.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
