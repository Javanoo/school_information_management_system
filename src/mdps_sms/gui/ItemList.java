package mdps_sms.gui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.stage.Stage;
import mdps_sms.Main;
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
	private TreeSet<E> data = new TreeSet<>();

	private Form staffForm = new Form();
	private StudentForm studentForm = new StudentForm(new TreeSet<SchoolClass>());
	private TeacherForm teacherForm = new TeacherForm();
	private FleetForm fleetForm = new FleetForm();

	private ActionBar actionBar = new ActionBar();

	private ContextMenu subMenu = new ContextMenu();
	private MenuItem view = new MenuItem("view");

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
	TableColumn<E, String> codeNumber = new TableColumn<>("Code Number");
	TableColumn<E, String> accountAdmin = new TableColumn<>("Account administrator");
	TableColumn<E, String> accountNumber = new TableColumn<>("Account number");

	ItemList(){}


	ItemList(Person type, TreeSet<E> itemList){

		data = itemList;

		//list
		Label noItems = new Label("nothing");
		noItems.setFont(Font.font("Ubuntu", FontWeight.BOLD, 16));
		noItems.setTextFill(Color.GRAY);
		StackPane placeHolder = new StackPane(noItems);
		placeHolder.setStyle("-fx-background-color: white");



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
		codeNumber.setCellValueFactory(new PropertyValueFactory<>("codeNumber"));
		accountAdmin.setCellValueFactory(new PropertyValueFactory<>("accountAdmin"));
		accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
		
		view.setOnAction(e -> {
			showSummary();
		});
		subMenu.getItems().add(view);
		subMenu.setId("tableSubMenu");

		table.getColumns().add(name);
		table.getColumns().add(gender);
		table.setContextMenu(subMenu);

		if(type instanceof Staff) {
			table.getColumns().add(role);
			table.getColumns().add(phone);
			table.getColumns().add(email);
			table.getColumns().add(salary);
			table.getColumns().add(accountNumber);
			table.getColumns().add(accountAdmin);
			actionBar.getDelete().setOnAction(e -> {
				if(table.getSelectionModel().getSelectedItems().size() != 0) {
					data.removeAll(table.getSelectionModel().getSelectedItems());
					table.setItems(FXCollections.observableList(new LinkedList<>(data)));
				}
			});
			getActionBar().getAdd().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(getStaffForm());
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(200);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
					getStaffForm().cancel();
				});
			});
			getStaffForm().cancel.setOnAction(e -> {
				getTable().setItems(FXCollections.observableList(new LinkedList<>(getData())));
				Main.popup.hide();
				Main.popup.getContent().clear();
			});

			//edit
			getActionBar().getEdit().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(getStaffForm());
				getStaffForm().edit((Staff)table.getSelectionModel().getSelectedItem(),getData());
				Main.popup.show(Main.primaryStage);
			});
		}else if(type instanceof Student) {
			table.getColumns().add(classroom);
			table.getColumns().add(parent);
			phone.setText("Parent's phone");
			table.getColumns().add(phone);
			email.setText("Parent's email");
			table.getColumns().add(email);
			table.getColumns().add(0, codeNumber);
			/*if(data.isEmpty()) {
				noItems = new Label("Add classes first, to add students");
				actionBar.setDisable(true);
			}*/
			actionBar.getDelete().setOnAction(e -> {
				//remove student from classroom first
				((Student)table.getSelectionModel().getSelectedItem()).getClassroom().getStudents().remove(table.getSelectionModel().getSelectedItem());
				//then delete the student
				data.remove(table.getSelectionModel().getSelectedItem());
				table.setItems(FXCollections.observableList(new LinkedList<>(data)));
			});

			getActionBar().getAdd().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(getStudentForm());
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(200);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
					getStudentForm().cancel();
				});
			});
			getStudentForm().cancel.setOnAction(e -> {
				getTable().setItems(FXCollections.observableList(new LinkedList<>(getData())));
				Main.popup.hide();
				Main.popup.getContent().clear();
			});

			//edit
			getActionBar().getEdit().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(getStudentForm());
				getStudentForm().edit((Student)table.getSelectionModel().getSelectedItem(), getData());
				Main.popup.show(Main.primaryStage);
			});

		}else if(type instanceof Teacher) {
			table.getColumns().add(classroom);
			table.getColumns().add(qualification);
			table.getColumns().add(phone);
			table.getColumns().add(email);
			table.getColumns().add(salary);
			table.getColumns().add(accountNumber);
			table.getColumns().add(accountAdmin);

			actionBar.getDelete().setOnAction( e -> {
				//remove Teacher from classroom first
				for (SchoolClass elem : ((Teacher)table.getSelectionModel().getSelectedItem()).getClassroom())
					elem.getTeachers().remove(table.getSelectionModel().getSelectedItem());
				//then delete the teacher
				data.remove(table.getSelectionModel().getSelectedItem());
				table.setItems(FXCollections.observableList(new LinkedList<>(data)));
			});
			getActionBar().getAdd().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(getTeacherForm());
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(200);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
					getTeacherForm().cancel();
				});
			});
			getTeacherForm().cancel.setOnAction(e -> {
				getTable().setItems(FXCollections.observableList(new LinkedList<>(getData())));
				Main.popup.hide();
				Main.popup.getContent().clear();
			});

			//edit
			getActionBar().getEdit().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(getTeacherForm());
			//	getTeacherForm().edit((Teacher)table.getSelectionModel().getSelectedItem(),getData());
				Main.popup.show(Main.primaryStage);
			});
		}else if(type instanceof Fleet) {
			table.getColumns().add(phone);
			table.getColumns().add(email);
			table.getColumns().add(car);
			table.getColumns().add(carNumberPlate);
			table.getColumns().add(route);
			actionBar.getDelete().setOnAction(e -> {
				data.remove(table.getSelectionModel().getSelectedItem());
				table.setItems(FXCollections.observableList(new LinkedList<>(data)));
			});
			getActionBar().getAdd().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(getFleetForm());
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(200);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
					getFleetForm().cancel();
				});
			});
			getFleetForm().cancel.setOnAction(e -> {
				getTable().setItems(FXCollections.observableList(new LinkedList<>(getData())));
				Main.popup.hide();
				Main.popup.getContent().clear();
			});

			//edit
			getActionBar().getEdit().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(getFleetForm());
				getFleetForm().edit((Fleet)table.getSelectionModel().getSelectedItem(),getData());
				Main.popup.show(Main.primaryStage);
			});
		}
		table.setItems(FXCollections.observableList(new LinkedList<>(data)));
		table.getSelectionModel().selectFirst();
		table.requestFocus();
		table.setPlaceholder(placeHolder);
		table.setTableMenuButtonVisible(true);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);



		for(Form elem : new Form[]{staffForm, studentForm, teacherForm, fleetForm}) {
			elem.save.setOnAction(e -> {
				if (elem.createNew(data)) {
					table.setItems(FXCollections.observableList(new LinkedList<>(data)));
					elem.cancel.fire();
				}
			});
		}

		actionBar.getSearchBar().setOnKeyTyped(e -> {
			search((TextField)e.getSource(), data);
			((Button)(actionBar.getSearchContainer().getChildren().get(0))).setOnAction(v -> {
				actionBar.hideSearch();
				table.setItems(FXCollections.observableList(new LinkedList<>(data)));
			});
		});


		setCenter(table);
		setBottom(actionBar);
		setStyle("-fx-background-color: #232323");
		BorderPane.setAlignment(getBottom(), Pos.CENTER_RIGHT);
		BorderPane.setMargin(actionBar, new Insets(7, 7, 7 , 0));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void showSummary() {
		Popup summary = new Popup();

		summary.getContent().add(new Summary(table.getSelectionModel().getSelectedItem()));
		summary.setAutoHide(true);
		summary.show(Main.primaryStage);
		summary.setOnHiding(e -> summary.getContent().clear());
		summary.setAnchorLocation(AnchorLocation.WINDOW_BOTTOM_LEFT);
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


	/**
	 * @return the studentForm
	 */
	public synchronized StudentForm getStudentForm() {
		return studentForm;
	}


	/**
	 * @return the teacherForm
	 */
	public synchronized TeacherForm getTeacherForm() {
		return teacherForm;
	}


	/**
	 * @return the fleetForm
	 */
	public synchronized FleetForm getFleetForm() {
		return fleetForm;
	}


	/**
	 * @return the data
	 */
	public synchronized TreeSet<E> getData() {
		return data;
	}


	/**
	 * @return the table
	 */
	public synchronized TableView<E> getTable() {
		return table;
	}


	/**
	 * @param table the table to set
	 */
	public synchronized void setTable(TableView<E> table) {
		this.table = table;
	}
}