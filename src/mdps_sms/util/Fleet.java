package mdps_sms.util;

public class Fleet extends Person {
	private String car = "unknown";
	private String carNumberPlate = "unknown";
	private String route = "unknown";
	
	public Fleet(){}
	
	public Fleet(String name, String gender, String[] phone, String[] email, String location, String role, 
			String qualification, String car, String carNumberPlate, String route, String description) {
		setName(name);
		setGender(gender);
		setPhone(phone);
		setEmail(email);
		setLocation(location);
		setRole(role);
		setQualification(qualification);
		setCar(car);
		setCarNumberPlate(carNumberPlate);
		setRoute(route);
		setDescription(description);
	}

	/**
	 * @return the car
	 */
	public synchronized String getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public synchronized void setCar(String car) {
		this.car = car;
	}

	/**
	 * @return the carNumberPlate
	 */
	public synchronized String getCarNumberPlate() {
		return carNumberPlate;
	}

	/**
	 * @param carNumberPlate the carNumberPlate to set
	 */
	public synchronized void setCarNumberPlate(String carNumberPlate) {
		this.carNumberPlate = carNumberPlate;
	}

	/**
	 * @return the route
	 */
	public synchronized String getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public synchronized void setRoute(String route) {
		this.route = route;
	}
}
