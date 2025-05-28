package hust.soict.hedspi.lab01.Homework;

import java.util.Scanner;

public class EquationSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Chọn một tùy chọn:");
            System.out.println("1. Giải phương trình bậc nhất (ax + b = 0)");
            System.out.println("2. Giải hệ phương trình bậc nhất hai ẩn");
            System.out.println("3. Giải phương trình bậc hai (ax^2 + bx + c = 0)");
            System.out.println("4. Thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    solveLinearEquation(scanner);
                    break;
                case 2:
                    solveLinearSystem(scanner);
                    break;
                case 3:
                    solveQuadraticEquation(scanner);
                    break;
                case 4:
                    System.out.println("Chương trình kết thúc. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn 1, 2 hoặc 3.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Giải phương trình bậc nhất ax + b = 0
    private static void solveLinearEquation(Scanner scanner) {
        System.out.print("Nhập hệ số a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhập hệ số b: ");
        double b = scanner.nextDouble();

        if (a == 0) {
            System.out.println("Đây không phải là một phương trình hợp lệ (a không thể bằng 0). Không có nghiệm.");
        } else {
            double x = -b / a;
            System.out.println("Nghiệm của phương trình là: x = " + x);
        }
    }

    // Giải hệ phương trình bậc nhất hai ẩn
    private static void solveLinearSystem(Scanner scanner) {
        System.out.println("Giải hệ phương trình:");
        System.out.println("a1*x + b1*y = c1");
        System.out.println("a2*x + b2*y = c2");

        System.out.print("Nhập a1: ");
        double a1 = scanner.nextDouble();
        System.out.print("Nhập b1: ");
        double b1 = scanner.nextDouble();
        System.out.print("Nhập c1: ");
        double c1 = scanner.nextDouble();
        System.out.print("Nhập a2: ");
        double a2 = scanner.nextDouble();
        System.out.print("Nhập b2: ");
        double b2 = scanner.nextDouble();
        System.out.print("Nhập c2: ");
        double c2 = scanner.nextDouble();

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            if ((a1 / a2) == (b1 / b2) && (b1 / b2) == (c1 / c2)) {
                System.out.println("Hệ phương trình có vô số nghiệm.");
            } else {
                System.out.println("Hệ phương trình vô nghiệm.");
            }
        } else {
            double x = (c1 * b2 - c2 * b1) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            System.out.println("Nghiệm của hệ phương trình là: x = " + x + ", y = " + y);
        }
    }

    // Giải phương trình bậc hai ax^2 + bx + c = 0
    private static void solveQuadraticEquation(Scanner scanner) {
        System.out.print("Nhập hệ số a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhập hệ số b: ");
        double b = scanner.nextDouble();
        System.out.print("Nhập hệ số c: ");
        double c = scanner.nextDouble();

        if (a == 0) {
            System.out.println("Đây không phải là phương trình bậc hai. Hãy thử lại với a ≠ 0.");
            return;
        }

        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("Phương trình có hai nghiệm phân biệt: x1 = " + x1 + ", x2 = " + x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            System.out.println("Phương trình có nghiệm kép: x = " + x);
        } else {
            System.out.println("Phương trình không có nghiệm thực.");
        }
    }
}
