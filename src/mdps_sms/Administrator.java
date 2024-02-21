package mdps_sms;

public class Administrator extends AbstractPerson {
	
	private static final long serialVersionUID = 803976636899220758L;
	private String password;
	
	Administrator(){}
	
	Administrator(String name, String number, String email, String password){
		this.setName(name);
		this.setContact(new Contact(email, number, null, null));
		this.setRole(ADMINISTRATOR_ROLE);
		setPassword(password);
	}
	
	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}
	
	//------------------------- personnel management -----------------//
	//generic add functionality
	protected <E extends AbstractPerson> void addPerson(E object) {
		//implementation later on
	}
	
	//generic add functionality
	protected <E extends AbstractPerson> void removePerson(E object) {
		//implementation later on
	}
	
	//generic add functionality
	protected <E extends AbstractPerson> void editPerson(E object) {
		//implementation later on
	}
	
	//---------------------------classes management -----------------//
	// classroom creation
	protected SchoolClass createClass() {
		//implementation later on
		return null;
	}
	
	protected void editClass(SchoolClass classRoom) {
		//implementation later on
	}
}
