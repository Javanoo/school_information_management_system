package mdps_sms.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mdps_sms.Main;
import mdps_sms.util.Parent;
import mdps_sms.util.Person;
import mdps_sms.util.SchoolClass;
import mdps_sms.util.Student;

public class StudentForm extends Form {

	private Label forStudentId = new Label("Code number");
	private Label forParent = new Label("Parent");
	private Label forSecondParent = new Label("Second parent (optinal)");
	private Label forSecondParentFirstPhone = new Label("Phone");
	private Label forSecondParentSecondPhone = new Label("Second Phone");
	private Label forSecondParentFirstEmail = new Label("Email");
	private Label forSecondParentSecondEmail = new Label("Second Email");
	private Label forClassroom = new Label("Classroom");

	private TextField codeNumber = new TextField();
	private TextField parent = new TextField();
	private TextField secondParent = new TextField();
	private TextField secondParentFirstPhone = new TextField();
	private TextField secondParentSecondPhone = new TextField();
	private TextField secondParentFirstEmail = new TextField();
	private TextField secondParentSecondEmail = new TextField();
	private ListView<SchoolClass> classrooms = new ListView<>();

	private VBox studentIdPair = new VBox(forStudentId, codeNumber);
	private VBox parentPair = new VBox(forParent, parent);
	private VBox secondParentPair = new VBox(forSecondParent, secondParent);
	private VBox secondParentFirstPhonePair = new VBox(forSecondParentFirstPhone, secondParentFirstPhone);
	private VBox secondParentSecondPhonePair = new VBox(forSecondParentSecondPhone, secondParentSecondPhone);
	private VBox secondParentFirstEmailPair = new VBox(forSecondParentFirstEmail, secondParentFirstEmail);
	private VBox secondParentSecondEmailPair = new VBox(forSecondParentSecondEmail, secondParentSecondEmail);
	private VBox classroomPair = new VBox(forClassroom, classrooms);

	StudentForm(){
		super();
		
		Label noItems = new Label("no classes found");
		StackPane placeHolder = new StackPane(noItems);
		noItems.setFont(Font.font("Inter SemiBold", 14)); noItems.setTextFill(Color.BLACK);
		this.classrooms.setItems(FXCollections.observableList(Main.classrooms));
		this.classrooms.setPlaceholder(placeHolder);

		formTitle.setText("Student Registration");
		forFirstSection.setText("Student Details");

		this.styleLabels(forStudentId, forParent, forSecondParent, forSecondParentFirstPhone, forSecondParentSecondPhone,
				forSecondParentFirstEmail, forSecondParentSecondEmail, forClassroom);
		this.styleTextInputControls(codeNumber, parent, secondParent, secondParentFirstPhone, secondParentSecondPhone,
				secondParentFirstEmail, secondParentSecondEmail);
		this.styleVPairs(studentIdPair, parentPair, secondParentPair, secondParentFirstPhonePair, secondParentSecondPhonePair
				, secondParentFirstEmailPair, secondParentSecondEmailPair, classroomPair);

		codeNumber.setPromptText("enter student code...");
		parent.setPromptText("enter parent name...");
		secondParent.setPromptText("enter other parent's name...");
		secondParentFirstPhone.setPromptText("enter other parent's phone number...");
		secondParentSecondPhone.setPromptText("enter other parent's second phone number...");
		secondParentFirstEmail.setPromptText("enter other parent's email...");
		secondParentSecondEmail.setPromptText("enter other parent's second email");


		firstSection.getChildren().add(2, studentIdPair);
		firstSection.getChildren().add(5, parentPair);
		firstSection.getChildren().addAll(10, Arrays.asList(secondParentPair,secondParentFirstPhonePair, secondParentSecondPhonePair
				,secondParentFirstEmailPair, secondParentSecondEmailPair));
		firstSection.getChildren().remove(locationPair);

		forSecondSection.setText("Academics");
		secondSection.getChildren().clear();
		secondSection.getChildren().addAll(forSecondSection, separate2, classroomPair);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <E extends Person> boolean createNew(E type, ArrayList itemList) {
		if(verifyEntries(new Control[] {name, gender, parent},
						new String[] {"Enter name", "Choose gender", "Enter parent"})) {
			Parent[] parentArray = createParents(parent, firstPhone, secondPhone, firstEmail, secondEmail, secondParent,
					secondParentFirstPhone, secondParentSecondPhone, secondParentFirstEmail, secondParentSecondEmail);
			String description = this.description.getText().isBlank() ? "no description" : this.description.getText();
			SchoolClass selectedClass = classrooms.getSelectionModel().getSelectedItem();
			
			if(selectedClass != null && parentArray != null) {
				if(itemList == null) itemList = new ArrayList<Student>();
				Student newStudent = new Student(name.getText(), codeNumber.getText(),gender.getValue(), selectedClass, parentArray, description);
				if(itemList.contains(type)) {
					SchoolClass classCopy = ((Student)type).getClassroom();
					classCopy.getStudents().remove((Student)type);
					Main.classrooms.remove(((Student)type).getClassroom());
					Main.classrooms.add(classCopy);
				}
				selectedClass.getStudents().add(newStudent);
				itemList.add(newStudent);
				Main.saveData(itemList, Main.STORAGEFILE_S);
				Main.saveData(Main.classrooms, Main.STORAGEFILE_C);
				return true;
			}
			if(selectedClass == null) warn("select a class");
			return false;
		}
		return false;
	}

	/**
	 * 
	 * Returns a parent array instance, with parent instances holding the passed args as their property values
	 * <p>
	 * if one of the arguments is blank, the property that was supposed to get the value is assigned a none values.
	 * were all the args don't pass through the validation process null reference is passed back.
	 *
	 * @param firstParent
	 * @param firstParentPhone
	 * @param firstParentPhone2
	 * @param firstParentEmail
	 * @param firstParentEmail2
	 * @param secondParent
	 * @param secondParentPhone
	 * @param secondParentPhone2
	 * @param secondParentEmail
	 * @param secondParentEmail2
	 * @return parentArray
	 *
	 * @see Parent
	 */
	public Parent[] createParents(TextField firstParent, TextField firstParentPhone, TextField firstParentPhone2,
			TextField firstParentEmail, TextField firstParentEmail2, TextField secondParent, TextField secondParentPhone, TextField secondParentPhone2,
			TextField secondParentEmail, TextField secondParentEmail2) {
		Parent parent1 = null;
		Parent parent2 = new Parent("none", new String[] {"none", "none"}, new String[] {"none", "none"});
		String fParent = firstParent.getText();
		String fParentP1 = firstParentPhone.getText();
		String fParentP2 = firstParentPhone2.getText();
		String fParentE1 = firstParentEmail.getText();
		String fParentE2 = firstParentEmail2.getText();

		String sParent = secondParent.getText();
		String sParentP1 = secondParentPhone.getText();
		String sParentP2 = secondParentPhone2.getText();
		String sParentE1 = secondParentEmail.getText();
		String sParentE2 = secondParentEmail2.getText();

		//if first parent name is not set fail
		if(!fParent.isBlank()) {
			//or if the parents first phone number is not set fail.
			if(!fParentP1.isBlank() && verifyPhone(fParentP1)) {
				parent1 = new Parent(fParent, new String[]{fParentP1, ((!fParentP2.isBlank() && verifyPhone(fParentP2)) ? fParentP2 : "none" )},
						new String[]{((!fParentE1.isBlank() && verifyEmail(fParentE1)) ? fParentE1 : "none") , ((!fParentE2.isBlank() && verifyEmail(fParentE2)) ? fParentE2 : "none") });
				//check for second Parent
				if(!sParent.isBlank()) {
					if(!sParentP1.isBlank() && verifyPhone(sParentP1)) {
						parent2 = new Parent(sParent, new String[]{sParentP1, ((!sParentP2.isBlank() && verifyPhone(sParentP2)) ? sParentP2 : "none" )},
								new String[]{((!sParentE1.isBlank() && verifyEmail(sParentE1)) ? sParentE1 : "none") , ((!sParentE2.isBlank() && verifyEmail(sParentE2)) ? sParentE2 : "none") });

					}
				}
				return new Parent[] {parent1, parent2};
			}
			warn("Enter phone number...");
			return null;
		}
		warn("Enter parent...");
		return null;
	}

	@SuppressWarnings("rawtypes")
	public <E extends Person> void edit(Student person, ArrayList itemList) {
		save.setText("Save");
		codeNumber.setText(person.getCodeNumber());
		name.setText(person.getName());
		gender.setValue(person.getGender());
		parent.setText((person.getParents()[0]).getName());
		firstPhone.setText((person.getParents()[0]).getPhoneArray()[0]);
		secondPhone.setText((person.getParents()[0]).getPhoneArray()[1]);
		firstEmail.setText((person.getParents()[0]).getEmailArray()[0]);
		secondEmail.setText((person.getParents()[0]).getEmailArray()[1]);

		secondParent.setText((person.getParents()[1]).getName());
		secondParentFirstPhone.setText((person.getParents()[1]).getPhoneArray()[0]);
		secondParentSecondPhone.setText((person.getParents()[1]).getPhoneArray()[1]);
		secondParentFirstEmail.setText((person.getParents()[1]).getEmailArray()[0]);
		secondParentSecondEmail.setText((person.getParents()[1]).getEmailArray()[1]);
		
		classrooms.getSelectionModel().select(person.getClassroom());

		description.setText(person.getDescription());
		save.setOnAction(e -> {
			if (createNew(person, itemList)) {
				cancel.fire();
				save.setText("Register");
			}
		});
	}
	
	/*@Override
	public void cancel() {
		sup
		codeNumber.clear();
		parent.clear();
		secondParent.clear();
		secondParentFirstPhone.clear();
		secondParentSecondPhone.clear();
		secondParentFirstEmail.clear();
		secondParentSecondEmail.clear();
		classrooms.getSelectionModel().clearSelection();
	}*/
}
