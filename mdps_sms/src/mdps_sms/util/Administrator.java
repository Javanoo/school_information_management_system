package mdps_sms.util;

import java.util.Date;

/**
 * This is used to create administrator objects, needed by the App class in the mdps_sms_gui. 
 * <p>
 * It is a subclass of the person abstract class and adds more specialized methods needed to perform
 * administration activities.
 * 
 * @see Person App
 */
public class Administrator extends Person {
	
	private static final long serialVersionUID = 803976636899220758L;
	private String password  = "123";
	private int session = 0;
	
	public Administrator(){}
	
	public Administrator(String name, String email, String password){
		this.setName(name);
		this.setEmail(new String[]{email});
		setPassword(password);
		this.setRole("Administrator");
		this.setDateRegistered(new Date());
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getSession() {return session;}
	
	public void setSession(int session) {this.session = session;}
}