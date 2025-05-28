package JavaBasics;

import java.util.Scanner;

public class StarTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số dòng của tam giác
        System.out.print("Enter the height of the triangle (n): ");
        int n = scanner.nextInt();

        // Vẽ tam giác
        for (int i = 1; i <= n; i++) {
        	int k=0;
        	while(k<n-i)
        	{
        		System.out.print(" ");
        		k=k+1;
        	}
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close(); // Đóng Scanner để tránh rò rỉ bộ nhớ
    }
}
