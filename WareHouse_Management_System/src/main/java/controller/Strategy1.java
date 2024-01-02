package controller;

import model.GetPrice;

public class Strategy1 implements Strategy {
    private double price;
    private GetPrice getPrice;

    public Strategy1() {
        this.getPrice = GetPrice.getInstance();
    }

    @Override
    public double pricing(int productID, int quantity) {
        
        // Retrieve the price from the database using the GetPrice class
        float unitPrice = getPrice.getProductPrice(productID);

        if (quantity > 50) {
            this.price = (double) (unitPrice * quantity * 0.9); // 10% discount
        } else {
            this.price = (double) (unitPrice * quantity);
        }

        return this.price;
    }
}
