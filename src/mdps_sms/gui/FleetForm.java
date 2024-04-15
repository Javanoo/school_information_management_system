package mdps_sms.gui;

import java.util.TreeSet;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mdps_sms.Main;
import mdps_sms.util.Fleet;
import mdps_sms.util.Person;
import mdps_sms.util.Staff;

public class FleetForm extends Form {
	private Label forCar = new Label("Car name");
	private Label forCarNumberPlate = new Label("Car number Plate");
	private Label forRoute = new Label("Route");
	
	private TextField car = new TextField();
	private TextField carNumberPlate = new TextField();
	private TextField route = new TextField();
	
	private VBox carPair = new VBox(forCar, car);
	private VBox carNumberPlatePair = new VBox(forCarNumberPlate, carNumberPlate);
	private VBox routePair = new VBox(forRoute, route);
	
	
	public FleetForm() {
		super();
		formTitle.setText("Fleet Registration");
		role.setText("Fleet member");
		role.setEditable(false);
		styleLabels(forCar, forCarNumberPlate, forRoute);
		styleVPairs(carPair, carNumberPlatePair, routePair);
		styleTextInputControls(car, carNumberPlate, route);
		
		car.setPromptText("enter car name...");
		carNumberPlate.setPromptText("enter car number plate...");
		route.setPromptText("enter route taken...");
		
		secondSection.getChildren().remove(this.salaryPair);
		secondSection.getChildren().addAll(carPair, carNumberPlatePair, routePair);
	}
	
	@Override
	public boolean createNew(TreeSet itemList) {
		if(verifyEntries(new Control[] {name, gender,firstPhone, firstEmail, role, qualification, car, carNumberPlate, route, location}, 
				new String[] {"Enter name", "Choose gender", "Enter phone", "Enter email" , "Enter role", 
						"Enter qualification", "Enter car name", "Enter number plate", "Enter route", "Enter address"})) {
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
		String description = this.description.getText().isBlank() ? "no description" : this.description.getText() ; 
	
		itemList.add(new Fleet(name.getText(), gender.getValue().getText(), phone, email, location.getText(), role.getText(), qualification.getText(),
				car.getText(), carNumberPlate.getText(), route.getText(), description));
		Main.saveData(itemList, Main.STORAGEFILE_F);
		return true;
	
		}
		return false;
	}
	
	public <E extends Person> void edit(Fleet person, TreeSet itemList) {
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
		route.setText(person.getRoute());
		car.setText(person.getCar());
		carNumberPlate.setText(person.getCarNumberPlate());
		description.setText(person.getDescription());
		save.setOnAction(e -> {
			itemList.remove(person);
			createNew(itemList);
			cancel.fire();
		});
	}

}
