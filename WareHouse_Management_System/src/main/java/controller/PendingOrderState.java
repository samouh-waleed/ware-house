package controller;

public class PendingOrderState implements OrderState {
    int productID;
    int orderQuantity;
    String nextTextSate;
//    String isInit = "";
    PendingOrderState(int productID, int orderQuantity){
        this.productID = productID;
        this.orderQuantity = orderQuantity;
    }
    public String handleOrder(OrderContext context, int productID, int orderQuantity) {

        RestockingState restockObj = new RestockingState(productID, orderQuantity, toString()); 
        context.setState(restockObj);
//        String restockRes = new RestockingState().handleOrder(context, productID, orderQuantity);
//        System.out.println(restockObj.toString());
        context.handleOrder(productID, orderQuantity); // Move to restocking
//        return ("Order for Product " + productID + " Quantity " + orderQuantity + 
//                " is pending - order exceeds available quantity\n");
//        this.nextTextSate = context.getState().toString();
//        isInit = "Restocking Operation for Product " + productID + " initiated\n";
        return "";
    }
    public String toString() {
        return ("Order for Product " + productID + " Quantity " + orderQuantity + " is pending - order exceeds available quantity\n" 
    + "Restocking Operation for Product " + productID + " initiated\n");
    }
}