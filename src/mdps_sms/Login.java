package mdps_sms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

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
	
	private VBox credentialsField = new VBox();
	
	private Button login = null;
	private Button settings = null;
	private Button cancel = null;
	
	public Login(char formType) {
		//fields
		forName.setFont(Font.font("inter SemiBold", 14));
		forName.setTextFill(Color.WHITE);
		name.setMaxWidth(250);
		name.setMinHeight(30);
		name.setPromptText("Enter name");
		name.setFont(Font.font("Outfit", 14));
		name.setStyle("-fx-background-color: #F3F3F3");
		nameField.getChildren().addAll(forName, name);
		nameField.setSpacing(2);
		
		forEmail.setFont(Font.font("inter SemiBold", 14));
		forEmail.setTextFill(Color.WHITE);
		email.setMaxWidth(250);
		email.setMinHeight(30);
		email.setPromptText("Enter name");
		email.setFont(Font.font("Outfit", 14));
		email.setStyle("-fx-background-color: #F3F3F3");
		emailField.getChildren().addAll(forEmail, email);
		emailField.setSpacing(2);
		
		forPassword.setFont(Font.font("inter SemiBold", 14));
		forPassword.setTextFill(Color.WHITE);
		password.setMaxWidth(250);
		password.setMinHeight(30);
		password.setPromptText("Enter password");
		password.setFont(Font.font("Outfit", 14));
		password.setStyle("-fx-background-color: #F3F3F3");
		passwordField.getChildren().addAll(forPassword, password);
		passwordField.setSpacing(2);
		
		forConfirmPassword.setFont(Font.font("inter SemiBold", 14));
		forConfirmPassword.setTextFill(Color.WHITE);
		confirmPassword.setMaxWidth(250);
		confirmPassword.setMinHeight(30);
		confirmPassword.setPromptText("Re-enter password");
		confirmPassword.setFont(Font.font("Outfit", 14));
		confirmPassword.setStyle("-fx-background-color: #F3F3F3");
		confirmPasswordField.getChildren().addAll(forConfirmPassword, confirmPassword);
		confirmPasswordField.setSpacing(2);
		
		//show fields for the specific form type
		if(formType == 'L' || formType == 'l') {
			credentialsField.getChildren().addAll(nameField, passwordField);
		}else {
			credentialsField.getChildren().addAll(nameField, emailField, passwordField, confirmPasswordField);
		}
		credentialsField.setAlignment(Pos.CENTER);
		credentialsField.setSpacing(15);
		
		//buttons and icons
		StackPane lockContainer = new StackPane(createIcon("lockWhite.png", 24));
		lockContainer.setMinSize(700, 100);
		
		login = createButton("logInWhite.png", 24, "login");
		StackPane loginContainer = new StackPane(login);
		login.setOnAction(e -> verify());
		
		settings = createButton("settingsWhite.png", 24, "settings");
		StackPane settingsContainer = new StackPane(settings);
		settingsContainer.setMinSize(200, 200);
		
		cancel = createButton("cancelWhite.png", 24, "cancel[esc]");
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
		this.setStyle("-fx-background-color: #404040");
	}
	
	public ImageView createIcon(String name, double iconSize) {
		ImageView image = new ImageView(name);
		image.setFitHeight(iconSize);
		image.setFitWidth(iconSize);
		return image;
	}
	
	public Button createButton(String iconName, double iconSize, String tip) {
		Button button = new Button();
		button.setGraphic(createIcon(iconName, iconSize));
		button.setStyle("-fx-background-color: #e3dedb08;");
		button.setPadding(new Insets(5));
		button.setTooltip(new Tooltip(tip));
		button.getTooltip().setFont(Font.font("ubuntu", 14));
		
		//register events
		button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #e3dedb5f;"));
		button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #e3dedb08;"));
		
		//round corner effect
		/*Rectangle rect = new Rectangle(iconSize + 10, iconSize +10);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		button.setClip(rect);*/
		
		return button;
	}
	
	public void clearFields() {
		this.name.clear();
		this.email.clear();
		this.password.clear();
		this.confirmPassword.clear();
	}
	
	public void verify() {
		
	}
}
