package mdps_sms;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * This class is used for making instances of the Gui settings object.
 * The object helps the user in configuring personal preferred settings which 
 * overrides the defaults. 
 * It also has a restore option which can be used to revert the settings to their
 * default values in case the user messed up.
 */
public class Settings extends BorderPane {
	
	//top header
	Label forSettings =  new Label("Settings");
	Button restore = new Button("Restore");
	Separator separate = new Separator();
	HBox liner = new HBox(forSettings, restore);
	
	//first card
	Label changeAccount = new Label("Change account details");
	//--------------------------------------------------------
	Label forAccountName = new Label("account name");
	TextField name = new TextField();
	VBox namePair = new VBox(forAccountName, name);
	//--------------------------------------------------------
	Label forAccountEmail = new Label("account email");
	TextField email = new TextField();
	VBox emailPair = new VBox(forAccountEmail, email);
	
	Label changePassword = new Label("Change password details");
	//---------------------------------------------------------
	Label forOldPassword = new Label("old password");
	PasswordField oldPassword = new PasswordField();
	VBox oldPair = new VBox(forOldPassword, oldPassword);
	//---------------------------------------------------------
	Label forNewPassword = new Label("new password");
	PasswordField newPassword = new PasswordField();
	VBox newPair = new VBox(forNewPassword, newPassword);
	
	Card generalSettings = new Card("General");
	
	Settings(){
		
		forSettings.setFont(Font.font("Outfit SemiBold", 24));
		forSettings.setTextFill(Color.web("#232323"));
		
		restore.setFont(Font.font("Outfit SemiBold", 16));
		restore.setTextFill(Color.WHITE);
		restore.setStyle("-fx-background-color: #232323");
		liner.setPadding(new Insets(25, 0, 15, 0));
		liner.setMaxWidth(710);
		liner.setSpacing(531);
		
		
		//style sub headers
		for(Label elem : new Label[]{changeAccount, changePassword}) {
			elem.setFont(Font.font("Outfit SemiBold", 16));
			elem.setTextFill(Color.WHITE);
		}
		//style points
		for(Label elem : new Label[]{forAccountName, forAccountEmail, forOldPassword, forNewPassword}) {
			elem.setFont(Font.font("Outfit", 16));
			elem.setTextFill(Color.WHITE);
		}
		//style fields
		for(TextField elem : new TextField[]{name, email, oldPassword, newPassword}) {
			elem.setFont(Font.font("ubuntu", 14));
			elem.setMinWidth(250);
			elem.setMinHeight(40);
			elem.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
		}
		
		name.setPromptText("enter name ...");
		name.setText("Admin");
		email.setPromptText("enter email address ...");
		email.setText("Admin@yahoo.co.uk");
		namePair.setSpacing(5);
		emailPair.setSpacing(5);
		generalSettings.board.add(changeAccount, 0, 0);
		generalSettings.board.add(namePair, 0, 1);
		generalSettings.board.add(emailPair, 1, 1);
		generalSettings.board.setHgap(150);
		
		changePassword.setPadding(new Insets(50,0,0,0));
		oldPassword.setPromptText("enter old password ...");
		newPassword.setPromptText("enter new password ...");
		oldPair.setSpacing(5);
		newPair.setSpacing(5);
		generalSettings.board.add(changePassword, 0, 2);
		generalSettings.board.add(oldPair, 0, 3);
		generalSettings.board.add(newPair, 1, 3);
		generalSettings.board.setHgap(150);
		generalSettings.board.setVgap(5);
		Rectangle genRec = new Rectangle(700, 300);
		genRec.setArcHeight(30);
		genRec.setArcWidth(30);
		generalSettings.board.setClip(genRec);
		
		
		
		this.setTop(liner);
		this.setCenter(generalSettings);
		BorderPane.setAlignment(liner, Pos.CENTER);
	}
}

class Card extends VBox{
	Label cardLabel = new Label();
	GridPane board = new GridPane();
	
	Card(String label){
		cardLabel.setText(label);
		
		//default label and board style
		cardLabel.setFont(Font.font("Outfit SemiBold", 20));
		cardLabel.setTextFill(Color.web("#232323"));
		
		board.setStyle("-fx-background-color: #232323");
		board.setPadding(new Insets(30));
		board.setMaxWidth(700);
		
		this.getChildren().addAll(cardLabel, board);
		this.setSpacing(10);
		this.setMaxWidth(700);
	}
}