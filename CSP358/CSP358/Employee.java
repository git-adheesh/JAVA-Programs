package CSP358;

import java.util.Arrays;
import java.util.Scanner;

class Designation
{
	String designation;
	double DA;
	
	public void setDesigAndDA(char dCode)
	{
		if(dCode == 'e')
		{
			this.designation = "Engineer";
			this.DA = 20000;
		}
		else if(dCode == 'c')
		{
			this.designation = "Consultant";
			this.DA = 32000;
		}
		else if(dCode == 'k')
		{
			this.designation = "Clerk";
			this.DA = 12000;
		}
		else if(dCode == 'r')
		{
			this.designation = "Receptionist";
			this.DA = 15000;
		}
		else if(dCode == 'm')
		{
			this.designation = "Manager";
			this.DA = 40000;
		}
	}
	
	
	void printDesigData()
	{
		System.out.println("Designation is: "+designation+" DA is: "+DA);
		System.out.println();
	}
	
}

public class Employee extends Designation
{
	int empID;
	double basic, hra, it;
	String empName, dateOfJoining, dept;
	char desigCode;
	
	double salary = 0;
	
	int pos;
	
	public void setData(int id, String name, String date, char dc, String dept, double b, double h, double it)
	{
		this.empID = id;
		this.empName = name;
		this.dateOfJoining = date;
		this.desigCode = dc;
		this.dept = dept;
		this.basic = b;
		this.hra = h;
		this.it = it;
		
		//Initializing the data from Designation Class
		setDesigAndDA(desigCode);
		
	}

	
	public void printData()
	{
		System.out.println(empID+" "+empName+" "+dateOfJoining+" "+desigCode+" "+dept+" "+basic+" "+hra+" "+it);
	}
	
	public void answer()
	{
		System.out.println("EMP No. \tEMP NAME\tDEPARTMENT\tDESIGNATION\tSALARY");
//		System.out.println();
		salary = basic+hra+DA+-it;
		
		System.out.println(empID+" \t\t"+empName+" \t\t"+dept+" \t\t"+designation+" \t\t"+salary);
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int userInput;
		
		char flag = 'y';
		
		int empID[] = {1001, 1002, 1003, 1004, 1005, 1006, 1007};
		String name[] = {"Ashish", "Sushma", "Rahul", "Chahat", "Ranjan", "Suman", "Tanmay"};
		String jd[] = {"01/04/2009", "23/08/2012", "12/11/2008", "29/01/2013", "16/07/2005","1/1/2000", "12/06/2006"};
		char dc[] = {'e', 'c', 'k', 'r', 'm', 'e', 'c'};
		String dept[] = {"R&D", "PM", "Acct", "Front Desk", "Engg", "Manufacutring", "PM"};
		double basicPay[] = {20000, 30000, 10000, 12000, 50000, 23000, 29000};
		double HRA[] = {8000, 12000, 8000, 6000, 20000, 9000, 12000};
		double IT[] = {3000, 9000, 1000, 2000, 20000, 4400, 10000};
		
		Employee obj[] =  new Employee[7]; //allocating memory
		
		for(int i = 0; i<obj.length; i++)
		{
			obj[i] = new Employee(); //creating actual objects
			obj[i].setData(empID[i], name[i], jd[i], dc[i], dept[i], basicPay[i], HRA[i], IT[i]);
			
		}
		
	/*	for(int i = 0; i<obj.length; i++)
		{
			obj[i].printData();
			obj[i].printDesigData();
		}*/
		
		while(flag == 'y')
		{
			
			System.out.println("Enter the EMPLOYEE ID (varies from 1001 to 1007) to display the details: ");
			userInput = sc.nextInt();
			
			System.out.println();
			System.out.println();
			int pos = (Arrays.binarySearch(empID, userInput)); //searching position in empID array
			if(pos >= 0 && pos < empID.length)
			{
				obj[pos].answer();
				System.out.println("Do you want to see the details of other Employee:(y/n) ");
				char ch = sc.next().charAt(0);
				if(ch=='n')
					flag='n';
			}
			else
				System.out.println("There is no Employee with EmpID: "+userInput);
			
			
		}
		System.out.println("THANKS!");
		
		sc.close();
		
}
}
