package controller;

public interface PricingStrategy {
	public double getPricing(int productID, int quantity, int strategyId);
}
