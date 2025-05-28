import java.util.Arrays;
import java.util.Scanner;

public class ArrayOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số phần tử của mảng
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        int[] myArray = new int[n];

        // Nhập các phần tử của mảng
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            myArray[i] = scanner.nextInt();
        }

        // Hiển thị mảng ban đầu
        System.out.println("Original array: " + Arrays.toString(myArray));

        // Sắp xếp mảng
        Arrays.sort(myArray);

        // Tính tổng và trung bình
        int sum = 0;
        for (int num : myArray) {
            sum += num;
        }
        double average = (double) sum / n;

        // Hiển thị kết quả
        System.out.println("Sorted array: " + Arrays.toString(myArray));
        System.out.println("Sum of array elements: " + sum);
        System.out.println("Average value of array elements: " + average);

        scanner.close();
    }
}