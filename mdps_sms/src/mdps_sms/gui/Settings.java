package mdps_sms.gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import mdps_sms.Main;
import mdps_sms.util.Administrator;
import mdps_sms.util.Configer;
import mdps_sms.util.Person;
import mdps_sms.util.Student;

/**
 * This class is used for making instances of the Gui settings object.
 * This object helps the user in configuring personal preferred settings which 
 * overrides the defaults. 
 * It also has a restore option which can be used to revert the settings to their
 * default values in case the user messed up.
 */
public class Settings extends BorderPane {
	
	Administrator admin = new Administrator();
	
	//top header
	Label forSettings =  new Label("Settings");
	Button restore = new Button("Restore");
	Button cancel = UiComponents.createButton("cancelWhite.png", 22, "cancel");
	
	//first card
	Label changeAccount = new Label("Change account details");
	//--------------------------------------------------------
	Label forAccountName = new Label("Account name");
	TextField name = new TextField();
	VBox namePair = new VBox(forAccountName, name);
	//--------------------------------------------------------
	Label forAccountEmail = new Label("Account email");
	TextField email = new TextField();
	VBox emailPair = new VBox(forAccountEmail, email);
	
	Label changePassword = new Label("Change password details");
	//---------------------------------------------------------
	Label forOldPassword = new Label("Old password");
	PasswordField oldPassword = new PasswordField();
	VBox oldPair = new VBox(forOldPassword, oldPassword);
	//---------------------------------------------------------
	Label forNewPassword = new Label("New password");
	PasswordField newPassword = new PasswordField();
	VBox newPair = new VBox(forNewPassword, newPassword);
	
	Card generalSettings = new Card("General");
	
	
	//third card
	Label changeFont = new Label("Font Feel");
	//--------------------------------------------------------
	Label forFont = new Label("Font");
	TextField font = new TextField();
	VBox fontPair = new VBox(forFont, font);
	//--------------------------------------------------------
	Label forFontSize = new Label("Font size");
	TextField fontSize = new TextField();
	VBox fontSizePair = new VBox(forFontSize, fontSize);
		
	Label changeTheme= new Label("Theme Feel");
	//---------------------------------------------------------
	Label forTheme = new Label("Theme");
	TextField theme = new TextField();
	VBox themePair = new VBox(forTheme, theme);
	//---------------------------------------------------------
	Label forAccentColor = new Label("Accent color");
	TextField accentColor = new TextField();
	VBox accentColorPair = new VBox(forAccentColor, accentColor);
		
	Card appearanceSettings = new Card("Apearance");
	
	
	//third card
	Label changeMemory = new Label("Buffer size");
	//--------------------------------------------------------
	Label forBuffer = new Label("Buffer size");
	TextField buffer = new TextField();
	VBox bufferPair = new VBox(forBuffer, buffer);
	//--------------------------------------------------------
	Label forThread = new Label("Number of threads");
	TextField thread = new TextField();
	VBox threadPair = new VBox(forThread, thread);
		
	Label changeSecurity = new Label("Security");
	//---------------------------------------------------------
	Label forEncryptionStandard = new Label("Encryption standard");
	TextField encryptionStandard = new TextField();
	VBox encryptionPair = new VBox(forEncryptionStandard, encryptionStandard);
	//---------------------------------------------------------
	Label forRecoveryEmail = new Label("Recovery email");
	TextField recoveryEmail = new TextField();
	VBox recoveryPair = new VBox(forRecoveryEmail, recoveryEmail);
		
	Card securitySettings = new Card("Security and Performance");
	
	//Button cancel = new Button("Cancel");
	Button save = new Button("Save");
	
	Settings(){}
	
	Settings(ArrayList<Person> list){
		
		this.admin = admin;
		
		forSettings.setFont(Font.font("Outfit SemiBold", 24));
		forSettings.setTextFill(Color.web("#232323"));
		
		restore.setFont(Font.font("Outfit SemiBold", 16));
		restore.setTextFill(Color.WHITE);
		restore.setMinWidth(100);
		cancel.setOnAction(e -> {
			//App.switchView(new ItemList(list));
			App.leftPanelContents.setDisable(false);
		});
		restore.setStyle("-fx-background-color: #232323");
		
		
		//style sub headers
		for(Label elem : new Label[]{changeAccount, changePassword, changeMemory, changeSecurity, 
				changeFont, changeTheme}) {
			elem.setFont(Font.font("Inter SemiBold", 16));
			elem.setTextFill(Color.BLACK);
		}
		//style points
		for(Label elem : new Label[]{forAccountName, forAccountEmail, forOldPassword, forNewPassword
				, forBuffer, forThread, forEncryptionStandard, forRecoveryEmail, forFont, forFontSize,
				forTheme, forAccentColor}) {
			elem.setFont(Font.font("Inter", 16));
			elem.setTextFill(Color.BLACK);
			elem.setPrefWidth(200);
			elem.setAlignment(Pos.CENTER_RIGHT);
		}
		//style fields
		for(TextField elem : new TextField[]{name, email, oldPassword, newPassword, buffer, thread, 
				encryptionStandard, recoveryEmail, font, fontSize, theme, accentColor}) {
			elem.setFont(Font.font("Inter SemiBold", 14));
			elem.setMinWidth(250);
			elem.setMinHeight(40);
			elem.setStyle("-fx-background-color: #898989; -fx-text-fill: white");
		}
		
		name.setPromptText("enter name ...");
		name.setText("Admin");
		email.setPromptText("enter email address ...");
		email.setText("Admin@yahoo.co.uk");
		generalSettings.board.add(changeAccount, 0, 0);
		GridPane.setColumnSpan(changeAccount, 2);
		GridPane.setHalignment(changeAccount, HPos.CENTER);
		generalSettings.board.add(forAccountName, 0, 1);
		GridPane.setHalignment(forAccountName, HPos.RIGHT);
		generalSettings.board.add(name, 1,1);
		generalSettings.board.add(forAccountEmail, 0, 2);
		GridPane.setHalignment(forAccountEmail, HPos.RIGHT);
		generalSettings.board.add(email, 1, 2);
		
		changePassword.setPadding(new Insets(30,0,0,0));
		oldPassword.setPromptText("enter old password ...");
		newPassword.setPromptText("enter new password ...");
		oldPair.setSpacing(5);
		newPair.setSpacing(5);
		generalSettings.board.add(changePassword, 0, 3);
		GridPane.setColumnSpan(changePassword, 2);
		GridPane.setHalignment(changePassword, HPos.CENTER);
		generalSettings.board.add(forOldPassword, 0, 4);
		GridPane.setHalignment(forOldPassword, HPos.RIGHT);
		generalSettings.board.add(oldPassword, 1, 4);
		generalSettings.board.add(forNewPassword,0 , 5);
		GridPane.setHalignment(forNewPassword, HPos.RIGHT);
		generalSettings.board.add(newPassword, 1, 5);
		generalSettings.board.setHgap(20);
		generalSettings.board.setVgap(10);
		
		
		font.setPromptText("enter size ...");
		font.setText("Inter");
		fontSize.setPromptText("enter font size");
		fontSize.setText("4");
		fontPair.setSpacing(5);
		fontSizePair.setSpacing(5);
		appearanceSettings.board.add(changeFont, 0, 0);
		GridPane.setColumnSpan(changeFont, 2);
		GridPane.setHalignment(changeFont, HPos.CENTER);
		appearanceSettings.board.add(forFont, 0, 1);
		GridPane.setHalignment(forFont, HPos.RIGHT);
		appearanceSettings.board.add(font, 1, 1);
		appearanceSettings.board.add(forFontSize, 0, 2);
		GridPane.setHalignment(forFontSize, HPos.RIGHT);
		appearanceSettings.board.add(fontSize, 1, 2);
		
		changeTheme.setPadding(new Insets(30,0,0,0));
		theme.setPromptText("enter theme ...");
		accentColor.setPromptText("enter recovery email ...");
		themePair.setSpacing(5);
		accentColorPair.setSpacing(5);
		appearanceSettings.board.add(changeTheme, 0, 3);
		GridPane.setColumnSpan(changeTheme, 2);
		GridPane.setHalignment(changeTheme, HPos.CENTER);
		appearanceSettings.board.add(forTheme, 0, 4);
		GridPane.setHalignment(forTheme, HPos.RIGHT);
		appearanceSettings.board.add(theme, 1, 4);
		appearanceSettings.board.add(forAccentColor, 0, 5);
		GridPane.setHalignment(forAccentColor, HPos.RIGHT);
		appearanceSettings.board.add(accentColor, 1, 5);
		appearanceSettings.board.setHgap(20);
		appearanceSettings.board.setVgap(10);
		appearanceSettings.setDisable(true);
		
		
		buffer.setPromptText("enter size ...");
		buffer.setText("512kbs");
		thread.setPromptText("enter number of threads ...");
		thread.setText("4");
		bufferPair.setSpacing(5);
		threadPair.setSpacing(5);
		securitySettings.board.add(changeMemory, 0, 0);
		GridPane.setColumnSpan(changeMemory, 2);
		GridPane.setHalignment(changeMemory, HPos.CENTER);
		securitySettings.board.add(forBuffer, 0, 1);
		GridPane.setHalignment(forBuffer, HPos.RIGHT);
		securitySettings.board.add(buffer, 1, 1);
		securitySettings.board.add(forThread, 0, 2);
		GridPane.setHalignment(forThread, HPos.RIGHT);
		securitySettings.board.add(thread, 1, 2);
		
		changeSecurity.setPadding(new Insets(30,0,0,0));
		encryptionStandard.setPromptText("enter standard ...");
		recoveryEmail.setPromptText("enter recovery email ...");
		encryptionPair.setSpacing(5);
		recoveryPair.setSpacing(5);
		securitySettings.board.add(changeSecurity, 0, 3);
		GridPane.setColumnSpan(changeSecurity, 2);
		GridPane.setHalignment(changeSecurity, HPos.CENTER);
		securitySettings.board.add(forRecoveryEmail, 0, 4);
		GridPane.setHalignment(forRecoveryEmail, HPos.RIGHT);
		securitySettings.board.add(recoveryEmail, 1, 4);
		securitySettings.board.add(forEncryptionStandard, 0, 5);
		GridPane.setHalignment(forEncryptionStandard, HPos.RIGHT);
		securitySettings.board.add(encryptionStandard, 1, 5);
		securitySettings.board.setHgap(20);
		securitySettings.board.setVgap(10);
		
		
		VBox cards = new VBox(generalSettings, appearanceSettings, securitySettings);
		cards.setSpacing(30);
		cards.setAlignment(Pos.CENTER);
		
		ScrollPane scrl= new ScrollPane(new StackPane(cards));
		scrl.setPadding(new Insets(0, 110, 0, 110));
		scrl.setMinViewportWidth(490);
		scrl.setMaxWidth(700);
		
		BorderPane bottom = new BorderPane();
		BorderPane center = new BorderPane();
		BorderPane cancelPanel = new BorderPane();
		
		cancelPanel.setRight(cancel);
		cancelPanel.setMaxWidth(724);
		cancelPanel.setPadding(new Insets(10, 10 ,10, 0));
		BorderPane.setAlignment(cancel, Pos.CENTER_RIGHT);
		cancel.setStyle("-fx-background-color: #232323");
		cancel.setOnMouseExited( e -> {
			cancel.setStyle("-fx-background-color: #232323");
		});
		
		bottom.setLeft(restore);
		bottom.setRight(save);
		bottom.setMaxWidth(724);
		bottom.setPadding(new Insets(10, 0 ,10, 0));
		BorderPane.setAlignment(save, Pos.CENTER_RIGHT);
		
		center.setCenter(scrl);
		
		/*cancel.setFont(Font.font("Outfit SemiBold", 16));
		cancel.setTextFill(Color.WHITE);
		cancel.setMinWidth(100);
		cancel.setStyle("-fx-background-color: #232323");
		cancel.setOnMouseEntered(e -> {
			cancel.setStyle("-fx-background-color: red");
		});
		cancel.setOnMouseExited( e -> {
			cancel.setStyle("-fx-background-color: #232323");
		});*/
		save.setFont(Font.font("Outfit SemiBold", 16));
		save.setTextFill(Color.WHITE);
		save.setMinWidth(100);
		save.setStyle("-fx-background-color: #232323");
		save.setOnAction(e -> {
			Configer config = new Configer(name.getText(), email.getText(), newPassword.getText(), "", 8, "", "", 1024, 2, "", "");
			Main.saveData(config, "config.sys");
	});
		
		setTop(cancelPanel);
		setCenter(center);
		setBottom(bottom);
		BorderPane.setAlignment(cancelPanel, Pos.CENTER);
		BorderPane.setAlignment(bottom, Pos.CENTER);
		BorderPane.setAlignment(forSettings, Pos.CENTER);
	}
}

class Card extends VBox{
	Label cardLabel = new Label();
	GridPane board = new GridPane();
	Separator separate = new Separator();
	StackPane labelHolder = new StackPane(cardLabel);
	
	Card(String label){
		cardLabel.setText(label);
		cardLabel.setMinHeight(35);
		
		separate.setMaxWidth(350);
		
		labelHolder.setMaxWidth(300);
		labelHolder.setStyle("-fx-background-color: #232323");
		Rectangle saveRec = new Rectangle(300, 35);
		saveRec.setArcHeight(35);
		saveRec.setArcWidth(35);
		labelHolder.setClip(saveRec);
		
		//default label and board style
		cardLabel.setFont(Font.font("Inter SemiBold", 16));
		cardLabel.setTextFill(Color.WHITE);
		
		board.setPadding(new Insets(10));
		
		getChildren().addAll(labelHolder, separate, board);
		setSpacing(10);
		setAlignment(Pos.CENTER);
	}
}