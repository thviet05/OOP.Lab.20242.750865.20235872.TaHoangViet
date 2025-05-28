package JavaBasics;
import java.util.Arrays;
import java.util.Scanner;
public class bai6_5 {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước mảng
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // Khởi tạo mảng
        int[] my_array1 = new int[n];

        // Nhập giá trị cho mảng
        System.out.println("Enter " + n + " integer values:");
        for (int i = 0; i < n; i++) {
            my_array1[i] = scanner.nextInt();
        }

        // Hiển thị mảng trước khi sắp xếp
        System.out.println("Original array: " + Arrays.toString(my_array1));

        // Sắp xếp mảng
        Arrays.sort(my_array1);

        // Hiển thị mảng sau khi sắp xếp
        System.out.println("Sorted array: " + Arrays.toString(my_array1));

        // Tính tổng và trung bình
        int sum = 0;
        for (int num : my_array1) {
            sum += num;
        }
        double average = (double) sum / n;

        // Hiển thị kết quả
        System.out.println("Sum of elements: " + sum);
        System.out.println("Average value: " + average);

        scanner.close(); // Đóng Scanner để tránh rò rỉ bộ nhớ
    }
}

