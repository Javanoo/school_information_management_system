package mdps_sms.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public abstract class UiComponents {
	
	public static String theme = "dark";
	public static int iconSize = 24;
	public static int fontSize = 14;
	public static String iconPrefix = "Black.png";
	public static String backgroundcolor = "#E2DFD8";

	public UiComponents() {
		// TODO Auto-generated constructor stub
	}
	
	public static ImageView createIcon(String name, double iconSize) {
		ImageView image = new ImageView(name);
		image.setFitHeight(iconSize);
		image.setFitWidth(iconSize);
		return image;
	}
	
	public static Button createButton(String iconName, double iconSize, String tip) {
		Button button = new Button();
		button.setGraphic(createIcon(iconName, iconSize));
		button.setStyle("-fx-background-color: #e3dedb08;");
		button.setPadding(new Insets(5));
		button.setTooltip(new Tooltip(tip));
		button.getTooltip().setFont(Font.font("ubuntu", 14));
		button.getTooltip().setShowDelay(new Duration(50));
		
		//register events
		button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #AAAAAA"));
		button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #e3dedb08;"));
		
		//round corner effect
		Rectangle rect = new Rectangle(iconSize + 10, iconSize +10);
		rect.setArcHeight(30);
		rect.setArcWidth(30);
		button.setClip(rect);
		
		return button;
	}
}
