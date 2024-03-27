package mdps_sms;

import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ApplicationForm extends BorderPane {
	
	String[] genderChoice = {"Male", "Female"};
	String[] roleChoice = {"STAFF", "STUDENT", "TEACHER", "PARENT"};
	String[] classroomChoice = {"Standard 1", "standard 2", "standard 3", "standard4",
			"standard 5", "standard 6", "standard 7", "standard 8"};
	
	//Labels
	Label forName = new Label("Name");
	Label forSurname  = new Label("Surname");
	Label forNationality  = new Label("Nationality");
	Label forGender  = new Label("Gender");
	Label forParents  = new Label("Parents");
	Label forDateOfBirth  = new Label("Date of Birth");
	Label forRole  = new Label("Role");
	Label forEmail = new Label("Email");
	Label forLocation  = new Label("Location");
	Label forPrimaryPhone  = new Label("Primary phone number");
	Label forSecondaryPhone  = new Label("Secondary phone number");
	Label forNote  = new Label("Summary notes");
	Label forClassroom  = new Label("Classroom");
	
	//Fields
	TextField name = new TextField();
	TextField surname = new TextField();
	TextField nationality = new TextField();
	ComboBox<String> gender = new ComboBox<>();
	TextField parents = new TextField();
	TextField dateOfBirth = new TextField();
	ComboBox<String> role = new ComboBox<>();
	TextField email = new TextField();
	TextField location = new TextField();
	TextField primaryPhone = new TextField();
	TextField secondaryPhone = new TextField();
	TextArea note = new TextArea();
	ComboBox<String> classroom = new ComboBox<>();
	
	//Containers for labels and fields
	VBox namePair = new VBox(forName, name);
	VBox surnamePair = new VBox(forSurname, surname) ;
	VBox nationalityPair = new VBox(forNationality, nationality);
	VBox genderPair = new VBox(forGender, gender);
	VBox parentsPair = new VBox(forParents, parents);
	VBox dateOfBirthPair = new VBox(forDateOfBirth, dateOfBirth);
	VBox rolePair = new VBox(forRole, role);
	VBox emailPair = new VBox(forEmail, email) ;
	VBox locationPair = new VBox(forLocation, location);
	VBox primaryPhonePair = new VBox(forPrimaryPhone, primaryPhone);
	VBox secondaryPhonePair = new VBox(forSecondaryPhone, secondaryPhone);
	VBox notePair = new VBox(forNote, note) ;
	VBox classroomPair = new VBox(forClassroom, classroom);
	
	//no-arg constructor
	ApplicationForm(){}
	
	//constructor
	ApplicationForm(char type){
		
		//styles all labels
		for(Label elem : new Label[]{forName, forSurname, forNationality, forGender,
		forParents, forDateOfBirth, forRole, forEmail, forLocation, forPrimaryPhone, 
		forSecondaryPhone, forNote, forClassroom}) {
			
			elem.setFont(Font.font("Outfit", 16));
			elem.setTextFill(Color.WHITE);
		}
		
		//styles all text fields
		for(TextField elem : new TextField[]{name, surname, nationality,parents, dateOfBirth, 
		email, location, primaryPhone, secondaryPhone}) {
			
			elem.setFont(Font.font("inter", 14));
			elem.setMinWidth(250);
			elem.setMinHeight(40);
			elem.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
		}
		
		//styles all containers
		for(VBox elem : new VBox[]{namePair, surnamePair, nationalityPair, genderPair, parentsPair, 
		dateOfBirthPair, rolePair, emailPair, locationPair, primaryPhonePair, secondaryPhonePair, 
		notePair, classroomPair}) {
			
			elem.setSpacing(5);
			elem.setStyle("-fx-background-color: transparent;");
		}
		
		//combos
		gender.setMinWidth(250);
		gender.setMinHeight(40);
		gender.setItems(FXCollections.observableArrayList(genderChoice));
		gender.setValue("none");
		gender.setBlendMode(BlendMode.DIFFERENCE);
		
		role.setMinWidth(250);
		role.setMinHeight(40);
		role.setItems(FXCollections.observableArrayList(roleChoice));
		role.setValue("none");
		
		classroom.setMinWidth(250);
		classroom.setMinHeight(40);
		classroom.setItems(FXCollections.observableArrayList(classroomChoice));
		classroom.setValue("none");
		
		
		note.setMaxWidth(650);
		
		GridPane container = new GridPane();
		
		container.add(namePair, 0, 0);
		container.add(surnamePair, 1, 0);
		container.add(nationalityPair, 0, 1);
		container.add(genderPair, 0, 2);
		container.add(parentsPair, 0, 3);
		container.add(dateOfBirthPair, 0, 4);
		container.add(rolePair, 0, 5);
		container.add(emailPair, 0, 6);
		container.add(locationPair, 0, 7);
		container.add(primaryPhonePair, 0, 8);
		container.add(secondaryPhonePair, 1, 8);
		container.add(notePair, 0, 9);
		container.add(classroomPair, 0, 10);
		container.setAlignment(Pos.CENTER);
		container.setHgap(150);
		container.setVgap(10);
		container.setMinWidth(660);
		//container.setGridLinesVisible(true);
		GridPane.setColumnSpan(notePair, 2);
		
		this.setCenter(new StackPane(container));
		this.setMaxWidth(700);
		this.setPadding(new Insets(5));
		BorderPane.setAlignment(container, Pos.CENTER);
		this.setStyle("-fx-background-color: #232323");
	}
	
}
