package hust.soict.hedspi.lab01.Homework;

import java.util.Arrays;

public class SortArray {
    public static void main(String[] args) {
        // Khởi tạo mảng số nguyên
        int[] myArray = {1789, 2035, 1899, 1456, 2013};

        // Hiển thị mảng ban đầu
        System.out.println("Mảng ban đầu: " + Arrays.toString(myArray));

        // Sắp xếp mảng theo thứ tự tăng dần
        Arrays.sort(myArray);

        // Hiển thị mảng sau khi sắp xếp
        System.out.println("Mảng sau khi sắp xếp: " + Arrays.toString(myArray));

        // Tính tổng các phần tử trong mảng
        int sum = 0;
        for (int num : myArray) {
            sum += num;
        }

        // Tính giá trị trung bình
        double average = (double) sum / myArray.length;

        // Hiển thị kết quả
        System.out.println("Tổng của các phần tử: " + sum);
        System.out.println("Giá trị trung bình: " + average);
    }
}
