package controller;

//OrderState.java
public interface OrderState {
 String handleOrder(OrderContext context, int productID, int orderQuantity);
 String toString();
 }


