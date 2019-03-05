package edu.cpp.cs.cs141.prog_assgmnt_3;

import java.io.Serializable;
import java.util.ArrayList;

public class Fish extends Animal implements Serializable{
	public enum TYPE{guppy, goldfish, carp, shark, swordfish}
	private TYPE variety;
	
	public Fish(Owner o, String n, int a, ArrayList<Disease> d, ArrayList<String> v, int t) {
		super(o,n,a,d,v);
		switch(t) {
		case 1:
			variety = TYPE.guppy;
			break;
		case 2:
			variety = TYPE.goldfish;
			break;
		case 3:
			variety = TYPE.carp;
			break;
		case 4:
			variety = TYPE.shark;
			break;
		case 5:
			variety = TYPE.swordfish;
			break;
		default:
			break;
			
		}
	}
	
	public String getVariety() {
		return variety.toString();
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
