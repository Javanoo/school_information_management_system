package mdps_sms.gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mdps_sms.Main;
import mdps_sms.util.Administrator;
import mdps_sms.util.SchoolCalendar;
import mdps_sms.util.SchoolClass;
import mdps_sms.util.Staff;
import mdps_sms.util.Student;

public class DashBoard extends BorderPane {
	public Text user = new Text("Matthews");
	public StackPane userHolder = new StackPane(user);
	
	public Label forCalendar = new Label("Calendar");
	public ListView<SchoolCalendar.DayEntry> days = new ListView<>();
	public VBox calendar = new VBox(forCalendar, days);
	public Text today  = new Text("APR 17 2024");
	public BorderPane firstTab = new BorderPane();
	
	public Label forNumberofStudents = new Label("Students");
	public Label forNumberofFStudents = new Label("Female Students");
	public Label forNumberofMStudents = new Label("Male Students");
	public HBox secondTab = new HBox(forNumberofStudents, forNumberofFStudents, forNumberofMStudents); 
	
	public Label forNumberofMPersonnel = new Label("Male staff");
	public Label forNumberofFPersonnel = new Label("Female Staff");
	public Label forNumberofPersonnel = new Label("Staff");
	public HBox thirdTab = new HBox(forNumberofPersonnel, forNumberofFPersonnel, forNumberofMPersonnel);
	
	public Label forNumberofTeachers = new Label("Teachers");
	public Label forNumberofSubjects = new Label("Classes Subjects");
	public Label forNumberofClasses = new Label("Classes");
	public HBox fourthTab = new HBox(forNumberofClasses, forNumberofTeachers, forNumberofSubjects);
	
	public Label fees = new Label("Total fees to collect");
	public Label feesPaid = new Label("Fees Paid");
	public Label feesBalance = new Label("Balances");
	CalendarTable cale = new CalendarTable(Main.cal);
	
	public HBox fifthTab = new HBox(cale);

	
	public VBox centralPanel = new VBox(secondTab, thirdTab, fourthTab, fifthTab);
	SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM YYYY");
	
	public DashBoard(Administrator admin, SchoolCalendar cal) {
		
		cale.actionBar.setVisible(false);
		cale.table.setContextMenu(null);
		
		user.setFill(Color.WHITE);
		user.setFont(Font.font(Main.configuration.font  + " SemiBold", Main.configuration.fontSize + 2));
		user.setText(admin.getName());
		userHolder.setStyle("-fx-background-color: " + Main.configuration.theme);
		userHolder.setPadding(new Insets(10));
		userHolder.setMinSize(300, 35);
		userHolder.setMaxWidth(300);
		Rectangle userRec = new Rectangle(300, 40);
		userRec.setArcHeight(35);
		userRec.setArcWidth(35);
		userHolder.setClip(userRec);
		
		today.setFill(Color.WHITE);
		today.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 4));
		today.setText(dateFormat.format(new Date()));
		today.setWrappingWidth(200);
		forCalendar.setTextFill(Color.WHITE);
		forCalendar.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		if(cal.getDates() != null) {
			days.setItems(FXCollections.observableArrayList(cal.getDates()));
		}
		days.setMaxWidth(280);
		calendar.setMinHeight(450);
		calendar.setAlignment(Pos.TOP_CENTER);
		calendar.setSpacing(10);
		firstTab.setCenter(today);
		firstTab.setBottom(calendar);
		firstTab.setPadding(new Insets(5));
		firstTab.setMinSize(300, 780);
		BorderPane.setAlignment(today, Pos.CENTER);
		firstTab.setStyle("-fx-background-color: " + Main.configuration.theme);
		Rectangle firstRec = new Rectangle(300, 1000);
		firstRec.setArcHeight(35);
		firstRec.setArcWidth(35);
		firstTab.setClip(firstRec);
		
		forNumberofStudents.setText("Number of Students\n\n" + Main.students.size() + "");
		secondTab.setAlignment(Pos.CENTER_LEFT);
		secondTab.setSpacing(180);
		
		int male = 0; int female = 0;
		for(Student elem : Main.students) {
			if(elem.getGender().equals("Male")) male++;
			else female++;
		}
		forNumberofFStudents.setText("Number of  Female Students\n\n" + female + "");
		forNumberofMStudents.setText("Number of Male Students\n\n" + male + "");
		forNumberofStudents.setTextFill(Color.WHITE);
		forNumberofStudents.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		forNumberofFStudents.setTextFill(Color.WHITE);
		forNumberofFStudents.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		forNumberofMStudents.setTextFill(Color.WHITE);
		forNumberofMStudents.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		secondTab.setStyle("-fx-background-color: " + Main.configuration.theme);
		secondTab.setPadding(new Insets(10));
		
		int Smale = 0; int Sfemale = 0;
		for(Staff elem : Main.staff) {
			if(elem.getGender().equals("Male")) Smale++;
			else Sfemale++;
		}
		forNumberofFPersonnel.setText("Number of  Female Staff\n\n" + Sfemale + "");
		forNumberofMPersonnel.setText("Number of Male Staff\n\n" + Smale + "");
		forNumberofPersonnel.setTextFill(Color.WHITE);
		forNumberofPersonnel.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		forNumberofFPersonnel.setTextFill(Color.WHITE);
		forNumberofFPersonnel.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		forNumberofMPersonnel.setTextFill(Color.WHITE);
		forNumberofMPersonnel.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		forNumberofPersonnel.setText("Number of Staff\n\n" + Main.staff.size() + "");
		thirdTab.setStyle("-fx-background-color: " + Main.configuration.theme);
		thirdTab.setAlignment(Pos.CENTER_LEFT);
		thirdTab.setPadding(new Insets(10));
		thirdTab.setSpacing(200);
		
		
		forNumberofClasses.setText("Number of Classes\n\n" + Main.classrooms.size() + "");
		int subject = 0; int teacher = 0;
		for(SchoolClass elem : Main.classrooms) {
			subject += elem.getSubjects().size();
			teacher += elem.getTeachers().size();
		}
		forNumberofTeachers.setText("Number of Teachers\n\n" + teacher + "");
		forNumberofSubjects.setText("Number of Subjects\n\n" + subject + "");
		forNumberofClasses.setTextFill(Color.WHITE);
		forNumberofClasses.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		forNumberofTeachers.setTextFill(Color.WHITE);
		forNumberofTeachers.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		forNumberofSubjects.setTextFill(Color.WHITE);
		forNumberofSubjects.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		fourthTab.setStyle("-fx-background-color: " + Main.configuration.theme);
		fourthTab.setAlignment(Pos.CENTER_LEFT);
		fourthTab.setSpacing(200);
		fourthTab.setMinHeight(150);
		fourthTab.setPadding(new Insets(10));
		
		int feesT = 0; int paid = 0;
		for(Student elem : Main.students) {
			feesT += elem.getFees();
			paid += elem.getFeesPaid();
		}
		fees.setText("Total Fees Amount\n\n" + feesT + "");
		feesPaid.setText("Collected\n\n" + paid + "");
		fees.setTextFill(Color.WHITE);
		feesPaid.setFont(Font.font("Inter SemiBold", Main.configuration.fontSize + 2));
		feesPaid.setTextFill(Color.WHITE);
		fees.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		feesBalance.setTextFill(Color.WHITE);
		feesBalance.setFont(Font.font(Main.configuration.font + " SemiBold", Main.configuration.fontSize + 2));
		feesBalance.setText("To Collect\n\n" + (feesT - paid));
		fifthTab.setStyle("-fx-background-color: " + Main.configuration.theme);
		fifthTab.setStyle("-fx-background-color: " + Main.configuration.theme);
		
		secondTab.setMinHeight(150);
		thirdTab.setMinHeight(150);
		centralPanel.setSpacing(10);
		
		setTop(userHolder);
		setLeft(firstTab);
		setCenter(centralPanel);
		BorderPane.setMargin(firstTab, new Insets(10,0,0,0));
		BorderPane.setMargin(centralPanel, new Insets(10,0,0,10));
	}
}
