package edu.cpp.cs.cs141.prog_assgmnt_3;
import java.util.*;

public class Animal {
	private Owner owner;
	protected String name;
	protected int age; 
	protected ArrayList<Disease> disease;
	protected ArrayList<String> vaccination;
	
	public Animal() {
		owner = null;
		name = "";
		age = 0;
		disease = null;
		vaccination = null;
	}
	public Animal(Owner o, String n, int a, ArrayList<Disease> d, ArrayList<String> v) {
		owner = o;
		name = n;
		age = a;
		disease = d;
		vaccination = v;
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public ArrayList<Disease> getDisease(){
		return disease;
	}
	public ArrayList<String> getVaccination(){
		return vaccination;
	}
	public void setName(String o) {
		name = o;
	}
	public void setOwner(Owner o) {
		owner = o;
	}
}

