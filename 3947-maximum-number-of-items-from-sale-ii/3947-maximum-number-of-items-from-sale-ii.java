import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count how many items have each factor value
        long[] factorCount = new long[maxFactor + 1];
        for (int[] item : items) {
            factorCount[item[0]]++;
        }

        // For each factor f, compute total items whose factor is a multiple of f
        long[] multipleCount = new long[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (factorCount[f] > 0) {
                for (int m = f; m <= maxFactor; m += f) {
                    multipleCount[f] += factorCount[m];
                }
            }
        }

        // Group profitable opportunities by item price:
        // Each item with factor f has (multipleCount[f] - 1) valid target items j != i.
        // If price <= 2 * minPrice, using up to `pairs` copies at `price` each is worth considering.
        TreeMap<Long, Long> priceToOpportunities = new TreeMap<>();
        for (int[] item : items) {
            int f = item[0];
            long price = item[1];
            long pairs = multipleCount[f] - 1;

            if (pairs > 0 && price <= 2L * minPrice) {
                priceToOpportunities.put(
                    price,
                    priceToOpportunities.getOrDefault(price, 0L) + pairs
                );
            }
        }

        long remBudget = budget;
        long totalItems = 0;

        for (Map.Entry<Long, Long> entry : priceToOpportunities.entrySet()) {
            long price = entry.getKey();
            long availablePairs = entry.getValue();
            long take = Math.min(availablePairs, remBudget / price);

            totalItems += 2L * take;
            remBudget -= take * price;
        }

        // Spend remaining budget on the globally cheapest item
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}