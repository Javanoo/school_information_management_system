package mdps_sms.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
import mdps_sms.util.SchoolCalendar;

public class CalendarTable extends BorderPane{
	
	Label calendarTitle = new Label("SCHOOL CALENDAR");
	Label today = new Label("");
	ComboBox<String> view = new ComboBox<>();
	BorderPane titleBar = new BorderPane();
	
	TableView<SchoolCalendar.DayEntry> table = new TableView<>();
	TableColumn<SchoolCalendar.DayEntry, String> day = new TableColumn<>("Day");
	TableColumn<SchoolCalendar.DayEntry, String> dayType = new TableColumn<>("Day type");
	TableColumn<SchoolCalendar.DayEntry, String> events = new TableColumn<>("Events");
	TableColumn<SchoolCalendar.DayEntry, String> description = new TableColumn<>("Description");
	
	StackPane noItems = new StackPane(new Label("no calendar set."));
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM dd yyyy");
	
	ActionBar actionBar = new ActionBar();
	
	public static SchoolCalendar calendarData = new SchoolCalendar();
	TreeSet<SchoolCalendar.DayEntry> dates = new TreeSet<SchoolCalendar.DayEntry>();
	
	MenuItem viewCal = new MenuItem("view");
	MenuItem editCal = new MenuItem("edit");
	MenuItem printDay = new MenuItem("print day");
	MenuItem printCal = new MenuItem("print calendar");
	
	ContextMenu calMenu = new ContextMenu(viewCal, editCal, printDay, printCal);
	
	CalendarTable(){}
	
	CalendarTable(SchoolCalendar cal){
		dates =cal.getDates();
		if(dates.size() != 0)
		calendarTitle.setText("SCHOOL CALENDAR - " + (dateFormat.format(dates.first().getDate())).toUpperCase() 
				+ " TO " +  (dateFormat.format(dates.last().getDate())).toUpperCase());
		calendarTitle.setTextFill(Color.WHITE);
		calendarTitle.setFont(Font.font("Inter SemiBold", 14));
		
		today.setText(dateFormat2.format(new Date()).toUpperCase());
		today.setTextFill(Color.WHITE);
		today.setFont(Font.font("Inter SemiBold", 14));
		GridPane.setHalignment(view, HPos.RIGHT);
		GridPane.setHalignment(today, HPos.CENTER);
		
		view.setItems(FXCollections.observableArrayList("Calendar","Timetable"));
		view.getSelectionModel().selectFirst();
		view.setStyle("-fx-background-color: white");
		view.setMinHeight(30);
		view.setMinWidth(150);
		view.setOnAction(e -> {
			if(view.getSelectionModel().getSelectedItem().equals("Calendar")) {
				setTop(titleBar);
				setCenter(table);
			}
			
		});
		Rectangle viewRec = new Rectangle(150, 30);
		viewRec.setArcHeight(30);
		viewRec.setArcWidth(30);
		view.setClip(viewRec);
		
		
		titleBar.setLeft(calendarTitle);
		titleBar.setCenter(today);
		titleBar.setRight(view);
		titleBar.setBottom(new Separator());
		((Separator)titleBar.getBottom()).setPadding(new Insets(10, 0, 0, 0));
		titleBar.setPadding(new Insets(10, 10, 0, 10));
		BorderPane.setAlignment(calendarTitle, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(today, Pos.BOTTOM_CENTER);
		titleBar.setStyle("-fx-background-color: #232323");
		
		table.setItems(FXCollections.observableArrayList(dates));
		table.getColumns().addAll(day, dayType, events, description);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		table.setContextMenu(calMenu);
		editCal.setOnAction(e -> actionBar.getEdit().fire());
		
		table.widthProperty().addListener(e -> titleBar.setMinWidth(table.getMinWidth()));
		
		noItems.setStyle("-fx-background-color: white");
		table.setPlaceholder(noItems);
		day.setCellValueFactory(new PropertyValueFactory<>("dateRepresentation"));
		dayType.setCellValueFactory(new PropertyValueFactory<>("dayType"));
		events.setCellValueFactory(new PropertyValueFactory<>("event"));
		description.setCellValueFactory(new PropertyValueFactory<>("Description"));
		
		actionBar.getAdd().setOnAction( e -> { createCalendar();});
		actionBar.getDelete().setOnAction( e -> { deleteCalendar(cal);});
		actionBar.getEdit().setOnAction( e -> {	
			if(table.getSelectionModel().getSelectedItem() != null)
			editCalendar(cal);
		});
		actionBar.getSearchBar().setOnKeyTyped(( e -> { searchCalendar();}));
		
		setTop(titleBar);
		setCenter(table);
		setBottom(actionBar);
		BorderPane.setAlignment(getBottom(), Pos.CENTER_RIGHT);
		setStyle("-fx-background-color: #232323");
		BorderPane.setMargin(getBottom(), new Insets(7, 7, 7 , 0));
	}
	
	void createCalendar() {
		Label forStartDay = new Label("Start day");
		Label forEndDay = new Label("End day");
		
		forStartDay.setFont(Font.font("Inter", 14));
		forStartDay.setTextFill(Color.WHITE);
		
		forEndDay.setFont(Font.font("Inter", 14));
		forEndDay.setTextFill(Color.WHITE);
		
		DatePicker datePicks = new DatePicker();
		DatePicker datePicks2 = new DatePicker();
		
		VBox pair = new VBox(forStartDay, datePicks);
		VBox pair2 = new VBox(forEndDay, datePicks2);
		
		VBox pair3 = new VBox(pair, pair2);
		
		Button done = new Button("Done");
		Button cancel = new Button("Cancel");
		
		HBox buttons = new HBox(done, cancel);
		
		BorderPane view = new BorderPane();
		pair3.setSpacing(10);
		view.setCenter(pair3);
		view.setBottom(buttons);
		
		Main.popup.getContent().clear();
		Main.popup.getContent().add(view);
		Main.popup.show(Main.primaryStage);
		Main.popup.setAnchorY(250);
		Main.popup.setOnHiding(v -> {
			Main.popup.getContent().clear();
		});
		
		done.setOnAction(e -> {
			SchoolCalendar calendar = new SchoolCalendar(datePicks.getValue(), datePicks2.getValue());
			Main.saveData(calendar, Main.STORAGEFILE_C2);
			table.setItems(FXCollections.observableArrayList(calendar.getDates()));
			cancel.fire();
		});
		cancel.setOnAction(e -> {
			Main.popup.hide();
			Main.popup.getContent().clear();
		});
		
		view.setStyle("-fx-background-color: #232323");
		view.setPadding(new Insets(10));
		view.setMinSize(400, 500);
	}
	void deleteCalendar(SchoolCalendar cal) {
		table.getItems().clear();
		cal = new SchoolCalendar();
		Main.saveData(cal, Main.STORAGEFILE_C2);
	}
	void editCalendar(SchoolCalendar calendar) {
		
		Label forDay = new Label("Type");
		Label fortype = new Label("Event");
		Label forDesc = new Label("Decription");
		
		forDay.setFont(Font.font("Inter", 14));
		forDay.setTextFill(Color.WHITE);
		
		fortype.setFont(Font.font("Inter", 14));
		fortype.setTextFill(Color.WHITE);
		
		forDesc.setFont(Font.font("Inter", 14));
		forDesc.setTextFill(Color.WHITE); 
		
		TextField type = new TextField(table.getSelectionModel().getSelectedItem().getDayType());
		TextField events = new TextField(table.getSelectionModel().getSelectedItem().getEvent());
		TextArea desc = new TextArea(table.getSelectionModel().getSelectedItem().getDescription());
		
		VBox pair = new VBox(forDay, type);
		pair.setSpacing(5);
		VBox pair2 = new VBox(fortype, events);
		pair2.setSpacing(5);
		VBox pair3 = new VBox(forDesc, desc);
		pair3.setSpacing(5);
		
		VBox pairs = new VBox(pair,pair2,pair3);
		pairs.setSpacing(10);
		
		Button done = new Button("Done");
		Button cancel = new Button("Cancel");
		
		HBox buttons = new HBox(done, cancel);
		
		BorderPane view = new BorderPane();
		view.setCenter(pairs);
		view.setBottom(buttons);
		
		view.setStyle("-fx-background-color: #232323");
		view.setPadding(new Insets(10));
		view.setMinSize(400, 500);
		
		Main.popup.getContent().clear();
		Main.popup.getContent().add(view);
		Main.popup.show(Main.primaryStage);
		Main.popup.setAnchorY(250);
		Main.popup.setOnHiding(v -> {
			Main.popup.getContent().clear();
		});
		
		done.setOnAction(e -> {
			for(SchoolCalendar.DayEntry elem : calendar.getDates()) {
				if(elem.equals(table.getSelectionModel().getSelectedItem())) {
					elem.setDayType(type.getText());
					elem.setEvent(events.getText());
					elem.setDescription(desc.getText());
				}
			}
			
			Main.saveData(calendar, Main.STORAGEFILE_C2);
			table.setItems(FXCollections.observableArrayList(calendar.getDates()));
			cancel.fire();
		});
		cancel.setOnAction(e -> {
			Main.popup.hide();
			Main.popup.getContent().clear();
		});
	}
	void searchCalendar() {}
}
