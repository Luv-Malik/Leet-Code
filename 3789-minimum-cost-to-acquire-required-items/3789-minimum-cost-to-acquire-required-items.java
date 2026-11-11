class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Strategy A: Only buy Type 1 and Type 2 items
        long costA = (long) need1 * cost1 + (long) need2 * cost2;
        
        // Strategy B: Only buy Type 3 (bundle) items
        long costB = (long) costBoth * Math.max(need1, need2);
        
        // Strategy C: Buy min(need1, need2) bundles, and cover the remainder with singles
        int mn = Math.min(need1, need2);
        long costC = (long) costBoth * mn 
                   + (long) (need1 - mn) * cost1 
                   + (long) (need2 - mn) * cost2;
        
        // Return the minimum of all three strategies
        return Math.min(costA, Math.min(costB, costC));
    }
}