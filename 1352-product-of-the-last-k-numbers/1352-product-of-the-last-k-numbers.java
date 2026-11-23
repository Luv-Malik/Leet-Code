import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1); // Base sentinel value
    }

    public void add(int num) {
        if (num == 0) {
            // Any product crossing a 0 becomes 0, so reset the current segment
            prefix.clear();
            prefix.add(1);
        } else {
            int lastProduct = prefix.get(prefix.size() - 1);
            prefix.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {
        int n = prefix.size();
        // Fewer than k elements exist since the last 0 -> window includes 0
        if (n <= k) {
            return 0;
        }
        return prefix.get(n - 1) / prefix.get(n - k - 1);
    }
}