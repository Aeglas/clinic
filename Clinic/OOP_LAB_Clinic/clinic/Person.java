package clinic;

import java.util.Optional;
import clinic.Doctor;

public class Person {
	
	private String SSN;
	private String first;
	private String last;
	private Optional<Doctor> doctor;
	
	public Person(String SSN, String first, String last, Optional<Doctor> doctor) {
		this.SSN = SSN;
		this.first = first;
		this.last = last;
		this.doctor = doctor;
	}
	
	public String getSSN(){
		return SSN;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public Doctor getDoctor() {
		if (doctor != null) {
			return doctor.get();
		}else {
			return null;
		}
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = Optional.ofNullable(doctor);
	}

}
