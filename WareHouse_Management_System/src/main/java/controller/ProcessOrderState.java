package controller;

public class ProcessOrderState implements OrderState {
	private int productID;
	private int orderQuantity;
	private String currStateText;
	private String prevText;
	public ProcessOrderState(int proudctID, int orderQuantity, String prevText){
		this.orderQuantity = orderQuantity;
		this.productID = productID;
		this.prevText = prevText;
	}
    public String handleOrder(OrderContext context, int productID, int orderQuantity) {
    	String result = "";
        if (context.updateProductQuantityAfterOrder(productID, orderQuantity)) {
        	this.currStateText = this.prevText + "Order for Product " + productID + " and Quantity " + orderQuantity + 
            		" with price " + context.getPrice(productID, orderQuantity)+ " was processed successfully.";
            // No need to transition to another state as this is the final state
        } else {
        	this.currStateText = this.prevText + "Failed to process the order for Product " + productID;
//            context.setState(new RejectedOrderState()); // Failed to process, reject the order
        }
//        return result;
		return "";
    }
    public String toString() {
		return currStateText;
    	
    }
}
