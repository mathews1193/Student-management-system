package assignment3;

public class Student 
{
	String Name;
	double gpa;
	int idNumber;
	
	public Student()
	{
		Name = " ";
		gpa = 0;
		idNumber = 0;
	}
	public Student(String newname, double newgpa, int newidNumber)
	{
		this.Name = newname;
		this.gpa = newgpa;
		this.idNumber = newidNumber;
		
	}
	public Student(Student S)
	{
		Name = S.Name;
		gpa = S.gpa;
		idNumber = S.idNumber;
		
	}
	public void setName(String name)
	{
		this.Name = name;
	}
	public void setGpa(double gpa1)
	{
		this.gpa = gpa1;
	}
	public void setIdnumber(int id)
	{
		this.idNumber = id;
	}
	public String getName()
	{
		return Name;
	}
	public double getGpa()
	{
		return gpa;
	}
	public int getIdnumber()
	{
		return idNumber;
	}

}
