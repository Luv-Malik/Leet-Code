class Solution:
    def minSwaps(self, s: str) -> int:
        swaps = 0
        extra_close = 0
        
        for char in s:
            if char == '[':
                extra_close -= 1
            else:
                extra_close += 1
            
            # Track the maximum number of unmatched closing brackets
            if extra_close > 0:
                swaps = max(swaps, extra_close)
                
        # Each swap fixes two unbalanced brackets
        return (swaps + 1) // 2