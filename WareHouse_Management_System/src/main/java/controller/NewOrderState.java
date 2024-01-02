package controller;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;

public class NewOrderState implements OrderState {
	public static Queue<Order> orderQ = new LinkedList<Order>();

	public String handleOrder(OrderContext context, int productID, int orderQuantity) {
		String result = "";
		String isRec = "";
		Order order = Order.getInstance(productID, orderQuantity, LocalTime.now());

		if (context.isOrderExceedsMaxQuantity(productID, orderQuantity)) {

			context.setState(new RejectedOrderState(productID, orderQuantity));// Handle rejection
			context.handleOrder(productID, orderQuantity);
			result = context.getState().toString();
			orderQ.add(order);
		} else if (context.isOrderQuantityAvailable(productID, orderQuantity)) {
			context.setState(new ProcessOrderState(productID, orderQuantity, "")); // Process the order
			context.handleOrder(productID, orderQuantity);
			result = context.getState().toString();
			orderQ.add(order);
		} else {
			isRec = "Order is received for Product " + productID + " and Quantity " + orderQuantity + "\n";
			context.setState(new PendingOrderState(productID, orderQuantity));// Pending and restocking
			context.handleOrder(productID, orderQuantity);

			result = isRec + result + context.getState().toString();
			orderQ.add(order);

		}
		System.out.println(result);
		return result;
	}
}