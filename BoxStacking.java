import java.util.Arrays;

/*Box Stacking.
 * You are given a set of n types of rectangular 3-D boxes, 
 * where the i^th box has height h(i), width w(i) and depth d(i) (all real numbers). 
 * You want to create a stack of boxes which is as tall as possible, 
 * but you can only stack a box on top of another box if 
 * the dimensions of the 2-D base of the lower box are each strictly larger than those of the 2-D base of the higher box. 
 * Of course, you can rotate a box so that any side functions as its base. 
 * It is also allowable to use multiple instances of the same type of box.
*/

public class BoxStacking {

	static float[] memoArray;
	
	static class Box implements Comparable<Box>{
		float h;
		float w;
		float d;
		float area;
		
		Box(float height, float width, float depth) {
			h = height;
			w = width;
			d = depth;
		}

		@Override
		public int compareTo(Box o) {
			return (int) (o.area-this.area);
		}
	}
	
	static float maxHeight(Box[] arr, int n) {
		Box[] rotatedBox = new Box[n * 3];
		
		for(int i = 0; i < n; i++) {
			Box box = arr[i];
			
			rotatedBox[i* 3] = new Box(box.h, Math.max(box.d, box.w), Math.min(box.d, box.w));		//keeping d <= w
			rotatedBox[(i * 3) + 1] = new Box(box.d, Math.max(box.h, box.w), Math.min(box.h, box.w));
			rotatedBox[(i * 3) + 2] = new Box(box.w, Math.max(box.h, box.d), Math.min(box.h, box.d));
		}
		
		for(int i = 0; i < rotatedBox.length; i++) {
			rotatedBox[i].area = rotatedBox[i].d * rotatedBox[i].w;
		}
		
		Arrays.sort(rotatedBox);

		memoArray = new float[rotatedBox.length];
		
		for(int i = 0; i < rotatedBox.length; i++) {
			memoArray[i] = 0;
			Box box = rotatedBox[i];
			float val = 0;
			for(int j = 0; j < i; j++) {
				Box previousBox = rotatedBox[j];
				if(box.d < previousBox.d && box.w < previousBox.w) {
					val = Math.max(val, memoArray[j]);
				}
			}
			memoArray[i] = val + box.h;
		}
		
		float max = 0;
        for(int i = 0; i < rotatedBox.length; i++){
            max = Math.max(max, memoArray[i]);
        }
         
        return max;
	}
	
	public static void main(String[] args) {
		Box[] arr = new Box[4];
        arr[0] = new Box(4, 7, 9);
        arr[1] = new Box(5, 8, 9);
        arr[2] = new Box(11, 20, 40);
        arr[3] = new Box(1, 2, 3);
        
        System.out.println(maxHeight(arr, 4));
	}
}
