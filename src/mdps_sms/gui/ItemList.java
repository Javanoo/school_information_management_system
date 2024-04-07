package mdps_sms.gui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import mdps_sms.util.Fleet;
import mdps_sms.util.Person;
import mdps_sms.util.SchoolClass;
import mdps_sms.util.Staff;
import mdps_sms.util.Student;
import mdps_sms.util.Teacher;

/**
 * creates objest for listing students, teachers or staff members.
 * <p>
 * This class has 3 constructors that enables that facilitates creating an object
 * for viewing a list of staff object or any of its sub classes.
 *
 * @author matthews offen
 * @see Person
 */
public class ItemList<E extends Person> extends BorderPane{
	private Form staffForm = new Form();
	private StudentForm studentForm = new StudentForm();
	private TeacherForm teacherForm = new TeacherForm();
	private FleetForm fleetForm = new FleetForm();

	private ActionBar actionBar = new ActionBar(null);

	TableView<E> table = new TableView<>();
	TableColumn<E, String> name = new TableColumn<>("Name");
	TableColumn<E, String> gender = new TableColumn<>("Gender");
	TableColumn<E, String> role = new TableColumn<>("Role");
	TableColumn<E, String> parent = new TableColumn<>("Parent");
	TableColumn<E, String> phone = new TableColumn<>("Phone");
	TableColumn<E, String> email = new TableColumn<>("Email");
	TableColumn<E, String> salary = new TableColumn<>("Salary");
	TableColumn<E, String> qualification = new TableColumn<>("Qualification");
	TableColumn<E, String> classroom = new TableColumn<>("Classroom");
	TableColumn<E, String> car = new TableColumn<>("Car");
	TableColumn<E, String> carNumberPlate = new TableColumn<>("Number Plate");
	TableColumn<E, String> route = new TableColumn<>("Route");

	ItemList(){}


	ItemList(Person type, TreeSet<E> itemList){

		//list
		Label noItems = new Label("nothing");
		noItems.setFont(Font.font("Ubuntu", FontWeight.BOLD, 16));
		noItems.setTextFill(Color.GRAY);

		name.setMinWidth(220);
		gender.setMinWidth(220);
		role.setMinWidth(220);
		phone.setMinWidth(220);
		email.setMinWidth(220);
		salary.setMinWidth(220);
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		role.setCellValueFactory(new PropertyValueFactory<>("role"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		qualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
		classroom.setCellValueFactory(new PropertyValueFactory<>("classroom"));
		car.setCellValueFactory(new PropertyValueFactory<>("car"));
		carNumberPlate.setCellValueFactory(new PropertyValueFactory<>("carNumberPlate"));
		route.setCellValueFactory(new PropertyValueFactory<>("route"));
		
		table.getColumns().add(name);
		table.getColumns().add(gender);
		
		if(type instanceof Staff) {
			table.getColumns().add(role);
			table.getColumns().add(phone);
			table.getColumns().add(email);
			table.getColumns().add(salary);
			actionBar.getDelete().setOnAction(e -> {
				itemList.remove(table.getSelectionModel().getSelectedItem());
				table.setItems((ObservableList<E>) FXCollections.observableList(new LinkedList<>(itemList)));
			});
		}else if(type instanceof Student) {
			table.getColumns().add(classroom);
			table.getColumns().add(parent);
			table.getColumns().add(phone);
			table.getColumns().add(email);
			if(itemList.isEmpty()) {
				noItems = new Label("Add classes first, to add students");
				actionBar.setDisable(true);
			}
			actionBar.getDelete().setOnAction(e -> {
				//remove student from classroom first
				((Student)table.getSelectionModel().getSelectedItem()).getClassroom().getStudents().remove((Student)table.getSelectionModel().getSelectedItem());
				//then delete the student
				itemList.remove(table.getSelectionModel().getSelectedItem());
				table.setItems((ObservableList<E>) FXCollections.observableList(new LinkedList<>(itemList)));
			});
		}else if(type instanceof Teacher) {
			table.getColumns().add(classroom);
			table.getColumns().add(qualification);
			table.getColumns().add(phone);
			table.getColumns().add(email);
			table.getColumns().add(salary);
			
			actionBar.getDelete().setOnAction( e -> {
				//remove Teacher from classroom first
				for (SchoolClass elem : ((Teacher)table.getSelectionModel().getSelectedItem()).getClassroom()) 
					elem.getTeachers().remove((Teacher)table.getSelectionModel().getSelectedItem());
				//then delete the teacher
				itemList.remove(table.getSelectionModel().getSelectedItem());
				table.setItems((ObservableList<E>) FXCollections.observableList(new LinkedList<>(itemList)));
			});
		}else if(type instanceof Fleet) {
			table.getColumns().add(phone);
			table.getColumns().add(email);
			table.getColumns().add(car);
			table.getColumns().add(carNumberPlate);
			table.getColumns().add(route);
			actionBar.getDelete().setOnAction(e -> {
				itemList.remove(table.getSelectionModel().getSelectedItem());
				table.setItems((ObservableList<E>) FXCollections.observableList(new LinkedList<>(itemList)));
			});
		}else {}
		table.setItems((ObservableList<E>) FXCollections.observableList(new LinkedList<>(itemList)));
		table.getSelectionModel().selectFirst();
		table.requestFocus();
		table.setPlaceholder(noItems);
		table.setTableMenuButtonVisible(true);

		for(Form elem : new Form[]{staffForm, studentForm, teacherForm, fleetForm}) {
			elem.save.setOnAction(e -> {
				if (elem.createNew(itemList)) {
					table.setItems((ObservableList<E>) FXCollections.observableList(new LinkedList<>(itemList)));
					elem.cancel.fire();
				}
			});
		}
		
		
		
		actionBar.getSearchBar().setOnKeyTyped(e -> {
			search((TextField)e.getSource(), itemList);
			((Button)(actionBar.getSearchContainer().getChildren().get(0))).setOnAction(v -> {
				actionBar.hideSearch();
				table.setItems((ObservableList<E>) FXCollections.observableList(new LinkedList<>(itemList)));
			});
		});


		setCenter(table);
		setBottom(actionBar);
		setStyle("-fx-background-color: #232323");
		BorderPane.setAlignment(getBottom(), Pos.CENTER_RIGHT);
		BorderPane.setMargin(actionBar, new Insets(7, 7, 7 , 0));
	}
	
	/**
	 * @return the staffForm
	 */
	public synchronized Form getStaffForm() {
		return staffForm;
	}

	/**
	 * @param staffForm the staffForm to set
	 */
	public synchronized void setStaffForm(Form staffForm) {
		this.staffForm = staffForm;
	}

	/**
	 * @return the actionBar
	 */
	public synchronized ActionBar getActionBar() {
		return actionBar;
	}

	/**
	 * @param actionBar the actionBar to set
	 */
	public synchronized void setActionBar(ActionBar actionBar) {
		this.actionBar = actionBar;
	}

	/**
	 * displays all available searched items.
	 */
	protected void search(TextField source, TreeSet<E> itemList) {
		//query to use with RegExp for searching
		String query = source.getText();
		//new set that holds the matching items
		TreeSet<E> matchedItems = new TreeSet<>();
		Iterator<E> iter = itemList.iterator();
		while(iter.hasNext() && !query.isEmpty()) {
			E person = iter.next();
			if((person.getName().toLowerCase()).matches(query.toLowerCase() + ".*")) 
				matchedItems.add(person);
		}
		if(matchedItems.isEmpty())table.setItems(FXCollections.observableList(new LinkedList<>(itemList)));
		else
			table.setItems((FXCollections.observableList(new LinkedList<>(matchedItems))));
	}
}