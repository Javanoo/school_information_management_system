/**
 * 
 */
package mdps_sms;

import java.util.ArrayList;

/**
 * 
 */
public class Parent extends AbstractPerson {
	private static final long serialVersionUID = -4414380891539446068L;
	private ArrayList<Student> children = new ArrayList<>();
	private Contact contact = new Contact();
	
	Parent(){}
	
	Parent(Contact contact){
		setContact(contact);
	}

	public ArrayList<Student> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<Student> children) {
		this.children = children;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
