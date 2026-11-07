import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for free servers: sorted by weight, then by index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // Compare weights
            }
            return Integer.compare(a[1], b[1]);     // Compare indices
        });

        // Min-heap for busy servers: sorted by free time, then weight, then index
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]); // Compare free times
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]); // Compare weights
            }
            return Long.compare(a[2], b[2]);     // Compare indices
        });

        // Initialize free servers pool
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i }); // {weight, index}
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // Move all servers that have finished their tasks by currentTime back to freeServers
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] curr = busyServers.poll();
                int weight = (int) curr[1];
                int index = (int) curr[2];
                freeServers.offer(new int[] { weight, index });
            }

            // If no servers are free, fast-forward time to the earliest completion time of a busy server
            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                    long[] curr = busyServers.poll();
                    int weight = (int) curr[1];
                    int index = (int) curr[2];
                    freeServers.offer(new int[] { weight, index });
                }
            }

            // Assign the task to the best available server
            int[] bestServer = freeServers.poll();
            int weight = bestServer[0];
            int index = bestServer[1];

            ans[j] = index;
            // Push the server to busy servers queue with its new release time
            busyServers.offer(new long[] { currentTime + tasks[j], weight, index });
        }

        return ans;
    }
}