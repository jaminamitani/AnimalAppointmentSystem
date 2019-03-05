package edu.cpp.cs.cs141.prog_assgmnt_3;

import java.util.ArrayList;
import java.io.Serializable;

public class Bird extends Animal implements Serializable{
	public enum TYPE{parrot, owl, sparrow, crane, penguin}
	private TYPE variety;
	
	
	public Bird(Owner o, String n, int a, ArrayList<Disease> d, ArrayList<String> v, int t) {
		super(o,n,a,d,v);
		switch(t) {
		case 1:
			variety = TYPE.parrot;
			break;
		case 2:
			variety = TYPE.owl;
			break;
		case 3:
			variety = TYPE.sparrow;
			break;
		case 4:
			variety = TYPE.crane;
			break;
		case 5:
			variety = TYPE.penguin;
			break;
		default:
			break;
			
		}
	}
	public void displayAnimal() {
	    System.out.println("Pet Name: " + name + "  --  " + variety);
	    System.out.println("Pet Age: " + age);
	    System.out.print("Pet Disease list: \n");
	    for(int i = 0; i < disease.size(); i++) {
		    System.out.println(" 　　" + disease.get(i).getDisease() + " - " + disease.get(i).getStatus());
	    }
	    System.out.print("Pet Vaccination list: \n");
	    for(int i = 0; i < vaccination.size(); i++) {
		    System.out.println(" 　　" + vaccination.get(i));
	    }	   
	}
}
