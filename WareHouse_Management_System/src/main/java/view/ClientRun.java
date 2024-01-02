package view;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.Order;
import controller.OrderContext;
import model.OrderData;

public class ClientRun {
	LocalTime myObj = LocalTime.now();
	private Scanner scanner = new Scanner(System.in);
	public int productNum;
	public int productQunt;
	public int productName; // to store the product details selected by the client

	public void ClientRun() {

		try { // print out will be replaced by GUI
			System.out.println("Step 1 --> Choose Product ID:" + '\n' + "1) Product1" + '\n' + "2) Product2" + '\n'
					+ "3) Product3" + '\n' + "4) Product4" + '\n' + "5) Product5");
			System.out.print("Please enter you choice: ");
			int productSelected = scanner.nextInt();
			//selection used to let the user choose a product, will be replaced by a drop down
			int productNum = productSelected;
			if(productNum > 5) {
				System.out.println("Error");
			}
			productName  = productSelected;
			productQunt = orderQuantity();

		} catch (InputMismatchException e) {
			System.out.println("Error: You did not enter an integer.");
			ClientRun();
		}
		
		try {
			// start order objects and passes it to handler to pass on the states
			
			OrderData orderDetails = new OrderData();
			OrderContext context = new OrderContext(orderDetails);

			// Start handling the order state
			context.handleOrder(productName, productQunt);
		}catch(Exception e) {
			e.printStackTrace();
		}


		try {
			System.out.println("---------------------");
			System.out.println("Please choose an action: " + '\n' + "1) Place an Order" + '\n' + "2) Go to Admin view"
					+ '\n' + "3) Terminate");
			System.out.print("Please enter your choice: ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				ClientRun();
				break;
			case 2:
				AdminRun admin = new AdminRun();
				admin.AdminRun();
				break;
			case 3:
				System.out.println("Terminating");
				System.exit(0);
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Error: You did not enter an integer.");
			scanner.next(); // clear the wrong input
			ClientRun();
		}

	}

	public void orderDetails() {
		System.out.println("Order Details: ");
		System.out.println("Product: " + productName);
		System.out.println("Quantity: " + productQunt);
		System.out.println("Client Time Stamp: " + myObj);
	}

	public int orderQuantity() {
		try {
			System.out.print("Step 2 --> Choose Quantity(1-1000): ");
			int productQuantity = scanner.nextInt();
			productQunt = productQuantity;
			return productQunt;
		} catch (InputMismatchException e) {
			System.out.println("Error: You did not enter an integer.");
			orderQuantity();
		}
		return 0;
	}
}