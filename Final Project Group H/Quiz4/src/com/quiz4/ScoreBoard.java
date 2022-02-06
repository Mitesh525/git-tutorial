package com.quiz4;
import java.sql.*;
import java.util.Scanner;

//Author-Abhijeet Suravase (Worked on Designed methods and Formatting Output of Userinput and Calulating Score method) 
//Author-Mitesh Sawant-Designed displayscore method

public class ScoreBoard extends Show_Questions implements UserInput 
  {
	static PreparedStatement preparedstatement = null;
    static int Student_id;
	static Connection connect=null;
	ResultSet rs;
   
	public void displayScore()
	{
	     int count=1;
	     try {
	     connect=ConnectDB.getConnection();
		 preparedstatement=connect.prepareStatement("SELECT studentinfo.student_id,studentinfo.student_name ,scoreinfo.Score,scoreinfo.Grade FROM studentinfo left join scoreinfo on studentinfo.student_id=scoreinfo.student_id  order by Score desc "); 
	 	 	 
	 	 	ResultSet rs=preparedstatement.executeQuery(); 
	 	 	System.out.printf("%10s %10s %26s %10s %10s\n","Sr No","Student_id" ,"Student Name" ," Score", "Grade"); 

	 	 	while(rs.next()) { 
	 	 	
	 	 	System.out.printf("%10s %10s %26s %10s %10s \n",count,rs.getInt(1) ,rs.getString(2),rs.getString(3),rs.getString(4));
	 	 	count++;
	 	 	}
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }

	        

	}
	
	public void getUserInput()throws Exception

	{
		connect=ConnectDB.getConnection();
		 System.out.println("\tENTER YOUR STUDENT ID");
		 	 Scanner scanner=new Scanner(System.in);
		 	 Student_id=scanner.nextInt();
		 	 
		 	preparedstatement=connect.prepareStatement("select Student_id from studentinfo"); 
		 	 
			 	ResultSet rs=preparedstatement.executeQuery();
			 	while(rs.next())
			 	{
			 	int si=rs.getInt(1);//si is variable to store student id from table
		 	 
		 	 if(Student_id==si)
		 	 {
		 		System.out.println("\tSTUDENT ID IS ALREADY PRESENT IN THE SYSTEM");	 
		 	 }
			 	}
		 	 
		     System.out.println("\tENTER YOUR NAME");
		     String Student_name=scanner.next();
		 	 System.out.println("\t---------------------------------------------------");

	try { 
		preparedstatement=connect.prepareStatement("insert into studentinfo(student_id,student_name) values(?,?)");  
		preparedstatement.setInt(1,Student_id);//1 specifies the first parameter in the query  
		preparedstatement.setString(2,Student_name);  
		 	 	  
		 	 	
		 	 	preparedstatement.executeUpdate();
		 	 	
		 	 	
			    System.out.println("\n\tNOW YOU CAN TAKE THE QUIZ!!! HERE WE GO...\n");
 		 		System.out.println("\t---------------------------------------------------");

		}
	catch (Exception e) {
			e.printStackTrace();
		} 
	
	}
	
public void calculateScore(int Score) {
	
try {   
 		
 		System.out.println("\tYOUR SCORE = "+Score);
 		
 		connect=ConnectDB.getConnection();
 		
 		if(Score>=8 && Score<=10)
 		{
 			preparedstatement =connect.prepareStatement("insert into scoreinfo values(?,?,?)");
 			preparedstatement.setInt(1, Score);
 			preparedstatement.setString(2, "A");
 			preparedstatement.setInt(3, Student_id);
 			preparedstatement.executeUpdate();
 			System.out.println("\n\tCONGRATULATIONS YOU ARE CLASS A PERFORMER");
 		}
 		else if(Score>=6 && Score<8)
 		{
 			preparedstatement = connect.prepareStatement("insert into scoreinfo values(?,?,?)");
 			preparedstatement.setInt(1, Score);
 			preparedstatement.setString(2, "B");
 			preparedstatement.setInt(3, Student_id);
 			preparedstatement.executeUpdate();
 			System.out.println("\n\tCONGRATULATIONS YOU ARE CLASS B PERFORMER");
 		}
 		else if(Score>4 && Score<6)
 		{
 			preparedstatement = connect.prepareStatement("insert into scoreinfo values(?,?,?)");
 			preparedstatement.setInt(1, Score);
 			preparedstatement.setString(2, "C");
 			preparedstatement.setInt(3, Student_id);
 			preparedstatement.executeUpdate();
 			System.out.println("\n\tCONGRATULATIONS YOU ARE CLASS C PERFORMER");
 		}
 		else if(Score<5) {
 			preparedstatement = connect.prepareStatement("insert into scoreinfo values(?,?,?)");
 			preparedstatement.setInt(1, Score);
 			preparedstatement.setString(2, "D");
 			preparedstatement.setInt(3, Student_id);
 			preparedstatement.executeUpdate();
 	 		System.out.println("\n\tSORRY YOU FAILED IN THIS QUIZ!!!All THE BEST FOR NEXT TIME!!!");
 	 		}
}
 		catch(Exception e)
 		{
 			e.printStackTrace();
 		}
       finally 
       {
	
       }
}

}


