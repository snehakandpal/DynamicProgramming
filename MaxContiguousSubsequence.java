/*Maximum Value Contiguous Subsequence.
 *  Given a sequence of n real numbers A(1) ... A(n),
 *  determine a contiguous subsequence A(i) ... A(j) 
 *  for which the sum of elements in the subsequence is maximized.
 */

public class MaxContiguousSubsequence {

	static int memoArray[];

	static int findMaxSubsequenceEndingAtIndex(int[] arr, int index) {

		if(index == 0)
			memoArray[index] = arr[0];
		else
			memoArray[index] = Math.max(findMaxSubsequenceEndingAtIndex(arr, index - 1) + arr[index], arr[index]);
		
		return memoArray[index];
	}
	
	
	static int findMaxSum(int[] arr) {
		memoArray = new int[arr.length];
		int maximum = 0;
		
		findMaxSubsequenceEndingAtIndex(arr, arr.length - 1);
		
		for(int i = 0; i < memoArray.length; i++) {
			maximum = maximum(memoArray); 
		}
		
		return maximum;
	}
	
	static int maximum(int[] ma) {
		int max = ma[0];
		for(int i = 1; i < ma.length; i++) {
			if(ma[i] > max)
				max = ma[i];
		}
		return max;
	}
	
	public static void main(String[] args) {
		
		int[] arr = {3, -2, -4, 5, 1, -1, 3, -6, 4};
		
		System.out.println(findMaxSum(arr));
	}
}
