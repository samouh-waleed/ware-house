package controller;

import java.time.*;
import java.util.*;

public class Order {
	private int ProductID;
	private int Quantity;
	private LocalTime timeStamp;
	private static Queue<Order> orderQueue = new LinkedList<>();

	
	private Order(int productID, int quantity, LocalTime timeStamp) {
		ProductID = productID;
		Quantity = quantity;
		this.timeStamp = timeStamp;
	}

	
	//Factory Method
	public static Order getInstance(int productID, int quantity, LocalTime timeStamp) {
		Order order = new Order(productID, quantity, timeStamp);
		addToOrderQueue(order);
		return order;
	}
	
	// Add the order to object of orders
    public static void addToOrderQueue(Order order) {
        orderQueue.add(order);
    }

	// get the first order in the orderList
	public Order getQueueFront(Queue<Order> orderQueue) {
		return orderQueue.peek();
	}
	
	
	public Queue<Order> getOrderQueue() {
		return orderQueue;
	}

	public void setOrderQueue(Queue<Order> orderQueue) {
		this.orderQueue = orderQueue;
	}

	public Queue<Order> orderQueue() {
		return orderQueue = new LinkedList<>();
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public LocalTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
