package assignment3;

import java.io.*;
import java.util.Scanner;
import java. util. Arrays;
import java.util.Comparator;

public class StudentDatabase 
{
	private Student[] Students; // array of students in database // 
	private int NumStudents; // number of students in database // 
	private int max = 10; // max number of students in database // 
	private int currentNum; // current number of student left that can be added // 
	private Student noStudents = null; // null object of student information // 
	
	public StudentDatabase(int num) // constructor // 
	{
		NumStudents = num; // max number of student in database // 
		Students = new Student[NumStudents]; // max number of student in the database array // 
		NumStudents = 0;
	}
	public void AddStudent() // add a new student to the database // 
	{
		int num = currentNum; // number of student that can be added to the database // 
		
		while (num < max)
		{
		   Scanner data = new Scanner (System.in);
		   
		   System.out.print ("Enter the name of student " + (num + 1) + " :"); // user input student name // 
		   String name = data.nextLine();
		   
		   System.out.print ("Enter the GPA of student " + (num + 1) + " :"); // user input student gpa // 
		   double gpa = data.nextDouble();
		 
		   System.out.print ("Enter ID Number of student " + (num + 1) + " :"); // user input student Id number // 
		   int idNumber = data.nextInt();


		   Student list = new Student (name, gpa, idNumber); // call to student information and adds to the list in the database // 
		   Students[num] = list;
		   
		   System.out.println( "\nStudent " + (num + 1) + " has been successfully added to the database.");
		   
		   System.out.println("\nDo you want to contiune adding students"); 
		   System.out.println ("0 - Finish");
		   System.out.println("1 - Contiune");
		   
		   Scanner input = new Scanner (System.in); // choice for contiune or finishing addng students // 
		   int choice = input.nextInt();
		   
		   if (choice == 0) 
		   {
			   num++;
			   break;
		   }
		   else 
		   {
			   num++; 
		   }
		   
		}
		NumStudents = num;
		currentNum = num;
		System.out.println("There are " + NumStudents + " students in the database");
		DisplayStudents();
	}
	public void AddStudents(String StudentsFile) // adding students to database by file // 
	{
		Scanner fileReader;
		int count = 0;
	
		System.out.println("Searching for " + StudentsFile + " now in the system.......");
		
		try
		{
			fileReader = new Scanner(new File(StudentsFile));
			
			System.out.println("Opening " + StudentsFile + " now.......");
			
			while (fileReader.hasNextLine() && count <= max) // reading data from file  // 
			{
				if (this.NumStudents + 1 > max) // if number of students have reached the max // 
				{
					System.out.println("Invalid entry. The max number of student has been reached in database. (Max = 10)");
					System.out.println("If you want to add another student in the database, you need to remove one first.");
					
					 Scanner input = new Scanner (System.in);
					 int choice = input.nextInt();
					
					System.out.println("\nWhat would you like to do?"); // options to do after error message // 
					System.out.println("\n0 - Main Menu");
					System.out.println("\n1 - Remove a Student from the database");
					
					if (choice == 0)
						MainMenu(); // return to main menu // 
					else 
						RemoveStudentByName();// call to remove a student // 
				}
			  
				String line = fileReader.nextLine().trim(); // read string into a array and removes space before and after the data // 
				String[] data = line.split(","); // data breaks after the comma // 
				
				String name = data[0]; // student name being stored in array 0 // 
				double gpa = Double.parseDouble(data[1]); // GPA being stored in array 1 // 
				int id = Integer.parseInt(data[2]); // ID Number being stored in array 2 // 
				  
				Student list = new Student(name, gpa, id); // call to the set the student information and store to list // 
				this.Students[this.NumStudents++] = list; // store the current student information into the student information class // 
				count++;
			}
			
			fileReader.close(); // close file after reading // 
			System.out.println("\n" + count + " students were added successfully."); // display number of student added by file // 
			
			currentNum = max - count; // number of students left that can be added to database // 
	}
	catch (FileNotFoundException error) // error message // 
		{
			System.out.println(StudentsFile + "does not exist in the system"); // file not found and terminate program // 
			System.out.println("Terminating program");
			System.exit(0);
		}
		DisplayStudents();		// display database // 
	}
	public void DisplayStudents() // display student information on screen // 
	{ 
		
		System.out.println("\n\tStudent Database\n\t");
		System.out.println("Name\t"+"\tGPA\t" + "\tID\t\n");

		Student[] list = new Student[NumStudents];  // reading student information into a list to sort // 
		
		for (int i = 0; i < NumStudents; i++)
		{
			list[i] = Students[i];
		}

		Arrays.sort(list, new Comparator<Student>() // sort student by descending GPA
		{
			public int compare(Student student, Student student2) // compare two GPAs // 
			{
				if(student2.gpa > student.gpa)
				{
					return 1; // GPA 2 greater than GPA 1 // 
				}
				
				else if(student2.gpa == student.gpa)
				{
					return 0; // GPA equal // 
				}
				else
				{// GPA 1 greater than GPA 2 // 
				return -1;
				}
			}
		});

		for(int i = 0; i < NumStudents; i++)
		{
			System.out.println(list[i].Name + "\t\t" + list[i].gpa + "\t\t" + list[i].idNumber);
		}
	       
	    Scanner input = new Scanner(System.in);
	    
		System.out.println("\n0 - Main Menu"); // option to continue database or exit program // 
		System.out.println("1 - Exit");
		
		int count = input.nextInt();    
	       
	 if (count == 0)
	 {
		 System.out.println("There are " + NumStudents + " students in the database");
		 MainMenu(); // main menu // 
	 }
	 else 
	 {
		 System.out.println("There are " + NumStudents + " students in the database");
		 System.exit(0); // exit program // 
	 }
	       
   }
	public Student SearchStudentsByName(String StudentName) // Searches a student by their name in the database // 
	{
		for (int i = 0; i < NumStudents; i++)
        {
			if (Students[i].Name.equals(StudentName)) // if name entered and name in database match //
			{
				System.out.println(Students[i].Name +" is in the database."); // name found // 
			    return Students[i];
			}	
        }
		return noStudents;
	}
	public Student SearchStudentsByIdNumber(int StudentIdNumber) // searches a student in the database by Id Number // 
	{
		for (int i = 0; i < NumStudents; i++)
        {
			if (Students[i].idNumber == StudentIdNumber) // if ID number entered and ID number in database match // 
			{
				System.out.println("ID Number, " + Students[i].idNumber + " Found in the database." ); // display Student found // 
				return Students[i]; // return found // 
			}
        } 
		return noStudents;
	}
	public Student[] SearchStudentsByGpa(double StudentGpa) // search for GPA in the database // 
	{
		int num = 0;
		
		for (int i = 0; i < NumStudents; i++)
			{
			if(Students[i].gpa >= StudentGpa) // if GPA entered and GPA equal or greater than is found in database // 
				{
					num++;
				}
			}
		
		Student[] list = new Student[num]; // store student information that meet the GPA in a list // 
		
		num = 0;

		
		for(int i = 0; i < NumStudents; i++)
		{
			if(Students[i].gpa >= StudentGpa)
			{
				list[num] = Students[i];
				num++;
			}
		}
		
		if (num > 0)
		{
			System.out.println("Results for GPA Found in the database." ); // GPA found in database // 
			return list;
		}
		else
		{
			return null;
		}
	}
	public void RemoveStudentByName() // removes a student from the database by searching for their name // 
	{
		 Scanner data = new Scanner(System.in);
		 
		 System.out.println("Enter the name of the student you would to remove from the database."); // enter student name // 
         String StudentName = data.next();
		
		int count = -1; // count used to find the student in the list to remove // 
		
		for (int i = 0; i < NumStudents; i++)
		{
			if (Students[i].Name.equals(StudentName)) // if the name matches the one in the database // 
			  {
				count = i;
				break;
			  }
			else 
			  {
				System.out.println("Student information does not exist."); // not found // 
			  }
		}
		if (count > -1)
		{
			System.out.println("Removing " + Students[count].Name + " from the student databse........"); // removes student from the database // 
			
			for (int i = count + 1; i < NumStudents; i++)
				Students[i-1] = Students[i];
			  
			NumStudents--;
		}
		System.out.println("Student removed from the database successfully"); // Notifies that student was remove successfully // 
		System.out.println("There are " + NumStudents + " students in the database"); // current number of students in database // 
		DisplayStudents();
		
	}
	public void MainMenu() // menu system for the program // 
	{
		System.out.println("***********************************************");
		 
		 System.out.println("\tStudent Database\t\n");
		 
		 System.out.println("\t1 - Add A New Student\t");
		 System.out.println("\t2 - Load Students from a file\t");
		 System.out.println("\t3 - Display Students in Database\t");
		 System.out.println("\t4 - Remove A Student\t");
		 System.out.println("\t5 - Search by Student Name\t");
		 System.out.println("\t6 - Search by Student ID Number\t");
		 System.out.println("\t7 - Search by Student GPA\t");
		 System.out.println("\t8 - Exit\t");
		 
		  System.out.println("***********************************************");
		  
		 System.out.println("Choose an option.");
		 
		 Scanner main = new Scanner(System.in);
		 int choice = main.nextInt();
		 
		 Scanner data = new Scanner(System.in);
		 
		 switch (choice)
		 {
		 case 1: 
			 AddStudent(); // add student using user input // 
		 case 2:
			 System.out.println("Enter file Name"); // enter student name // 
	         String fileName = data.next();
             AddStudents(fileName); // load student information from file // 
		 case 3: 
			 DisplayStudents(); // display student information // 
		 case 4: 
			 RemoveStudentByName(); // remove student // 
		 case 5: 
			 System.out.println("Enter Student Name"); // enter student name // 
	         String StudentName = data.next();
			 Student db = SearchStudentsByName(StudentName); // return student information // 
			 if(db == null) // if null // 
			 {
                System.out.println("Invalid entry. Student name not found"); // if not found exit program // 
                System.exit(0);
             }
             else
                 System.out.println("Name: "+db.getName()+"\tGPA: "+db.getGpa()+"\tID: "+db.getIdnumber()); // if found // 
			 	 MainMenu();
		 case 6: 
			 System.out.println("Enter Student ID Number"); // enter Student ID // 
			 int StudentIdNumber = data.nextInt();
			 Student db1 =  SearchStudentsByIdNumber(StudentIdNumber);	// return student information based on ID number  // 
			 if(db1 == null) // if null // 
			 {
                System.out.println("Invalid entry. Student ID Number not found"); // if not found exit program // 
                System.exit(0);
             }
             else
                 System.out.println("Name: "+db1.getName()+"\tGPA: "+db1.getGpa()+"\tID: "+db1.getIdnumber()); // if found // 
			 	 MainMenu();
		 case 7: 
			 System.out.println("Enter Student GPA"); // enter GPA // 
	         double StudentGpa = data.nextDouble();
			Student[] db2 = SearchStudentsByGpa(StudentGpa); // return student information based on GPA equal to or greater than // 
			 if(db2 == null)
			 {
                System.out.println("Invalid entry. Student GPA not found");
                System.exit(0);
             }
             else
             { 
            	 for(int i = 0; i < db2.length; i++)
            	 {
            		 System.out.println("Name: "+db2[i].getName()+"\tGPA: "+db2[i].getGpa()+"\tID: "+db2[i].getIdnumber()); // display student information // 
            		 
            		 if (db2 [i+1] == null) // when list is finished break from loop // 
                         break;
            	 }
            	 MainMenu();
             }
		 case 8:
			 DisplayStudents(); // exit the program // 
		 }
	}		
}