package mdps_sms;

import java.util.LinkedList;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * creates objest for listing students, teachers or staff members.
 * <p>
 * This class has 3 constructors that enables that facilitates creating an object
 * for viewing a list of staff object or any of its sub classes.
 * 
 * @author matthews offen
 * @see Staff
 */
public class ItemList extends BorderPane{
	private GridPane columnHeads = new GridPane ();
	private Separator separate = new Separator();
	private ListView<GridPane> list = new ListView<>();
	private LinkedList<GridPane> containerList = new LinkedList<>();
	
	ItemList(TreeSet<Staff> item){
		
		//Column Headers
		if(item.pollFirst() instanceof Student) {
			columnHeads.add(new Label("Name"), 0, 0);
			columnHeads.add(new Label("Gender"), 1, 0);
			columnHeads.add(new Label("Nationality"), 2, 0);
			columnHeads.add(new Label("Class"), 3, 0);
			columnHeads.add(new Label("Parent"), 4, 0);
			columnHeads.add(new Label("Date joined"), 5, 0);
		}else if(item.pollFirst() instanceof Teacher){
			columnHeads.add(new Label("Name"), 0, 0);
			columnHeads.add(new Label("Gender"), 1, 0);
			columnHeads.add(new Label("Nationality"), 2, 0);
			columnHeads.add(new Label("Class"), 3, 0);
			columnHeads.add(new Label("Subjects"), 4, 0);
			columnHeads.add(new Label("Date joined"), 5, 0);
		}else {
			columnHeads.add(new Label("Name"), 0, 0);
			columnHeads.add(new Label("Gender"), 1, 0);
			columnHeads.add(new Label("Nationality"), 2, 0);
			columnHeads.add(new Label("Role"), 3, 0);
			columnHeads.add(new Label("Location"), 4, 0);
			columnHeads.add(new Label("Date joined"), 5, 0);
		}
		
		for(Node elem : columnHeads.getChildren()) {
			((Label)elem).setFont(Font.font("Inter SemiBold", 14));
			((Label)elem).setTextFill(Color.WHITE);
		}
		
		//delete after done
		for(Staff elem : item) {
			GridPane list = new GridPane();
			
			list.add(new Label(elem.getName()), 0, 0);
			list.add(new Label(elem.getGender()), 1, 0);
			list.add(new Label(elem.getNationality()), 2, 0);
			list.add(new Label(elem.getClass().toString()), 3, 0);
			list.add(new Label("unknown"), 4, 0);
			list.add(new Label(elem.getDateRegistered().toString().substring(0, 5)), 5, 0);
			
			for(Node node : list.getChildren()) {
				((Label)node).setFont(Font.font("monospace", 14));
				((Label)node).setMaxWidth(60);
				
				list.setHgap(180);
				list.setAlignment(Pos.CENTER);
				GridPane.setHalignment(list.getChildren().get(0), HPos.LEFT);
			}
			
			containerList.add(list);
				
		}
		
		columnHeads.setHgap(180);
		columnHeads.setPadding(new Insets(10, 0, 10, 0));
		columnHeads.setStyle("-fx-background-color: #232323");
		columnHeads.setAlignment(Pos.CENTER);
		GridPane.setHalignment(columnHeads.getChildren().get(0), HPos.LEFT);
		
		
		//list
		Label noItems = new Label("nothing yet");
		noItems.setFont(Font.font("Ubuntu", FontWeight.MEDIUM, 16));
		noItems.setTextFill(Color.GRAY);
		
		list.setItems(FXCollections.observableList(containerList));
		list.setPlaceholder(noItems);
		
		
		ActionBar actionBar = new ActionBar();
		
		this.setTop(columnHeads);
		this.setCenter(list);
		this.setBottom(actionBar);
		BorderPane.setAlignment(this.getBottom(), Pos.CENTER_RIGHT);
		BorderPane.setMargin(actionBar, new Insets(5, 0, 0 , 0));
	}
	
	/*ItemList(Teacher item){
		columnHeads.add(new Label("Name"), 0, 0);
		columnHeads.add(new Label("Gender"), 1, 0);
		columnHeads.add(new Label("Nationality"), 2, 0);
		columnHeads.add(new Label("Class"), 3, 0);
		columnHeads.add(new Label("Subjects"), 4, 0);
		columnHeads.add(new Label("Date joined"), 5, 0);
		
		
	}*/
	
	/*ItemList(Staff item){
		columnHeads.add(new Label("Name"), 0, 0);
		columnHeads.add(new Label("Gender"), 1, 0);
		columnHeads.add(new Label("Nationality"), 2, 0);
		columnHeads.add(new Label("Role"), 3, 0);
		columnHeads.add(new Label("Location"), 4, 0);
		columnHeads.add(new Label("Date joined"), 5, 0);		
	}*/
}