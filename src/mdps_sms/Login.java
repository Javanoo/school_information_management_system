package mdps_sms;

import javafx.geometry.Pos;
import javafx.scene.Scene;
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

/**
 * This class creates a form. The type of form created is dependent on the character passed to  the class's
 * constructor. If an 'L'(for login) character is passed, the resulting form generated will be a log in form.  
 * this type of form is used to authenticate a user. Where as, if a 'C'(for credentials) character is passed to the  
 * constructor the resulting form will be used to collect credentials which will be used for verifying the user on
 * each attempted login.
 */
public class Login extends GridPane {
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
	
	public Login(char formType) {
		//fields
		forName.setFont(Font.font("inter SemiBold", 15));
		forName.setTextFill(Color.WHITE);
		name.setMaxWidth(250);
		name.setMinHeight(40);
		name.setPromptText("Enter name");
		name.setFont(Font.font("Outfit", 16));
		name.setStyle("-fx-background-color: #F3F3F3");
		name.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		nameField.getChildren().addAll(forName, name);
		nameField.setSpacing(2);
		
		forEmail.setFont(Font.font("inter SemiBold", 15));
		forEmail.setTextFill(Color.WHITE);
		email.setMaxWidth(250);
		email.setMinHeight(40);
		email.setPromptText("Enter name");
		email.setFont(Font.font("Outfit", 16));
		email.setStyle("-fx-background-color: #F3F3F3");
		emailField.getChildren().addAll(forEmail, email);
		emailField.setSpacing(2);
		
		forPassword.setFont(Font.font("inter SemiBold", 15));
		forPassword.setTextFill(Color.WHITE);
		password.setMaxWidth(250);
		password.setMinHeight(40);
		password.setPromptText("Enter password");
		password.setFont(Font.font("Outfit", 14));
		password.setStyle("-fx-background-color: #F3F3F3");
		password.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		passwordField.getChildren().addAll(forPassword, password);
		passwordField.setSpacing(2);
		
		forConfirmPassword.setFont(Font.font("inter SemiBold", 15));
		forConfirmPassword.setTextFill(Color.WHITE);
		confirmPassword.setMaxWidth(250);
		confirmPassword.setMinHeight(40);
		confirmPassword.setPromptText("Re-enter password");
		confirmPassword.setFont(Font.font("Outfit", 14));
		confirmPassword.setStyle("-fx-background-color: #F3F3F3");
		confirmPassword.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		confirmPasswordField.getChildren().addAll(forConfirmPassword, confirmPassword);
		confirmPasswordField.setSpacing(2);
		
		forToken.setFont(Font.font("inter SemiBold", 15));
		forToken.setTextFill(Color.WHITE);
		token.setMaxWidth(350);
		token.setMinHeight(40);
		token.setPromptText("Enter the token sent to your email...");
		token.setFont(Font.font("Outfit", 16));
		token.setStyle("-fx-background-color: #F3F3F3");
		token.focusedProperty().addListener(e -> {if(!errorInfor.getText().isEmpty()) errorInfor.setText("");});
		tokenField.getChildren().addAll(forToken, token);
		tokenField.setSpacing(2);
		
		errorInfor.setFont(Font.font("inter SemiBold", 16));
		errorInfor.setFill(Color.WHITE);
		
		//show fields for the specific form type
		if(formType == 'L' || formType == 'l') {
			credentialsField.getChildren().addAll(nameField, passwordField);
		}else {
			credentialsField.getChildren().addAll(nameField, emailField, passwordField, 
					confirmPasswordField,new VBox(errorInfor));
		}
		credentialsField.setAlignment(Pos.CENTER);
		credentialsField.setSpacing(15);
		
		//buttons and icons
		StackPane lockContainer = new StackPane(UiComponents.createIcon("lockWhite.png", 24));
		lockContainer.setMinSize(700, 100);
		
		login = UiComponents.createButton("logInWhite.png", 24, "login");
		StackPane loginContainer = new StackPane(login);
		login.setOnAction(e -> verify());
		
		settings = UiComponents.createButton("settingsWhite.png", 24, "settings");
		StackPane settingsContainer = new StackPane(settings);
		settingsContainer.setMinSize(200, 200);
		
		cancel = UiComponents.createButton("cancelWhite.png", 24, "cancel[esc]");
		StackPane cancelContainer = new StackPane(cancel);
		cancelContainer.setMinSize(200, 200);
		cancel.setOnAction(e -> clearFields());
		
		Rectangle rect = new Rectangle(700, 400);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		
		if(formType == 'L' || formType == 'l') {
			credentialsField.setMinSize(300, 200);
			loginContainer.setMinSize(200, 200);
			this.add(lockContainer, 0, 0);
			GridPane.setColumnSpan(this.getChildren().get(0), 3);
			this.add(settingsContainer, 0, 1);
			this.add(credentialsField, 1, 1);
			this.add(loginContainer, 2, 1);
		}else {
			credentialsField.setMinSize(300, 400);
			loginContainer.setMinSize(200, 400);
			this.add(cancelContainer, 0, 0);
			this.add(credentialsField, 1, 0);
			this.add(loginContainer, 2, 0);
		}
		
		this.setMaxHeight(400);
		this.setMaxWidth(700);
		this.setClip(rect);
		this.setStyle("-fx-background-color:  #232323");
	}
	
	public void clearFields() {
		name.clear();
		email.clear();
		password.clear();
		confirmPassword.clear();
		errorInfor.setText("");
	}
	
	public void verify() {
		if((name.getText()).matches("[A-Z][a-zA-Z]{1,20}")) {
			if(password.getText().equals(confirmPassword.getText())) {
				String generatedToken = ((int)(1000 + Math.random() * 1000)) + "";
				System.out.println(generatedToken);
				credentialsField.getChildren().removeAll(nameField, emailField, passwordField, 
					confirmPasswordField,new VBox(errorInfor));
				credentialsField.getChildren().addAll(tokenField, new VBox(errorInfor));
				login.setOnAction(e -> {if(token.getText().equals(generatedToken)) {
											 Main.holder.getChildren().clear();
											 Main.holder.getChildren().add(new App(Main.scene));
										}else {
											token.clear();
											errorInfor.setText("Wrong Token.\nCancel to go back or Re-enter.");
										}
				});
				cancel.setOnAction(e -> {
					errorInfor.setText("");
					credentialsField.getChildren().removeAll(tokenField, credentialsField.getChildren().get(1) );
					credentialsField.getChildren().addAll(nameField, emailField, passwordField, 
							confirmPasswordField,new VBox(errorInfor));
					login.setOnAction(a -> verify());
					cancel.setOnAction(a -> clearFields());
				});
			}
			else{
				password.clear();
				confirmPassword.clear();
				errorInfor.setText("The passwords did not match.");
			}
		}else{
			name.clear();
			errorInfor.setText("Please enter a valid name");
		}
	}
}
