package hust.soict.hedspi.lab01.Homework;

import java.util.Scanner;
public class DisplayDaysOfMonth {
    // Hàm kiểm tra năm nhuận
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Hàm lấy số ngày của một tháng
    public static int getDaysInMonth(int month, int year) {
        int[] commonYearDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapYearDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        return isLeapYear(year) ? leapYearDays[month - 1] : commonYearDays[month - 1];
    }

    // Hàm chuyển đổi tên tháng sang số
    public static int convertMonthName(String monthName) {
        String[][] validMonthNames = {
                {"January", "Jan.", "Jan", "1"},
                {"February", "Feb.", "Feb", "2"},
                {"March", "Mar.", "Mar", "3"},
                {"April", "Apr.", "Apr", "4"},
                {"May", "May", "May", "5"},
                {"June", "June", "Jun", "6"},
                {"July", "July", "Jul", "7"},
                {"August", "Aug.", "Aug", "8"},
                {"September", "Sept.", "Sep", "9"},
                {"October", "Oct.", "Oct", "10"},
                {"November", "Nov.", "Nov", "11"},
                {"December", "Dec.", "Dec", "12"}
        };

        for (int i = 0; i < validMonthNames.length; i++) {
            for (String name : validMonthNames[i]) {
                if (monthName.equalsIgnoreCase(name)) {
                    return i + 1;
                }
            }
        }
        return -1; // Giá trị không hợp lệ
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year, month;

        // Nhập năm hợp lệ
        while (true) {
            System.out.print("Nhập năm (số nguyên không âm): ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year >= 0) break;
            } else {
                scanner.next(); // Loại bỏ dữ liệu không hợp lệ
            }
            System.out.println("Năm không hợp lệ. Vui lòng nhập lại.");
        }

        scanner.nextLine(); // Xử lý dòng mới còn sót lại

        // Nhập tháng hợp lệ
        while (true) {
            System.out.print("Nhập tháng (tên đầy đủ, viết tắt hoặc số): ");
            String monthName = scanner.nextLine().trim();
            month = convertMonthName(monthName);
            if (month != -1) break;
            System.out.println("Tháng không hợp lệ. Vui lòng nhập lại.");
        }

        // Hiển thị kết quả
        int days = getDaysInMonth(month, year);
        System.out.println("Số ngày trong tháng " + month + " của năm " + year + " là: " + days);

        scanner.close();
    }
}
