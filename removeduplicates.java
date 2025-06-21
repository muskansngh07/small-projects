package Arrays;
import java.util.Scanner;

class Solution {
    public int remove(int[] nums) {
        if (nums.length == 0) return 0;

        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];  // move unique element forward
            }
        }
        return j + 1;
    }
}

class removeDuplicates {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of sorted array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " sorted elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Solution sol = new Solution();
        int newSize = sol.remove(arr);

        System.out.println("The size of the modified array is: " + newSize);
        System.out.print("Modified array (unique elements): ");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }
}
