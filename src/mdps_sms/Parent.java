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
	public ArrayList<Student> children = new ArrayList<>();
	Parent(){
		super();
	}
	Parent(Contact contact){
		super(contact);
		setRole(PARENT_ROLE);
	}
	
	java.util.Iterator<Student> iterator = children.iterator();
	
	public void addChild(Student child) {
		children.add(child);
	}
	
	public Student getChild(Student child) {
		return children.get(children.indexOf(child));
	}
	
	public void removeChild(Student child) {
		children.remove(child);
	}
	
	public void displayAllChildren () {
		for(Student elem : children) {
			System.out.println(elem.getName());
		}
	}
}
