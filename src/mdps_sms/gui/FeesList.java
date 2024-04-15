package mdps_sms.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import mdps_sms.util.Student;

public class FeesList extends BorderPane {
	
	private LinkedHashSet<Student> class1 = new LinkedHashSet<>();
	private LinkedHashSet<Student> class2 = new LinkedHashSet<>();
	private LinkedHashSet<Student> class3 = new LinkedHashSet<>();
	private LinkedHashSet<Student> class4 = new LinkedHashSet<>();
	private LinkedHashSet<Student> class5 = new LinkedHashSet<>();
	private LinkedHashSet<Student> class6 = new LinkedHashSet<>();
	private LinkedHashSet<Student> class7 = new LinkedHashSet<>();
	private LinkedHashSet<Student> class8 = new LinkedHashSet<>();
	
	private TableView<Student> studentsTable = new TableView<>();
	private TableColumn<Student, String> studentCode = new TableColumn<>("Code Number");
	private TableColumn<Student, String> studentName = new TableColumn<>("Name");
	private TableColumn<Student, String> feesBalance = new TableColumn<>("Balance");
	private TableColumn<Student, String> paid = new TableColumn<>("Paid");
	
	private Label forNoItems = new Label("No List");
	
	private ComboBox<String> classChoice = new ComboBox<>();
	
	private Label forClass1 = new Label("Class 1");
	private Label forClass2 = new Label("Class 2");
	private Label forClass3 = new Label("Class 3");
	private Label forClass4 = new Label("Class 4");
	private Label forClass5 = new Label("Class 5");
	private Label forClass6 = new Label("Class 6");
	private Label forClass7 = new Label("Class 7");
	private Label forClass8 = new Label("Class 8");
	
	private Label forTitle = new Label("Fees Balances as of ");
	private BorderPane title = new BorderPane();
	
	ActionBar actionBar = new ActionBar();
	
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM dd yyyy");
	
	FeesList(){}
	
	FeesList(TreeSet<Student> studentData){
		
		//sort the students into classes
		if(studentData != null) {
			for(Student elem : studentData) {
				if(elem.getClassroom().getName().equalsIgnoreCase("Class 1")) class1.add(elem);
				else if(elem.getClassroom().getName().equalsIgnoreCase("Class 2")) class2.add(elem);
				else if(elem.getClassroom().getName().equalsIgnoreCase("Class 3")) class3.add(elem);
				else if(elem.getClassroom().getName().equalsIgnoreCase("Class 4")) class4.add(elem);
				else if(elem.getClassroom().getName().equalsIgnoreCase("Class 5")) class5.add(elem);
				else if(elem.getClassroom().getName().equalsIgnoreCase("Class 6")) class6.add(elem);
				else if(elem.getClassroom().getName().equalsIgnoreCase("Class 7")) class7.add(elem);
				else class8.add(elem);
			}
		}
		
		classChoice.setItems(FXCollections.observableArrayList("Class 1", "Class 2", "Class 3", "Class 4", 
				"Class 5", "Class 6", "Class 7", "Class 8"));
		
		for(Label elem : new Label[] {forTitle,forClass1, forClass2, forClass3,forClass4, forClass5, forClass6, forClass7, forClass8}) {
			elem.setTextFill(Color.BLACK);
			elem.setFont(Font.font("Inter SemiBold", 14));
		}
		
		forTitle.setTextFill(Color.WHITE);
		
		studentCode.setCellValueFactory(new PropertyValueFactory<>("codeNumber"));
		studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
		feesBalance.setCellValueFactory(new PropertyValueFactory<>("feeBalance"));
		paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
		
		StackPane holder = new StackPane(forNoItems);
		holder.setStyle("-fx-background-color: white");
		
		studentsTable.getColumns().addAll(studentCode, studentName, paid, feesBalance);
		studentsTable.setPlaceholder(holder);
		studentsTable.setTableMenuButtonVisible(true);
		studentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class1)));
		
		forTitle.setText((forTitle.getText() + dateFormat2.format(new Date())).toUpperCase());
		
		classChoice.setStyle("-fx-background-color: white");
		classChoice.getSelectionModel().selectFirst();
		classChoice.setOnAction(e -> {
			switch(classChoice.getSelectionModel().getSelectedItem()) {
				case "Class 1" : studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class1))); break;
				case "Class 2" : studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class2))); break;
				case "Class 3" : studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class3))); break;
				case "Class 4" : studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class4))); break;
				case "Class 5" : studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class5))); break;
				case "Class 6" : studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class6))); break;
				case "Class 7" : studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class7))); break;
				case "Class 8" : studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class8))); break;
				default : ;
			}
		});
		classChoice.setMinHeight(30);
		classChoice.setMinWidth(100);
		Rectangle classRec = new Rectangle(100, 30);
		classRec.setArcHeight(30);
		classRec.setArcWidth(30);
		classChoice.setClip(classRec);
		
		title.setLeft(forTitle);
		title.setRight(classChoice);
		title.setBottom(new Separator());
		title.setPadding(new Insets(10, 10, 10, 10));
		((Separator)title.getBottom()).setPadding(new Insets(10, 0, 0, 0));
		title.setStyle("-fx-background-color: #232323");
		BorderPane.setAlignment(forTitle, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(classChoice, Pos.CENTER_RIGHT);
		
		actionBar.getDelete().setDisable(true);
		
		setTop(title);
		setCenter(studentsTable);
		setBottom(actionBar);
		setPadding(new Insets(0));
		setStyle("-fx-background-color: #232323");
		BorderPane.setAlignment(actionBar, Pos.CENTER_RIGHT);
		BorderPane.setMargin(actionBar, new Insets(7, 7, 7 , 0));
	}
}
