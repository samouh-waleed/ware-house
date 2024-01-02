package view;

import java.util.Scanner;

import controller.*;


public class AdminRun {
    public void AdminRun() {
    	Login loginController = new Login();
    	Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Admin view");
		System.out.println("Please start with providing your credentials");
		
		//scanner.nextLine();
		boolean reg;
		
		boolean contLoop = true;
		while (contLoop) {
			System.out.print("Enter username: ");
    		String username = scanner.nextLine();
    		System.out.print("Enter password: ");
    		String password = scanner.nextLine();
    		boolean isAuthenticated = loginController.authenticateUser(username, password);
    		if (isAuthenticated) {
    			contLoop =false;
    			System.out.println("Login Successful!");
    			/*
    			 * Have to direct the Admin to his own view
    			 * have to list 
    			 * 1) Last order that was placed: product, quantity, timeStamp 
    			 * 2) current product quantity in warehouse: Product, quantity
    			 */
    		}else {
    			System.out.println("Login Failed");
    			contLoop =true;
    		}
            
		}

    	
    }

}
