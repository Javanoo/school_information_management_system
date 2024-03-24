package mdps_sms;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Settings extends BorderPane {
	private Label header = new Label("Settings");
	private Button restore = new Button("Restore");
	private Separator horizontalLine = new Separator();
	private GridPane topHeader = new GridPane(); 
	
	ScrollPane scrBar = new ScrollPane(this.getCenter());
	
	
	private VBox centerBody = new VBox();
	
	private Button cancel = new Button("Cancel");
	private Button delete = new Button("Delete Account");
	private Button save = new Button("Save");
	private GridPane footer = new GridPane();
	
	public Settings() {
		//for top heading
		header.setFont(Font.font("Outfit SemiBold", 24));
		header.setTextFill(Color.valueOf("#232323"));
		
		restore.setFont(Font.font("Outfit SemiBold", 16));
		restore.setTextFill(Color.WHITE);
		restore.setStyle("-fx-background-color: #232323");
		restore.setMinWidth(100);
		
		horizontalLine.setMinWidth(500);
		horizontalLine.setMinHeight(5);
		horizontalLine.setStyle("-fx-background-color: #232323");
		
		topHeader.add(header, 0, 0);
		topHeader.add(restore, 1, 0);
		topHeader.add(horizontalLine, 0, 1);
		topHeader.setAlignment(Pos.CENTER);
		GridPane.setHalignment(restore, HPos.RIGHT);
		GridPane.setValignment(header, VPos.CENTER);
		GridPane.setColumnSpan(horizontalLine, 2);
		topHeader.setVgap(10);
		topHeader.setHgap(500);
		topHeader.setMaxWidth(700);
	//	topHeader.setStyle("-fx-background-color: red");
		
		//for center or main body
		
		//for footer / action buttons
		cancel.setFont(Font.font("Outfit SemiBold", 16));
		cancel.setTextFill(Color.WHITE);
		cancel.setStyle("-fx-background-color: #232323");
		cancel.setMinWidth(100);
		
		delete.setFont(Font.font("Outfit SemiBold", 16));
		delete.setTextFill(Color.WHITE);
		
		delete.setStyle("-fx-background-color: #CB1E1E");
		delete.setMinWidth(150);
		
		save.setFont(Font.font("Outfit SemiBold", 16));
		save.setTextFill(Color.WHITE);
		save.setStyle("-fx-background-color: #232323");
		save.setMinWidth(100);
		
		//footer.add(cancel, 0, 0);
		footer.add(delete, 0, 0);
		footer.add(save, 1, 0);
		footer.setAlignment(Pos.CENTER);
		GridPane.setHalignment(save, HPos.RIGHT);
		footer.setHgap(440);
		footer.setMaxWidth(700);
	//	footer.setStyle("-fx-background-color: red");
		
		this.setTop(new StackPane(topHeader));
		this.setCenter(centerBody);
		this.setBottom(footer);
		this.setPadding(new Insets(40,0,10,0));
		this.setAlignment(footer, Pos.CENTER);
	}
}
