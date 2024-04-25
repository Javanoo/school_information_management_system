package mdps_sms.gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mdps_sms.Main;
import mdps_sms.util.Administrator;
import mdps_sms.util.SchoolCalendar;

/**
 * This class creates a form. The type of form created is dependent on the character passed to  the class's
 * constructor. If an 'L'(for login) character is passed, the resulting form generated will be a log in form.  
 * this type of form is used to authenticate a user. Where as, if a 'C'(for credentials) character is passed to the  
 * constructor the resulting form will be used to collect credentials which will be used for verifying the user on
 * each attempted login.
 */
public class Login extends GridPane {
	
	private String nameString, emailString, passwordString;
	
	private Label forName  = new Label("Name");
	private TextField name = new TextField();
	private VBox nameField = new VBox();
	
	private Label forEmail  = new Label("Email");
	private TextField email = new TextField();
	private VBox emailField = new VBox();
	
	private Label forPassword  = new Label("Password");
	private PasswordField password = new PasswordField();
	private VBox passwordField = new VBox();
	
	private Label forConfirmPassword  = new Label("Confirm Password");
	private PasswordField confirmPassword = new PasswordField();
	private VBox confirmPasswordField = new VBox();
	
	private Label forToken  = new Label("Token");
	private TextField token = new PasswordField();
	private VBox tokenField = new VBox();
	
	private Text errorInfor  = new Text();
	
	private VBox credentialsField = new VBox();
	
	private Button login = null;
	private Button settings = null;
	private Button cancel = null;
	
	Administrator admin = null;
	
	public Login() {};
	
	public Login(Administrator admin, App app) {
		
		this.admin = admin;
		
		//fields
		forName.setFont(Font.font("inter SemiBold", 15));
		forName.setTextFill(Color.WHITE);
		name.setMaxWidth(450);
		name.setMinHeight(40);
		name.setPromptText("enter name...");
		name.setFont(Font.font("inter SemiBold", 15));
		name.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
		name.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		nameField.getChildren().addAll(forName, name);
		nameField.setSpacing(2);
		
		forEmail.setFont(Font.font("inter SemiBold", 15));
		forEmail.setTextFill(Color.WHITE);
		email.setMaxWidth(450);
		email.setMinHeight(40);
		email.setPromptText("enter name...");
		email.setFont(Font.font("inter SemiBold", 15));
		email.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
		email.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		emailField.getChildren().addAll(forEmail, email);
		emailField.setSpacing(2);
		
		forPassword.setFont(Font.font("inter SemiBold", 15));
		forPassword.setTextFill(Color.WHITE);
		password.setMaxWidth(450);
		password.setMinHeight(40);
		password.setPromptText("enter password...");
		password.setFont(Font.font("inter SemiBold", 15));
		password.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
		password.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		passwordField.getChildren().addAll(forPassword, password);
		passwordField.setSpacing(2);
		
		forConfirmPassword.setFont(Font.font("inter SemiBold", 15));
		forConfirmPassword.setTextFill(Color.WHITE);
		confirmPassword.setMinWidth(350);
		confirmPassword.setMinHeight(40);
		confirmPassword.setPromptText("re-enter password...");
		confirmPassword.setFont(Font.font("inter SemiBold", 15));
		confirmPassword.setStyle("-fx-background-color: #484848; -fx-text-fill: white");
		confirmPassword.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		confirmPasswordField.getChildren().addAll(forConfirmPassword, confirmPassword);
		confirmPasswordField.setSpacing(2);
		
		forToken.setFont(Font.font("inter SemiBold", 15));
		forToken.setTextFill(Color.WHITE);
		token.setMaxWidth(450);
		token.setMinHeight(40);
		token.setPromptText("enter the token sent to your email...");
		token.setFont(Font.font("Outfit", 16));
		token.setStyle("-fx-background-color: #F3F3F3");
		token.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		tokenField.getChildren().addAll(forToken, token);
		tokenField.setSpacing(2);
		
		errorInfor.setFont(Font.font("inter SemiBold", 18));
		errorInfor.setFill(Color.WHITE);
		
		login = new Button("Done");
		login.setFont(Font.font("inter SemiBold", 15));
		login.setMinSize(100, 20);
		login.setTextFill(Color.BLACK);
		login.setStyle("-fx-background-color: #ADADAD");
		
		
		settings = UiComponents.createButton("settingsWhite.png", 24, "settings");
		StackPane settingsContainer = new StackPane(settings);
		settingsContainer.setMinSize(200, 200);
		
		cancel = new Button("Clear");
		cancel.setFont(Font.font("inter SemiBold", 15));
		cancel.setMinSize(100, 20);
		cancel.setTextFill(Color.BLACK);
		cancel.setStyle("-fx-background-color: #ADADAD");
		StackPane cancelContainer = new StackPane(cancel);
		cancelContainer.setMinWidth(150);
		cancel.setOnAction(e -> clearFields());
		
		//show fields for the specific form type
		if(this.admin != null) {
			credentialsField.getChildren().addAll(nameField, passwordField);
			credentialsField.setMinSize(300, 200);
			this.add(UiComponents.createIcon("lockWhite.png", 28), 0, 0);
			this.add(errorInfor, 0, 1);
			this.add(credentialsField, 0, 2);
			this.add(cancel, 0, 3);
			this.add(login, 1, 3);
			cancel.setText("Forgot Password");
			cancel.setOnAction(e -> {
				credentialsField.getChildren().clear();
				credentialsField.getChildren().add(tokenField);
				cancel.setText("Back");
			});
			GridPane.setColumnSpan(getChildren().get(0), 2);
			GridPane.setHalignment(getChildren().get(0), HPos.CENTER);
			login.setOnAction(e -> {
				if(authenticate(this.admin)) {
					//register session
					this.admin.setSession(this.admin.getSession() + 1);
					Main.saveData(this.admin, Main.STORAGEFILE1);
					Main.mainContainer.getChildren().clear();
					Main.mainContainer.getChildren().add(app);
					Main.fadeIn(Main.mainContainer, 400);
				};
			});
		}else {
			credentialsField.getChildren().addAll(nameField, emailField, passwordField, confirmPasswordField);
			credentialsField.setMinSize(300, 300);
			this.add(errorInfor, 0, 0);
			this.add(credentialsField, 0, 1);
			this.add(cancel, 0, 2);
			this.add(login, 1, 2);
			login.setOnAction(e -> {
				if(authenticate()) {
					//save admin details and register session
					this.admin = new Administrator(nameString, emailString, passwordString);
					this.admin.setSession(this.admin.getSession() + 1);
					Main.saveData(this.admin, Main.STORAGEFILE1);
					Main.mainContainer.getChildren().clear();
					Main.mainContainer.getChildren().add(new App(this.admin));
				};
			});
		}
	//	credentialsField.setAlignment(Pos.CENTER);
		credentialsField.setSpacing(15);
		
		this.setStyle("-fx-background-color:  #111F26");
		GridPane.setColumnSpan(credentialsField, 2);
		GridPane.setColumnSpan(errorInfor, 2);
		GridPane.setHalignment(login, HPos.RIGHT);
		GridPane.setHalignment(errorInfor, HPos.CENTER);
		this.setVgap(20);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20));
	//	this.setGridLinesVisible(true);
	}
	
	public void clearFields() {
		name.clear();
		email.clear();
		password.clear();
		confirmPassword.clear();
		errorInfor.setText("");
	}
	
	
	protected boolean authenticate() {
		if(!name.getText().isBlank() && name.getText().matches("\\w{2,}")) {
			if(verifyEmail(email.getText())) {
				if(verifyPassword(password.getText(), confirmPassword.getText())) {
					nameString = name.getText();
					emailString = email.getText();
					passwordString = password.getText();
					return true;
				}
				errorInfor.setText(password.getText().isBlank() || confirmPassword.getText().isBlank() ? "Please enter passwords." : 
							password.getText().length() < 8 || confirmPassword.getText().length() < 8 ? "Passwords too short" : "Password does not match." );
			}else {
				errorInfor.setText(email.getText().isBlank() ? "Please enter email." : "Please enter a valid email.");
			}
		}else {
			errorInfor.setText(name.getText().isBlank() ? "Please enter name." : "Please enter a valid name.");
		}
		return false;
	}
	
	protected boolean authenticate(Administrator admin) {
		String requiredName = admin.getName();
		String requiredPassword = admin.getPassword();
		
		System.out.println(requiredName + "  " +  requiredPassword);
		if(!name.getText().isBlank() && name.getText().equals(requiredName)) {
			if(!password.getText().isBlank() && password.getText().equals(requiredPassword)) {
				return true;
			}else {
				errorInfor.setText(password.getText().isBlank() ? "Please enter password." : "invalid password");
			}
		}else {
			errorInfor.setText(name.getText().isBlank() ? "Please enter name." : "Not authorised");
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
	
	public boolean verifyPassword(String password, String copyPassword) {
		if(verifyPassword(password) && verifyPassword(copyPassword)) {
			return password.equals(copyPassword);
		}
		return false;
	}
	
	public boolean verifyEmail(String email) {
		if(!email.isBlank() && email.matches("\\w+@\\w+\\.\\w+")) {
			return true;
		}
		return false;
	}

	//These methods are helper methods for verifyPassword method

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
