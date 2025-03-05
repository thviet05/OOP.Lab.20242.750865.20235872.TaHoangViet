import java.util.Scanner;

public class EquationSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChọn loại phương trình cần giải:");
            System.out.println("1. Phương trình bậc nhất một ẩn");
            System.out.println("2. Hệ phương trình bậc nhất hai ẩn");
            System.out.println("3. Phương trình bậc hai một ẩn");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = scanner.nextInt();

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
                case 0:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        }
    }

    // Giải phương trình bậc nhất ax + b = 0
    public static void solveLinearEquation(Scanner scanner) {
        System.out.print("Nhập a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhập b: ");
        double b = scanner.nextDouble();

        if (a == 0) {
            if (b == 0) {
                System.out.println("Phương trình có vô số nghiệm.");
            } else {
                System.out.println("Phương trình vô nghiệm.");
            }
        } else {
            double x = -b / a;
            System.out.println("Nghiệm của phương trình: x = " + x);
        }
    }

    // Giải hệ phương trình bậc nhất hai ẩn
    public static void solveLinearSystem(Scanner scanner) {
        System.out.print("Nhập a11: ");
        double a11 = scanner.nextDouble();
        System.out.print("Nhập a12: ");
        double a12 = scanner.nextDouble();
        System.out.print("Nhập b1: ");
        double b1 = scanner.nextDouble();
        System.out.print("Nhập a21: ");
        double a21 = scanner.nextDouble();
        System.out.print("Nhập a22: ");
        double a22 = scanner.nextDouble();
        System.out.print("Nhập b2: ");
        double b2 = scanner.nextDouble();

        double D = a11 * a22 - a21 * a12;
        double Dx = b1 * a22 - b2 * a12;
        double Dy = a11 * b2 - a21 * b1;

        if (D == 0) {
            if (Dx == 0 && Dy == 0) {
                System.out.println("Hệ phương trình có vô số nghiệm.");
            } else {
                System.out.println("Hệ phương trình vô nghiệm.");
            }
        } else {
            double x = Dx / D;
            double y = Dy / D;
            System.out.println("Nghiệm của hệ phương trình: x = " + x + ", y = " + y);
        }
    }

    // Giải phương trình bậc hai ax^2 + bx + c = 0
    public static void solveQuadraticEquation(Scanner scanner) {
        System.out.print("Nhập a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhập b: ");
        double b = scanner.nextDouble();
        System.out.print("Nhập c: ");
        double c = scanner.nextDouble();

        if (a == 0) {
            System.out.println("Hệ số a phải khác 0 để là phương trình bậc hai.");
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
            System.out.println("Phương trình vô nghiệm.");
        }
    }
}