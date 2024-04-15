package mdps_sms.gui;

import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import mdps_sms.Main;
import mdps_sms.util.Person;
import mdps_sms.util.Staff;

public class Form extends BorderPane{
	
	ArrayList<Staff> data = new ArrayList<Staff>();
	
	private Text[] genderChoices = {new Text("Male"), new Text("Female")};
	
	public Label formTitle = new Label("Staff Registration");
	protected Label errorInfor = new Label("");
	protected HBox title =new HBox(formTitle, errorInfor);
	
	
	protected Label forFirstSection = new Label("Personal Information");
	private Label forName = new Label("Name");
	private Label forGender = new Label ("Gender");
	private Label forPrimaryPhone = new Label("Primary phone");
	private Label forSecondaryPhone = new Label("Secondary phone");
	private Label forPrimaryEmail = new Label("Primary email");
	private Label forSecondaryEmail = new Label("Secondary email");
	private Label forLocation = new Label("Address");
	private Separator separate1 = new Separator();
	protected Label forSecondSection = new Label("Professional Information");
	private Label forRole = new Label("Role");
	private Label forQualification = new Label("Qualification");
	private Label forSalary = new Label("Salary");
	private Label forAccountAdmin = new Label("Account administrator");
	private Label forAccountNumber = new Label("Account number");
	protected Separator separate2 = new Separator();
	private Label forThirdSection = new Label("Other Information");
	private Label forDescription = new Label("Description");
	private Separator separate3 = new Separator();
	
	protected TextField name = new TextField();
	protected ComboBox<Text> gender = new ComboBox<>();
	protected TextField role = new TextField();
	protected TextField qualification = new TextField();
	protected TextField salary = new TextField();
	protected TextField location = new TextField();
	protected TextField firstPhone = new TextField();
	protected TextField secondPhone = new TextField();
	protected TextField firstEmail = new TextField();
	protected TextField secondEmail = new TextField();
	protected TextArea description = new TextArea();
	protected TextField accountAdmin = new TextField();
	protected TextField accountNumber = new TextField();
	
	Button save = new Button("Register");
	Button cancel = new Button("Cancel");
	
	private VBox namePair = new VBox(forName, name);
	private VBox genderPair = new VBox(forGender, gender);
	private VBox primaryPhonePair = new VBox(forPrimaryPhone, firstPhone);
	private VBox secondaryPhonePair = new VBox(forSecondaryPhone, secondPhone);
	private VBox primaryEmailPair =  new VBox(forPrimaryEmail, firstEmail);
	private VBox secondaryEmailPair = new VBox(forSecondaryEmail, secondEmail);
	protected VBox locationPair = new VBox(forLocation, location);
	public VBox firstSection = new VBox(forFirstSection, separate1, namePair, genderPair, primaryPhonePair, 
			secondaryPhonePair, primaryEmailPair, secondaryEmailPair, locationPair);
	
	private VBox rolePair = new VBox(forRole, role);
	private VBox qualificationPair = new VBox(forQualification, qualification);
	public VBox salaryPair = new VBox(forSalary, salary);
	public VBox accountAdminPair = new VBox(forAccountAdmin, accountAdmin);
	public VBox accountNumberPair = new VBox(forAccountNumber, accountNumber);
	public VBox secondSection = new VBox(forSecondSection, separate2, rolePair, qualificationPair, salaryPair, accountAdminPair, accountNumberPair);
	
	private VBox descriptionPair = new VBox(forDescription, description);
	public VBox thirdSection = new VBox(forThirdSection, separate3, descriptionPair);
	
	private HBox buttonsPair = new HBox(cancel, save);
	
	private VBox innerContainer = new VBox();
	private ScrollPane outerContainer = new ScrollPane(innerContainer);

	public Form() {
		
		for(Text elem : genderChoices) {
			elem.setFill(Color.WHITE);
			elem.setFont(Font.font("Inter SemiBold", 15));
			elem.setFont(Font.font("Inter SemiBold", 15));
		}
		
		styleLabels(forName, forGender, forPrimaryPhone, forSecondaryPhone, forPrimaryEmail, forSecondaryEmail, forLocation, 
				forRole, forQualification, forSalary, forDescription, forAccountAdmin, forAccountNumber);
		formTitle.setTextFill(Color.web("White"));
		title.setStyle("-fx-background-color: #232323");
		formTitle.setFont(Font.font("inter SemiBold", 17));
		formTitle.setPadding(new Insets(10, 0, 10, 0));
		forFirstSection.setFont(Font.font("Inter SemiBold", 16));
		forFirstSection.setTextFill(Color.WHITE);
		forSecondSection.setFont(Font.font("Inter SemiBold", 16));
		forSecondSection.setTextFill(Color.WHITE);
		forThirdSection.setFont(Font.font("Inter SemiBold", 16));
		forThirdSection.setTextFill(Color.WHITE);
		
		
		name.setPromptText("enter name..."); 
		gender.setItems(FXCollections.observableArrayList(genderChoices));
		gender.setPromptText(genderChoices[0].getText());
		gender.setValue(new Text("Choose"));
		role.setPromptText("enter role/position...");
		qualification.setPromptText("enter qualifications...");
		salary.setPromptText("enter salary...");
		location.setPromptText("enter address location...");
		firstPhone.setPromptText("enter phone...");
		secondPhone.setPromptText("enter alternative phone...");
		firstEmail.setPromptText("enter email...");
		secondEmail.setPromptText("enter alternative email...");
		accountAdmin.setPromptText("enter account admin e.g. FDH Bank..");
		accountNumber.setPromptText("enter account number...");
		description.setPromptText("a short description of the individual...");
		description.setMaxHeight(80);
		description.setWrapText(true);
		description.setMaxWidth(540);
		description.setFont(Font.font("Inter SemiBold", 15));
		styleTextInputControls(name, gender, role, qualification, salary, location, firstPhone, secondPhone, 
				firstEmail, secondEmail, accountNumber, accountAdmin
		);
		styleVPairs(namePair, genderPair, primaryPhonePair, secondaryPhonePair, primaryEmailPair, secondaryEmailPair, locationPair, rolePair, 
				qualificationPair, salaryPair, descriptionPair
		);
		
		firstSection.setSpacing(30);
		secondSection.setSpacing(30);
		thirdSection.setSpacing(20);
		
		save.setMinWidth(100);
		save.setMinHeight(30);
		save.setStyle("-fx-background-color: white");
		save.setFont(Font.font("Inter SemiBold", 15));
		save.setTextFill(Color.BLACK);
		Rectangle saveRec = new Rectangle(100, 30);
		saveRec.setArcHeight(25);
		saveRec.setArcWidth(25);
		save.setClip(saveRec);
		
		cancel.setMinWidth(100);
		cancel.setMinHeight(30);
		cancel.setStyle("-fx-background-color: white");
		cancel.setFont(Font.font("Inter SemiBold", 15));
		cancel.setTextFill(Color.BLACK);
		cancel.setPadding(new Insets(5));
		cancel.setOnAction(e -> cancel());
		Rectangle cancelRec = new Rectangle(100, 30);
		cancelRec.setArcHeight(25);
		cancelRec.setArcWidth(25);
		cancel.setClip(cancelRec);
		
		title.setSpacing(240);
		title.setAlignment(Pos.CENTER_LEFT);
		title.setPadding(new Insets(0, 0, 5, 10));
		
		innerContainer.getChildren().addAll(firstSection, secondSection, thirdSection);
		innerContainer.setStyle("-fx-background-color: #232323");
		innerContainer.setAlignment(Pos.TOP_CENTER);
		innerContainer.setPadding(new Insets(20));
		innerContainer.setSpacing(30);
		innerContainer.setMinWidth(480);
		outerContainer.setMinWidth(600);
		outerContainer.setStyle("-fx-background-color: #565656");
		
		buttonsPair.setSpacing(380);
		buttonsPair.setPadding(new Insets(15,10,10,10));
		buttonsPair.setStyle("-fx-background-color: #232323");
		
		setTop(title);
		setCenter(outerContainer);
		setBottom(buttonsPair);
		this.setMaxHeight(700);
		this.setMaxWidth(600);
		Rectangle formRec = new Rectangle(600, 700);
		formRec.setArcHeight(30);
		formRec.setArcWidth(30);
		setClip(formRec);
		//BorderPane.setAlignment(outerContainer, Pos.CENTER);
		BorderPane.setAlignment(buttonsPair, Pos.CENTER_LEFT);
	}
	
	/**
	 * Verifies the email by checking the format used in this object's email(Textfield) 
	 * property.
	 * @return true if the email is in a correct format, otherwise false.
	 */
	public boolean verifyEmail(String email) {
		if(!email.isBlank() && email.matches("\\w+@\\w+\\.\\w+")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifies the phone number by checking the format used in this object's phone(Textfield) 
	 * property.
	 * @return true if the phone number is in a correct format, otherwise false.
	 */
	public boolean verifyPhone(String phone) {
		if(phone.length() == 10) {
			for(char elem : phone.toCharArray()) {
				if(!Character.isDigit(elem)) return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean createNew(TreeSet itemList) {
		if(verifyEntries(new Control[] {name, gender,firstPhone, firstEmail, role, qualification, salary, location}, 
						new String[] {"Enter name", "Choose gender", "Enter phone", "Enter email" , "Enter role", 
								"Enter qualification", "Enter salary", "Enter address"})) {
			if(!verifyPhone(firstPhone.getText())) {
				warn("Check phone number");
				return false;
			}
			if(secondPhone.getText().isBlank() && !verifyPhone(firstPhone.getText())) {
				warn("Check second phone number");
				return false;
			}
			if(!verifyEmail(firstEmail.getText())) {
				warn("Check email");
				return false;
			}
			if(!secondEmail.getText().isBlank() && !verifyEmail(secondEmail.getText())) {
				warn("Check second email");
				return false;
			}
			String[] phone = {firstPhone.getText(), secondPhone.getText().isBlank() ? "none" : secondPhone.getText()};
			String[] email = {firstEmail.getText(), secondEmail.getText().isBlank() ? "none" : secondEmail.getText()};
			String description = this.description.getText().isBlank() ? "no description" : this.description.getText(); 
			
			itemList.add(new Staff(name.getText(), gender.getValue().getText(), location.getText(), phone, email, role.getText(), qualification.getText(),
					salary.getText(), accountAdmin.getText(), accountNumber.getText(), description));
			return true;
			
		}
		return false;
	}
	
	
	
	public boolean verifyEntries(Control[] input, String[] errorMsgs) {
		for(int i = 0; i < input.length; i++) {
			if(input[i] instanceof TextField) {
				if(((TextField)input[i]).getText().isBlank()) {
					warn(errorMsgs[i]);
					return false;
				}
			}else if (input[i] instanceof ComboBox) {
				if(((ComboBox<Text>)input[i]).getValue().getText().equalsIgnoreCase("Choose")) {
					warn(errorMsgs[i]);
					return false;
				}
			}
		}
		return true;
	}
	
	public <E extends Person> void edit(Staff person, TreeSet itemList) {
		name.setText(person.getName()); 
		gender.setValue(new Text(person.getGender()));
		role.setText(person.getRole());
		qualification.setText(person.getQualification());;
		salary.setText(person.getSalary());
		location.setText(person.getLocation());
		firstPhone.setText(person.getPhone());;
		secondPhone.clear();
		firstEmail.setText(person.getEmail());;
		secondEmail.clear();
		description.setText(person.getDescription());
		save.setOnAction(e -> {
			itemList.remove(person);
			createNew(itemList);
			cancel.fire();
		});
	}
	
	public void cancel() {
		name.clear(); 
		gender.setValue(genderChoices[0]);
		role.clear();
		qualification.clear();
		salary.clear();
		location.clear();
		firstPhone.clear();
		secondPhone.clear();
		firstEmail.clear();
		secondEmail.clear();
		description.clear();
	}
	
	public void warn(String infor) {
		errorInfor.setFont(Font.font("Inter SemiBold", 14));
		errorInfor.setStyle("-fx-background-color: #FF3E3E; -fx-text-fill: white"); 
		errorInfor.setPadding(new Insets(5, 5, 5, 5));
		errorInfor.setMinWidth(170);
		errorInfor.setAlignment(Pos.CENTER);
		errorInfor.setText(infor);
		errorInfor.setMinHeight(30);
		Rectangle rec = new Rectangle(170, 30);
		rec.setArcHeight(25);
		rec.setArcWidth(25);
		errorInfor.setClip(rec);
		
		Main.fadeIn(errorInfor, 200);
	} 
	
	@SuppressWarnings("unchecked")
	public void styleTextInputControls(Control...labeled) {
		for(Control elem : labeled ) {
			if(elem instanceof TextField) {
				((TextField)elem).setMaxWidth(340);
				((TextField)elem).setMinWidth(340);
				((TextField)elem).setMinHeight(40);;
				((TextField)elem).setFont(Font.font("Inter", 14));
				((TextField)elem).setStyle("-fx-background-color: #484848; -fx-text-fill: white");
				((TextField)elem).focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) {
					errorInfor.setText(""); errorInfor.setStyle("-fx-background-color: none");}});
			}else {
				((ComboBox<String>)elem).setMinWidth(340);
				((ComboBox<String>)elem).setMinHeight(40);
				((ComboBox<String>)elem).setStyle("-fx-background-color: #484848");
			}
		}
	}
	
	public void styleVPairs(VBox...boxs) {
		for(VBox elem : boxs) {
			elem.setSpacing(2);
		}
	}
	
	public void styleLabels(Label...labels) {
		for(Label elem : labels) {
			((Label)elem).setFont(Font.font("Inter ", 14));
			((Label)elem).setTextFill(Color.WHITE);
		}
	}

	/**
	 * @return the cancel
	 */
	public synchronized Button getCancel() {
		return cancel;
	}

	/**
	 * @param cancel the cancel to set
	 */
	public synchronized void setCancel(Button cancel) {
		this.cancel = cancel;
	}
}
