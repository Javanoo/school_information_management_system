package mdps_sms.util;

import java.util.Date;

public class Administrator extends Person {
	
	private static final long serialVersionUID = 803976636899220758L;
	private String password;
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
	
	public int getSession() {return session;}
	
	public void setSession(int session) {this.session = session;}
}