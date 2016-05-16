package clinic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Doctor extends Person {
	
	private int id;
	private String specialization;
	private Collection<Person> patients = new ArrayList<>();
	
	public Doctor(String SSN, String first, String last, Optional<Doctor> doctor, int id, String specialization) {
		super(SSN, first, last, doctor);
		this.id = id;
		this.specialization = specialization;
	}

	public int getId(){
		return id;
	}
	
	public String getSpecialization(){
		return specialization;
	}
	
	public Collection<Person> getPatients() {
		return patients;
	}
	
	public void addPatient(Person patient) {
		patients.add(patient);
	}

}
