package mdps_sms.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import mdps_sms.util.Person;
import mdps_sms.util.SchoolClass;
import mdps_sms.util.Student;
import mdps_sms.util.Teacher;

public class Classrooms extends BorderPane {
	
	private Label forAvailableClasses = new Label("Available Classes");
	private Button addNewClass = new Button("Add");
	
	public static TableView<SchoolClass> classesTable = new TableView<>();
	private TableColumn<SchoolClass, String> name = new TableColumn<>("Name");
	private TableColumn<SchoolClass, String> subjects = new TableColumn<>("Subjects");
	private TableColumn<SchoolClass, String> numberOfStudents = new TableColumn<>("Number of Students");
	private TableColumn<SchoolClass, String> numberOfTeachers = new TableColumn<>("Number of Teachers");
	
	private Label forNoItems = new Label("No classes");
	private Label forTitle = new Label("Available Classes");
	private BorderPane title = new BorderPane();
	
	ActionBar actionBar = new ActionBar();
	
	private ContextMenu subMenu = new ContextMenu();
	private MenuItem viewTeachers = new MenuItem("view teachers");
	private MenuItem viewStudents = new MenuItem("view students");
	private MenuItem attendance = new MenuItem("attendance");
	
	Classrooms(){
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		subjects.setCellValueFactory(new PropertyValueFactory<>("subjectRep"));
		numberOfStudents.setCellValueFactory(new PropertyValueFactory<>("numberOfStudents"));
		numberOfTeachers.setCellValueFactory(new PropertyValueFactory<>("numberOfTeachers"));
		
		forTitle.setTextFill(Color.WHITE);
		forTitle.setFont(Font.font("Inter SemiBold", 14));
		forTitle.setText(forTitle.getText().toUpperCase());
		
		StackPane holder = new StackPane(forNoItems);
		holder.setStyle("-fx-background-color: white");
		
		classesTable.getColumns().add(name);
		classesTable.getColumns().add(subjects);
		classesTable.getColumns().add(numberOfStudents);
		classesTable.getColumns().add(numberOfTeachers);
		classesTable.setPlaceholder(holder);
		classesTable.setTableMenuButtonVisible(true);
		classesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		classesTable.setItems(FXCollections.observableList(Main.classrooms));
		classesTable.setContextMenu(subMenu);
		
		title.setLeft(forTitle);
		title.setBottom(new Separator());
		title.setPadding(new Insets(10, 10, 0, 10));
		((Separator)title.getBottom()).setPadding(new Insets(10, 0, 0, 0));
		title.setStyle("-fx-background-color: #232323");
		BorderPane.setAlignment(forTitle, Pos.BOTTOM_LEFT);
		
		actionBar.getDelete().setOnAction(e -> {
			if(classesTable.getSelectionModel().getSelectedItems() != null && classesTable.getSelectionModel().getSelectedItems().size() != 0) {
				for(SchoolClass elem : classesTable.getSelectionModel().getSelectedItems()) {
					for(Student item : elem.getStudents()) ((Student)item).setClassroom(new SchoolClass());
					for(Teacher item : elem.getTeachers()) { 
						((Teacher)item).getClass_subject().remove(elem);
					}
				}
				Main.classrooms.removeAll(classesTable.getSelectionModel().getSelectedItems());
				Main.saveData(Main.classrooms, Main.STORAGEFILE_C);
				if(Main.classrooms != null)
					classesTable.setItems(FXCollections.observableList(Main.classrooms));
			}
		});
		actionBar.getAdd().setOnAction(e -> {
			Main.popup.getContent().clear();
			Main.popup.getContent().add(new classForm());
			Main.popup.show(Main.primaryStage);
			Main.popup.setAnchorY(250);
			Main.popup.setOnHiding(v -> {
				Main.popup.getContent().clear();
			});
		});
		actionBar.getEdit().setOnAction(e -> {
			if(classesTable.getSelectionModel().getSelectedItems() != null && classesTable.getSelectionModel().getSelectedItems().size() != 0) {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(new classForm());
				((classForm)Main.popup.getContent().get(0)).edit(classesTable.getSelectionModel().getSelectedItems().get(0), Main.classrooms);
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(250);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
				});
			}
		});
		actionBar.getSearchBar().setOnKeyTyped(e -> {
			search((TextField)e.getSource(), Main.classrooms);
			((Button)(actionBar.getSearchContainer().getChildren().get(0))).setOnAction(v -> {
				actionBar.hideSearch();
				classesTable.setItems(FXCollections.observableList(Main.classrooms));
			});
		});
		
		viewTeachers.setOnAction(e -> {
			if(classesTable.getSelectionModel().getSelectedItem() != null && (classesTable.getSelectionModel().getSelectedItem()).getTeachers().size() != 0)
				showItems(classesTable.getSelectionModel().getSelectedItem(), (classesTable.getSelectionModel().getSelectedItem()).getTeachers(), 's');
		});
		viewStudents.setOnAction(e -> {
			if(classesTable.getSelectionModel().getSelectedItem() != null && (classesTable.getSelectionModel().getSelectedItem()).getStudents().size() != 0)
				showItems( classesTable.getSelectionModel().getSelectedItem(), (classesTable.getSelectionModel().getSelectedItem()).getStudents(), 's');
		});
		attendance.setOnAction(e -> {
			if(classesTable.getSelectionModel().getSelectedItems() != null)
				;//recordAttendance(classesTable.getSelectionModel().getSelectedItems());
			showItems( classesTable.getSelectionModel().getSelectedItem(), (classesTable.getSelectionModel().getSelectedItem()).getStudents(), 'a');
		});
		subMenu.getItems().addAll(viewTeachers, viewStudents, attendance);
		
		setTop(title);
		setCenter(classesTable);
		setBottom(actionBar);
		setPadding(new Insets(0));
		setStyle("-fx-background-color: #232323");
		BorderPane.setAlignment(actionBar, Pos.CENTER_RIGHT);
		BorderPane.setMargin(actionBar, new Insets(7, 7, 7 , 0));
	}
	
	<E extends Person> void showItems(SchoolClass classroom, TreeSet<E> items, char a){
		BorderPane listTab = new BorderPane();
		Label title = new Label();
		ListView<E> listView = new ListView<>();
		Button remove = new Button("Remove");
		Button cancel = new Button("Cancel");
		HBox buttons = new HBox(cancel, remove);
		
		title.setText(classroom.getName() + (items.first() instanceof Student ? " Students" : " Teachers"));
		
		if(a == 'a')
			title.setText(classroom.getName() + " Attendance");
		title.setTextFill(Color.WHITE);
		title.setFont(Font.font("Inter SemiBold", 14));
		title.setPadding(new Insets(10));
		
		listView.setItems(FXCollections.observableList(new LinkedList<>(items)));
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		remove.setMinWidth(100);
		remove.setMinHeight(35);
		remove.setStyle("-fx-background-color: white");
		remove.setFont(Font.font("Inter SemiBold", 15));
		remove.setTextFill(Color.BLACK);
		Rectangle remRec = new Rectangle(100, 35);
		remRec.setArcHeight(25);
		remRec.setArcWidth(25);
		remove.setClip(remRec);
		
		if(a == 'a') {
			remove.setText("Record");
			remove.setOnAction(e -> {});//write( listView, new Date().toString()) + classroom.getName() + ".attendance");
		}
		
		cancel.setMinWidth(100);
		cancel.setMinHeight(35);
		cancel.setStyle("-fx-background-color: white");
		cancel.setFont(Font.font("Inter SemiBold", 15));
		cancel.setTextFill(Color.BLACK);
		cancel.setOnAction(e -> {
			Main.popup.hide();
			Main.popup.getContent().clear();
		});
		Rectangle canRec = new Rectangle(100, 35);
		canRec.setArcHeight(25);
		canRec.setArcWidth(25);
		cancel.setClip(canRec);
		
		buttons.setSpacing(180);
		
		listTab.setTop(title);
		listTab.setCenter(listView);
		listTab.setBottom(buttons);
		listTab.setPadding(new Insets(10, 10, 0, 10));
		listTab.setMaxWidth(400);
		listTab.setMaxHeight(500);
		Rectangle listRec = new Rectangle(400, 500);
		listRec.setArcHeight(35);
		listRec.setArcWidth(35);
		listTab.setClip(listRec);
		BorderPane.setAlignment(title, Pos.CENTER_LEFT);
		BorderPane.setMargin(buttons, new Insets(15, 0, 10, 0));
		listTab.setStyle("-fx-background-color: #232323");
		
		Main.popup.getContent().clear();
		Main.popup.getContent().add(listTab);
		Main.popup.show(Main.primaryStage);
		Main.popup.setAnchorY(200);
		Main.popup.setOnHiding(v -> {
			Main.popup.getContent().clear();
		});
	}
	
	void search(TextField source, ArrayList<SchoolClass> classroomsData) {
		//query to use with RegExp for searching
		String query = source.getText();
		//new set that holds the matching items
		TreeSet<SchoolClass> matchedItems = new TreeSet<>();
		Iterator<SchoolClass> iter = classroomsData.iterator();
		while(iter.hasNext() && !query.isEmpty()) {
			SchoolClass classroom = iter.next();
			if((classroom.getName().toLowerCase()).matches(query.toLowerCase() + ".*"))
				matchedItems.add(classroom);
		}
		if(matchedItems.isEmpty())classesTable.setItems(FXCollections.observableList(Main.classrooms));
		else
			classesTable.setItems((FXCollections.observableList(new LinkedList<>(matchedItems))));
	}
	
	class classForm extends BorderPane{
		private Label forName = new Label("Class name");
		private Label forFees = new Label("Class Fees");
		private Label forSubjects = new Label("Class subjects");
		
		private TextField name = new TextField();
		private TextField fees = new TextField();
		private Button cancel = new Button("Cancel");
		private Button addSubjects = new Button("Add subjects");
		private Button removeSubjects = new Button("Remove");
		private Button create = new Button("Create");
		private HBox buttons = new HBox(cancel, addSubjects, create);
		
		private VBox namePair = new VBox(forName, name);
		private VBox feesPair = new VBox(forFees, fees);
		private VBox subjectsHolder = new VBox();
		ScrollPane scrl = new ScrollPane(subjectsHolder);
		private VBox subjectsPair = new VBox(forSubjects,scrl);
		
		private VBox mainHolder = new VBox();
		
		classForm(){
			for(Label elem : new Label[]{forName, forFees, forSubjects}) {
				elem.setTextFill(Color.WHITE);
				elem.setFont(Font.font("Inter SemiBold", 16));
			}
			for(TextField elem :  new TextField[]{name, fees}) {
					elem.setMaxWidth(340);
					elem.setMinHeight(35);;
					elem.setFont(Font.font("Inter", 14));
					elem.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
					((TextField)elem).focusedProperty().addListener(e -> {
						create.setStyle("-fx-background-color: white");
						create.setTextFill(Color.BLACK);
					});
			}
			for(Button elem : new Button[] {cancel, create}) {
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
			
			name.setPromptText("enter class name, e.g. Class 1...");
			fees.setPromptText("enter class fee...");
			
			namePair.setSpacing(5);
			feesPair.setSpacing(5);
			
			addSubjects.setMinWidth(150);
			addSubjects.setMinHeight(35);
			addSubjects.setStyle("-fx-background-color: white");
			addSubjects.setFont(Font.font("Inter SemiBold", 15));
			addSubjects.setTextFill(Color.BLACK);
			Rectangle addSubRec = new Rectangle(150, 35);
			addSubRec.setArcHeight(35);
			addSubRec.setArcWidth(35);
			addSubjects.setClip(addSubRec);
			
			
			namePair.setMaxWidth(340);
			feesPair.setMaxWidth(340);
			subjectsPair.setMaxWidth(340);
			
			scrl.setStyle("-fx-background-color: #232323");
			scrl.setMaxHeight(250);
			subjectsHolder.setStyle("-fx-background-color: #232323");
			subjectsHolder.setAlignment(Pos.CENTER);
			
			addSubjects.setOnAction(e -> {
				TextField input = new TextField();
				input.setPromptText("enter subject...");
				
				input.setMinWidth(210);
				input.setMinHeight(35);;
				input.setFont(Font.font("Inter", 14));
				input.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
				
				removeSubjects = new Button("Remove");
				removeSubjects.setMinWidth(90);
				removeSubjects.setMinHeight(30);
				removeSubjects.setStyle("-fx-background-color: white");
				removeSubjects.setFont(Font.font("Inter SemiBold", 14));
				removeSubjects.setTextFill(Color.BLACK);
				Rectangle removeRec = new Rectangle(90, 30);
				removeRec.setArcHeight(30);
				removeRec.setArcWidth(30);
				removeSubjects.setClip(removeRec);
				
				
				HBox entry  = new HBox(input, removeSubjects);
				entry.setSpacing(10);
				removeSubjects.setOnAction(v -> subjectsHolder.getChildren().remove(entry));
			
				subjectsHolder.getChildren().add(entry);
			});
			cancel.setOnAction(e -> {
				Main.popup.hide();
				Main.popup.getContent().clear();
			});
			
			create.setOnAction(e -> {
				if(create(subjectsHolder) != null) {
					if(Main.classrooms == null)
						Main.classrooms = new ArrayList<SchoolClass>();
					Main.classrooms.add(create(subjectsHolder));
					Main.saveData(Main.classrooms, Main.STORAGEFILE_C);
					cancel.fire();
					classesTable.setItems(FXCollections.observableList(Main.classrooms));
				}
			});
			
			subjectsHolder.setSpacing(10);
			subjectsHolder.setPadding(new Insets(0, 10, 0, 0));
			
			mainHolder.getChildren().addAll(namePair, feesPair, subjectsPair);
			mainHolder.setSpacing(20);
			mainHolder.setAlignment(Pos.TOP_CENTER);
			
			buttons.setSpacing(15);
			
			setCenter(mainHolder);
			setBottom(buttons);
			setMinWidth(400);
			setMinHeight(500);
			setPadding(new Insets(20, 10, 10, 10));
			setStyle("-fx-background-color: #232323");
			
			Rectangle rec = new Rectangle(400, 500);
			rec.setArcHeight(35);
			rec.setArcWidth(35);
			setClip(rec);
		}
		
		SchoolClass create(VBox subjects) {
			if(name.getText().matches("Class \\d*")) {
				try {
					if(!fees.getText().isBlank() && Long.valueOf(fees.getText()) != null) {
						TreeSet<String> subjectsList = new TreeSet<>();
						for(Node elem : subjects.getChildren()) {
							if(!((TextField)((HBox)elem).getChildren().get(0)).getText().isBlank())
								subjectsList.add(((TextField)((HBox)elem).getChildren().get(0)).getText());
						}
						return new SchoolClass(name.getText(), (Long.valueOf(fees.getText())).longValue(), subjectsList);
					}
				}catch(Exception e){
				}
			}
			create.setStyle("-fx-background-color: red; -fx-text-fill: white");
			Main.fadeIn(create, 250);
			return null;
		}
		void edit(SchoolClass classroom, ArrayList<SchoolClass> classroomsData) {
			name.setText(classroom.getName());
			fees.setText(classroom.getFees() + "");
			
			for(String elem : classroom.getSubjects()) {
				addSubjects.fire();
				((TextField)(((HBox)(subjectsHolder.getChildren().get(subjectsHolder.getChildren().size() - 1))).getChildren().get(0))).setText(elem);
			}
			create.setOnAction(e -> {
				classroomsData.remove(classroom);
				classroomsData.add(create(subjectsHolder));
				classesTable.setItems(FXCollections.observableList(new LinkedList<>(classroomsData)));
				Main.saveData(classroomsData, Main.STORAGEFILE_C);
				cancel.fire();
			});
		}
	}
}
