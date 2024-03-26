package mdps_sms;

import java.awt.Paint;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
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
public class Settings extends GridPane {
	
	//top header
	Label forSettings =  new Label("Settings");
	Button restore = new Button("Restore");
	Button cancel = UiComponents.createButton("cancelBlack.png", 28, "cancel");
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
	
	
	
	
	
	
	
	//third card
	Label changeMemory = new Label("Change memory buffer size");
	//--------------------------------------------------------
	Label forBuffer = new Label("Buffer size");
	TextField buffer = new TextField();
	VBox bufferPair = new VBox(forBuffer, buffer);
	//--------------------------------------------------------
	Label forThreads = new Label("number of threads");
	TextField thread = new TextField();
	VBox threadPair = new VBox(forThreads, thread);
		
	Label changeSecurity = new Label("Change security details");
	//---------------------------------------------------------
	Label forEncryptionStandard = new Label("encryption standard");
	TextField encryptionStandard = new TextField();
	VBox encryptionPair = new VBox(forEncryptionStandard, encryptionStandard);
	//---------------------------------------------------------
	Label forRecoveryEmail = new Label("recovery email");
	TextField recoveryEmail = new TextField();
	VBox recoveryPair = new VBox(forRecoveryEmail, recoveryEmail);
		
	Card securitySettings = new Card("Security and Performance");
	
	Button delete = new Button("Delete Account");
	Button save = new Button("Save");
	
	Settings(){
		
		forSettings.setFont(Font.font("Outfit SemiBold", 24));
		forSettings.setTextFill(Color.web("#232323"));
		
		restore.setFont(Font.font("Outfit SemiBold", 16));
		restore.setTextFill(Color.WHITE);
		cancel.setOnAction(e -> Main.switchScene(new App(Main.scene)));
		restore.setStyle("-fx-background-color: #232323");
		separate.setBorder(null);
		separate.setMinHeight(5);
		separate.setStyle("-fx-background-color: black");
		liner.setPadding(new Insets(15, 0, 15, 0));
		liner.setMaxWidth(710);
		liner.setSpacing(540);
		
		
		//style sub headers
		for(Label elem : new Label[]{changeAccount, changePassword, changeMemory, changeSecurity }) {
			elem.setFont(Font.font("Outfit SemiBold", 16));
			elem.setTextFill(Color.WHITE);
		}
		//style points
		for(Label elem : new Label[]{forAccountName, forAccountEmail, forOldPassword, forNewPassword
				, forBuffer, forThreads, forEncryptionStandard, forRecoveryEmail}) {
			elem.setFont(Font.font("Outfit", 16));
			elem.setTextFill(Color.WHITE);
		}
		//style fields
		for(TextField elem : new TextField[]{name, email, oldPassword, newPassword, buffer, thread, 
				encryptionStandard, recoveryEmail}) {
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
		generalSettings.board.add(emailPair, 0, 2);
		
		changePassword.setPadding(new Insets(30,0,0,0));
		oldPassword.setPromptText("enter old password ...");
		newPassword.setPromptText("enter new password ...");
		oldPair.setSpacing(5);
		newPair.setSpacing(5);
		generalSettings.board.add(changePassword, 0, 3);
		generalSettings.board.add(oldPair, 0, 4);
		generalSettings.board.add(newPair, 0, 5);
		generalSettings.board.setVgap(15);
		Rectangle genRec = new Rectangle(700, 470);
		genRec.setArcHeight(30);
		genRec.setArcWidth(30);
		generalSettings.board.setClip(genRec);
		
		
		
		buffer.setPromptText("enter size ...");
		buffer.setText("512kbs");
		thread.setPromptText("enter number of threads ...");
		thread.setText("4");
		bufferPair.setSpacing(5);
		threadPair.setSpacing(5);
		securitySettings.board.add(changeMemory, 0, 0);
		securitySettings.board.add(bufferPair, 0, 1);
		securitySettings.board.add(threadPair, 0, 2);
		
		changeSecurity.setPadding(new Insets(30,0,0,0));
		encryptionStandard.setPromptText("enter standard ...");
		recoveryEmail.setPromptText("enter recovery email ...");
		encryptionPair.setSpacing(5);
		recoveryPair.setSpacing(5);
		securitySettings.board.add(changeSecurity, 0, 3);
		securitySettings.board.add(encryptionPair, 0, 4);
		securitySettings.board.add(recoveryPair, 0, 5);
		securitySettings.board.setVgap(15);
		Rectangle secRec = new Rectangle(700, 470);
		secRec.setArcHeight(30);
		secRec.setArcWidth(30);
		securitySettings.board.setClip(secRec);
		
		
		VBox cards = new VBox(generalSettings, securitySettings );
		cards.setSpacing(30);
		
		ScrollPane scrl= new ScrollPane(cards);
		scrl.setMaxHeight(700);
		scrl.setBlendMode(getBlendMode().DARKEN);
		scrl.setStyle("-fx-background-color: none");
		
		this.add(liner, 0, 0);
		this.add(cancel, 1, 0);
		this.add(separate, 0, 1);
		this.add(scrl, 0, 2);
		this.setAlignment(Pos.TOP_CENTER);
		this.setVgap(10);
		this.setHgap(50);
	//	this.setGridLinesVisible(true);
		GridPane.setHalignment(generalSettings, HPos.CENTER);
		GridPane.setValignment(cancel, VPos.CENTER);
		GridPane.setValignment(liner, VPos.CENTER);
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
		board.setMinWidth(690);
		
		this.getChildren().addAll(cardLabel, board);
		this.setSpacing(10);
		this.setMaxWidth(700);
	}
}