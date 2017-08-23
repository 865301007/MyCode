class Solution {
public:
	double myPow(double x, int n) {
        double result = 1;
		bool _ = n<0;
		x = _ ? 1 / x : x;
	    unsigned long	long n_ = abs(n);
		
		while (n_)
		{
			if (n_ & 1)
				result *= x;
			x *= x;
			n_ >>= 1;
		}
		return result;
	}
};