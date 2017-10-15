		
/* Making Change. 
 * You are given n types of coin denominations of values v(1) < v(2) < ... < v(n) (all integers)
 * Assume v(1) = 1, so you can always make change for any amount of money C
 * Give an algorithm which makes change for an amount of money C with as few coins as possible.
 */

public class MakingChange {

	static int[] denominationOfCoin = {1, 6, 7}; 
	
	static int[] memoArray;
		
	static int minCoins(int C) {
				
		if (denominationOfCoinContains(C)) {
			memoArray[C] = 1;
			return 1;
		}

		if(memoArray[C] != 0)
			return memoArray[C];
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < denominationOfCoin.length; i++) {
			if(C - denominationOfCoin[i] > 0) {
				int minTemp = minCoins(C - denominationOfCoin[i]);
				if(minTemp < min) {
					min = minTemp;
				}
			}
		}
		memoArray[C] = min + 1;
		return min + 1;
	}
	
	static boolean denominationOfCoinContains(int C) {
		for(int i = 0; i < denominationOfCoin.length; i++) {
			if(denominationOfCoin[i] == C)
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		int C = 12;
		memoArray = new int[C + 1];
		
		System.out.println(minCoins(C));
	}
}
