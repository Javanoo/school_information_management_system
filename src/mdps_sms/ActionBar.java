package mdps_sms;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;

public class ActionBar extends HBox {
	
	private Button search = UiComponents.createButton("searchBlack.png", 22, "search");
	private Button add = UiComponents.createButton("addBlack.png", 22, "add");
	private Button edit = UiComponents.createButton("editBlack.png", 22, "edit");
	private Button delete = UiComponents.createButton("deleteBlack.png", 22, "delete");
	
	Separator separator = new Separator();
	Separator separator2 = new Separator();
	Separator separator3 = new Separator();
	
	public ActionBar() {
		separator.setOrientation(Orientation.VERTICAL);
		separator.setMaxHeight(20);
		separator.setStyle("-fx-color: black");
		separator2.setOrientation(Orientation.VERTICAL);
		separator2.setMaxHeight(20);
		separator3.setOrientation(Orientation.VERTICAL);
		separator3.setMaxHeight(20);
		
		
		add.setOnAction(e -> new ApplicationForm('s'));
		
		
		
		this.getChildren().addAll(search,separator, add, separator2, edit,  
				separator3, delete);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: " + UiComponents.backgroundcolor);
		this.setMinHeight(35);
		this.setMaxWidth(180);
		this.setSpacing(2);
		Rectangle actRec = new Rectangle(180, 35);
		actRec.setArcHeight(10);
		actRec.setArcWidth(10);
		this.setClip(actRec);
	}

}
