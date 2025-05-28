import javax.swing.JOptionPane;

public class Calculator {
    public static void main(String[] args) {
        // Nhập hai số từ người dùng
        String strNum1 = JOptionPane.showInputDialog("Enter the first number:");
        String strNum2 = JOptionPane.showInputDialog("Enter the second number:");

        // Chuyển đổi từ chuỗi sang số thực (double)
        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        // Tính toán các phép toán
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        String quotient;

        // Kiểm tra mẫu số trước khi chia
        if (num2 != 0) {
            quotient = String.valueOf(num1 / num2);
        } else {
            quotient = "Undefined (cannot divide by zero)";
        }

        // Hiển thị kết quả
        String result = "Sum: " + sum + "\n" +
                        "Difference: " + difference + "\n" +
                        "Product: " + product + "\n" +
                        "Quotient: " + quotient;

        JOptionPane.showMessageDialog(null, result, "Calculation Results", JOptionPane.INFORMATION_MESSAGE);
        
        System.exit(0);
    }
}
