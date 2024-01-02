package controller;

import java.util.HashMap;

public class PricingStrategyFactory implements PricingStrategy {
	private HashMap<Integer, Strategy> map = new HashMap<>();
	private static PricingStrategyFactory instance;

	private PricingStrategyFactory() {
		// Populate the map with instances of Strategy1, Strategy2, and Strategy3
		map.put(1, new Strategy1());
		map.put(2, new Strategy2());
		map.put(3, new Strategy3());
	}

	public static PricingStrategyFactory getInstance() {
		if (instance == null) {
			instance = new PricingStrategyFactory();
		}
		return instance;
	}

	@Override
	public double getPricing(int productID, int quantity,int strategyId) {
		Strategy strategy = map.get(strategyId);
		if (strategy != null) {
			double a = strategy.pricing(productID, quantity);
			if (a >= 1000)
				return a * 0.95;
			else
				return a;
		} else {
			// Handle the case when the strategyId is not found
			System.out.println("Invalid strategyId: " + strategyId);
			return 0;
		}
	}
}
