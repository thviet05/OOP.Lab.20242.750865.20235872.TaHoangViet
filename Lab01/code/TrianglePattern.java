import java.util.Scanner;

public class TrianglePattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập chiều cao của tam giác
        System.out.print("Enter the height of the triangle (n): ");
        int n = scanner.nextInt();

        // Vẽ tam giác
        for (int i = 1; i <= n; i++) {
            // In khoảng trắng trước dấu *
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // In các dấu *
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println(); // Xuống dòng sau mỗi hàng
        }

        scanner.close();
    }
}