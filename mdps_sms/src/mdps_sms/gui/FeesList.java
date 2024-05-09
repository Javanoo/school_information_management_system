package mdps_sms.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import mdps_sms.Main;
import mdps_sms.gui.Classrooms.classForm;
import mdps_sms.util.PdfPrinter;
import mdps_sms.util.SchoolClass;
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
	
	ArrayList<Student> students = new ArrayList<>();

	private MenuItem printAll = new MenuItem("print geneal report");
	private ContextMenu subMenu = new ContextMenu(printAll);
	
	FeesList(){}
	
	FeesList(ArrayList<Student> studentData){
		
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
		
		printAll.setOnAction(e -> PdfPrinter.printFeesReport(Main.classrooms.toArray(new SchoolClass[] {})));
		
		classChoice.setItems(FXCollections.observableArrayList("Class 1", "Class 2", "Class 3", "Class 4", 
				"Class 5", "Class 6", "Class 7", "Class 8"));
		classChoice.setPadding(new Insets(0, 5, 0, 5));
		
		for(Label elem : new Label[] {forTitle,forClass1, forClass2, forClass3,forClass4, forClass5, forClass6, forClass7, forClass8}) {
			elem.setTextFill(Color.BLACK);
			elem.setFont(Font.font("Inter SemiBold", 14));
		}
		
		forTitle.setTextFill(Color.WHITE);
		
		studentCode.setCellValueFactory(new PropertyValueFactory<>("codeNumber"));
		studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
		feesBalance.setCellValueFactory(new PropertyValueFactory<>("feesBalance"));
		paid.setCellValueFactory(new PropertyValueFactory<>("feesPaid"));
		
		StackPane holder = new StackPane(forNoItems);
		holder.setStyle("-fx-background-color: white");
		
		studentsTable.getColumns().addAll(studentCode, studentName, paid, feesBalance);
		studentsTable.setPlaceholder(holder);
		studentsTable.setTableMenuButtonVisible(true);
		studentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		studentsTable.setItems(FXCollections.observableList(new LinkedList<>(class1)));
		studentsTable.setContextMenu(subMenu);
		
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
		title.setPadding(new Insets(10, 10, 0, 10));
		((Separator)title.getBottom()).setPadding(new Insets(10, 0, 0, 0));
		title.setStyle("-fx-background-color: " + Main.configuration.theme);
		BorderPane.setAlignment(forTitle, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(classChoice, Pos.CENTER_RIGHT);
		
		actionBar.getDelete().setDisable(true);
		actionBar.getAdd().setOnAction(e -> {
			if(studentsTable.getSelectionModel().getSelectedItem() != null) {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(new FeesForm(studentsTable.getSelectionModel().getSelectedItem()));
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(250);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
				});
			}
		});
		actionBar.getEdit().setOnAction(e -> {
			if(studentsTable.getSelectionModel().getSelectedItem() != null) {
				FeesForm form = new FeesForm(studentsTable.getSelectionModel().getSelectedItem());
				form.editEntries(studentsTable.getSelectionModel().getSelectedItem());
				Main.popup.getContent().clear();
				Main.popup.getContent().add(form);
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(250);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
				});
			}
		});
		
		setTop(title);
		setCenter(studentsTable);
		setBottom(actionBar);
		setPadding(new Insets(0));
		setStyle("-fx-background-color: " + Main.configuration.theme);
		BorderPane.setAlignment(actionBar, Pos.CENTER_RIGHT);
		BorderPane.setMargin(actionBar, new Insets(7, 7, 7 , 0));
	}

	class FeesForm extends BorderPane{
		private Label forName = new Label("Fees for ");
		private Label forDate = new Label("Date");
		private Label forFeesPaid = new Label("Fees Paid");
		
		private TextField name = new TextField();
		private TextField feesPaid = new TextField();
		private Button cancel = new Button("Cancel");
		private Button add = new Button("Save");
		private HBox buttons = new HBox(cancel, add);
		
		private VBox namePair = new VBox(forName, name);
		private VBox feesPaidPair = new VBox(forFeesPaid, feesPaid);
		
		private VBox mainHolder = new VBox();
		
		public FeesForm(Student person){
			for(Label elem : new Label[]{forName, forFeesPaid, forDate}) {
				elem.setTextFill(Color.WHITE);
				elem.setFont(Font.font("Inter SemiBold", 16));
			}
			for(TextField elem :  new TextField[]{name, feesPaid}) {
					elem.setMaxWidth(340);
					elem.setMinHeight(35);;
					elem.setFont(Font.font("Inter", 14));
					elem.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
					((TextField)elem).focusedProperty().addListener(e -> {
						add.setStyle("-fx-background-color: white");
						add.setTextFill(Color.BLACK);
					});
			}
			for(Button elem : new Button[] {cancel, add}) {
				elem.setMinWidth(100);
				elem.setMinHeight(35);
				elem.setStyle("-fx-background-color: white");
				elem.setFont(Font.font("Inter SemiBold", 15));
				elem.setTextFill(Color.BLACK);
				Rectangle elemRec = new Rectangle(100, 35);
				elemRec.setArcHeight(35);
				elemRec.setArcWidth(35);
				elem.setClip(elemRec);
			}
			
			name.setText(person.getName());
			name.setEditable(false);
			name.setFocusTraversable(false);
			feesPaid.setPromptText("enter amount...");
			feesPaid.focusedProperty().addListener(e -> {
				add.setStyle("-fx-background-color: white");
				add.setTextFill(Color.BLACK);
			});
			
			namePair.setSpacing(5);
			feesPaidPair.setSpacing(5);
			
			namePair.setMaxWidth(340);
			feesPaidPair.setMaxWidth(340);
			
			cancel.setOnAction(e -> {
				Main.popup.hide();
				Main.popup.getContent().clear();
			});
			
			add.setOnAction(e -> {
				try {
					makeEntry(person, Long.valueOf(feesPaid.getText()));
					students.remove(person);
					students.add(person);
					Main.saveData(students, Main.STORAGEFILE_S);
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
					studentsTable.refresh();
				}catch(Exception error) {
					add.setStyle("-fx-background-color: red; -fx-text-fill: white");
					Main.fadeIn(add, 250);
				}
			});
			
			mainHolder.getChildren().addAll(namePair, feesPaidPair);
			mainHolder.setSpacing(20);
			mainHolder.setAlignment(Pos.TOP_CENTER);
			
			buttons.setSpacing(180);
			
			setCenter(mainHolder);
			setBottom(buttons);
			setMinWidth(400);
			setMinHeight(400);
			setPadding(new Insets(20, 10, 10, 10));
			setStyle("-fx-background-color: " + Main.configuration.theme);
			
			Rectangle rec = new Rectangle(400, 400);
			rec.setArcHeight(35);
			rec.setArcWidth(35);
			setClip(rec);
		}
		
		void makeEntry(Student student, Long feesPaid) {
			student.getFeesPaidEntry().put((new Date()).toString(), feesPaid);	
			student.setFeesPaid(feesPaid);
			cancel.fire();
		}
		HashMap<String, Long> createEntry(VBox payHistory) {
			HashMap<String, Long> newPayments = new HashMap<>();
			for(Node elem : payHistory.getChildren()) {
				if(!((TextField)((HBox)elem).getChildren().get(1)).getText().isBlank())
					newPayments.put(((TextField)((HBox)elem).getChildren().get(0)).getText(), Long.valueOf(((TextField)((HBox)elem).getChildren().get(1)).getText()));
			}
			return newPayments;
		}
			
		
		void editEntries(Student person) {
			name.setText(person.getName() + " Payment History");
			name.setAlignment(Pos.CENTER);
			namePair.getChildren().remove(forName);
			
			VBox historyContainer = new VBox();
			
			if(person.getFeesPaidEntry().keySet() != null) {
				for(String elem : person.getFeesPaidEntry().keySet()) {
					HBox entryView = new HBox();
					TextField date = new TextField(elem);
					date.setMaxWidth(110);
					date.setEditable(false);
					TextField fee = new TextField(person.getFeesPaidEntry().get(elem) + "");
					fee.setMaxWidth(110);
					Button remove = new Button("remove");
				
					entryView.getChildren().addAll(date, fee, remove);
					entryView.setSpacing(5);
					remove.setOnAction(e -> historyContainer.getChildren().remove(entryView));
					System.out.println("entered.......................");
					historyContainer.getChildren().add(entryView);
				}
				add.setOnAction(e -> {
					person.setFeesPaidEntry(createEntry(historyContainer));
					cancel.fire();
				});
			}
			ScrollPane container = new ScrollPane(historyContainer);
			container.setMaxWidth(340);
			container.setMaxHeight(200);
			mainHolder.getChildren().remove(feesPaidPair);
			mainHolder.getChildren().add(container);
		}
	}
}
