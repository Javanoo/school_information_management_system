package mdps_sms.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
	
	HashSet<String> codes = new HashSet<>();

	StudentForm(){
		super();
		
		Iterator<Student> codeIter = Main.students.iterator();
		while(codeIter.hasNext()) {
			codes.add(codeIter.next().getCodeNumber());
		}
		
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
		
		//override name's inherited listener
		name.setOnKeyTyped(e -> {
			if(!name.getText().isBlank()) {
				codeNumber.setText(generateCode(name.getText()));
			}
			else codeNumber.clear();
		});

		codeNumber.setEditable(false);
		codeNumber.setPromptText("------");
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
				
				Iterator<SchoolClass> iter = Main.classrooms.iterator();
				
				Student newStudent = new Student(name.getText(), codeNumber.getText(),gender.getValue(), selectedClass, parentArray, description);
				
				//edit an already existing student
				if(itemList.contains((Student)type)) {
					((Student)type).setName(name.getText());
					((Student)type).setGender(gender.getValue());
					((Student)type).setParents(parentArray);
					((Student)type).setDescription(description);
					
					//update classes
					if(!selectedClass.getName().equals((((Student)type).getClassroom()).getName())) {
						SchoolClass classCopy = (((Student)type).getClassroom());
						
						while(iter.hasNext()) {
							SchoolClass classroom = iter.next();
							if(classroom.getName().equals(classCopy.getName())) {
								classroom.getStudents().remove(((Student)type));
								break;
							}
						}
						
						Iterator<SchoolClass> iter2 = Main.classrooms.iterator();
						
						while(iter2.hasNext()) {
							SchoolClass classroom = iter2.next();
							if(classroom.getName().equals(selectedClass.getName())) {
								classroom.getStudents().add(((Student)type));
								break;
							}
						}
						
						((Student)type).setClassroom(selectedClass);
					}
					
					//update student list
					itemList.set(itemList.indexOf((Student)type), (Student)type);
					return true;
				}
				
				//add student to the class
				while(iter.hasNext()) {
					SchoolClass classroom = iter.next();
					if(classroom.getName().equals(selectedClass.getName())) {
						classroom.getStudents().add(newStudent);
						break;
					}
				}
				itemList.add(newStudent);
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
		Parent parent2 = new Parent("", new String[] {"", ""}, new String[] {"", ""});
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
				parent1 = new Parent(fParent, new String[]{fParentP1, ((!fParentP2.isBlank() && verifyPhone(fParentP2)) ? fParentP2 : "" )},
						new String[]{((!fParentE1.isBlank() && verifyEmail(fParentE1)) ? fParentE1 : "") , ((!fParentE2.isBlank() && verifyEmail(fParentE2)) ? fParentE2 : "") });
				//check for second Parent
				if(!sParent.isBlank()) {
					if(!sParentP1.isBlank() && verifyPhone(sParentP1)) {
						parent2 = new Parent(sParent, new String[]{sParentP1, ((!sParentP2.isBlank() && verifyPhone(sParentP2)) ? sParentP2 : "" )},
								new String[]{((!sParentE1.isBlank() && verifyEmail(sParentE1)) ? sParentE1 : "") , ((!sParentE2.isBlank() && verifyEmail(sParentE2)) ? sParentE2 : "") });

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
		name.setOnKeyTyped(e -> {});
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
	
	private String generateCode(String Name) {
		String[] codeArray = Name.split(" ");
		String code = "";
		if(codeArray.length == 1) {
			 code =  (codeArray[0].charAt(0) + "" + codeArray[0].charAt(codeArray[0].length() - 1)).toUpperCase();
		}else {
			 code =  (codeArray[0].charAt(0) + "" + codeArray[0].charAt(codeArray[0].length() - 1) + "" + 
					codeArray[codeArray.length - 1].charAt(codeArray[codeArray.length - 1].length() - 1)
			).toUpperCase();
		}
		
		String codeCopy = new String(code);
		
		//number
		while(codes != null) {
			long pad = 100 + ((int)(Math.random() * 900));
			code += pad + "";
			if(!codes.contains(code)) break;
			code = codeCopy;
		}
		return code;
	}
}
