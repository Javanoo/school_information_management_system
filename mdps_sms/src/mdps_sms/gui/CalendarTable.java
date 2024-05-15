package mdps_sms.gui;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
import javafx.util.Callback;
import mdps_sms.Main;
import mdps_sms.gui.FeesList.FeesForm;
import mdps_sms.util.PdfPrinter;
import mdps_sms.util.SchoolCalendar;
import mdps_sms.util.SchoolCalendar.DayEntry;

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
	
	TreeSet<SchoolCalendar.DayEntry> dates = new TreeSet<SchoolCalendar.DayEntry>();
	
	MenuItem viewCal = new MenuItem("view");
	MenuItem editCal = new MenuItem("edit");
	MenuItem printCal = new MenuItem("print calendar");
	
	ContextMenu calMenu = new ContextMenu(viewCal, editCal, printCal);
	
	CalendarTable(){}
	
	CalendarTable(SchoolCalendar cal){
		dates = cal.getDates();
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
		
		printCal.setOnAction(e -> PdfPrinter.printCalendar(cal));
		
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
		//titleBar.setCenter(today);
		titleBar.setRight(today);
		titleBar.setBottom(new Separator());
		((Separator)titleBar.getBottom()).setPadding(new Insets(10, 0, 0, 0));
		titleBar.setPadding(new Insets(10, 10, 0, 10));
		BorderPane.setAlignment(calendarTitle, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(today, Pos.BOTTOM_CENTER);
		titleBar.setStyle("-fx-background-color: " + Main.configuration.theme);
		
		day.setCellFactory(column -> {
            return new AgeTableCell();
        });
		dayType.setCellFactory(column -> {
            return new AgeTableCell();
        });
		events.setCellFactory(column -> {
            return new AgeTableCell();
        });
		description.setCellFactory(column -> {
            return new AgeTableCell();
        });
		
		table.setItems(FXCollections.observableArrayList(dates));
		table.getColumns().addAll(day, dayType, events, description);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		table.setContextMenu(calMenu);
		table.setFixedCellSize(45);
		editCal.setOnAction(e -> actionBar.getEdit().fire());
		
		table.widthProperty().addListener(e -> titleBar.setMinWidth(table.getMinWidth()));
		
		noItems.setStyle("-fx-background-color: white");
		table.setPlaceholder(noItems);
		day.setCellValueFactory(new PropertyValueFactory<>("dateRepresentation"));
		dayType.setCellValueFactory(new PropertyValueFactory<>("dayType"));
		events.setCellValueFactory(new PropertyValueFactory<>("event"));
		description.setCellValueFactory(new PropertyValueFactory<>("Description"));
		
		actionBar.getAdd().setOnAction( e -> { createCalendar();});
		actionBar.getDelete().setOnAction( e -> { 
			if(Main.cal.getDates().size() != 0)
				deleteCalendar();
		});
		actionBar.getEdit().setOnAction( e -> {	
			if(table.getSelectionModel().getSelectedItem() != null)
				editCalendar(Main.cal);
		});
		actionBar.getSearchBar().setOnKeyTyped(( e -> { searchCalendar();}));
		
		setTop(titleBar);
		setCenter(table);
		setBottom(actionBar);
		BorderPane.setAlignment(getBottom(), Pos.CENTER_RIGHT);
		setStyle("-fx-background-color: " + Main.configuration.theme);
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
		
		HBox buttons = new HBox(cancel, done);
		buttons.setSpacing(145);
		
		for(DatePicker elem :  new DatePicker[]{datePicks2, datePicks}) {
			elem.setMaxWidth(345);
			elem.setMinHeight(35);;
			elem.setStyle("-fx-background-color: #484848; -fx-text-fill: black");
			elem.setEditable(false);
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
			System.out.println(calendar.getDates().size());
			Main.cal = calendar;
			table.setItems(FXCollections.observableArrayList(calendar.getDates()));
			cancel.fire();
		});
		cancel.setOnAction(e -> {
			Main.fadeOut(Main.popup.getContent().get(0), 250);
			Main.saveData(Main.cal, Main.STORAGEFILE_C2);
		});
		
		view.setStyle("-fx-background-color: " + Main.configuration.theme);
		view.setPadding(new Insets(20));
		view.setMinSize(385, 340);
		Rectangle rec = new Rectangle(385, 340);
		rec.setArcHeight(35);
		rec.setArcWidth(35);
		view.setClip(rec);
	}
	
	void deleteCalendar() {
		String prompt = "Delete school Calendar?";  
		
		GridPane confirm = actionBar.confirm(prompt);
		
		Main.popup.getContent().clear();
		Main.popup.getContent().add(confirm);
		Main.popup.show(Main.primaryStage);
		
		((Button)confirm.getChildren().get(2)).setOnAction( v -> {
			table.getItems().clear();
			Main.cal = new SchoolCalendar();
			Main.saveData(Main.cal, Main.STORAGEFILE_C2);
			
			((Button)confirm.getChildren().get(1)).fire();
		});
		((Button)confirm.getChildren().get(1)).setOnAction(v -> {
			Main.popup.hide();
			Main.popup.getContent().clear();
		});
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
		
		ComboBox<String> type = new ComboBox<>();
		type.setItems(FXCollections.observableArrayList("Exams day", "School day", "Holiday"));
		type.getSelectionModel().select(table.getSelectionModel().getSelectedItem().getDayType());
		TextField events = new TextField(table.getSelectionModel().getSelectedItem().getEvent());
		TextArea desc = new TextArea(table.getSelectionModel().getSelectedItem().getDescription());
		
		VBox pair = new VBox(forDay, type);
		pair.setSpacing(5);
		VBox pair2 = new VBox(fortype, events);
		pair2.setSpacing(5);
		VBox pair3 = new VBox(forDesc, desc);
		pair3.setSpacing(5);
		
		VBox pairs = new VBox(pair,pair2,pair3);
		pairs.setSpacing(20);
		
		Button done = new Button("Done");
		Button cancel = new Button("Cancel");
		
		HBox buttons = new HBox(cancel, done);
		
		buttons.setSpacing(145);
		
		for(Control elem :  new Control[]{type, events}) {
			if(elem instanceof TextField) {
				elem.setMaxWidth(345);
				elem.setMinHeight(35);;
				elem.setStyle("-fx-background-color: #fefefe; -fx-text-fill: black");
				elem.focusedProperty().addListener(e -> {
					done.setStyle("-fx-background-color: white");
					done.setTextFill(Color.BLACK);
				});
			}else {
				elem.setMaxWidth(345);
				elem.setMinHeight(35);;
				elem.setStyle("-fx-background-color: #fefefe; -fx-text-fill: black");
			}
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
		
		
		desc.setMaxWidth(345);
		desc.setMaxHeight(85);;
		desc.setStyle("-fx-background-color: #fefefe; -fx-text-fill: black");
		
		BorderPane view = new BorderPane();
		view.setCenter(pairs);
		view.setBottom(buttons);
		
		view.setStyle("-fx-background-color: " + Main.configuration.theme);
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
		
		done.setOnAction(e -> {
			System.out.println(calendar.getDates().size());
			for(SchoolCalendar.DayEntry elem : calendar.getDates()) {
				if(elem.equals(table.getSelectionModel().getSelectedItem())) {
					elem.setDayType(type.getValue());
					elem.setEvent(events.getText());
					elem.setDescription(desc.getText());
				}
			}
			
			calendar.updateExamDates();
			System.out.println(calendar.getDates().size());
			table.refresh();
			cancel.fire();
		});
		cancel.setOnAction(e -> {
			Main.popup.hide();
			Main.popup.getContent().clear();
			Main.saveData(calendar, Main.STORAGEFILE_C2);
		});
	}
	void searchCalendar() {}
}

// Custom cell factory for styling age column
class AgeTableCell extends javafx.scene.control.TableCell<SchoolCalendar.DayEntry, String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setText("");
            setStyle("-fx-background-color: wheat");
        } else {
            setText(String.valueOf(item));
            if (item.contains("SUNDAY") || item.contains("SATURDAY") || item.isBlank()) {
                setTextFill(Color.RED);
                setStyle("-fx-font-weight: bold; -fx-background-color: wheat; -fx-border-color: wheat");
            }else {
                setTextFill(Color.BLACK);
                setStyle("");
            }
        }
    }
 }
