package mdps_sms.gui;

import java.util.TreeSet;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import mdps_sms.Main;
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
		return false;
	}

}
