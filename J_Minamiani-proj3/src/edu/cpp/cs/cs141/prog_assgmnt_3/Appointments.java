package edu.cpp.cs.cs141.prog_assgmnt_3;
import java.io.Serializable;

public class Appointments implements Serializable{

	private String client;
	private int dateM, dateD;
	private int timeMin,timeHour;
	private boolean resolved;
	private Owner owner;
	private Dog dog;
	private Bird bird;
	private Fish fish;
	private boolean bb,dd,ff;
	
	public Appointments() {
		client = "";
		dateM = 0;
		dateD = 0;
		timeMin = 0;
		timeHour = 0;
		resolved = false;
		owner = new Owner();
		dog = null;
		fish = null;
		bird = null;
	}
	public Appointments(String c, int m, int d, int min, int hour, Owner o, Dog a) {
		client = c;
		dateM = m;
		dateD = d;
		timeMin = min;
		timeHour = hour;
		resolved = false;
		owner =  o;
		dog = a;
		dd = true;
	}
	public Appointments(String c, int m, int d, int min, int hour, Owner o, Bird a) {
		client = c;
		dateM = m;
		dateD = d;
		timeMin = min;
		timeHour = hour;
		resolved = false;
		owner =  o;
		bird = a;
		bb = true;
	}
	public Appointments(String c, int m, int d, int min, int hour, Owner o, Fish a) {
		client = c;
		dateM = m;
		dateD = d;
		timeMin = min;
		timeHour = hour;
		resolved = false;
		owner =  o;
		fish = a;
		ff = true;
	}
	public String status() {
		if(!resolved) 
			return "Outstanding";	
		else//appointment finished
			return "Resolved";
	}
	public Owner getOwner() {
		return owner;
	}
	public int getDateMonth() {
		return dateM;
	}
	public int getDateDay() {
		return dateD;
	}
	
	public boolean isDog() {
		return dd;
	}public boolean isBird() {
		return bb;
	}public boolean isFish() {
		return ff;
	}
	public void setOwner(Owner o) {
		owner = o;
	}
	public void setClient(String o) {
		client = o;
	}
	public void setDateM(int o) {
		dateM = o;
	}
	public void setDateD(int o) {
		dateD = o;
	}
	public void setTimeM(int o) {
		timeMin = o;
	}
	public void setTimeH(int o) {
		timeHour = o;
	}
	public void setResolved(boolean r) {
		resolved = r;
	}
	
	
	public void display() {
		System.out.println("  --Owner Information-- ");
		owner.display();
		System.out.println("  --Pet Information-- ");
		if(isDog()) {
			dog.displayAnimal();
		}
		else if(isBird()) {
			bird.displayAnimal();
		}
		else if(isFish()) {
			fish.displayAnimal();
		}
		
		System.out.println("Client name: " + client);
		System.out.println("Appointment Date(M/D/Y): " + dateM + "/" + dateD + "/2017");
		System.out.println("Appointment Time:\t" + timeHour + ":" + timeMin);
		System.out.println("Status: " + status() + "\n");
	}
	
}
