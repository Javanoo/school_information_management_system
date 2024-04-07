package mdps_sms.gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import mdps_sms.Main;
import mdps_sms.util.Fleet;
import mdps_sms.util.Person;
import mdps_sms.util.Staff;
import mdps_sms.util.Student;
import mdps_sms.util.Teacher;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;

public class ActionBar extends HBox {
	
	private Button search = UiComponents.createButton("searchBlack.png", 22, "search");
	private Button add = UiComponents.createButton("addBlack.png", 22, "add");
	private Button edit = UiComponents.createButton("editBlack.png", 22, "edit");
	private Button delete = UiComponents.createButton("deleteBlack.png", 22, "delete");
	
	private TextField searchBar = new TextField();
	private HBox searchContainer = new HBox(UiComponents.createButton("cancelBlack.png", 22, "cancel search"), searchBar);
	
	Separator separator = new Separator();
	Separator separator2 = new Separator();
	Separator separator3 = new Separator();
	
	Rectangle actRec = new Rectangle(165, 37);
	
	public ActionBar(Person ps) {
		separator.setOrientation(Orientation.VERTICAL);
		separator.setMaxHeight(20);
		separator.setStyle("-fx-color: black");
		separator2.setOrientation(Orientation.VERTICAL);
		separator2.setMaxHeight(20);
		separator3.setOrientation(Orientation.VERTICAL);
		separator3.setMaxHeight(20);
		
		searchBar.setStyle("-fx-background-color: white; -fx-font-family: \"Inter SemiBold\"; -fx-font-size: 14; -fx-text-fill: black;");
		searchBar.setPromptText("Search list...");
		searchContainer.setAlignment(Pos.CENTER);
		searchContainer.setSpacing(2);
		
		getChildren().addAll(search,separator, add, edit,  
				separator3, delete);
		setAlignment(Pos.CENTER);
		setStyle("-fx-background-color: white");
		setMinHeight(35);
		setMaxWidth(165);
		setSpacing(2);
		setPadding(new Insets(3, 2, 3, 2));
		
		actRec.setArcHeight(30);
		actRec.setArcWidth(30);
		setClip(actRec);
		
		((Button)searchContainer.getChildren().get(0)).setOnAction(e -> {
			hideSearch();
			Main.fadeIn(searchContainer);
		});
		
		search.setOnAction(e -> {
			showSearch();
			Main.fadeIn(searchContainer);
		});
	}
	public void hideSearch() {
		getChildren().remove(0);
		setMaxWidth(180);
		actRec.setWidth(180);
		searchBar.clear();
	}
	
	public void showSearch() {
		if(!getChildren().contains(searchContainer)) {
			getChildren().add(0, searchContainer);
			setMaxWidth(350);
			actRec.setWidth(350);
		}
	}

	/**
	 * @return the search
	 */
	public synchronized Button getSearch() {
		return search;
	}

	/**
	 * @return the add
	 */
	public synchronized Button getAdd() {
		return add;
	}

	/**
	 * @return the edit
	 */
	public synchronized Button getEdit() {
		return edit;
	}

	/**
	 * @return the delete
	 */
	public synchronized Button getDelete() {
		return delete;
	}

	/**
	 * @return the searchBar
	 */
	public synchronized TextField getSearchBar() {
		return searchBar;
	}

	/**
	 * @return the searchContainer
	 */
	public synchronized HBox getSearchContainer() {
		return searchContainer;
	}

}
