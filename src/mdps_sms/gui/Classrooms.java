package mdps_sms.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mdps_sms.util.SchoolClass;

public class Classrooms extends BorderPane {
	
	private Label forAvailableClasses = new Label("Available Classes");
	private Button addNewClass = new Button("Add");

	
	class classroomCard extends BorderPane{
		private Label forClassName = new Label();
		private Label forSubjects = new Label();
		private Label forNumberOfStudents = new Label("Number of Students");
		private Label forNumberOfTeachers = new Label("Number of Teachers");
		private Label numberOfStudents = new Label();
		private Label numberOfTeachers = new Label();
		
		private Button recordAttendance = new Button("Record Attendance");
		private Button viewStudents = new Button("view students");
		private Button viewTeachers = new Button("view teachers");
		private Button editClass = new Button("edit class");
		private Button remove = new Button("Delete class");
		
		private HBox subjectsPair = new HBox();
		private HBox studentsPair = new HBox();
		private HBox teachersPair = new HBox();
		
		private VBox classroomProperties = new VBox();
		
		classroomCard(){}
		
		classroomCard(SchoolClass classroom){
			forClassName.setText(classroom.getName());
			
			numberOfStudents.setText(classroom.getStudents().size() + ""); 
			numberOfTeachers.setText(classroom.getTeachers().size() + "");
			
			classroomProperties.getChildren().add(subjectsPair);
			classroomProperties.getChildren().add(studentsPair);
			classroomProperties.getChildren().add(teachersPair);
			classroomProperties.getChildren().add(recordAttendance);
		}
	}
}
