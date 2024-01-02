package controller;

public class RejectedOrderState implements OrderState {
	private int productID;
	private int orderQuantity;
	public RejectedOrderState(int productID, int orderQuantity){
		this.productID = productID;
		this.orderQuantity = orderQuantity;
	}
    public String handleOrder(OrderContext context, int productID, int orderQuantity) {
		return "";
//        return "Order for Product " + productID + " Quantity " + orderQuantity +
//        		" exceeds the max quantity set for this product and cannot be processed";
        // No further action required as this is a terminal state
    }
    public String toString() {
    	return "Order for Product " + this.productID + " Quantity " + this.orderQuantity +
        		" exceeds the max quantity set for this product and cannot be processed";
    }
}
