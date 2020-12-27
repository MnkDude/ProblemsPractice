import com.sun.istack.internal.NotNull;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

class ArrayProblems {

    static public void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] findarr = {1, 3, 5, 7, 9};
//        System.out.println(sumOfMaximum_i_aOfi(findarr));
//        System.out.println(1 / 2);
        int[][] query= {
                {1,3},
                {3,0,2},
                {2,1},
                {3,1,4}
        };

//        queryInEfficient(arr, query);
        System.out.println(-9%2);
    }

    static void queryInEfficient(int[] ar, int[][] query) {
        int start = 0, end = ar.length - 1;
        for (int i = 0; i < query.length; i++) {
            if (query[i][0] == 1) {
                start = (start + query[i][1]) % ar.length;
                end=(start-1<0)?ar.length-1:start-1;
//                end = (end + query[i][1]) % ar.length;
            } else if (query[i][0] == 2) {
                start = end + 1 - query[i][1];
                end=(start-1<0)?ar.length-1:start-1;
//                end = (end - query[i][1] < 0) ? end + query[i][1] : end - query[i][1];
            } else {
                int startIndex, endIndex;
                startIndex = (start + query[i][1]) % ar.length;
                endIndex = (end + query[i][2]) % ar.length;
                System.out.println(sumOf(ar, (endIndex - query[i][2] < 0) ? endIndex + query[i][2] : endIndex - query[i][2], endIndex));
            }
        }
    }

    static int sumOf(int[] ar, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += ar[i];
        }
        return sum;
    }

    static int hammingDistance(int[] ar) { // 1 2 3 , 2 3 1 , 3 1 2 ,
        int[] temp = Arrays.copyOf(ar, ar.length);
        int max = 0;
        for (int i = 1; i < ar.length; i++) {
            rotateClockWise(temp);
            int tempMax = 0;
            for (int i1 = 0; i1 < temp.length; i1++) {
                if (ar[i1] != temp[i1]) tempMax++;
            }
            max = Math.max(max, tempMax);
        }
        return max;
    }

    static int findMin(int[] ar, int start, int end) {
        if (end < start) return ar[0];
        if (start == end) return ar[start];
        int mid = (start + end) / 2;
        if (mid < end && ar[mid + 1] < ar[mid]) return ar[mid + 1];
        if (mid > start && ar[mid] < ar[mid - 1]) return ar[mid - 1];
        if (ar[end] > ar[mid]) return findMin(ar, start, mid - 1);
        return findMin(ar, mid + 1, end);
    }

    static int binarySearch1(int ar[], int start, int end, int element) {
        if (end >= start) {
            int middle = (end + start) / 2;
            if (element == ar[middle]) return middle;
            if (element > ar[middle]) return binarySearch1(ar, middle + 1, end, element);
            if (element < ar[middle]) return binarySearch1(ar, start, middle - 1, element);
        }
        return -1;
    }

    static int rotationCount(int[] ar) {
        for (int i = 0; i < ar.length - 1; i++) {
            if (ar[i] > ar[i + 1]) { // 7 8 9 1 2 3 4
                return i;
            }
        }
        return 0;
    }

    static private int rotationBinarySearch(int[] ar, int start, int end) {
        if (end >= start) {
            int middle = start + (end - start) / 2;
            if (ar[middle] < ar[middle - 1]) return middle;
//            if (element > ar[middle]) return binarySearch(ar, middle + 1, end, element);
//            if (element < ar[middle]) return binarySearch(ar, start, middle - 1, element);
        }
        return -1;
    }

    static int maxSum(int arr[]) {
        int arrSum = 0;
        int currVal = 0;
        for (int i = 0; i < arr.length; i++) {
            arrSum += arr[i];
            currVal += (i * arr[i]);
        }
        int maxVal = currVal;
        for (int j = 1; j < arr.length; j++) {
            currVal += arrSum - arr.length * arr[arr.length - j];
            if (currVal > maxVal) maxVal = currVal;
        }

        // Return result
        return maxVal;
    }

    static int sumOfMaximum_i_aOfi(int[] ar) {
        int sum = 0;
        for (int i = 0; i < ar.length; i++) {
            int temp_sum = 0;
            for (int j = 0; j < ar.length; j++) {
                temp_sum = temp_sum + (j * ar[j]);
            }
            if (temp_sum > sum) sum = temp_sum;
            rotateClockWise(ar);
        }
        return sum;
    }

    static int lcm(int a, int b, int max) {
        if (max % a == 0 && max % b == 0) {
            return max;
        }
        return lcm(a, b, ++max);
    }

    static int binarySearch(int ar[], int element) {
        return binarySearch(ar, 0, ar.length - 1, element);
    }

    static boolean isPairOfSumPresent(int[] ar, int sum) {
        int pivot = ar.length - findPivot(ar);
        while (--pivot >= 0) {
            rotateClockWise(ar);
        }
        return isPairOfSumPresent(ar, 0, ar.length - 1, sum);
    }

    static private boolean isPairOfSumPresent(int[] ar, int start, int end, int sum) {
        if (end >= start) {
            if (ar[start] + ar[end] == sum) return true;
            if (ar[start] + ar[end] > sum) return isPairOfSumPresent(ar, start, end - 1, sum);
            if (ar[start] + ar[end] < sum) return isPairOfSumPresent(ar, start + 1, end, sum);
        }
        return false;
    }

    static private int binarySearch(int ar[], int start, int end, int element) {
        if (end >= start) {
            int middle = (end + start) / 2;
            if (element == ar[middle]) return middle;
            if (element > ar[middle]) return binarySearch(ar, middle + 1, end, element);
            if (element < ar[middle]) return binarySearch(ar, start, middle - 1, element);
        }
        return -1;
    }

    static int searchInSortedRotatedArray(int[] ar, int element) {
        int i = findPivot(ar);
        if (i != -1) {
            int index = binarySearch(ar, 0, i - 1, element);
            if (index == -1) index = binarySearch(ar, i, ar.length - 1, element);
            return index;
        }
        return binarySearch(ar, 0, ar.length - 1, element);
    }

    static private int findPivot(int[] ar) {
        for (int i = 1; i < ar.length; i++) {
            if (ar[i] < ar[i - 1]) {
                return i;
            }
        }
        return -1;
    }

    static int gcd(int a, int b) {
//        System.out.println(lcm(3,6,Math.max(3,6)));
        System.out.println(a + " " + b);
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    static void rotateClockWise(int[] ar) {
        int temp = ar[ar.length - 1];
        for (int i = ar.length - 1; i > 0; i--) {

            ar[i] = ar[i - 1];
        }
        "sdfsdf".indexOf("d");

        ar[0] = temp;
    }

    static void printArray(int ar[]) {
        for (int i : ar) {
            System.out.print(i + " ");
        }
    }

    static void rotate(int ar[], int d, int n) {
        d = d % n;
        reverse(ar, 0, d - 1);
        reverse(ar, d, ar.length - 1);
        reverse(ar, 0, ar.length - 1);
        for (int i : ar) {
            System.out.print(i + " ");
        }
    }

    static void reverse(int ar[], int start, int end) {

        while (start < end) {
            int temp = ar[start];
            ar[start] = ar[end];
            ar[end] = temp;
            start++;
            end--;
        }
    }

    static void getInputForGFG() {
        Scanner in = new Scanner(System.in);
        int testcases = in.nextInt();
        while (testcases-- != 0) {
            int size = in.nextInt();
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = in.nextInt();
            }
        }
    }

    public static String reverseWord(String str) {
        String s = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            s += str.charAt(i);
        }
        Set<Integer> set = new HashSet<>();
        set.add(5);
        set.add(3);
        set.add(-8);
        for (Integer integer : set) {
            System.out.println(integer);

        }
        return s;
    }

    static void rearrangeEvenOdd(Scanner in) {

    }

    static void rearrange(Scanner in) {

        for (int i = 0; i < 5; i++) {

            /* O(n^2)
            boolean found=false;
            for (int j =0; j < size; j++) {
                if(array[j]==i) {
                    found=true;
                    System.out.print(i + " ");
                    break;
                }
            }
            if(!found) System.out.print("-1 ");*/
            /* another approach
            if(array[i]!=-1&&array[i]!=i){
                int temp=array[i];
                while(array[temp]!=-1&&array[temp]!=temp) {
                    int temp1=array[temp];
                    array[temp]=temp;
                    temp=temp1;
                }
                array[temp]=temp;
                if(array[i]!=i) {
                    array[i]=-1;
                }
            } */
            /* HashSet
                        Set<Integer> s = new HashSet<Integer>();
            for(int i : array)  s.add(i);
            for(int i = 0; i < size; i++)  {
                if(s.contains(i)) array[i] = i;
                else array[i] = -1;
            }
            System.out.println(Arrays.toString(array));
            */


        }
        for (int i : new int[]{1, 2}) {
            System.out.print(i + " ");
        }
        String s = "1234567890";
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.print(s.charAt(i));
        }

    }

    static void rotation(Scanner in, int d) {
        int size = in.nextInt();
        int rotateSize = in.nextInt(); //2     // i size rotSize
        int[] array = new int[size];       // 1 2 3 4 5
        int[] rotatedArray = new int[size];// 3 4 5
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        for (int i = 0; i < size; i++) {
            if (i + rotateSize >= size) rotatedArray[i] = array[i - size + rotateSize];
            else rotatedArray[i] = array[i + rotateSize];
            System.out.print(rotatedArray[i] + " ");
        }
    }
}
