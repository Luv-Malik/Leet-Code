class Solution:
    def diffWaysToCompute(self, expression: str, memo: dict = None) -> list[int]:
        if memo is None:
            memo = {}
        if expression in memo:
            return memo[expression]
        
        res = []
        for i, char in enumerate(expression):
            if char in "+-*":
                # Divide the expression into left and right sub-problems
                left = self.diffWaysToCompute(expression[:i], memo)
                right = self.diffWaysToCompute(expression[i+1:], memo)
                
                # Combine results using the operator
                for l in left:
                    for r in right:
                        if char == '+':
                            res.append(l + r)
                        elif char == '-':
                            res.append(l - r)
                        elif char == '*':
                            res.append(l * r)
                            
        # Base case: if the string is just a number, no operators were found
        if not res:
            res.append(int(expression))
            
        memo[expression] = res
        return res