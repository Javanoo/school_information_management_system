package mdps_sms.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mdps_sms.Main;
import mdps_sms.util.Person;

class Summary<E extends Person> extends BorderPane{
	public Label forTitle = new Label("");
	protected Label date = new Label("");
	protected HBox title =new HBox(forTitle, date);


	private Label forFirstSection = new Label("Personal Information");
	private Label forName = new Label("Name");
	private Label forGender = new Label ("Gender");
	private Label forPrimaryPhone = new Label("Primary Phone");
	private Label forSecondaryPhone = new Label("Secondary Phone");
	private Label forPrimaryEmail = new Label("Primary Email");
	private Label forSecondaryEmail = new Label("Secondary Email");
	private Label forLocation = new Label("Address");

	private Label forSecondSection = new Label("Professional Information");
	private Label forRole = new Label("Role");
	private Label forQualification = new Label("Qualification");
	private Label forSalary = new Label("Salary");

	private Label forThirdSection = new Label("Other Information");
	private Label forDescription = new Label("Description");

	private Text name = new Text();
	private Text gender = new Text();
	private Text role = new Text();
	private Text qualification = new Text();
	private Text salary = new Text();
	private Text location = new Text();
	private Text firstPhone = new Text();
	private Text secondPhone = new Text();
	private Text firstEmail = new Text();
	private Text secondEmail = new Text();
	private Text description = new Text();

	private VBox namePair = new VBox(forName, name);
	private VBox genderPair = new VBox(forGender, gender);
	private VBox primaryPhonePair = new VBox(forPrimaryPhone, firstPhone);
	private VBox secondaryPhonePair = new VBox(forSecondaryPhone, secondPhone);
	private VBox primaryEmailPair =  new VBox(forPrimaryEmail, firstEmail);
	private VBox secondaryEmailPair = new VBox(forSecondaryEmail, secondEmail);
	private VBox locationPair = new VBox(forLocation, location);
	public VBox firstSection = new VBox(forFirstSection, genderPair, primaryPhonePair,
			secondaryPhonePair, primaryEmailPair, secondaryEmailPair, locationPair);

	private VBox rolePair = new VBox(forRole, role);
	private VBox qualificationPair = new VBox(forQualification, qualification);
	public VBox salaryPair = new VBox(forSalary, salary);
	public VBox secondSection = new VBox(forSecondSection, rolePair, qualificationPair, salaryPair);

	private VBox descriptionPair = new VBox(forDescription, description);
	public VBox thirdSection = new VBox(forThirdSection, descriptionPair);

	VBox mainContainer = new VBox();

	Summary(E person){

		forTitle.setText(person.getName());
		date.setText(person.getDateRegistered().toString());
		title.setSpacing(100);
		title.setMaxWidth(400);

		gender.setText(person.getGender());
		firstPhone.setText(person.getPhoneArray()[0]);
		secondPhone.setText(person.getPhoneArray().length > 1 ? person.getPhoneArray()[1] : "none");
		firstEmail.setText(person.getEmailArray()[0]);
		secondEmail.setText(person.getEmailArray().length > 1 ? person.getEmailArray()[1] : "none");
		location.setText(person.getLocation());
		role.setText(person.getRole());
		qualification.setText(person.getQualification());
		salary.setText(person.getSalary());
		description.setText(person.getDescription());

		forTitle.setFont(Font.font("Inter SemiBold", 14));
		forTitle.setWrapText(true);
		forTitle.setTextFill(Color.WHITE);
		forTitle.setMinWidth(150);
		date.setFont(Font.font("Inter SemiBold", 14));
		date.setTextFill(Color.WHITE);
		date.setWrapText(true);
		styleLabels(forName, forGender, forPrimaryPhone, forSecondaryPhone, forPrimaryEmail, forSecondaryEmail, forLocation,
				forRole, forQualification, forSalary, forDescription);
		styleTextInputControls(name, gender, role, qualification, salary, location, firstPhone, secondPhone,
				firstEmail, secondEmail, description
		);
		styleVPairs(namePair, genderPair, primaryPhonePair, secondaryPhonePair, primaryEmailPair, secondaryEmailPair, locationPair, rolePair,
				qualificationPair, salaryPair, descriptionPair
		);

		forFirstSection.setFont(Font.font("Inter SemiBold", 15));
		forFirstSection.setTextFill(Color.WHITE);
		forSecondSection.setFont(Font.font("Inter SemiBold", 15));
		forSecondSection.setTextFill(Color.WHITE);
		forThirdSection.setFont(Font.font("Inter SemiBold", 15));
		forThirdSection.setTextFill(Color.WHITE);

		firstSection.setSpacing(10);
		secondSection.setSpacing(10);
		descriptionPair.setSpacing(5);
		descriptionPair.setMinHeight(50);

		mainContainer.getChildren().addAll(firstSection, secondSection);
		ScrollPane scrl = new ScrollPane(mainContainer);

		mainContainer.setMaxWidth(400);
		mainContainer.setMaxHeight(450);
		mainContainer.setPadding(new Insets(10));
		mainContainer.setSpacing(20);
		Rectangle sumRec = new Rectangle(400, 700);
		sumRec.setArcHeight(24);
		sumRec.setArcWidth(24);
		setClip(sumRec);

		VBox topBox = new VBox(title, new Separator());
		topBox.setSpacing(5);
		VBox bottomBox = new VBox(new Separator(),descriptionPair);
		bottomBox.setSpacing(5);
		scrl.setStyle("-fx-background-color: #454545");
		
		setTop(topBox);
		setCenter(scrl);
		setBottom(bottomBox);
		setMaxHeight(700);
		setMaxWidth(400);
		setStyle("-fx-background-color: #232323");
		setPadding(new Insets(10));
		Main.fadeIn(this, 300);
		setEffect(new DropShadow());
	}

	public void styleVPairs(VBox...boxs) {
		for(VBox elem : boxs) {
			elem.setSpacing(2);
		}
	}

	public void styleLabels(Label...labels) {
		for(Label elem : labels) {
			elem.setFont(Font.font("Inter SemiBold", 14));
			elem.setTextFill(Color.WHITE);
		}
	}
	public void styleTextInputControls(Text...labeled) {
		for(Text elem : labeled ) {
			elem.setFont(Font.font("Inter", 14));
			elem.setFill(Color.WHITE);
		}
	}

}
