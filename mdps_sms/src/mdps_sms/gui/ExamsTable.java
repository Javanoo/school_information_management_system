package mdps_sms.gui;

import java.awt.Event;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import mdps_sms.Main;
import mdps_sms.gui.FeesList.FeesForm;
import mdps_sms.util.PdfPrinter;
import mdps_sms.util.SchoolCalendar;
import mdps_sms.util.SchoolClass;
import mdps_sms.util.Student;

public class ExamsTable extends BorderPane {
	Label calendarTitle = new Label("EXAMS TABLE");
	Label today = new Label("");
	ComboBox<String> view = new ComboBox<>();
	ComboBox<String> classView = new ComboBox<>();
	BorderPane titleBar = new BorderPane();
	
	TableView<SchoolCalendar.ExamEntry> table = new TableView<>();
	TableColumn<SchoolCalendar.ExamEntry, String> day = new TableColumn<>("Day");
	TableColumn<SchoolCalendar.ExamEntry, String> supervisor = new TableColumn<>("Supervisors");
	TableColumn<SchoolCalendar.ExamEntry, String> time = new TableColumn<>("8:00 - 10:00");
	TableColumn<SchoolCalendar.ExamEntry, String> otherTime = new TableColumn<>("10:00 - 12 : 00");
	
	ListView<Student> students = new ListView<>();
	
	StackPane noItems = new StackPane(new Label("No exams have been scheduled in the calendar.\nset the exam dates first."));
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM dd yyyy");
	
	ActionBar actionBar = new ActionBar();
	
	public static SchoolCalendar calendarData = new SchoolCalendar();
	
	//MenuItem viewCal = new MenuItem("view");
	MenuItem editCal = new MenuItem("edit");
	MenuItem printTable = new MenuItem("print timetable");
	MenuItem email = new MenuItem("email parent");
	
	ContextMenu calMenu = new ContextMenu(editCal, printTable);
	
	Mail mailor = new Mail();
	
	TreeSet<SchoolCalendar.ExamEntry> dates = new TreeSet<>();
	
	ExamsTable(){}
	
	ExamsTable(SchoolCalendar cal){
		
		ArrayList<String> classes = new ArrayList<>();
		ArrayList<Student> student = new ArrayList<>();
		
		Iterator<SchoolCalendar.DayEntry> iter = cal.getDates().iterator();
		Iterator<SchoolClass> classIter = Main.classrooms.iterator();
		
		while(classIter.hasNext()) {
			classes.add(classIter.next().getName());
		}
		Collections.sort(classes);
		
		dates = cal.getExamDates();
		
		if(dates.size() != 0)
		calendarTitle.setText("EXAMS TIMETABLE - " + (dateFormat.format(dates.first().getDate())).toUpperCase() 
				+ " TO " +  (dateFormat.format(dates.last().getDate())).toUpperCase());
		calendarTitle.setTextFill(Color.WHITE);
		calendarTitle.setFont(Font.font("Inter SemiBold", 14));
		
		today.setText(dateFormat2.format(new Date()).toUpperCase());
		today.setTextFill(Color.WHITE);
		today.setFont(Font.font("Inter SemiBold", 14));
		GridPane.setHalignment(view, HPos.RIGHT);
		GridPane.setHalignment(today, HPos.CENTER);
		
		view.setItems(FXCollections.observableArrayList("Timetable", "Record Results"));
		view.getSelectionModel().selectFirst();
		view.setStyle("-fx-background-color: white");
		view.setMinHeight(30);
		view.setMinWidth(150);
		view.setOnAction(e -> {
			if(view.getSelectionModel().getSelectedItem().equals("Record Results")) {
				setTop(titleBar);
				setCenter(table);
				printTable.setText("print results");
				printTable.setOnAction(v -> PdfPrinter.printResult(students.getSelectionModel().getSelectedItem()));
				calMenu.getItems().add(email);
				titleBar.setCenter(classView);
				editCal.setText("view");
				Main.fadeIn(classView, 400);
				
				Iterator<Student> studentIter = Main.students.iterator();
				student.clear();
				while(studentIter.hasNext()) {
					Student std = studentIter.next();
					if(std.getClassroom().getName().equals(classView.getSelectionModel().getSelectedItem()))
						student.add(std);
				}
				
				students.setItems(FXCollections.observableArrayList(student));
				students.setContextMenu(calMenu);
				setCenter(students);
				actionBar.getAdd().setDisable(false);
				actionBar.getDelete().setDisable(false);
			}else {
				if(calMenu.getItems().contains(email))
					calMenu.getItems().remove(email);
				printTable.setText("print timetable");
				printTable.setOnAction(v -> PdfPrinter.printTimeTable(Main.cal.getExamDates()));
				editCal.setText("edit");
				titleBar.setCenter(today);
				actionBar.getAdd().setDisable(true);
				actionBar.getDelete().setDisable(true);
				Main.fadeIn(today, 400);
				setCenter(table);
				editCal.setOnAction(a -> {
					actionBar.getEdit().fire();
				});
			}
			
		});
		Rectangle viewRec = new Rectangle(150, 30);
		viewRec.setArcHeight(30);
		viewRec.setArcWidth(30);
		view.setClip(viewRec);
		
		
		
		classView.setItems(FXCollections.observableArrayList(classes));
		classView.getSelectionModel().selectFirst();
		classView.setStyle("-fx-background-color: white");
		classView.setMinHeight(30);
		classView.setMinWidth(150);
		classView.setOnAction(e -> {
			Iterator<Student> studentIter = Main.students.iterator();
			
			student.clear();
			while(studentIter.hasNext()) {
				Student std = studentIter.next();
				if(std.getClassroom().getName().equals(classView.getSelectionModel().getSelectedItem()))
					student.add(std);
			}
			
			students.setItems(FXCollections.observableArrayList(student));
		});
		Rectangle cVRec = new Rectangle(150, 30);
		cVRec.setArcHeight(30);
		cVRec.setArcWidth(30);
		classView.setClip(cVRec);
		
		
		students.setFixedCellSize(40);
		
		
		
		
		titleBar.setLeft(calendarTitle);
		titleBar.setCenter(today);
		titleBar.setRight(view);
		titleBar.setBottom(new Separator());
		((Separator)titleBar.getBottom()).setPadding(new Insets(10, 0, 0, 0));
		titleBar.setPadding(new Insets(10, 10, 0, 10));
		BorderPane.setAlignment(calendarTitle, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(today, Pos.BOTTOM_CENTER);
		titleBar.setStyle("-fx-background-color: " + Main.configuration.theme);
		
		table.setItems(FXCollections.observableArrayList(dates));
		table.getColumns().addAll(day, supervisor, time, otherTime);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		table.setFixedCellSize(80);
		table.setContextMenu(calMenu);
		editCal.setOnAction(e -> actionBar.getEdit().fire());
		
		table.widthProperty().addListener(e -> titleBar.setMinWidth(table.getMinWidth()));
		
		noItems.setStyle("-fx-background-color: white");
		table.setPlaceholder(noItems);
		day.setCellValueFactory(new PropertyValueFactory<>("dateRepresentation"));
		supervisor.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		otherTime.setCellValueFactory(new PropertyValueFactory<>("otherTime"));
		
		actionBar.getAdd().setOnAction(e -> {
			if(students.getSelectionModel().getSelectedItem() != null) {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(enterResults(students.getSelectionModel().getSelectedItem()));
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(250);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
				});
			}
		});
	/*	actionBar.getDelete().setOnAction( e -> { deleteCalendar(cal);});
	*/	actionBar.getEdit().setOnAction( e -> {	
			if(table.getSelectionModel().getSelectedItem() != null)
			editCalendar(dates);
		});
		actionBar.getSearchBar().setOnKeyTyped(( e -> { searchCalendar( actionBar.getSearchBar(), dates);}));
		
		setTop(titleBar);
		setCenter(table);
		setBottom(actionBar);
		BorderPane.setAlignment(getBottom(), Pos.CENTER_RIGHT);
		setStyle("-fx-background-color: " + Main.configuration.theme);
		BorderPane.setMargin(getBottom(), new Insets(7, 7, 7 , 0));
		view.fireEvent(new ActionEvent());
	}
	
void editCalendar(TreeSet<SchoolCalendar.ExamEntry> examDates) {
		
		Label forDay = new Label("Supervisor");
		Label fortype = new Label("First Subjects");
		Label forDesc = new Label("Last Subjects");
		
		forDay.setFont(Font.font("Inter", 14));
		forDay.setTextFill(Color.WHITE);
		
		fortype.setFont(Font.font("Inter", 14));
		fortype.setTextFill(Color.WHITE);
		
		forDesc.setFont(Font.font("Inter", 14));
		forDesc.setTextFill(Color.WHITE); 
		
		TextField supervisor = new TextField(table.getSelectionModel().getSelectedItem().getSupervisor());
		TextField time = new TextField(table.getSelectionModel().getSelectedItem().getTime());
		TextField otherTime = new TextField(table.getSelectionModel().getSelectedItem().getOtherTime());
		
		VBox pair = new VBox(forDay, supervisor);
		pair.setSpacing(5);
		VBox pair2 = new VBox(fortype, time);
		pair2.setSpacing(5);
		VBox pair3 = new VBox(forDesc, otherTime);
		pair3.setSpacing(5);
		
		VBox pairs = new VBox(pair,pair2,pair3);
		pairs.setSpacing(10);
		
		Button done = new Button("Done");
		Button cancel = new Button("Cancel");
		
		HBox buttons = new HBox(cancel, done);
		buttons.setSpacing(145);
		
		for(TextField elem :  new TextField[]{supervisor, time, otherTime}) {
				elem.setMaxWidth(345);
				elem.setMinHeight(35);;
				elem.setStyle("-fx-background-color: #fefefe; -fx-text-fill: black");
				elem.focusedProperty().addListener(e -> {
					done.setStyle("-fx-background-color: white");
					done.setTextFill(Color.BLACK);
				});
		}
		for(Button elem : new Button[] {cancel, done}) {
			elem.setMinWidth(100);
			elem.setMinHeight(30);
			elem.setStyle("-fx-background-color: white");
			elem.setFont(Font.font("Inter SemiBold", 15));
			elem.setTextFill(Color.BLACK);
			Rectangle elemRec = new Rectangle(100, 30);
			elemRec.setArcHeight(30);
			elemRec.setArcWidth(30);
			elem.setClip(elemRec);
		}
		
		BorderPane view = new BorderPane();
		view.setCenter(pairs);
		view.setBottom(buttons);
		
		view.setStyle("-fx-background-color: #232323");
		view.setPadding(new Insets(20));
		view.setMinSize(385, 440);
		Rectangle rec = new Rectangle(385, 440);
		rec.setArcHeight(35);
		rec.setArcWidth(35);
		view.setClip(rec);
		
		Main.popup.getContent().clear();
		Main.popup.getContent().add(view);
		Main.popup.show(Main.primaryStage);
		Main.popup.setAnchorY(250);
		Main.popup.setOnHiding(v -> {
			Main.popup.getContent().clear();
		});
		
		done.setOnAction(e -> {
			for(SchoolCalendar.ExamEntry elem : examDates) {
				if(elem.equals(table.getSelectionModel().getSelectedItem())) {
					elem.setSupervisor(supervisor.getText());
					elem.setTime(time.getText());
					elem.setOtherTime(otherTime.getText());
				}
			}
			
			Main.saveData(Main.cal, Main.STORAGEFILE_C2);
			table.refresh();
			cancel.fire();
		});
		cancel.setOnAction(e -> {
			Main.popup.hide();
			Main.popup.getContent().clear();
		});
	}

	/**
 	* displays all available searched items.
 	*/
	protected void searchCalendar(TextField source, TreeSet<SchoolCalendar.ExamEntry> itemList) {
		//query to use with RegExp for searching
		String query = source.getText();
		//new set that holds the matching items
		ArrayList<SchoolCalendar.ExamEntry> matchedItems = new ArrayList<>();
		Iterator<SchoolCalendar.ExamEntry> iter = itemList.iterator();
		while(iter.hasNext() && !query.isEmpty()) {
			SchoolCalendar.ExamEntry person = iter.next();
			if((person.getSupervisor().toLowerCase()).matches(query.toLowerCase() + ".*") || 
					(person.getTime().toLowerCase()).matches(query.toLowerCase() + ".*") ||
				(person.getOtherTime().toLowerCase()).matches(query.toLowerCase() + ".*"))
				matchedItems.add(person);
		}
		if(matchedItems.isEmpty())table.setItems(FXCollections.observableArrayList(itemList));
		else
			table.setItems((FXCollections.observableList(new ArrayList<>(matchedItems))));
	}
	
	protected BorderPane enterResults(Student stud) {
		 BorderPane pane = new BorderPane();
		 Label forName = new Label("Name");
		 Label forResults = new Label("Results");
		
		 TextField name = new TextField();
		 Button cancel = new Button("Cancel");
		 Button add = new Button("Save");
		 HBox buttons = new HBox(cancel, add);
		
		 VBox results = new VBox();
		 
		 VBox namePair = new VBox(forName, name);
		 VBox resultsPair = new VBox(forResults, results);
		
		 VBox mainHolder = new VBox();
		
		for(Label elem : new Label[]{forName, forResults}) {
			elem.setTextFill(Color.WHITE);
			elem.setFont(Font.font("Inter SemiBold", 16));
		}
		for(TextField elem :  new TextField[]{name}) {
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
			
		name.setText(stud.getCodeNumber() + " " + stud.getName() + " : [" + stud.getClassroom().getName() + "]");
		name.setEditable(false);
		name.setFocusTraversable(false);
			
		namePair.setSpacing(5);
		resultsPair.setSpacing(5);
		
		namePair.setMaxWidth(340);
		resultsPair.setMaxWidth(340);
			
		cancel.setOnAction(e -> {
			Main.popup.hide();
			Main.popup.getContent().clear();
		});
		
		add.setOnAction(e -> {
		});
			
		mainHolder.getChildren().addAll(namePair, resultsPair);
		mainHolder.setSpacing(20);
		mainHolder.setAlignment(Pos.TOP_CENTER);
		
		buttons.setSpacing(180);
		
		ScrollPane container = new ScrollPane(results);
		container.setMaxWidth(340);
		container.setMaxHeight(200);
		mainHolder.getChildren().add(container);
		
		for(String elem : stud.getClassroom().getSubjects()) {
			HBox entry = new HBox();
			TextField subject = new TextField();
			TextField marks = new TextField();
			
			subject.setText(elem);
			subject.setEditable(false);
			marks.setPromptText("Enter mark...");
			
			for(TextField var :  new TextField[]{subject, marks}) {
				var.setMaxWidth(150);
				var.setMinHeight(35);;
				var.setFont(Font.font("Inter", 14));
				var.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
				((TextField)var).focusedProperty().addListener(e -> {
					add.setStyle("-fx-background-color: white");
					add.setTextFill(Color.BLACK);
				});
			}
			
			entry.getChildren().addAll(subject, marks);
			entry.setSpacing(5);
			
			results.getChildren().add(entry);
		}
		
		results.setSpacing(10);
		
		pane.setCenter(mainHolder);
		pane.setBottom(buttons);
		pane.setMinWidth(400);
		pane.setMinHeight(400);
		pane.setPadding(new Insets(20, 10, 10, 10));
		pane.setStyle("-fx-background-color: " + Main.configuration.theme);
		
		Rectangle rec = new Rectangle(400, 400);
		rec.setArcHeight(35);
		rec.setArcWidth(35);
		pane.setClip(rec);
		
		return pane;
	}
}
