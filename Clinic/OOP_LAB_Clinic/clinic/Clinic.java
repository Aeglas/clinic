package clinic;

import java.io.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Collector;
import java.util.stream.*;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;


public class Clinic {
	
	private Map<String, Person> patients = new HashMap<>();
	private Map<Integer, Doctor> doctors = new HashMap<>();

	public void addPatient(String first, String last, String ssn) {
		patients.put(ssn, new Person(ssn, first, last, null));
	}

	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		doctors.put(docID, new Doctor(ssn, first, last, null, docID, specialization));
	}

	public Person getPatient(String ssn) throws NoSuchPatient {
		if (patients.containsKey(ssn)){
			return patients.get(ssn);
		}else {
			throw new NoSuchPatient(); 
		}
	}

	public Doctor getDoctor(int docID) throws NoSuchDoctor {
		if (doctors.containsKey(docID)){
			return doctors.get(docID);
		}else {
			throw new NoSuchDoctor(); 
		}
	}
	
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		if (doctors.containsKey(docID)){
			if (patients.containsKey(ssn)){
				doctors.get(docID).addPatient(patients.get(ssn));
				patients.get(ssn).setDoctor(doctors.get(docID));
			}else {
				throw new NoSuchPatient(); 
			}
		}else {
			throw new NoSuchDoctor(); 
		}
	}

	/**
	 * returns the collection of doctors that have no patient at all, sorted in alphabetic order.
	 */
	public Collection<Doctor> idleDoctors(){
		Collection<Doctor> freeDoctors = doctors.values().stream()
				.filter(d -> d.getPatients().isEmpty())
				.collect(Collectors.toList());
		return freeDoctors;
	}

	/**
	 * returns the collection of doctors that a number of patients larger than the average.
	 */
	public Collection<Doctor> busyDoctors(){
		//Conto i pazienti per ogni dottore
		Map<Doctor, Long> patientsPerDoctor = patients.values().stream()
				.collect(groupingBy(Person::getDoctor, counting()));
		
		//Ottengo la media di pazienti per dottore
		Double averagePatientsPerDoctor = patientsPerDoctor.values().stream()
				.mapToDouble(p -> p)
				.average().getAsDouble();
		
		return patients.values().stream()
				.map(p -> p.getDoctor())
				.distinct()
				.filter(d -> d.getPatients().size() > averagePatientsPerDoctor)
				.collect(toList());
	}

	/**
	 * returns list of strings
	 * containing the name of the doctor and the relative number of patients
	 * with the relative number of patients, sorted by decreasing number.<br>
	 * The string must be formatted as "<i>### : ID SURNAME NAME</i>" where <i>###</i>
	 * represent the number of patients (printed on three characters).
	 */
	Collection<String> doctorsByNumPatients(){
		//restituisce una collezione di stringhe contenenti il nome del dottore
		//ed il relativo numero di pazienti ordinati in maniera decrescente di numero.
		//Le stringhe devono essere formattate come "### : ID SURNAME NAME" dove ### rappresenta il numero di pazienti (stampato su tre caratteri). 
		
		
		return null;
	}
	
	/**
	 * computes the number of
	 * patients per (their doctor's) specialization.
	 * The elements are sorted first by decreasing count and then by alphabetic specialization.<br>
	 * The strings are structured as "<i>### - SPECIALITY</i>" where <i>###</i>
	 * represent the number of patients (printed on three characters).
	 */
	public Collection<String> countPatientsPerSpecialization(){
		return null;
	}
	
	public void loadData(String path) throws IOException {
		
		try(BufferedReader in = new BufferedReader(new FileReader(path))){
			String line;
			while((line = in.readLine()) != null) {
				
				Scanner s = new Scanner(line);
				s.useDelimiter(";\\s*");
				
				String patternP = "([P]);([A-z]+);([A-z]+);(\\w+)";
				String patternD = "([M]);(\\d+);([A-z]+);([A-z]+);(\\w+);([A-z]+)";
				
				if (line.matches(patternP)) {
					s.next();
					addPatient(s.next(), s.next(), s.next());
				} else if (line.matches(patternD)) {
					s.next();
					String first, last, ssn, specialization;
					int docID;
					docID = Integer.parseInt(s.next());
					first = s.next();
					last = s.next();
					ssn = s.next();
					specialization = s.next();
					addDoctor(first, last, ssn, docID, specialization);
				} else {
					continue;
				}
				s.close();
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}


}
