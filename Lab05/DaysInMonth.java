package JavaBasics;
import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month = 0;
        int year = 0;
        while (true) {
            System.out.print("Enter month (1-12): ");
            if (scanner.hasNextInt()) {
                month = scanner.nextInt();
                if (month >= 1 && month <= 12) {
                    break;
                } else {
                    System.out.println("Invalid month. Month must be between 1 and 12.");
                    System.exit(0); 
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                System.exit(0); 
            }
        }
        while (true) {
            System.out.print("Enter year: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year >= 0) {
                    break;
                } else {
                    System.out.println("Invalid year. Year must be non-negative.");
                    System.exit(0); 
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                System.exit(0);            }
        }
        int daysInMonth;
        switch (month) {
            case 4: case 6: case 9: case 11:
                daysInMonth = 30;
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
                break;
            default:
                daysInMonth = 31;
                break;
        }
        System.out.println("Number of days in the selected month: " + daysInMonth);

        scanner.close();
    }
}