package controller;

import model.GetDsID;
import model.OrderData;

public class OrderContext {
    private OrderState currentState;
    private OrderData orderDetails; // The OrderData class from your model package.

    public OrderContext(OrderData orderDetails) {
        this.orderDetails = orderDetails;
        this.currentState = new NewOrderState(); // Set the initial state
    }

    public boolean isRestockNeeded(int productID) {
        return orderDetails.restockProduct(productID);
    }

    public boolean updateProductQuantityAfterOrder(int productID, int orderQuantity) {
        return orderDetails.updateProductQuantityAfterOrder(productID, orderQuantity);
    }

    public void setState(OrderState state) {
        this.currentState = state;
        
    }
    public OrderState getState() {
    	return this.currentState;
    }

    public void handleOrder(int productID, int orderQuantity) {
        currentState.handleOrder(this, productID, orderQuantity);
    }

    public boolean isOrderQuantityAvailable(int productID, int orderQuantity) {
        return orderDetails.isOrderQuantityAvailable(productID, orderQuantity);
    }

    public boolean isOrderExceedsMaxQuantity(int productID, int orderQuantity) {
        return orderDetails.isOrderExceedsMaxQuantity(productID, orderQuantity);
    }

    public boolean restockProduct(int productID) {
        return orderDetails.restockProduct(productID);
    }
    
    public double getPrice(int productID, int quantity) {
		PricingStrategyFactory p = PricingStrategyFactory.getInstance();
		int dsID = GetDsID.getInstance().getDsID(productID);
		return p.getPricing(productID, quantity, dsID);
	}
}
