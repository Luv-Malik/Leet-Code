class Solution:
    def sumCounts(self, nums):
        total_sum = 0
        n = len(nums)
        
        for i in range(n):
            distinct_elements = set()
            for j in range(i, n):
                distinct_elements.add(nums[j])
                count = len(distinct_elements)
                total_sum += count * count
                
        return total_sum