package com.quiz4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

//Author Mahendra Mane(Worked on All Questions and Answer Varifications Along with GUI)

public class Show_Questions 
{
	static Connection connect=null;
	static PreparedStatement preparedstatement = null;
	static int Score=0;
	Scanner scanner=new Scanner(System.in);
	boolean flag = false;
	String answer=null;

	public void Questions() 
	{  
		connect=ConnectDB.getConnection();
    try {
		
    	preparedstatement=connect.prepareStatement("select * from Questions order by rand() "); 
	 	 
	 	ResultSet resultset=preparedstatement.executeQuery(); 
	 	
	 	int count=1;//for quetion numbers
	 	
	 	while( resultset.next()) 
	 	  { 
	 	 	System.out.println("\tQuestion Number="+count);
	 	 	System.out.println("\n\t"+ resultset.getString(2));//questions
	 	 	
	 	    System.out.println(" \n\tOption A= "+resultset.getString(3)); //options
	 	    System.out.println(" \tOption B= "+resultset.getString(4)); 
	 	    System.out.println(" \tOption C= "+resultset.getString(5)); 
	    	System.out.println(" \tOption D= "+resultset.getString(6));
	 	
	    	do {
            	System.out.println("\n\tENTER YOUR ANSWER-A B C D\n");
                String answer = scanner.next();
                System.out.println();

                answer = answer.toUpperCase();
                  
                if (answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D"))
                {
                    if (answer.equals(resultset.getString(7)))
                    {
                    	System.out.println("\tCorrect..You Are Doing Great!!!");
             	        System.out.println("\t---------------------------------------------------");
                  	
                        Score = Score + 1;
                       
                    }
                    else if(answer!= resultset.getString(7))
                    {
                    	System.out.println("\tWrong Answer..Focus Budy!!!");	
                      	System.out.println("\t---------------------------------------------------");
                    }
                   
                 flag = false;
               } 
            else {
                    System.out.println("Please select from - A,B,C,D...");
                    flag = true;
                }

            } while (flag);
	    	         
           System.out.println("\n"); 
           count++;
	 	}//while close 
	 	 ScoreBoard sb=new ScoreBoard();//calling method of ScoreBoard class to calculate score
	 	 sb.calculateScore(Score);
	 	}//try close 
	    catch(Exception e) { 
	 	e.printStackTrace(); 
	} 
		

}
	
}
