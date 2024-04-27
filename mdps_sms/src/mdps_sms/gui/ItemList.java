package mdps_sms.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javafx.application.Application;
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
	private ActionBar actionBar = new ActionBar();

	private ContextMenu subMenu = new ContextMenu();
	private MenuItem view = new MenuItem("view");
	private MenuItem mail = new MenuItem("email");
	private MenuItem print = new MenuItem("print");
	private MenuItem printAll = new MenuItem("print all");
	
	Mail mailor = new Mail();

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
	TableColumn<E, String> classes = new TableColumn<>("Classroom");
	TableColumn<E, String> car = new TableColumn<>("Car");
	TableColumn<E, String> carNumberPlate = new TableColumn<>("Number Plate");
	TableColumn<E, String> route = new TableColumn<>("Route");
	TableColumn<E, String> codeNumber = new TableColumn<>("Code Number");
	TableColumn<E, String> accountAdmin = new TableColumn<>("Account administrator");
	TableColumn<E, String> accountNumber = new TableColumn<>("Account number");

	ItemList(){}


	ItemList(Person type, ArrayList<E> itemList){
		
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
		classes.setCellValueFactory(new PropertyValueFactory<>("classes"));
		car.setCellValueFactory(new PropertyValueFactory<>("car"));
		carNumberPlate.setCellValueFactory(new PropertyValueFactory<>("carNumberPlate"));
		parent.setCellValueFactory(new PropertyValueFactory<>("parentName"));
		route.setCellValueFactory(new PropertyValueFactory<>("route"));
		codeNumber.setCellValueFactory(new PropertyValueFactory<>("codeNumber"));
		accountAdmin.setCellValueFactory(new PropertyValueFactory<>("accountAdmin"));
		accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
		
		view.setOnAction(e -> {
			showSummary();
		});
		subMenu.getItems().addAll(view, print, printAll, mail);
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
			mail.setText("email staff");
			mail.setOnAction(e -> {
				if((Staff)table.getSelectionModel().getSelectedItem() != null)
				sendEmail((Staff)table.getSelectionModel().getSelectedItem());
			});
			actionBar.getDelete().setOnAction(e -> {
				if((Staff)table.getSelectionModel().getSelectedItem() != null) {
					itemList.removeAll(table.getSelectionModel().getSelectedItems());
					Main.saveData(itemList, Main.STORAGEFILE_s);
					table.setItems(FXCollections.observableList(itemList));
				}
			});
			getActionBar().getAdd().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(new Form());
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(200);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
				});
				
				//register forms buttons
				((Form)Main.popup.getContent().get(0)).cancel.setOnAction(v -> {
					getTable().setItems(FXCollections.observableList(itemList));
					Main.popup.hide();
					Main.popup.getContent().clear();
				});
				
				((Form)Main.popup.getContent().get(0)).save.setOnAction(v -> {
					if (((Form)Main.popup.getContent().get(0)).createNew(new Student(), itemList)) {
						//table.setItems(FXCollections.observableList(new LinkedList<>(data)));
						((Form)Main.popup.getContent().get(0)).cancel.fire();
					}
				});
			});
			//edit
			getActionBar().getEdit().setOnAction(e -> {
				if((Staff)table.getSelectionModel().getSelectedItem() != null) {
					Main.popup.getContent().clear();
					Main.popup.getContent().add(new Form());
					((Form)Main.popup.getContent().get(0)).edit((Staff)table.getSelectionModel().getSelectedItem(),itemList);
					Main.popup.show(Main.primaryStage);
					
					//register forms buttons
					((Form)Main.popup.getContent().get(0)).cancel.setOnAction(v -> {
						getTable().setItems(FXCollections.observableList(itemList));
						Main.popup.hide();
						Main.popup.getContent().clear();
					});
					
					((Form)Main.popup.getContent().get(0)).save.setOnAction(v -> {
						if (((Form)Main.popup.getContent().get(0)).createNew((Staff)table.getSelectionModel().getSelectedItem(), itemList)) {
							//table.setItems(FXCollections.observableList(new LinkedList<>(data)));
							((Form)Main.popup.getContent().get(0)).cancel.fire();
						}
					});
				}
			});
		}else if(type instanceof Student) {///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			table.getColumns().add(classroom);
			table.getColumns().add(parent);
			phone.setText("Parent's phone");
			table.getColumns().add(phone);
			email.setText("Parent's email");
			table.getColumns().add(email);
			table.getColumns().add(0, codeNumber);
			mail.setText("email parent");
			mail.setOnAction(e -> {
				if((Student)table.getSelectionModel().getSelectedItem() != null)
				sendEmail((Student)table.getSelectionModel().getSelectedItem());
			});
			
			actionBar.getDelete().setOnAction(e -> {
				if(table.getSelectionModel().getSelectedItems().size() != 0) {
					//remove student from classroom first
					for(E elem : table.getSelectionModel().getSelectedItems()) {
						SchoolClass classCopy = (((Student)elem).getClassroom());
						
						Iterator<SchoolClass> iter = Main.classrooms.iterator();
						
						while(iter.hasNext()) {
							SchoolClass classroom = iter.next();
							if(classroom.getName().equals(classCopy.getName())) {
								classroom.getStudents().remove(((Student)elem));
								break;
							}
						}
					}
					
					//remove students and save both the student dat and classrooms.
					itemList.removeAll(table.getSelectionModel().getSelectedItems());
					Main.saveData(itemList, Main.STORAGEFILE_S);
					Main.saveData(Main.classrooms, Main.STORAGEFILE_C);
					table.setItems(FXCollections.observableList(itemList));
				}
			});

			getActionBar().getAdd().setOnAction(e -> {
				//call the pop up
				Main.popup.getContent().add(new StudentForm());
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(200);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
				});
				
				//register forms buttons
				((StudentForm)Main.popup.getContent().get(0)).cancel.setOnAction(v -> {
					getTable().setItems(FXCollections.observableList(itemList));
					Main.popup.hide();
				});
				
				((StudentForm)Main.popup.getContent().get(0)).save.setOnAction(v -> {
					if (((StudentForm)Main.popup.getContent().get(0)).createNew(new Student(), itemList)) {
						((StudentForm)Main.popup.getContent().get(0)).cancel.fire();
						Main.saveData(itemList, Main.STORAGEFILE_S);
						Main.saveData(Main.classrooms, Main.STORAGEFILE_C);
					}
				});

			});

			//edit
			getActionBar().getEdit().setOnAction(e -> {
				if((Student)table.getSelectionModel().getSelectedItem() != null) {
					
					StudentForm form = new StudentForm();
					
					//register forms buttons
					form.cancel.setOnAction(v -> {
						getTable().setItems(FXCollections.observableList(itemList));
						Main.popup.hide();
						Main.popup.getContent().clear();
						Main.saveData(itemList, Main.STORAGEFILE_S);
						Main.saveData(Main.classrooms, Main.STORAGEFILE_C);
					});
					
					form.edit((Student)table.getSelectionModel().getSelectedItem(), itemList);
					Main.popup.getContent().add(form);
					Main.popup.show(Main.primaryStage);
				}
			});////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}else if(type instanceof Teacher) {
			table.getColumns().add(classes);
			table.getColumns().add(qualification);
			table.getColumns().add(phone);
			table.getColumns().add(email);
			table.getColumns().add(salary);
			table.getColumns().add(accountNumber);
			table.getColumns().add(accountAdmin);
			mail.setText("email teacher");
			mail.setOnAction(e -> {
				if((Teacher)table.getSelectionModel().getSelectedItem() != null)
				sendEmail((Teacher)table.getSelectionModel().getSelectedItem());
			});

			actionBar.getDelete().setOnAction( e -> {
				if(table.getSelectionModel().getSelectedItems() != null) {
					//remove Teacher from classroom first
					for (E elem : table.getSelectionModel().getSelectedItems()) {
						 for(SchoolClass var : ((Teacher)elem).getClass_subject().keySet()) {
							 
						 }
					}
					//then delete the teacher
					itemList.removeAll(table.getSelectionModel().getSelectedItems());
					table.setItems(FXCollections.observableList(new LinkedList<>(itemList)));
					Main.saveData(itemList, Main.STORAGEFILE_T);
					Main.saveData(Main.classrooms, Main.STORAGEFILE_C);
					
				}
			});
			getActionBar().getAdd().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(new TeacherForm(Main.classrooms));
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(200);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
				});
				
				//register forms buttons
				((TeacherForm)Main.popup.getContent().get(0)).cancel.setOnAction(v -> {
					getTable().setItems(FXCollections.observableList(itemList));
					Main.popup.hide();
					Main.popup.getContent().clear();
				});
				
				((TeacherForm)Main.popup.getContent().get(0)).save.setOnAction(v -> {
					if (((TeacherForm)Main.popup.getContent().get(0)).createNew(new Teacher(), itemList)) {
						//table.setItems(FXCollections.observableList(new LinkedList<>(data)));
						((TeacherForm)Main.popup.getContent().get(0)).cancel.fire();
					}
				});
			});
			//edit
			getActionBar().getEdit().setOnAction(e -> {
				if((Teacher)table.getSelectionModel().getSelectedItem() != null) {
					Main.popup.getContent().clear();
					Main.popup.getContent().add(new TeacherForm(Main.classrooms));
					((TeacherForm)Main.popup.getContent().get(0)).edit((Teacher)table.getSelectionModel().getSelectedItem(),itemList);
					Main.popup.show(Main.primaryStage);
					

					((TeacherForm)Main.popup.getContent().get(0)).save.setOnAction(v -> {
						if (((TeacherForm)Main.popup.getContent().get(0)).createNew(new Teacher(), itemList)) {
							//table.setItems(FXCollections.observableList(new LinkedList<>(data)));
							((TeacherForm)Main.popup.getContent().get(0)).cancel.fire();
						}
					});
				}
			});
		}else if(type instanceof Fleet) {
			table.getColumns().add(phone);
			table.getColumns().add(email);
			table.getColumns().add(car);
			table.getColumns().add(carNumberPlate);
			table.getColumns().add(route);
			mail.setText("email member");
			mail.setOnAction(e -> {
				if((Fleet)table.getSelectionModel().getSelectedItem() != null)
				sendEmail((Fleet)table.getSelectionModel().getSelectedItem());
			});
			
			actionBar.getDelete().setOnAction(e -> {
				if((Fleet)table.getSelectionModel().getSelectedItem() != null) {
					itemList.removeAll(table.getSelectionModel().getSelectedItems());
					table.setItems(FXCollections.observableList(itemList));
					Main.saveData(itemList, Main.STORAGEFILE_F);
				}
			});
			getActionBar().getAdd().setOnAction(e -> {
				Main.popup.getContent().clear();
				Main.popup.getContent().add(new FleetForm());
				Main.popup.show(Main.primaryStage);
				Main.popup.setAnchorY(200);
				Main.popup.setOnHiding(v -> {
					Main.popup.getContent().clear();
					Main.saveData(itemList, Main.STORAGEFILE_F);
				});
				
				//register forms buttons
				((FleetForm)Main.popup.getContent().get(0)).cancel.setOnAction(v -> {
					getTable().setItems(FXCollections.observableList(itemList));
					Main.popup.hide();
					Main.popup.getContent().clear();
				});
				
				((FleetForm)Main.popup.getContent().get(0)).save.setOnAction(v -> {
					if (((FleetForm)Main.popup.getContent().get(0)).createNew(new Fleet(), itemList)) {
						//table.setItems(FXCollections.observableList(new LinkedList<>(data)));
						((FleetForm)Main.popup.getContent().get(0)).cancel.fire();
					}
				});
			});
			
			//edit
			getActionBar().getEdit().setOnAction(e -> {
				if((Fleet)table.getSelectionModel().getSelectedItem() != null) {
					Main.popup.getContent().clear();
					Main.popup.getContent().add(new FleetForm());
					((FleetForm)Main.popup.getContent().get(0)).edit((Fleet)table.getSelectionModel().getSelectedItem(), itemList);
					Main.popup.show(Main.primaryStage);
					
					//register forms buttons
					((FleetForm)Main.popup.getContent().get(0)).cancel.setOnAction(v -> {
						getTable().setItems(FXCollections.observableList(itemList));
						Main.popup.hide();
						Main.popup.getContent().clear();
					});
				}
			});
		}
		if(itemList != null) {
			table.setItems(FXCollections.observableList(itemList));
			table.getSelectionModel().selectFirst();
		}
		table.requestFocus();
		table.setPlaceholder(placeHolder);
		table.setTableMenuButtonVisible(true);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

		actionBar.getSearchBar().setOnKeyTyped(e -> {
			search((TextField)e.getSource(), itemList);
			((Button)(actionBar.getSearchContainer().getChildren().get(0))).setOnAction(v -> {
				actionBar.hideSearch();
				table.setItems(FXCollections.observableList(itemList));
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
	protected void search(TextField source, ArrayList<E> itemList) {
		//query to use with RegExp for searching
		String query = source.getText();
		//new set that holds the matching items
		ArrayList<E> matchedItems = new ArrayList<>();
		Iterator<E> iter = itemList.iterator();
		while(iter.hasNext() && !query.isEmpty()) {
			E person = iter.next();
			if((person.getName().toLowerCase()).matches(query.toLowerCase() + ".*"))
				matchedItems.add(person);
		}
		if(matchedItems.isEmpty())table.setItems(FXCollections.observableList(itemList));
		else
			table.setItems((FXCollections.observableList(new ArrayList<>(matchedItems))));
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
	
	public void sendEmail(Person client) {
		if(!client.getEmail().equals("none")) {
			//notify("opening mailing client");
			mailor.sendMail(client.getEmail());
		}else {
			//notify(client.getName() + " does not have an email.");
		}
	}
}

class Mail extends Application {
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	}
	public void sendMail(String client) {
		getHostServices().showDocument("mailto:" + client);
	}
}