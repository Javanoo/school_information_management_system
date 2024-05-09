package mdps_sms.gui;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import mdps_sms.Main;
import mdps_sms.util.Administrator;
import mdps_sms.util.Config;
import mdps_sms.util.Person;

/**
 * This class is used for making instances of the Gui settings object.
 * This object helps the user in configuring personal preferred settings which
 * overrides the defaults.
 * It also has a restore option which can be used to revert the settings to their
 * default values in case the user messed up.
 */
public class Settings extends BorderPane {

	Administrator admin = new Administrator();
	
	DirectoryChooser dir = new DirectoryChooser();

	//top header
	Label forSettings =  new Label("Settings");
	Button restore = new Button("Restore");
//	Button cancel = UiComponents.createButton("cancelWhite.png", 22, "cancel");

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
	ComboBox<String> font = new ComboBox<>();
	VBox fontPair = new VBox(forFont, font);
	//--------------------------------------------------------
	Label forFontSize = new Label("Font size");
	ComboBox<String> fontSize = new ComboBox<>();
	VBox fontSizePair = new VBox(forFontSize, fontSize);

	Label changeTheme= new Label("Theme Feel");
	//---------------------------------------------------------
	Label forTheme = new Label("Theme");
	ComboBox<String> theme = new ComboBox<>();
	VBox themePair = new VBox(forTheme, theme);

	Card appearanceSettings = new Card("Apearance");


	//third card
	Label changeMemory = new Label("Buffer size");
	//--------------------------------------------------------
	Label forBuffer = new Label("Buffer size");
	ComboBox<String> buffer = new ComboBox<>();
	VBox bufferPair = new VBox(forBuffer, buffer);
	//--------------------------------------------------------
	Label forThread = new Label("Number of threads");
	ComboBox<String> thread = new ComboBox<>();
	VBox threadPair = new VBox(forThread, thread);

	Label changeSecurity = new Label("Backup");
	//---------------------------------------------------------
	Label forEncryptionStandard = new Label("Backup time period");
	ComboBox<String> encryptionStandard = new ComboBox<>();
	VBox encryptionPair = new VBox(forEncryptionStandard, encryptionStandard);
	//---------------------------------------------------------
	Label forRecoveryEmail = new Label("Backup location");
	TextField recoveryEmail = new TextField();
	VBox recoveryPair = new VBox(forRecoveryEmail, recoveryEmail);

	Card securitySettings = new Card("Backup and Performance");

	Button cancel = new Button("Cancel");
	Button save = new Button("Save");
	
	VBox floatActions =  new VBox(save, restore, cancel);
	StackPane floatBar = new StackPane(floatActions);

	Settings(Administrator admin){

		forSettings.setFont(Font.font("Outfit SemiBold", 24));
		forSettings.setTextFill(Color.web("#232323"));

		restore.setFont(Font.font("Outfit SemiBold", 16));
		restore.setTextFill(Color.WHITE);
		restore.setMinWidth(100);
		cancel.setOnAction(e -> {
			App.leftPanelContents.setDisable(false);
		});
		restore.setStyle("-fx-background-color: " + Main.configuration.theme);
		restore.setOnMouseEntered(e -> {
			restore.setStyle("-fx-background-color: green");
		});
		restore.setOnMouseExited(e -> {
			restore.setStyle("-fx-background-color: " + Main.configuration.theme);
		});


		//style sub headers
		for(Label elem : new Label[]{changeAccount, changePassword, changeMemory, changeSecurity,
				changeFont, changeTheme}) {
			elem.setFont(Font.font("Inter SemiBold", 16));
			elem.setTextFill(Color.BLACK);
		}
		//style points
		for(Label elem : new Label[]{forAccountName, forAccountEmail, forOldPassword, forNewPassword
				, forBuffer, forThread, forEncryptionStandard, forRecoveryEmail, forFont, forFontSize,
				forTheme}) {
			elem.setFont(Font.font("Inter", 16));
			elem.setTextFill(Color.BLACK);
			elem.setPrefWidth(200);
			elem.setAlignment(Pos.CENTER_RIGHT);
		}
		//style fields
		for(Control elem : new Control[]{name, email, oldPassword, newPassword, buffer, thread,
				encryptionStandard, recoveryEmail, font, fontSize, theme}) {
			if(elem instanceof TextField) {
				((TextField)elem).setFont(Font.font("Inter SemiBold", 14));
				((TextField)elem).setMinWidth(250);
				((TextField)elem).setMinHeight(40);
				((TextField)elem).setStyle("-fx-background-color: #dedede;");
			}else {
				((ComboBox<String>)elem).setMinWidth(250);
				((ComboBox<String>)elem).setMinHeight(40);
				((ComboBox<String>)elem).setStyle("-fx-background-color: #dedede;");
			}
		}

		name.setPromptText("enter name ...");
		name.setText(admin.getName());
		email.setPromptText("enter email address ...");
		email.setText(admin.getEmail());
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


		font.getSelectionModel().select(Main.configuration.font);
		font.setItems(FXCollections.observableArrayList("Inter", "Outfit"));
		fontSize.getSelectionModel().select(Main.configuration.fontSize == 12 ? "Tiny" :
			Main.configuration.fontSize == 14 ? "Normal" : "Large");
		fontSize.setItems(FXCollections.observableArrayList("Tiny", "Normal", "Large"));
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
		fontSize.setTooltip(new Tooltip("Requires program restart,\nas such the program closes \nand you will have to restart it"));
		fontSize.getTooltip().setShowDelay(new Duration(500));
		fontSize.getTooltip().setStyle("-fx-background-color: " + Main.configuration.theme + "; -fx-text-fill: white");
		font.setTooltip(new Tooltip("Requires program restart,\nas such the program closes \nand you will have to restart it"));
		font.getTooltip().setShowDelay(new Duration(500));
		font.getTooltip().setStyle("-fx-background-color: " + Main.configuration.theme + "; -fx-text-fill: white");

		changeTheme.setPadding(new Insets(30,0,0,0));
		theme.setItems(FXCollections.observableArrayList("Black", "Blue", "Green"));
		themePair.setSpacing(5);
		theme.getSelectionModel().select(Main.configuration.theme.equals("#232323") ? "Black" :
			Main.configuration.theme.equals("#3A7795") ? "Blue" : "Green");
		appearanceSettings.board.add(changeTheme, 0, 3);
		GridPane.setColumnSpan(changeTheme, 2);
		GridPane.setHalignment(changeTheme, HPos.CENTER);
		appearanceSettings.board.add(forTheme, 0, 4);
		GridPane.setHalignment(forTheme, HPos.RIGHT);
		appearanceSettings.board.add(theme, 1, 4);
		appearanceSettings.board.setHgap(20);
		appearanceSettings.board.setVgap(10);
		theme.setTooltip(new Tooltip("Requires program restart,\nas such the program closes \nand you will have to restart it"));
		theme.getTooltip().setShowDelay(new Duration(500));
		theme.getTooltip().setStyle("-fx-background-color: " + Main.configuration.theme + "; -fx-text-fill: white");


		buffer.setPromptText("enter size ...");
		buffer.setItems(FXCollections.observableArrayList("512kbs", "1024kbs", "2048kbs"));
		buffer.getSelectionModel().selectFirst();
		thread.setPromptText("enter number of threads ...");
		thread.setItems(FXCollections.observableArrayList("as necessarly", "2", "4"));
		thread.getSelectionModel().selectFirst();
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
		encryptionStandard.setItems(FXCollections.observableArrayList("twice a week", "once week"));
		encryptionStandard.getSelectionModel().selectFirst();
		recoveryEmail.setPromptText("choose location");
		recoveryEmail.setTooltip(new Tooltip("click to choose"));
		recoveryEmail.setText(Main.configuration.backupPath);
		recoveryEmail.setEditable(false);
		recoveryEmail.setOnMouseClicked(e -> {
			recoveryEmail.setText(dir.showDialog(Main.primaryStage) == null ? "School.bck" :
				dir.showDialog(Main.primaryStage).getAbsolutePath() + "/School.bck");
		});
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
		scrl.setPadding(new Insets(50, 710, 50, 710));
		scrl.setMinViewportWidth(490);
		//scrl.setMaxWidth(700);

		BorderPane bottom = new BorderPane();
		BorderPane center = new BorderPane();
		BorderPane cancelPanel = new BorderPane();

		
		
		
		floatActions.setSpacing(5);
		floatActions.setAlignment(Pos.CENTER);
		floatBar.setMaxWidth(120);
		floatBar.setMaxHeight(110);
		Rectangle floatRec = new Rectangle(120, 110);
		floatRec.setArcHeight(25);
		floatRec.setArcWidth(25);
		//floatBar.setClip(floatRec);
		floatBar.setTranslateX(1280);
		floatBar.setTranslateY(700);
		
		

		
		center.setCenter(scrl);

		cancel.setFont(Font.font("Inter SemiBold", 16));
		cancel.setTextFill(Color.WHITE);
		cancel.setMinWidth(100);
		cancel.setStyle("-fx-background-color: " + Main.configuration.theme);
		cancel.setOnMouseEntered(e -> {
			cancel.setStyle("-fx-background-color: red");
		});
		cancel.setOnMouseExited( e -> {
			cancel.setStyle("-fx-background-color: " + Main.configuration.theme);
		});
		save.setFont(Font.font("Inter SemiBold", 16));
		save.setTextFill(Color.WHITE);
		save.setMinWidth(100);
		save.setStyle("-fx-background-color: " + Main.configuration.theme);
		save.setOnMouseEntered(e -> {
			save.setStyle("-fx-background-color: green");
		});
		save.setOnMouseExited(e -> {
			save.setStyle("-fx-background-color: " + Main.configuration.theme);
		});
		
		save.setOnAction(e -> {
			if(!name.getText().isBlank())
				admin.setName(name.getText());
			if(verifyEmail(email.getText()) && !email.getText().equals(admin.getEmail()))
				admin.setEmail(new String[]{email.getText()});
			if(admin.getPassword().equals(oldPassword.getText()) && verifyPassword(newPassword.getText()))
				admin.setPassword(newPassword.getText());
			
			Main.configuration.setFont(font.getSelectionModel().getSelectedItem());
			Main.configuration.setFontSize(fontSize.getSelectionModel().getSelectedItem().equals("Normal") ? 14 : 
				fontSize.getSelectionModel().getSelectedItem().equals("Tiny") ? 12 :
					fontSize.getSelectionModel().getSelectedItem().equals("Large") ? 16 : 14);
			Main.configuration.setTheme(theme.getSelectionModel().getSelectedItem().equals("Black") ? "#232323" : 
				theme.getSelectionModel().getSelectedItem().equals("Blue") ? "#3A7795" :
					theme.getSelectionModel().getSelectedItem().equals("Green") ? "#3A957D" : "#232323"
			);
			
			Main.configuration.setBuffer(buffer.getSelectionModel().getSelectedItem().equals("512kbs") ? 1 :
				buffer.getSelectionModel().getSelectedItem().equals("1024kbs") ? 2 : 4);
			Main.configuration.setBackupPath(recoveryEmail.getText());
			
			Main.saveData(Main.configuration, "cnfg");
			Main.saveData(admin, Main.STORAGEFILE1);
			cancel.fire();
		});
		
		restore.setOnAction(e -> {
			Main.configuration = new Config();
			
			Main.saveData(Main.configuration, "cnfg");
			Main.saveData(admin, Main.STORAGEFILE1);
			cancel.fire();
		});
		
		setCenter(scrl);
		getChildren().add(floatBar);
	}

	public StackPane getFloatBar() {
		return floatBar;
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
	
	protected boolean verifyPassword(String password) {
		if(passwordLength(password) && passwordComposition(password)
				&& _2digits(password)) {
			return true;
		}
		else {
			return false;
		}
	}

	//check password length
	protected boolean passwordLength(String password){
		int length = password.length();
		if (length < 8) return false;
		else return true;
	}
	//Check if password only consists of letters and digits
	protected boolean passwordComposition(String password){
		boolean result = false;
		for (int i = 0; i < password.length() - 1 ; i++){
			if (Character.isDigit(password.charAt(i)) || 
			    Character.isLetter(password.charAt(i)))
				result = true;
			else result = false;
		}
		return result;
	}
	//check if password contains atleast two digits.
	protected boolean _2digits(String password){
		int count = 0;
		for (int i = 0; i < password.length() - 1; i++){
			if (Character.isDigit(password.charAt(i)))
				count++;
		}
		if (count >= 2) return true;
		else return false;
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
		labelHolder.setStyle("-fx-background-color: " + Main.configuration.theme);
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