package mdps_sms.gui;

import java.util.ArrayList;

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
		
		secondSection.getChildren().removeAll(this.salaryPair, this.accountAdminPair, this.accountNumberPair);
		secondSection.getChildren().addAll(carPair, carNumberPlatePair, routePair);
	}
	
	@Override
	public <E extends Person> boolean createNew(E type, ArrayList itemList) {
		if(verifyEntries(new Control[] {name, gender,firstPhone, role, qualification, car, carNumberPlate, route, location}, 
				new String[] {"Enter name", "Choose gender", "Enter phone", "Enter role", 
						"Enter qualification", "Enter car name", "Enter number plate", "Enter route", "Enter address"})) {
		if(!verifyPhone(firstPhone.getText())) {
			warn("Check phone number");
			return false;
		}
		if(!secondPhone.getText().isBlank() && !secondPhone.getText().equalsIgnoreCase("none") && !verifyPhone(secondPhone.getText())) {
			warn("Check second phone number");
			return false;
		}
		if(!firstEmail.getText().isBlank() && !firstEmail.getText().equalsIgnoreCase("none") && !verifyEmail(firstEmail.getText())) {
			warn("Check email");
			return false;
		}
		if(!secondEmail.getText().isBlank() && !secondEmail.getText().equalsIgnoreCase("none") && !verifyEmail(secondEmail.getText())) {
			warn("Check second email");
			return false;
		}
		String[] phone = {firstPhone.getText(), secondPhone.getText().isBlank() ? "none" : secondPhone.getText()};
		String[] email = {firstEmail.getText().isBlank() ? "none" : firstEmail.getText(), secondEmail.getText().isBlank() ? "none" : secondEmail.getText()};
		String description = this.description.getText().isBlank() ? "no description" : this.description.getText() ; 
		
		if(itemList.contains(type)) itemList.remove(type);
		itemList.add(new Fleet(name.getText(), gender.getValue(), phone, email, location.getText(), role.getText(), qualification.getText(),
				car.getText(), carNumberPlate.getText(), route.getText(), description));
		Main.saveData(itemList, Main.STORAGEFILE_F);
		return true;
	
		}
		return false;
	}
	
	public <E extends Person> void edit(Fleet person, ArrayList itemList) {
		name.setText(person.getName()); 
		gender.setValue(person.getGender());
		role.setText(person.getRole());
		qualification.setText(person.getQualification());;
		salary.setText(person.getSalary());
		location.setText(person.getLocation());
		firstPhone.setText(person.getPhone());;
		secondPhone.setText(person.getPhoneArray()[1]);
		firstEmail.setText(person.getEmail());;
		secondEmail.setText(person.getEmailArray()[1]);
		route.setText(person.getRoute());
		car.setText(person.getCar());
		carNumberPlate.setText(person.getCarNumberPlate());
		description.setText(person.getDescription());
		save.setOnAction(e -> {
			if(createNew(person, itemList))
				cancel.fire();
		});
	}

}
