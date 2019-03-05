package edu.cpp.cs.cs141.prog_assgmnt_3;

public class Disease {
	private String status;
	private String disease;
	
	public Disease(String d, int a) {
		disease = d;
		if( a == 1)
			status = "Current";
		else
			status = "Past";
	}
	
	public String getDisease() {
		return disease;
	}
	public String getStatus() {
		return status;
	}
	
}
