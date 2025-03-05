import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập hai số từ người dùng
        System.out.print("Nhập số thứ nhất: ");
        double a = Double.parseDouble(scanner.nextLine());

        System.out.print("Nhập số thứ hai: ");
        double b = Double.parseDouble(scanner.nextLine());

        // Tính toán và hiển thị kết quả
        System.out.println("Tổng: " + (a + b));
        System.out.println("Hiệu: " + (a - b));
        System.out.println("Tích: " + (a * b));

        // Kiểm tra nếu b = 0 để tránh lỗi chia cho 0
        if (b != 0) {
            System.out.println("Thương: " + (a / b));
        } else {
            System.out.println("Không thể chia cho 0.");
        }

        scanner.close();
    }
}