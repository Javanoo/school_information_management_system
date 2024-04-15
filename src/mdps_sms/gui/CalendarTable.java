package mdps_sms.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
	
	CalendarTable(){}
	
	CalendarTable(TreeSet<SchoolCalendar.DayEntry> dates){
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
		titleBar.setPadding(new Insets(10));
		BorderPane.setAlignment(calendarTitle, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(today, Pos.BOTTOM_CENTER);
		titleBar.setStyle("-fx-background-color: #232323");
		
		table.setItems(FXCollections.observableArrayList(dates));
		table.getColumns().addAll(day, dayType, events, description);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		
		table.widthProperty().addListener(e -> titleBar.setMinWidth(table.getMinWidth()));
		
		noItems.setStyle("-fx-background-color: white");
		table.setPlaceholder(noItems);
		day.setCellValueFactory(new PropertyValueFactory<>("dateRepresentation"));
		dayType.setCellValueFactory(new PropertyValueFactory<>("dayType"));
		events.setCellValueFactory(new PropertyValueFactory<>("event"));
		description.setCellValueFactory(new PropertyValueFactory<>("Description"));
		
		setTop(titleBar);
		setCenter(table);
		setBottom(new ActionBar());
		BorderPane.setAlignment(getBottom(), Pos.CENTER_RIGHT);
		setStyle("-fx-background-color: #232323");
		BorderPane.setMargin(getBottom(), new Insets(7, 7, 7 , 0));
	}
}
