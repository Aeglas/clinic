import java.io.IOException;

import clinic.Clinic;


public class ExampleApp {

	public static void main(String[] args) {

		Clinic clinic = new Clinic();

		clinic.addPatient("Alice", "Green", "ALCGRN");
		clinic.addPatient("Robert", "Smith", "RBTSMT");
		clinic.addPatient("Steve", "Moore", "STVMRE");
		clinic.addPatient("Susan", "Madison", "SSNMDS");

		clinic.addDoctor("George", "Sun","SNUGRG", 14,"Physician");
		clinic.addDoctor("Kate", "Love", "LVOKTA",86,"Physician");

		try {
			clinic.assignPatientToDoctor("SSNMDS", 86);
			clinic.assignPatientToDoctor("ALCGRN", 14);
			clinic.assignPatientToDoctor("RBTSMT", 14);
			clinic.assignPatientToDoctor("STVMRE", 14);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
//		try {
//			clinic.loadData("C:\\Users\\Enrico\\Desktop\\file.txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(clinic.idleDoctors());
//		System.out.println(clinic.busyDoctors().toString());

		try {
			clinic.getDoctor(-1);
		} catch (Exception ex) {
			System.out.println("As expected we got the following exception for doctor -1:" + ex);
		}
	}

}
