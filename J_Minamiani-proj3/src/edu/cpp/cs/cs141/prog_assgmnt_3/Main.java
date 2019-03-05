/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #3
 *
 * Write a program that allows user to take charge of all appointments in a Veterinary Office.
 *　
 *Justen Akira Minamitani
 */
package edu.cpp.cs.cs141.prog_assgmnt_3;
import java.io.*;
import java.util.*;

public class Main implements Serializable, Comparator<Appointments>{
	ArrayList<Appointments> list = new ArrayList<Appointments>();
	ArrayList<Appointments> found = new ArrayList<Appointments>();
	ArrayList<Owner> owners = new ArrayList<Owner>();
	ArrayList<Appointments> history = new ArrayList<Appointments>();
	

	Scanner input = null;
	boolean done = false;
	int command;
	String commandString; 
	
	String client;
	int dateM, dateD;
	int timeMin,timeHour;
	boolean resolved;
	
	Owner owner;//temp used to add owner
	String name;
	String address;
	String phoneNumber;
	
	Dog dog;//temp used to add animal
	Bird bird;
	Fish fish;
	int animalType;
	String animalName;
	int variety;
	int age; 
	ArrayList<Disease> disease = new ArrayList<Disease>();
	ArrayList<String> vaccination = new ArrayList<String>();
	

	public Main() {//constructor 
		while(!done) { //repeat asking for input until exit is chosen
			askInput();
		}
		System.out.println("Program will end.\nGOODBYE");
	}
	
	
	public void menu() {
		System.out.println("\t----Choose command Number(1,2,3,4,5,6,7,8)-----\n"
				+ "1) Display list of Unresolved Appointments\n"
				+ "2) Display list of All Appointments\n"
				+ "3) Make appointment Resolved\n"
				+ "4) Search appointment\n"
				+ "5) Add appointment\n"
				+ "6) Save Data\n"
				+ "7) Load Data\n"
				+ "8) Exit");
	}
	

	
	public void askInput() {
		input = new Scanner(System.in);
		menu();
		
		do {
			try {//repeat until input is between 1-6
				input = new Scanner(System.in);
				command = input.nextInt();
				while(command < 0 || command > 9 ){
					System.out.println("Invalid Input");
					menu();
					command = input.nextInt();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input");
				menu();
				input.hasNextInt();
			}
		}while(command < 0 || command > 9);

		
		switch(command) {
		case 1:
			displayList(list);
			break;
		case 2: 
			displayList(history);
			break;
		case 3:
			makeResolved();
			break;
		case 4:
			switch(showSearch()) {
			case 1: //search by owner
				searchByOwner();
				break;
			case 2: //search by date
				searchByDate();
				break;
			case 3:
				searchByAnimalType();
			default:
				break;
			}
			break;
		case 5:
			addAppointment();
			break;
		case 6: 
			save();
			break;
		case 7:
			load();
			break;
		case 8:
			done = true;
			break;
		default:
			break;
		}
		
	}
	
	
	public void makeResolved() {
		int index=0;
		if(list.size()==0) {
			System.out.println("**No Appointments in list**");
			return;
		}
		do {
			try {//repeat until input is within list size
				System.out.println("Input Appointment ID# to be resolved:");
				System.out.println("Type \"9999\" to return");
				input = new Scanner(System.in);
				index = input.nextInt();
				input.nextLine();
				if(index == 9999)
					return;
				while(index < 0 || index > list.size()){
					System.out.println("Invalid Input");
					index = input.nextInt();
					
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input");
				input.hasNextInt();
			}
		}while(index < 0 || index > list.size());
		list.get(index-1).setResolved(true);
		history.get(index-1).setResolved(true);
		displayList(list);
		list.remove(index-1);
	}
	
	
	public void addAppointment() {
		boolean done1;
		int month = -1;
		
		System.out.println("\t----Input Information----");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\t  --Owner Information-- ");//////////////
		System.out.println("Name of Owner: ");///////////
			input = new Scanner(System.in);
			name = input.next();
		System.out.println("Owner Address: ");///////////
			input = new Scanner(System.in);
			address = input.next();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			boolean valid = false;
			do {
				System.out.println("Owner phone number(Ignore Area Code): ");
				input = new Scanner(System.in);
				phoneNumber = input.next();
				if(phoneNumber.length()!=7) { //check is there is 7 digits
					valid = false;
					System.out.println("Invalid Input");
					continue;
				}
				for ( int i=0 ; i < phoneNumber.length(); i++){ //check each char if integer
					valid = Character.isDigit(phoneNumber.charAt(i));
				}
				if(!valid) {//if not set of int, redo input
					System.out.println("Invalid Input");
					continue;
				}
				valid = true;
			}while(!valid);
		
		int pn = Integer.parseInt(phoneNumber);
		owner = new Owner(name, address, pn);
		owners.add(owner); //add owners to owner list
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		input.nextLine();
		System.out.println("Select Animal: \n1) Dog \n2) Bird \n3) Fish");
		do {
			try {//repeat until input is between 1-3
				input = new Scanner(System.in);
				animalType = input.nextInt();
				while(animalType < 0 || animalType > 4 ){
					System.out.println("Invalid Input");
					animalType = input.nextInt();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input - Enter a number for animal Type(1-3) -");
				input.hasNextInt();
			}
		}while(animalType < 0 || animalType > 4 );
		
		/////////////////////////////////////////////////////////////////////////////////////
		input.nextLine();
		switch(animalType) {
		case 1:   		//dog
			System.out.println("Select dog breed: \n1)Shiba \n2)Corgi \n3)Husky \n4)Pug \n5)Dachshund ");
			do {
				try {//repeat until input is between 1-3
					input = new Scanner(System.in);
					variety = input.nextInt();
					while(variety < 0 || variety > 6 ){
						System.out.println("Invalid Input");
						variety = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("Invalid Input - Enter a number for Dog Breed(1-6) -");
					input.hasNextInt();
				}
			}while(variety < 0 || variety > 6);
			break;
		case 2://bird
			System.out.println("Select Bird species: \n1)Parrot \n2)Owl \n3)Sparrow \n4)Crane \n5)Penguin ");
			do {
				try {//repeat until input is between 1-3
					input = new Scanner(System.in);
					variety = input.nextInt();
					while(variety < 0 || variety > 6 ){
						System.out.println("Invalid Input");
						variety = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("Invalid Input - Enter a number for Bird species(1-6) -");
					input.hasNextInt();
				}
			}while(variety < 0 || variety > 6);
			break;
		case 3://fish
			System.out.println("Select Fish species: \n1)Guppy \n2)Goldfish \n3)Carp \n4)Shark \n5)Swordfish ");
			do {
				try {//repeat until input is between 1-3
					input = new Scanner(System.in);
					variety = input.nextInt();
					while(variety < 0 || variety > 6 ){
						System.out.println("Invalid Input");
						variety = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("Invalid Input - Enter a number for Fish species(1-6) -");
					input.hasNextInt();
				}
			}while(variety < 0 || variety > 6);
			break;
			default:
				break;
		}
		
		input.nextLine();
		System.out.println("Pet Name: ");///////////
		input = new Scanner(System.in);
		animalName = input.next();
		
		System.out.println("Pet age: ");///////////
		do {
			try {//repeat until input is between 1-12
				input = new Scanner(System.in);
				age = input.nextInt();
				while(age < 0){
					System.out.println("Invalid Input");
					age = input.nextInt();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input - Enter a number for Month(1-12) -");
				input.hasNextInt();
			}
		}while(age < 0);
		
		boolean done = false;
		String temp;
		while(!done) {
			System.out.println("Diseases Name(if any): \n**Type \"end\" to continue**");///////////
			input = new Scanner(System.in);
			temp = input.next();
			input.nextLine();
			
			if(temp.equals("end"))
				break;
			
			int tempp=-1;
			System.out.println("Is Disease:\n1) Current\n2) Past ");///////////
			do {
				try {//repeat until input 1 or 2
					input = new Scanner(System.in);
					tempp = input.nextInt();
					while(tempp < 0 || tempp > 3){
						System.out.println("Invalid Input");
						tempp = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("Invalid Input - Enter a number for current or past -");
					input.hasNextInt();
				}
			}while(tempp < 0 || tempp > 3);
			
			Disease diseases = new Disease(temp, tempp);
			disease.add(diseases);
		}
		
		
		if(disease.size()!=0) {
			done = false;
			while(!done) {
				System.out.println("Vaccination Name(if any): \n**Type \"end\" to continue**");///////////
				input = new Scanner(System.in);
				temp = input.next();
				if(temp.equals("end"))
					break;
				vaccination.add(temp);
			}
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("Name of Client: ");///////////
			input = new Scanner(System.in);
			client = input.next();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("Appointment Date Month(1-12): ");///////////
		do {
			try {//repeat until input is between 1-12
				input = new Scanner(System.in);
				dateM = input.nextInt();
				while(dateM < 0 || dateM > 13 ){
					System.out.println("Invalid Input");
					dateM = input.nextInt();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input - Enter a number for Month(1-12) -");
				input.hasNextInt();
			}
		}while(dateM < 0 || dateM > 13 );

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("\t----" + month + "/ __ / 2017----");
		System.out.println("Appointment Date Day(1-31): ");///////////
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			do {
				try {//repeat until input is between 1-31
					input = new Scanner(System.in);
					dateD = input.nextInt();
					
					while(dateD < 0 || dateD > 32 ){
						System.out.println("Invalid Input");
						dateD = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("Invalid Input - Enter a number for Date(1-31) -");
					input.hasNextInt();
				}
			}while(dateD < 0 || dateD > 32 );
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11) {
			do {
				try {//repeat until input is between 1-30
					input = new Scanner(System.in);					
					dateD = input.nextInt();
					while(dateD < 0 || dateD > 31 ){
						System.out.println("Invalid Input");
						dateD = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("Invalid Input - Enter a number for Date(1-30) -");
					input.hasNextInt();
				}
			}while(dateD < 0 || dateD > 31 );
		}
		else if(month == 2) {
			do {
				try {//repeat until input is between 1-28
					input = new Scanner(System.in);
					dateD = input.nextInt();
					while(dateD < 0 || dateD > 29 ){
						System.out.println("Invalid Input");
						dateD = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("Invalid Input - Enter a number for Date(1-28) -");
					input.hasNextInt();
				}
			}while(dateD < 0 || dateD > 29 );
		}
		
		System.out.println("Appointment date set to: \n\t----" + month + " / " + dateD + " / 2017----");
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("Appointment Time Hour(0-23): ");///////////
		do {
			try {//repeat until input is between 1-24
				input = new Scanner(System.in);
				timeHour = input.nextInt();
				while(timeHour < 0 || timeHour > 24 ){
					System.out.println("Invalid Input");
					timeHour = input.nextInt();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input - Enter a number for Hour(0-23) -");
				input.hasNextInt();
			}
		}while(timeHour < 0 || timeHour > 25 );
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("\t----" + timeHour + ":__----");///////////
		System.out.println("Appointment Time Min(0-59): ");///////////
		do {
			try {//repeat until input is between 1-24
				input = new Scanner(System.in);
				timeMin = input.nextInt();
				while(timeMin < 0 || timeMin > 60 ){
					System.out.println("Invalid Input");
					timeMin = input.nextInt();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input - Enter a number for Hour(0-24) -");
				input.hasNextInt();
			}
		}while(timeMin < 0 || timeMin > 60 );
		System.out.println("Appointment time set to: \n\t----" + timeHour + ":" + timeMin + "----\n" );///////////

		//~~~~~~~      ~~~~~~~~~~~~~   ~~~~~~~~~~~~~~~~~   ~~~~~~~~~~~~~~     ~~~~~~~
		if(animalType ==1) {
			dog = new Dog(owner, animalName, age, disease, vaccination, variety);
			list.add(new Appointments(client, dateM, dateD, timeHour, timeMin, owner, dog)); //adding new Appointment into list
			history.add(new Appointments(client, dateM, dateD, timeHour, timeMin, owner, dog)); //adding new Appointment into list
		}
		else if(animalType ==2) {
			bird = new Bird(owner, animalName, age, disease, vaccination, variety);
			list.add(new Appointments(client, dateM, dateD, timeHour, timeMin, owner, bird)); //adding new Appointment into list
			history.add(new Appointments(client, dateM, dateD, timeHour, timeMin, owner, bird)); //adding new Appointment into list
		}
		else if(animalType ==3) {
			fish = new Fish(owner, animalName, age, disease, vaccination, variety);
			list.add(new Appointments(client, dateM, dateD, timeHour, timeMin, owner, fish)); //adding new Appointment into list
			history.add(new Appointments(client, dateM, dateD, timeHour, timeMin, owner, fish)); //adding new Appointment into list
		}
		System.out.println("\t**Appointment added with ID # " + (list.size()+1) + "**");
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public int showSearch() {
		if(list.size() == 0) {
			System.out.println("**No Appointments in list**");
			return 0;
		}
		input = new Scanner(System.in);
		System.out.println("\t--Search by--\n"
				+ "1) Seach by owner\n"
				+ "2) Search by date\n"
				+ "3) Search by Animal Type");
		do {
			try {//repeat until input is between 1-2
				input = new Scanner(System.in);
				command = input.nextInt();
				while(command < 0 || command > 4 ){
					System.out.println("Invalid Input");
					command = input.nextInt();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input");
				input.hasNextInt();
			}
		}while(command < 0 || command > 4 );
		return command;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	public void searchByAnimalType() {
		System.out.println("\t--Search by--\n"
				+ "1) List of Dog Appointments\n"
				+ "2) List of Bird Appointments\n"
				+ "3) List of Fish Appointments\n");
		do {
			try {//repeat until input is between 1-2
				input = new Scanner(System.in);
				command = input.nextInt();
				input.nextLine();
				while(command < 0 || command > 4){
					System.out.println("Invalid Input");
					command = input.nextInt();
					input.nextLine();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input");
				input.hasNextInt();
			}
		}while(command < 0 || command > 4);
		
		switch(command) {
		case 1:
			for(int i = 0; i < list.size();i++) { 
				if(list.get(i).isDog())
					found.add(list.get(i));
			}
			displayList(found);
			found.clear();
			break;
		case 2:
			for(int i = 0; i < list.size();i++) { 
				if(list.get(i).isBird())
					found.add(list.get(i));
			}
			displayList(found);
			found.clear();
			break;
		case 3:
			for(int i = 0; i < list.size();i++) { 
				if(list.get(i).isFish())
					found.add(list.get(i));
			}
			displayList(found);
			found.clear();
			break;
			default:
				break;
		}
	}
	
	public void searchByOwner() {
		if(list.size() == 0) {
			System.out.println("**No Appointments in list**");
			return;
		}
		String search;
		System.out.println("\t----Search by Owner name----");///////////
		System.out.println("Input Owner name:");///////////
		input = new Scanner(System.in);
		search = input.next();
		Appointments find = new Appointments();
		find.getOwner().setName(search);
		for(int i = 0; i < list.size();i++) {
			if(compare(list.get(i) , find) == 0 )
				found.add(list.get(i));
		}
		if(found.isEmpty())
			System.out.println("**Owner name not found**");
		displayList(found);
		found.clear();
	}
	public void searchByDate() {
		int searchMonth=-1;
		int searchDay=-1;
		
		System.out.println("\t----Search by Date----");///////////
		System.out.println("Input Month: ");///////////
		do {
			try {//check month////////
				input = new Scanner(System.in);
				searchMonth = input.nextInt();
				while(searchMonth < 0 || searchMonth > 13 ){
					System.out.println("Invalid Input");
					searchMonth = input.nextInt();
				}
			}catch(InputMismatchException ex){ // catching if input is not an int value
				System.out.println("Invalid Input");
				input.hasNextInt();
			}
		}while(searchMonth < 0 || searchMonth > 13 );
		
		System.out.println("\t----" + searchMonth + "/ __ / 2017----");
		System.out.println("Input Day: ");///////////
		if(searchMonth == 1 || searchMonth == 3 || searchMonth == 5 || searchMonth == 7 || searchMonth == 8 || searchMonth == 10 || searchMonth == 12) {
			do {
				try {//repeat until input is between 1-31
					input = new Scanner(System.in);
					searchDay = input.nextInt();
					
					while(searchDay < 0 || searchDay > 32 ){
						System.out.println("Invalid Input");
						searchDay = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("\t--Invalid Input - Enter a number for Date(1-31)--");
					input.hasNextInt();
				}
			}while(searchDay < 0 || searchDay > 32 );
		}
		else if(searchMonth == 4 || searchMonth == 6 || searchMonth == 9 || searchMonth == 11) {
			do {
				try {//repeat until input is between 1-30
					input = new Scanner(System.in);					
					searchDay = input.nextInt();
					while(searchDay < 0 || searchDay > 31 ){
						System.out.println("Invalid Input");
						searchDay = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("\t--Invalid Input - Enter a number for Date(1-30)--");
					input.hasNextInt();
				}
			}while(searchDay < 0 || searchDay > 31 );
		}
		else if(searchMonth == 2) {
			do {
				try {//repeat until input is between 1-28
					input = new Scanner(System.in);
					searchDay = input.nextInt();
					while(searchDay < 0 || searchDay > 29 ){
						System.out.println("Invalid Input");
						searchDay = input.nextInt();
					}
				}catch(InputMismatchException ex){ // catching if input is not an int value
					System.out.println("\t--Invalid Input - Enter a number for Date(1-28)--");
					input.hasNextInt();
				}
			}while(searchDay < 0 || searchDay > 29 );
		}
		for(int i = 0; i < list.size();i++) {
			if(list.get(i).getDateMonth() == searchMonth && list.get(i).getDateDay()==searchDay) 
				found.add(list.get(i));
		}

		if(found.isEmpty())
			System.out.println("**Appointment Date not found**");
		displayList(found);
		found.clear();
	}
	
	
	
	public void displayList(ArrayList<Appointments> a) {
		System.out.println("\t-----List of Appointments----" );
		if(a.size() == 0) {
			System.out.println("**No Appointments in list**");
			return;
		}
		for(int i = 0; i < a.size();i++) { 
			System.out.println("Client #" + (i+1) + ":");
			a.get(i).display();
		}
	}
	
	
	/*
	//compare Owner name
	public int compare(Appointments a,String s){  
		String res = a.getOwner().getName(); 
		return res.compareTo(s);  // 1 if greater, -1 is less, 0 if equal
	}
	*/
	@Override
	public int compare(Appointments a1, Appointments a2) {
		String s1 = a1.getOwner().getName(); 
		String s2 = a2.getOwner().getName(); 
		return s1.compareTo(s2);  // 1 if greater, -1 is less, 0 if equal
	}
	public void save() {
		try{  
			input = new Scanner(System.in);
			System.out.println("Input file name to be loaded:");
			commandString = input.next();
			FileOutputStream saveFile=new FileOutputStream(commandString + ".dat");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			save.writeObject(list);	
			save.writeObject(owners);
			save.close(); 
			saveFile.close();
		}
		catch(Exception exc){
			exc.printStackTrace(); // If error, print error info.
			System.out.println("\t---Save Fail---");
		}
	}
	
	public void load() {
		try{
			input = new Scanner(System.in);
			System.out.println("Input file name to be loaded:");
			commandString = input.next();
			FileInputStream saveFile = new FileInputStream(commandString + ".dat");
			ObjectInputStream save = new ObjectInputStream(saveFile);

			
			list = (ArrayList<Appointments>) save.readObject();
			owners = (ArrayList<Owner>) save.readObject();

			save.close(); 
			saveFile.close();
		}
		catch(Exception exc){
			exc.printStackTrace(); // If there was an error, print the info.
			System.out.println("\t---Load Fail---");
		}
			System.out.println("\t---Successfully Loaded Data---");
	}
	
	public static void main(String[] args) {
		Main program = new Main();	
	}


	


	

}
