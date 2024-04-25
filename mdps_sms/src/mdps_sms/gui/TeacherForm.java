package mdps_sms.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import mdps_sms.Main;
import mdps_sms.util.Person;
import mdps_sms.util.SchoolClass;
import mdps_sms.util.Staff;
import mdps_sms.util.Teacher;

public class TeacherForm extends Form {
	private Label forClassroom = new Label("Classes");
	private Label forSubjects = new Label("Subjects");
	private Label forAllocation = new Label("Classes and Subjects");
	
	private VBox classList = new VBox();
	private ScrollPane allocation = new ScrollPane(classList);
	
	private VBox allocationPair = new VBox(forAllocation, allocation);
	private Button addClassesAndSubjects = new Button("Add classes");
	
	ArrayList<SchoolClass> classroomsData = new ArrayList<SchoolClass> ();	
	public TeacherForm(ArrayList<SchoolClass> classroomsData) {
		super();
		
		this.classroomsData = classroomsData;
		
		formTitle.setText("Teacher Registration");
		role.setText("Teacher");
		role.setEditable(false);
		
		addClassesAndSubjects.setMinWidth(120);
		addClassesAndSubjects.setMinHeight(30);
		addClassesAndSubjects.setStyle("-fx-background-color: white");
		addClassesAndSubjects.setFont(Font.font("Inter SemiBold", 15));
		addClassesAndSubjects.setTextFill(Color.BLACK);
		Rectangle addRec = new Rectangle(120, 30);
		addRec.setArcHeight(25);
		addRec.setArcWidth(25);
		addClassesAndSubjects.setClip(addRec);
		
		allocation.setMaxWidth(290);
		
		addClassesAndSubjects.setOnAction(e -> {
			if(classroomsData.size() != classList.getChildren().size())
				classList.getChildren().add(createClassTab());
		});
		classList.setSpacing(10);
		classList.setAlignment(Pos.CENTER);
		allocation.setMaxHeight(300);
		
		this.styleLabels(forClassroom, forSubjects, forAllocation);
		styleVPairs(allocationPair);
		
		secondSection.getChildren().add(2,  allocationPair);
		buttonsPair.getChildren().add(1, addClassesAndSubjects);
		buttonsPair.setSpacing(130);
	}
	
	VBox createClassTab() {
		VBox tab = new VBox();
		ComboBox<SchoolClass> classOption = new ComboBox<>();
		ListView<String> subjectsOption = new ListView<>();
		
		subjectsOption.setMaxHeight(100);
		
		Button removeTab = new Button("remove");
		removeTab.setMinWidth(120);
		removeTab.setMinHeight(30);
		removeTab.setStyle("-fx-background-color: #343434");
		removeTab.setFont(Font.font("Inter SemiBold", 15));
		removeTab.setTextFill(Color.WHITE);
		Rectangle remRec = new Rectangle(120, 30);
		remRec.setArcHeight(25);
		remRec.setArcWidth(25);
		removeTab.setClip(remRec);
		
		classOption.setItems(FXCollections.observableList(classroomsData));
		classOption.getSelectionModel().selectFirst();
		subjectsOption.setItems(FXCollections.observableList(new LinkedList(classOption.getSelectionModel().getSelectedItem().getFreeSubjects())));
		subjectsOption.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		classOption.setMinWidth(120);
		//classOption.setStyle("-fx-background-color: #343434");
		classOption.setOnAction(e -> {
			subjectsOption.setItems(FXCollections.observableList(new LinkedList(classOption.getSelectionModel().getSelectedItem().getFreeSubjects())));
		});
		Rectangle classRec = new Rectangle(120, 30);
		remRec.setArcHeight(25);
		remRec.setArcWidth(25);
		removeTab.setClip(classRec);
		
		classOption.setPadding(new Insets(10));
		
		removeTab.setOnAction(e -> {
			classList.getChildren().remove(tab);
			classList.requestFocus();
		;});
		
		tab.setAlignment(Pos.CENTER);
		tab.setSpacing(5);
		tab.getChildren().addAll(classOption, subjectsOption, removeTab);
		tab.setPadding(new Insets(10));
		
		return tab;
	}
	
	@Override
	public <E extends Person> boolean createNew(E teacher, ArrayList itemList) {
		if(verifyEntries(new Control[] {name, gender,firstPhone, firstEmail, role, qualification, salary, location}, 
						new String[] {"Enter name", "Choose gender", "Enter phone", "Enter email" , "Enter role", 
								"Enter qualification", "Enter salary", "Enter address"}) ) {
			if(!verifyPhone(firstPhone.getText())) {
				warn("Check phone number");
				return false;
			}
			if(secondPhone.getText().isBlank() && !verifyPhone(firstPhone.getText())) {
				warn("Check second phone number");
				return false;
			}
			if(!verifyEmail(firstEmail.getText())) {
				warn("Check email");
				return false;
			}
			if(!secondEmail.getText().isBlank() && !verifyEmail(secondEmail.getText())) {
				warn("Check second email");
				return false;
			}
			String[] phone = {firstPhone.getText(), secondPhone.getText().isBlank() ? "none" : secondPhone.getText()};
			String[] email = {firstEmail.getText(), secondEmail.getText().isBlank() ? "none" : secondEmail.getText()};
			String description = this.description.getText().isBlank() ? "no description" : this.description.getText();
			HashMap<SchoolClass, ArrayList<String>> class_subject = new HashMap<>();
			
			for(Node elem : classList.getChildren()) {
				if(((ListView<String>)((VBox)elem).getChildren().get(1)).getSelectionModel().getSelectedItems().size() == 0) {
					warn(((ComboBox<SchoolClass>)((VBox)elem).getChildren().get(0)).getSelectionModel().getSelectedItem().getName() + " subjects not selected");
				}else {
					class_subject.put(((ComboBox<SchoolClass>)((VBox)elem).getChildren().get(0)).getSelectionModel().getSelectedItem(), 
							new ArrayList<>(((ListView<String>)((VBox)elem).getChildren().get(1)).getSelectionModel().getSelectedItems()));
				}
			}
			
			if(class_subject.size() != 0) {
				itemList.add(new Teacher(name.getText(), gender.getValue(), location.getText(), phone, email, role.getText(), qualification.getText(),
					class_subject, salary.getText(), accountAdmin.getText(), accountNumber.getText(), description));
				Main.saveData(itemList, Main.STORAGEFILE_T);
				return true;
			}
			
		}
		return false;
	}
	
}
