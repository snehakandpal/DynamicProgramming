/*Integer Knapsack Problem (Duplicate Items Allowed)
 * You have n types of items, where the ith item type has an integer size si and a value vi.
 * You need to ﬁll a knapsack of total capacity C with a selection of items of maximum value.
 * You can add multiple items of the same type to the knapsack.
 */

public class IntegerKnapsackWithDuplicateItems {

	static int[] sizeOfItem = {2, 3, 4};
	static int[] valueOfItem = {30, 150, 50};
	
	static int[] memoArray;
	static int[] sizeArray;		//to display what sizes are used
	
	static int maxValueOfKnapsack(int C) {
		if(memoArray[C] != 0)
			return memoArray[C];
		
		if(C == 0) {
			memoArray[C] = 0;
			return 0;
		}
		
		int maxValue = 0;
		for(int i = 0; i < sizeOfItem.length; i++) {
			if(C - sizeOfItem[i] >= 0) {
				int tempMax = maxValueOfKnapsack(C - sizeOfItem[i]) + valueOfItem[i];
				if (maxValue < tempMax) {
					maxValue = tempMax;
					sizeArray[C] = sizeOfItem[i];
				}
			}
		}
		
		if(maxValueOfKnapsack(C - 1) > maxValue) {
			System.out.println("here " + C);
			maxValue = maxValueOfKnapsack(C - 1);
			sizeArray[C] = 0;
		}
		memoArray[C] = maxValue;
		return memoArray[C];
	} 
	
	
	public static void main(String[] args) {
		
		int C = 4;
		memoArray = new int[C + 1];
		sizeArray = new int[C + 1];
		System.out.println(maxValueOfKnapsack(C));
		System.out.println();
		int i = sizeArray.length - 1;
		while(i > 0) {
			if(sizeArray[i] > 0){
				System.out.println(sizeArray[i]);
				i -= sizeArray[i];
			}
			else
				i--;
		}
	}
}
