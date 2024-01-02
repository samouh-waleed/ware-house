package view;

import java.util.Scanner;

import controller.Login;
import java.sql.*;

public class User {
	User() throws SQLException{
		try {
	        Class.forName("org.sqlite.JDBC");
	        Connection con=DriverManager.getConnection("jdbc:sqlite:src/main/java/model/wareHouseDatabase.db");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
//	
//	User(){
//		
//	}
//    
    public static void main(String[] args) {
    	
    	
        Scanner scanner = new Scanner(System.in);
        Login loginController = new Login();
        
        int optionSelected = 0;
        // out put for common welcome and will be changed to GUI
        System.out.println("Welcome to the WH App!");
        System.out.println("Click 1 for Client view.");
        System.out.println("Click 2 for Admin view.");
        
        while(true) {
        	try {
        		System.out.print("Please enter your choice: ");
        		optionSelected = scanner.nextInt();
        		if (optionSelected == 1 || optionSelected == 2) {
        			break;
        		}else {
        			System.out.println("Please enter either number 1 or number2.");
        		}
        	}catch(java.util.InputMismatchException e) {
        		System.out.println("Invalid input. Please enter a number.");
        		System.out.println("Click 1 for Client view.");
                System.out.println("Click 2 for Admin view.");
        		scanner.next();
        	}
        }
   
        switch(optionSelected){
        	case 1:
        		ClientRun client = new ClientRun();
        		client.ClientRun();
        		break;
        	case 2:
        		AdminRun admin = new AdminRun();
        		admin.AdminRun();
        		break;      
        }
    }
    
    public static void ClientRun() {
    }
 
}