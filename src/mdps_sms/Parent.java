/**
 * 
 */
package mdps_sms;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 */
public class Parent extends Person {
	private static final long serialVersionUID = -4414380891539446068L;
	private static ArrayList<Student> children = new ArrayList<>();
	
	Parent(){}
	
	Parent(String name, String location, String[] email, String[] phone){
		super();
		setName(name);
		setLocation(location);
		setEmail(email);
		setPhone(phone);
	}
	
	public synchronized void removeChild(Student student) {
		children.remove(student);
	}
	public synchronized void addChild(Student student) {
		children.add(student);
	}
	
	public String getQualification() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Parents qualifications aren't kept.");
	}
	

	@Override
	public void setQualification(String qualification) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Parents qualifications aren't kept.");
	}
	

	@Override
	public String getSalary() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("No salary information of parents is kept.");
	}
	

	@Override
	public void setSalary(String salary)throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("No salary information of parents is kept.");
	}

	@Override
	public String getRole() throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Roles aren't assigned to parents.");
	}

	@Override
	public void setRole(String role) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Roles aren't assigned to parents.");
	}

	@Override
	public Date getDateRegistered()throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Parent's registration date is not kept.");
	}

	@Override
	public void setDateRegistered(Date dateRegistered)throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Parent's registration date is not kept.");
	}

	@Override
	public String getDescription()throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Parents don't have description.");
	}

	@Override
	public void setDescription(String description) throws UnsupportedOperationException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Parents don't have description, check their child's instead.");
	}

	/**
	 * @return the children
	 */
	public static ArrayList<Student> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public static void setChildren(ArrayList<Student> children) {
		Parent.children = children;
	}
	
}
