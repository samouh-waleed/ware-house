package controller;

import model.GetPrice;

public class Strategy2 implements Strategy {
    private double price;
    private GetPrice getPrice;

    public Strategy2() {
        this.getPrice = GetPrice.getInstance();
    }

    @Override
    public double pricing(int productID, int quantity) {
        
        float unitPrice = getPrice.getProductPrice(productID);

        if (quantity > 60) {
            this.price = (double) (unitPrice * quantity * 0.85); // 15% discount
        } else {
            this.price = (double) (unitPrice * quantity);
        }

        return this.price;
    }
}
