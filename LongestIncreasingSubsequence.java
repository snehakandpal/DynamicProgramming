/* LongestIncreasingSubsequence.
 * Given a sequence of n real numbers A(1) ... A(n), 
 * determine a subsequence (not necessarily contiguous) of maximum length
 * in which the values in the subsequence form a strictly increasing sequence. 
 */

public class LongestIncreasingSubsequence {
	static int[] memoArray;
	
	static int findLongest(int[] arr) {
		for(int i = 0; i < memoArray.length; i++) {
			memoArray[i] = 1;
		}
		
		for(int i = 1; i < arr.length; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && memoArray[j] + 1 > memoArray[i]) {
					memoArray[i] = memoArray[j] + 1;
				}
			}
		}

		int max = 0;
		for(int i = 0; i < memoArray.length; i++) {
			if(max < memoArray[i]) {
				max = memoArray[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] arr = {10, 22, 9, 33, 21, 50, 41, 60};
		memoArray = new int[arr.length];
		
		System.out.println(findLongest(arr));
	}
}
