package mdps_sms;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ApplicationForm extends VBox {
	/*
	 * The first part is to fill in student information, the
	 * last part is for parents information.
	 */
	private VBox personalInforBox = new VBox();
	private Label personalInformation = new Label();
	//for name
	private Label forName = new Label("Name");
	private TextField firstName = new TextField();
	private TextField surName = new TextField();
	//for gender
	private Label forGender = new Label("Gender");
	private ComboBox<String> gender = new ComboBox<>();
	//for date of birth
	private Label forDateOfBirth = new Label("Date of birth");
	private TextField dateOfBirth = new TextField();
	//for nationality
	private Label forNationality = new Label("Nationality");
	private TextField nationality = new TextField();
	
	//parent information
	private VBox additioanContactlInforBox = new VBox();
	private Label additionalContactInformation = new Label ();
	//for optional parent input choice (either select from existing ones or create new one).
	
	//additional information
	private VBox noteBox = new VBox();
	private Label forNote = new Label("Additional remarks");
	private TextArea note = new TextArea();
	
	ApplicationForm(char l){
		//decide who's being registered a student or Teacher?
		switch(l) {
			case 's':
			case 'S':{
					getPersonalInformation().setText("Student Information");
					getAdditionalContactInformation().setText("Parent Information");
					break;
				 }
			case 't':
			case 'T':{
					getPersonalInformation().setText("Teacher Information");
					getAdditionalContactInformation().setText("Contact Information");
					break;
				 }
			default: ;
		}
		//set prompts and place fields
		getFirstName().setPromptText("Name");
		getSurName().setPromptText("Surname");
		GridPane gridForName = new GridPane();
		gridForName.add(forName, 0, 0);
		gridForName.add(getFirstName(), 0, 1);
		gridForName.add(getSurName(), 1, 1);
		
		getGender().setItems(FXCollections.observableArrayList("Male","Female"));
		getGender().setValue("None");
		GridPane gridForGender = new GridPane();
		gridForGender.add(forGender, 0, 0);
		gridForGender.add(getGender(), 0, 1);
		
		getDateOfBirth().setPromptText("DD-MM-YYYY");
		GridPane gridForDateOfBirth = new GridPane();
		gridForDateOfBirth.add(forDateOfBirth, 0, 0);
		gridForDateOfBirth.add(getDateOfBirth(), 0, 1);
		
		getNationality().setPromptText("Nationality");
		GridPane gridForNationality = new GridPane();
		gridForNationality.add(forNationality, 0, 0);
		gridForNationality.add(getNationality(), 0, 1);
		
		getNote().setPromptText("Description remarks ...");
		GridPane gridForNote = new GridPane();
		gridForNote.add(forNote, 0, 0);
		gridForNote.add(getNote(), 0, 1);
		
		personalInforBox.getChildren().addAll(getPersonalInformation(),gridForName, gridForGender, 
				gridForDateOfBirth, gridForNationality);
		
		
		this.getChildren().addAll(personalInforBox, additioanContactlInforBox, gridForNote);
	}

	public TextField getFirstName() {
		return firstName;
	}
	
	public void setFirstName(TextField firstName) {
		this.firstName = firstName;
	}

	public TextField getSurName() {
		return surName;
	}

	public void setSurName(TextField surName) {
		this.surName = surName;
	}

	public ComboBox<String> getGender() {
		return gender;
	}

	public void setGender(ComboBox gender) {
		this.gender = gender;
	}

	public TextField getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(TextField dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public TextField getNationality() {
		return nationality;
	}

	public void setNationality(TextField nationality) {
		this.nationality = nationality;
	}

	public TextArea getNote() {
		return note;
	}

	public void setNote(TextArea note) {
		this.note = note;
	}

	public Label getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(Label personalInformation) {
		this.personalInformation = personalInformation;
	}

	public Label getAdditionalContactInformation() {
		return additionalContactInformation;
	}

	public void setAdditionalContactInformation(Label additionalContactInformation) {
		this.additionalContactInformation = additionalContactInformation;
	}
}
