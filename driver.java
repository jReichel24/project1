package project1;
import java.util.Scanner;
import java.io.*;

public class driver {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
	{
		int menuOption;
		RandomAccessFile raf = null;
		File studentList;
		Scanner in = new Scanner(System.in);
		Scanner inputFile = null;
		String firstName, lastName;
		int id;
		double gpa;
		
		while(true)
		{
			//show all menu options
			printMenu();
			menuOption = in.nextInt();
			
			switch(menuOption)
			{
				//create random access file
				case 1:
					//get the input and output file
					System.out.print("\nEnter input file: students.txt\n");
/*******************studentList = new File(in.next());******************************/
					studentList = new File("students.txt");
					System.out.print("Enter the output file name: ");
					raf = new RandomAccessFile(in.next(), "rw");
					
					try
					{
						inputFile = new Scanner(studentList);
						
						//read and store the student's data from the file
						firstName = inputFile.next();
						lastName  = inputFile.next();
						id = inputFile.nextInt();
						gpa = inputFile.nextDouble();
						
						System.out.println(firstName);
						System.out.println(lastName);
						System.out.println(id);
						System.out.println(gpa+"\n");
						
						//create a new student object with the info
//						Student student = new Student(firstName, lastName, id, gpa);
						
					}
					catch(FileNotFoundException e)
					{
						System.out.println(e.getMessage());
					}
					break;
			
				//display the random access file
				case 2:
					//get the file name
					System.out.print("enter the random access file name: ");
					raf = new RandomAccessFile(in.next(), "rw");
					
					//try to display the file contents
					try
					{
						raf.seek(0);
						System.out.println(raf.readUTF());
						System.out.println(raf.readUTF());
						System.out.println(raf.readInt());
						System.out.println(raf.readDouble());
					}
					catch(FileNotFoundException e) {
						System.out.println(raf + " was not found!");
					}
					break;
			
				//retrieve a record
				case 3:
					//get the id to search for
					System.out.print("enter a student's id: ");
					id = in.nextInt();
					
					raf.seek(40);		//skips past the student's name and goes to the id
					if(id == raf.readInt())
					{
						//if found, print out record
						System.out.println("Record found!");
						raf.seek(raf.getFilePointer()-40);
						System.out.print(raf.readUTF()+"\t");
						System.out.print(raf.readUTF()+"\t");
						raf.readInt();
						System.out.println(raf.readDouble());
					}
					else
						raf.seek(raf.getFilePointer()+92);
					
					
					
					break;
			
				//modify a record
				case 4:
					
					break;
			
				//add a new record
				case 5:
					
					break;
			
				//delete a record
				case 6:
					
					break;
			
				//exit program
				case 7:
					try{
						in.close();
						//inputFile.close();
						raf.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
					
					System.exit(0);
					break;
				}
		}
	}
	
	public static void printMenu()
	{
		System.out.println("Select an opton: ");
		System.out.println("1: create a new random access file.");
		System.out.println("2: display a random access file.");
		System.out.println("3: retrieve a record from the file.");
		System.out.println("4: modify a record.");
		System.out.println("5: add a new record.");
		System.out.println("6: delete a record.");
		System.out.println("7: quit.");
	}
}
