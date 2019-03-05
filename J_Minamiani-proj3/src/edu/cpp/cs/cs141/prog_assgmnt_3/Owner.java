package edu.cpp.cs.cs141.prog_assgmnt_3;
import java.io.*;

public class Owner implements Serializable{
	private String name;
	private String address;
	private int phoneNumber;

	public Owner() {
		name = "";
		address = "";
		phoneNumber = 0;
	}
	public Owner(String n, String a, int p) {
		name = n;
		address = a;
		phoneNumber = p;
	}
	
	public void setName(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void display() {
		System.out.println("Owner Name: " + name);
		System.out.println("Owner Address: " + address);
		System.out.println("Owner Phone Number: " + phoneNumber);

	}
}
