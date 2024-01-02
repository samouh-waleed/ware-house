// In RestockingState.java
package controller;

public class RestockingState implements OrderState {
	private int productID;
	private int orderQuantity;
	private String currStateText;
	private String prevText;
	public RestockingState(int productID, int orderQuantity, String prevText){
		this.productID =  productID;
		this.orderQuantity = orderQuantity;
		this.prevText = prevText;
	}
    public String handleOrder(OrderContext context, int productID, int orderQuantity) {
        if (context.restockProduct(productID)) { 
        	this.currStateText = this.prevText + "Restocking operation for Product " + productID + " completed.\n";
        	OrderState order = new ProcessOrderState(productID, orderQuantity, this.currStateText);
            context.setState(order);
            this.currStateText = this.currStateText + "\n";
            context.handleOrder(productID, orderQuantity); // Proceed to process the order
        } else {
//            result = "Restocking failed for Product " + productID + ". Cannot process order.";
        	OrderState rejOrder = new RejectedOrderState(productID, orderQuantity);
            context.setState(rejOrder);// Restocking failed, reject the order
            
            this.currStateText = this.currStateText + "\n";
        }
		return "";
    }
    public String toString() {
    	return this.currStateText; 
    }
}
