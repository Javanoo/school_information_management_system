/**
 * This package is a school management system,
 * and this class implements the Serial-izable to 
 * enable binary i/o.
 */
package mdps_sms;

import java.io.Serializable;

/**
 * Creates an object solely meant for keeping contact information for an individual
 * mainly used various few classes in this package. 
 */
public class Contact implements Cloneable, Serializable, Comparable<Contact>{
	private static final long serialVersionUID = -3346460993797773103L;
	private String email;
	private String primaryPhone;
	private String secondaryPhone;
	private String location;
	
	Contact(String email, String primaryPhone, String secondaryPhone, String location){
		this.email = email;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int compareTo(Contact o) {
		return o.getPrimaryPhone().compareToIgnoreCase(getPrimaryPhone());
	}
}