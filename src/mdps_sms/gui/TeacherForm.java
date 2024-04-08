package mdps_sms.gui;

import java.util.Arrays;
import java.util.Collections;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TeacherForm extends Form {
	private Label forClassroom = new Label("Classes");
	private Label forSubjects = new Label("Subjects");
	
	private VBox classroomPair = new VBox(forClassroom);
	private VBox subjectsPair = new VBox(forSubjects);
	
	public TeacherForm() {
		super();
		formTitle.setText("Teacher Registration");
		role.setText("Teacher");
		role.setEditable(false);
		this.styleLabels(forClassroom, forSubjects);
		this.styleVPairs(classroomPair, subjectsPair);
		
		this.secondSection.getChildren().addAll(2, Arrays.asList(classroomPair, subjectsPair));
	}

}
