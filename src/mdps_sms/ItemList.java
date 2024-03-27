package mdps_sms;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ItemList extends VBox{
	private HBox columnHeads = new HBox();
	private Separator separate = new Separator();
	private ListView<HBox> list = new ListView<>();
	
	Label placeholder = new Label("loading..."); 
	StackPane placeholderContainer = new StackPane(placeholder); 
	
	LinkedList<HBox> studentList = new LinkedList<>();
	
	ItemList(BorderPane s){
		
		columnHeads.getChildren().addAll(new Label("StudentID"), new Label("Name"), new Label("Gender") ,
				new Label("Class room"), new Label ("Parent"), new Label ("Date addded"));
		columnHeads.maxWidthProperty().bind(s.widthProperty().subtract(30));
		columnHeads.setMinHeight(35);
		columnHeads.setSpacing(160);
		columnHeads.spacingProperty().bind(columnHeads.maxWidthProperty().divide(7.5));
		columnHeads.setAlignment(Pos.CENTER_LEFT);
		columnHeads.setPadding(new Insets(0, 0, 0, 15));
		columnHeads.setStyle("-fx-background-color:" + UiComponents.backgroundcolor);
		for( Node elem : columnHeads.getChildren()) {
			((Label)elem).setFont(Font.font("ubuntu mono", FontWeight.BOLD, 16));
		}
		Rectangle colRec = new Rectangle(columnHeads.getMaxWidth(), 35);
		colRec.widthProperty().bind(columnHeads.widthProperty());
		colRec.setArcHeight(15);
		colRec.setArcWidth(15);
	//	columnHeads.setClip(colRec);
		
		separate.maxWidthProperty().bind(colRec.widthProperty().subtract(10));
		
		placeholder.setFont(Font.font("Outfit SemiBold", 16));
		placeholderContainer.setStyle("-fx-background-color: " + UiComponents.backgroundcolor);
		
		//Dummy list for testing
		for(int i = 0; i <= 250; i++) {
			HBox entry = new HBox();
			entry.setSpacing(160);
			entry.spacingProperty().bind(columnHeads.maxWidthProperty().divide(6.7));
			entry.maxWidthProperty().bind(s.widthProperty().subtract(30));
			entry.setAlignment(Pos.CENTER_LEFT);
			entry.setPadding(new Insets(0, 0, 0, 15));
			entry.getChildren().addAll(new Label(Math.round(10000 + Math.random() * 10000) + ""), new Label(Math.round(10000 + Math.random() * 10000) + ""), 
					new Label(Math.round(10000 + Math.random() * 10000) + "") , new Label(Math.round(10000 + Math.random() * 10000) + ""), 
					new Label (Math.round(10000 + Math.random() * 10000) + ""), new Label (Math.round(10000 + Math.random() * 10000) + ""));
			for( Node elem : entry.getChildren()) {
				((Label)elem).setFont(Font.font("ubuntu mono", FontWeight.BOLD, 14));
			}
			studentList.add(entry);
		}
		
		list.setItems(FXCollections.observableList(studentList));
	//	list.setFixedCellSize(40);
		list.setPlaceholder(placeholderContainer);
		list.maxWidthProperty().bind(s.widthProperty().subtract(30));
		/*list.minHeightProperty().bind(s.heightProperty().subtract(125));
		Rectangle listRec = new Rectangle(list.getMaxWidth(), s.getMinHeight());
		listRec.widthProperty().bind(list.maxWidthProperty());
		listRec.heightProperty().bind(list.minHeightProperty());
		listRec.setArcHeight(15);
		listRec.setArcWidth(15);
		list.setClip(listRec);*/
		
		StackPane stack = new StackPane(list);
		stack.setMinHeight(700);
		
		
		this.getChildren().addAll(columnHeads, stack);
		this.maxWidthProperty().bind(s.maxWidthProperty().subtract(20));
		this.setSpacing(20);
		this.setAlignment(Pos.TOP_CENTER);
	}
}