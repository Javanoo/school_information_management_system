package mdps_sms;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * This class is used for making instances of the Gui settings object.
 * This object helps the user in configuring personal preferred settings which 
 * overrides the defaults. 
 * It also has a restore option which can be used to revert the settings to their
 * default values in case the user messed up.
 */
public class Settings extends BorderPane {
	
	//top header
	Label forSettings =  new Label("Settings");
	Button restore = new Button("Restore");
	Button cancel = UiComponents.createButton("cancelBlack.png", 28, "cancel");
	Separator separate = new Separator();
	HBox liner = new HBox(forSettings, restore);
	
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
	Label changeFont = new Label("Change font details");
	//--------------------------------------------------------
	Label forFont = new Label("Font");
	TextField font = new TextField();
	VBox fontPair = new VBox(forFont, font);
	//--------------------------------------------------------
	Label forFontSize = new Label("Font size");
	TextField fontSize = new TextField();
	VBox fontSizePair = new VBox(forFontSize, fontSize);
		
	Label changeTheme= new Label("Change theme details");
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
	Label changeMemory = new Label("Change memory buffer size");
	//--------------------------------------------------------
	Label forBuffer = new Label("Buffer size");
	TextField buffer = new TextField();
	VBox bufferPair = new VBox(forBuffer, buffer);
	//--------------------------------------------------------
	Label forThreads = new Label("Number of threads");
	TextField thread = new TextField();
	VBox threadPair = new VBox(forThreads, thread);
		
	Label changeSecurity = new Label("Change security details");
	//---------------------------------------------------------
	Label forEncryptionStandard = new Label("Encryption standard");
	TextField encryptionStandard = new TextField();
	VBox encryptionPair = new VBox(forEncryptionStandard, encryptionStandard);
	//---------------------------------------------------------
	Label forRecoveryEmail = new Label("Recovery email");
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
		restore.setMinWidth(100);
		cancel.setOnAction(e -> Main.switchScene(new App(Main.scene)));
		restore.setStyle("-fx-background-color: #232323");
		separate.setBorder(null);
		separate.setMinHeight(5);
		separate.setStyle("-fx-background-color: black");
		liner.setPadding(new Insets(15, 0, 15, 0));
		liner.setMaxWidth(720);
		liner.setSpacing(530);
		
		
		//style sub headers
		for(Label elem : new Label[]{changeAccount, changePassword, changeMemory, changeSecurity, 
				changeFont, changeTheme}) {
			elem.setFont(Font.font("Outfit SemiBold", 16));
			elem.setTextFill(Color.WHITE);
		}
		//style points
		for(Label elem : new Label[]{forAccountName, forAccountEmail, forOldPassword, forNewPassword
				, forBuffer, forThreads, forEncryptionStandard, forRecoveryEmail, forFont, forFontSize,
				forTheme, forAccentColor}) {
			elem.setFont(Font.font("Outfit", 16));
			elem.setTextFill(Color.WHITE);
		}
		//style fields
		for(TextField elem : new TextField[]{name, email, oldPassword, newPassword, buffer, thread, 
				encryptionStandard, recoveryEmail, font, fontSize, theme, accentColor}) {
			elem.setFont(Font.font("inter", 14));
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
		
		
		font.setPromptText("enter size ...");
		font.setText("Outfit");
		fontSize.setPromptText("enter font size");
		fontSize.setText("4");
		fontPair.setSpacing(5);
		fontSizePair.setSpacing(5);
		appearanceSettings.board.add(changeFont, 0, 0);
		appearanceSettings.board.add(fontPair, 0, 1);
		appearanceSettings.board.add(fontSizePair, 0, 2);
		
		changeTheme.setPadding(new Insets(30,0,0,0));
		theme.setPromptText("enter theme ...");
		accentColor.setPromptText("enter recovery email ...");
		themePair.setSpacing(5);
		accentColorPair.setSpacing(5);
		appearanceSettings.board.add(changeTheme, 0, 3);
		appearanceSettings.board.add(themePair, 0, 4);
		appearanceSettings.board.add(accentColorPair, 0, 5);
		appearanceSettings.board.setVgap(15);
		Rectangle appRec = new Rectangle(700, 470);
		appRec.setArcHeight(30);
		appRec.setArcWidth(30);
		appearanceSettings.board.setClip(appRec);
		appearanceSettings.setDisable(true);
	
		
		
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
		
		
		VBox cards = new VBox(generalSettings, appearanceSettings, securitySettings);
		cards.setSpacing(30);
		
		ScrollPane scrl= new ScrollPane(cards);
		scrl.setMinViewportWidth(710);
		scrl.setBlendMode(BlendMode.DARKEN);
		scrl.setStyle("-fx-background-color: none");
		
		GridPane center = new GridPane();
		
		center.add(liner, 0, 0);
		center.add(separate, 0, 1);
		center.add(scrl, 0, 2);
		center.setAlignment(Pos.TOP_CENTER);
		center.setVgap(10);
		center.setHgap(50);
		GridPane.setHalignment(generalSettings, HPos.CENTER);
		GridPane.setValignment(cancel, VPos.CENTER);
		GridPane.setValignment(liner, VPos.CENTER);
		
		GridPane bottom = new GridPane();
		
		delete.setFont(Font.font("Outfit SemiBold", 16));
		delete.setTextFill(Color.WHITE);
		delete.setMaxWidth(150);
		delete.setStyle("-fx-background-color: #232323");
		delete.setOnMouseEntered(e -> {
			delete.setStyle("-fx-background-color: red");
		});
		delete.setOnMouseExited( e -> {
			delete.setStyle("-fx-background-color: #232323");
		});
		save.setFont(Font.font("Outfit SemiBold", 16));
		save.setTextFill(Color.WHITE);
		save.setMinWidth(100);
		save.setStyle("-fx-background-color: #232323");
		
		bottom.add(delete, 0, 0);
		bottom.add(save, 1, 0);
		bottom.setAlignment(Pos.CENTER);
		bottom.setHgap(500);
		bottom.setMaxWidth(720);
		bottom.setPadding(new Insets(20, 0, 10, 0));
		GridPane.setHalignment(delete, HPos.LEFT);
		
		this.setCenter(center);
		this.setBottom(bottom);
		BorderPane.setAlignment(bottom, Pos.CENTER);
		BorderPane.setAlignment(center, Pos.CENTER);
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
		board.setMinWidth(700);
		
		this.getChildren().addAll(cardLabel, board);
		this.setSpacing(10);
		this.setMaxWidth(700);
	}
}