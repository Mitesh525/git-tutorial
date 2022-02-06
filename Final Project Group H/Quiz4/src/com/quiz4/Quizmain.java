package com.quiz4;
import java.sql.*;
import java.util.*;

//Author-Mitesh Sawant(Worked on Integration of Project)

public class Quizmain { 
	
	static PreparedStatement preparedstatement = null;
	static int Score=0;
	static Connection connect=null;
	static boolean flag=false;//FOR USER INPUT VALIDATION
	
	public static void main(String[] args) {
 	
		    System.out.println("\t---------------------------------------------------");
            System.out.println("\n\t!!!WELCOME TO THE JAVA QUIZ!!! \n");
 			System.out.println("\t---------------------------------------------------");
 			
 			 
 			 System.out.println("\t1.LOGIN WITH YOUR STUDENT ID AND NAME!!!\n ");
 			 System.out.println("\t2.SEE ONLY SCOREBOARD AND YOUR POSITION!!!\n ");
 			 System.out.println("\t3.SEARCH YOUR SCORE BY USING YOUR ID!!! ");
 			 Scanner scanner=new Scanner(System.in);
 			 do { 
 				 String choice=null;
 				do {
 			 System.out.print("\n\tPLEASE ENTER YOUR CHOICE >>>\n ");
 			
 			  choice=scanner.next();
 			 if(choice.equals("1")||choice.equals("2")||choice.equals("3")) 
 			 {
 				flag=false; 
 			 }
 			 else {
 				 
 				 flag=true;
 				System.out.print("\n\tWRONG CHOICE>>>ENTER CORRECT CHOICE AGAIN >>>\n ");
 			 }//2nd do close
 				}while(flag);
 		
 			
 			 switch(choice) {
 			
 			 case "1":
 				
 			 try {
 				ConnectDB.getConnection();//calling static method of ConnectDB class
 				
 		 	    ScoreBoard scoreboard=new ScoreBoard();
 		 	    scoreboard.getUserInput();//calling user input method of ScoreBoard class
 		 		Show_Questions showquestions=new Show_Questions();
 		 		showquestions.Questions();
 		 		
 		 		System.out.println("\t---------------------------------------------------");
 		 	    
 		 		System.out.println("\n\tYOUR RECORD IS PRESERVED FOR FURTHER REFRENCES\n");
 		        System.out.println("\tDO YOU WANT TO SEE SCOREBOARD NOW...PLEASE TYPE Y | N");
 		        String input=scanner.next();
 		         if (input.equalsIgnoreCase("Y"))
 		         {
 		        	int count=1;//to print serial numbers or rank
 		  		    connect=ConnectDB.getConnection();
 		  		 preparedstatement= connect.prepareStatement("SELECT studentinfo.student_id,studentinfo.student_name ,scoreinfo.Score,scoreinfo.Grade FROM studentinfo left join scoreinfo on studentinfo.student_id=scoreinfo.student_id  order by Score desc "); 
 		  	 	 	 
 		  	 	 	ResultSet resultset= preparedstatement.executeQuery(); 
 		  	 	 	System.out.printf("%10s %10s %26s %10s %10s\n","Sr No","Student_id" ,"Student Name" ," Score", "Grade"); 

 		  	 	 	while(resultset.next()) { 
 		  	 	 	
 		  	 	 	System.out.printf("%10s %10s %26s %10s %10s \n",count,resultset.getInt(1) ,resultset.getString(2),resultset.getString(3),resultset.getString(4));
 		  	 	 	count++;
 		  	 	 	}
 		        	
 		         }//if close
 		          else {
 		        	  System.out.println("\tThank You!!!Play Again");
 		         }
 		 		}//try complete
 		 		catch(Exception e) { 
 		 	 	 	e.printStackTrace(); 
 		 	 	} 
 			 break;
 			 
 			 case "2":
 		 		try {
 				 
 		 			ScoreBoard scoreboard=new ScoreBoard();
 		 			scoreboard.displayScore();
		  	 	 	
		         
 		 		}//case 2 try close
		  	 	 	catch(Exception e)
		  	 	 	{
		  	 	 	e.printStackTrace(); 
		  	 	 	}
		  	 	 	
 		 		break;
 			 
 			 case "3":
 				 try {
 				 System.out.println("\tENTER YOUR ID");
 				
 				 connect=ConnectDB.getConnection();
 				 int id=scanner.nextInt();
	        	 
	        	 preparedstatement=connect.prepareStatement("SELECT studentinfo.student_id,studentinfo.student_name ,scoreinfo.Score,scoreinfo.Grade\r\n"
	        	 		+ "FROM studentinfo left join scoreinfo on studentinfo.student_id=scoreinfo.student_id \r\n"
	        	 		+ "where studentinfo.Student_id=?"); 
	        	 preparedstatement.setInt(1, id);
	 	 	 	 
	  	 	 	ResultSet resultset=preparedstatement.executeQuery(); 
	  	 	 	 
	  	 	 	while(resultset.next()) { 
	  	 	 	System.out.println("\tStudent_id="+resultset.getInt(1)  + "--> Student Name="+resultset.getString(2)  +  "--> Score="+resultset.getString(3)+ " --> Grade="+resultset.getString(4)); 
	  	 	 	
	  	 	 	
	  	 	 	}
 				 }//try close
 				catch(Exception e) 
 				 {
	  	 	 		e.printStackTrace();
	  	 	 	    }
 				 break;	
 				 
 			 default :
 					 System.out.println("ENTER VALID CHOICE...");
	  	 	 	
 			 
 			 }//switch close
 			 
 			}//1st do close
 			 while(flag);
 		 	
 			
 			 
	}//main close

 			 
 			 }//class close
 			 
 			 
 		
 		
 		
 		
 		
      
 		
       
          	 



