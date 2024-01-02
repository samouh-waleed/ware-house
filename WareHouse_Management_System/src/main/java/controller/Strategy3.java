package controller;

import model.GetPrice;

public class Strategy3 implements Strategy {
    private double price;
    private GetPrice getPrice;

    public Strategy3() {
        this.getPrice = GetPrice.getInstance();
    }

    @Override
    public double pricing(int productID, int quantity) {
        
        // Retrieve the price from the database using the GetPrice class
        float unitPrice = getPrice.getProductPrice(productID);

        if (quantity > 75) {
            this.price = (double) (unitPrice * quantity * 0.75); // 25% discount
        } else {
            this.price = (double) (unitPrice * quantity);
        }

        return this.price;
    }
}
