package com.atul.dp._01knapsack;

import java.util.Arrays;

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
	 * ###################################################
	 * BRUTE FORCE
	 * ###################################################
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

	/**
	 * ###################################################
	 * Top-down Dynamic Programming with Memoization
	 * ###################################################
	 */
	public int solveKnapsack_top_down(int[] profits, int[] weights, int capacity) {
		Integer[][] dp = new Integer[profits.length][capacity + 1];
		return this.knapsackRecursive_top_down(dp, profits, weights, capacity, 0);
	}

	private int knapsackRecursive_top_down(Integer[][] dp, int[] profits, int[] weights, int capacity,
			int currentIndex) {
		if (capacity <= 0 || currentIndex >= profits.length)
			return 0;

		if (dp[currentIndex][capacity] != null)
			return dp[currentIndex][capacity];

		int profit1 = 0;
		if (weights[currentIndex] <= capacity)
			profit1 = profits[currentIndex] + knapsackRecursive_top_down(dp, profits, weights,
					capacity - weights[currentIndex], currentIndex + 1);

		int profit2 = knapsackRecursive_top_down(dp, profits, weights, capacity, currentIndex + 1);

		return Math.max(profit1, profit2);
	}

	/**
	 * ###################################################
	 * Bottom-up Dynamic Programming
	 * ###################################################
	 */
	public int solveKnapsack_bottom_up(int[] profits, int[] weights, int capacity) {
		if (capacity <= 0 || profits.length == 0 || weights.length == 0 || weights.length != profits.length)
			return 0;
		int n = profits.length;
		int[][] dp = new int[n][capacity + 1];
		for (int i = 0; i < n; i++) {
			dp[i][0] = 0;
		}
		print_arr(dp);
		for (int c = 0; c <= capacity; c++) {
			if (weights[0] <= c)
				dp[0][c] = profits[0];
		}
		print_arr(dp);
		for (int i = 1; i < n; i++) {
			for (int c = 1; c <= capacity; c++) {
				int profit1 = 0;
				int profit2 = 0;
				if (weights[i] <= c)
					profit1 = profits[i] + dp[i - 1][c - weights[i]];
				profit2 = dp[i - 1][c];
				dp[i][c] = Math.max(profit1, profit2);
				print_arr(dp);
			}
		}
		print_arr(dp);
		return dp[n - 1][capacity];
	}

	private void print_arr(int[][] dp) {
		System.out.println("###################################################");
		for (int[] row : dp)
			System.out.println(Arrays.toString(row));
	}

	/**
	 * ###################################################
	 * Bottom-up Dynamic Programming
	 * 				MEMORY OPTIMIZATION
	 * ###################################################
	 */

	private int solveKnapsack_bottom_up_optimized(int[] profits, int[] weights, int capacity) {
		// basic checks
		if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
			return 0;
		int n = profits.length;
		int[] dp = new int[capacity + 1];
		// if we have only one weight, we will take it if it is not more than
		// the
		// capacity
		for (int c = 0; c <= capacity; c++) {
			if (weights[0] <= c)
				dp[c] = profits[0];
		}
		// process all sub-arrays for all the capacities
		for (int i = 1; i < n; i++) {
			for (int c = capacity; c >= 0; c--) {
				int profit1 = 0, profit2 = 0;
				// include the item, if it is not more than the capacity
				if (weights[i] <= c)
					profit1 = profits[i] + dp[c - weights[i]];
				// exclude the item
				profit2 = dp[c];
				// take maximum
				dp[c] = Math.max(profit1, profit2);
			}
		}

		return dp[capacity];

	}

	public static void main(String[] args) {
		Knapsack ks = new Knapsack();
		int[] profits = { 1, 6, 10, 16 };
		int[] weights = { 1, 2, 3, 5 };
		int maxProfit = ks.solveKnapsack(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = ks.solveKnapsack(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);

		System.out.println("########## Top-down Dynamic Programming with Memoization ##########");
		maxProfit = ks.solveKnapsack_top_down(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = ks.solveKnapsack_top_down(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);

		System.out.println("########## Bottom-up Dynamic Programming ##########");
		maxProfit = ks.solveKnapsack_bottom_up(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = ks.solveKnapsack_bottom_up(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);

		System.out.println("########## Bottom-up Dynamic Programming OPTIMIZATION ##########");
		maxProfit = ks.solveKnapsack_bottom_up_optimized(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = ks.solveKnapsack_bottom_up_optimized(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);
	}
}
