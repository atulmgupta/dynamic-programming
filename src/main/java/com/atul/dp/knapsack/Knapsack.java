package com.atul.dp.knapsack;

/**
 * Let’s take the example of Merry, who wants to carry some fruits in the knapsack to get maximum profit. 
	Here are the weights and profits of the fruits:
	
	Items: { Apple, Orange, Banana, Melon }
	Weights: { 2, 3, 1, 4 }
	Profits: { 4, 5, 3, 7 }
	Knapsack capacity: 5
	
	Let’s try to put different combinations of fruits in the knapsack, 
	such that their total weight is not more than 5:
	
	Apple + Orange (total weight 5) => 9 profit
	Apple + Banana (total weight 3) => 7 profit
	Orange + Banana (total weight 4) => 8 profit
	Banana + Melon (total weight 5) => 10 profit
 * 
 *
 */
public class Knapsack {
	public int solveKnapsack(int[] profits, int[] weights, int capacity) {
		return this.knapsackRecursive(profits, weights, capacity, 0);
	}
	/**
	 * 
	 * BRUTE FORCE
	 */
	private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
		// base checks
		if (capacity <= 0 || currentIndex >= profits.length)
			return 0;

		// recursive call after choosing the element at the currentIndex
		// if the weight of the element at currentIndex exceeds the capacity, we
		// shouldn't process this
		int profit1 = 0;
		if (weights[currentIndex] <= capacity)
			profit1 = profits[currentIndex]
					+ knapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex + 1);

		// recursive call after excluding the element at the currentIndex
		int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

		return Math.max(profit1, profit2);
	}

	public static void main(String[] args) {
		Knapsack ks = new Knapsack();
		int[] profits = { 1, 6, 10, 16 };
		int[] weights = { 1, 2, 3, 5 };
		int maxProfit = ks.solveKnapsack(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = ks.solveKnapsack(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);
	}
}
