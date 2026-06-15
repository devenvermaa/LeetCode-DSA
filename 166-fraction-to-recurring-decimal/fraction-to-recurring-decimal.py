class Solution:
    def fractionToDecimal(self, numerator: int, denominator: int) -> str:
        if numerator == 0:
            return "0"
            
        res = []
        # Handle the sign
        if (numerator < 0) ^ (denominator < 0):
            res.append("-")
            
        # Use absolute values for calculation
        num = abs(numerator)
        den = abs(denominator)
        
        # Integral part
        res.append(str(num // den))
        remainder = num % den
        
        if remainder == 0:
            return "".join(res)
            
        res.append(".")
        
        # Map to track seen remainders and their corresponding string indices
        seen_remainders = {}
        
        while remainder != 0:
            if remainder in seen_remainders:
                # Insert '(' at the index where this remainder was first seen
                res.insert(seen_remainders[remainder], "(")
                res.append(")")
                break
                
            # Record the position of the current remainder
            seen_remainders[remainder] = len(res)
            
            remainder *= 10
            res.append(str(remainder // den))
            remainder %= den
            
        return "".join(res)