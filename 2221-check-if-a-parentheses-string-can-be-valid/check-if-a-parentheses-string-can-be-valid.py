class Solution:
    def canBeValid(self, s: str, locked: str) -> bool:
        n = len(s)
        if n % 2 != 0:
            return False  # Odd length can never be a valid parentheses string
        
        # 1. Left-to-right pass: check if we have too many ')'
        open_brackets = close_brackets = wild_cards = 0
        for i in range(n):
            if locked[i] == '0':
                wild_cards += 1
            elif s[i] == '(':
                open_brackets += 1
            else:
                close_brackets += 1
                
            # If closing brackets exceed available opening + wildcards, it's invalid
            if wild_cards < (close_brackets - open_brackets):
                return False
                
        # 2. Right-to-left pass: check if we have too many '('
        open_brackets = close_brackets = wild_cards = 0
        for i in range(n - 1, -1, -1):
            if locked[i] == '0':
                wild_cards += 1
            elif s[i] == '(':
                open_brackets += 1
            else:
                close_brackets += 1
                
            # If opening brackets exceed available closing + wildcards, it's invalid
            if wild_cards < (open_brackets - close_brackets):
                return False
                
        return True